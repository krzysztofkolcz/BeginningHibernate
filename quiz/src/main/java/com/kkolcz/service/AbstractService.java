package com.kkolcz.service;

import com.kkolcz.command.AbstractCommand;
import com.kkolcz.dao.Dao;
import com.kkolcz.model.AbstractModel;
import com.kkolcz.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractService<T extends AbstractModel<C>,D extends Dao<T>,C extends AbstractCommand> implements  Service<T>{

    protected D dao;

    AbstractService(D dao){
        this.dao = dao;
    }

    @Override
    public T findById(int id) {
        return dao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public void saveOrUpdate(T object) {
        if(object.isNew()) {
            dao.save(object);
        }else{
            dao.update(object);
        }
    }

    public void update(C command){
        T element =  (T)dao.findById(command.getId());
        element.fillDataFromCommandObject(command);
        dao.merge(element);
        dao.save(element);
    }

    @Override
    public void delete(T object) {
        dao.delete(object);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}
