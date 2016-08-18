package com.kkolcz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.List;

import java.lang.reflect.ParameterizedType;

import com.kkolcz.dao.Dao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.service.AbstractService;
import com.kkolcz.command.AbstractCommand;
import com.kkolcz.model.AbstractModel;
 
/* public abstract class AbstractService<T extends AbstractModel,C extends AbstractCommand,DAO extends AbstractDao> { */
@Transactional
public abstract class AbstractService<T extends AbstractModel,C extends AbstractCommand,D extends Dao<T>> {
     
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AbstractService.class);
    protected D dao;

    AbstractService(D dao){
       this.dao = dao;
    }

    public T findById(int id){
        return (T)dao.findById(Integer.valueOf(id));
    }

    public T findByName(String name){
        /* return (T)dao.findByName(name); */
        return (T)dao.findByNaturalKey(name);
    }

    public List<T> findAll(){
        return (List<T>)dao.findAll();
    }

    public boolean nameExist(String name){
        /* T element = (T)dao.findByName(name); */
        T element = (T)dao.findByNaturalKey(name);
        if (element != null) {
            return true;
        }
        return false;
    }

    public boolean nameExistExceptId(String name,int id){
        /* List<T> elements = dao.findByNameExceptId(name,id); */
        T element = dao.findByNaturalKeyExceptId(name,id);
        if(element != null ){
          return true;
        }
        return false;
    }

    public void add(C command,T element) {
        logger.info("logger abstract service add");
        System.out.println("abstract service add");
        element.fillDataFromCommandObject(command);
        System.out.println(element);
        System.out.println(dao.getClass().getSimpleName());
        dao.save(element);
    }

    public void update(C command){
        T element =  (T)dao.findById(command.getId());
        if(unique(command)){
            element.fillDataFromCommandObject(command); 
            dao.save(element);
        }
    }

    public abstract boolean unique(C command);
    /* np.: !nameExistExceptId(command.getName(),command.getId()) */
        

}





 
