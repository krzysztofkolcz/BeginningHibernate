package com.kkolcz.service;
 
import java.util.List;
import com.kkolcz.dao.Dao;
import com.kkolcz.dao.AbstractDao;
import com.kkolcz.service.AbstractService;
import com.kkolcz.command.AbstractCommand;
import com.kkolcz.model.AbstractModel;
 
public abstract class AbstractService<T extends AbstractModel,C extends AbstractCommand,DAO extends AbstractDao> {
     
    private DAO dao;

    public T findById(int id){
        return (T)dao.findById(id);
    }

    public T findByName(String name){
        return (T)dao.findByName(name);
    }

    public List<T> findAll(){
        return (List<T>)dao.findAll();
    }

    public boolean nameExist(String name){
        T element = (T)dao.findByName(name);
        if (element != null) {
            return true;
        }
        return false;
    }

    public boolean nameExistExceptId(String name,int id){
        List<T> elements = dao.findByNameExceptId(name,id);
        if(elements != null && elements.size()>0){
          return true;
        }
        return false;
    }

    public void add(C command,T element) {
        element.fillDataFromCommandObject(command);
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





 
