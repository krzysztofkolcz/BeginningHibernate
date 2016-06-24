package com.kkolcz.dao;
 
import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
 
import java.lang.reflect.ParameterizedType;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.kkolcz.model.Model;
 
import org.springframework.beans.factory.annotation.Autowired;
public abstract class AbstractDao<PK extends Serializable, T extends Model> implements Dao<T>{
     
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    protected SessionFactory sessionFactory;
     
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

 
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    public T findById(PK id) {
        T element = getByKey(id);
        return element;
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void save(T entity) {
        getSession().save(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(String orderBy) {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc(orderBy));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<T> list = (List<T>) criteria.list();
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<T> list = (List<T>) criteria.list();
        return list;
    }

    public void removeAll(){
        /* TODO */
    }

    public T findByNaturalKey(String naturalKeyValue) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(T.getNaturalKeyName(), naturalKeyValue));
        T element = (T) crit.uniqueResult();
        return element;
    }
    public List<T> findByNaturalKeyExceptId(String naturalKeyValue,int id){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(T.getNaturalKeyName(), naturalKeyValue));
        crit.add(Restrictions.ne("id", id));
        List<T> elements = (List<T>)crit.list();
        return elements;
    }

    /* TODO - change to findByNaturalKey */
    public T findByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        T element = (T) crit.uniqueResult();
        return element;
    }
    public List<T> findByNameExceptId(String name,int id){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        crit.add(Restrictions.ne("id", id));
        List<T> elements = (List<T>)crit.list();
        return elements;
    }

    public T findByFieldUnique(String fieldName, String fieldValue) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(fieldName, fieldValue));
        T element = (T) crit.uniqueResult();
        return element;
    }

    public List<T> findByField(String fieldName, String fieldValue) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(fieldName, fieldValue));
        List<T> elements = (List<T>)crit.list();
        return elements;
    }

    public List<T> findByFieldExceptId(String fieldName, String fieldValue,int id){
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq(fieldName, fieldValue));
        crit.add(Restrictions.ne("id", id));
        List<T> elements = (List<T>)crit.list();
        return elements;
    }

    public T findByFields(HashMap<String,String> map) {
        Criteria crit = createEntityCriteria();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            crit.add(Restrictions.eq(key, value));
        }

        T element = (T) crit.uniqueResult();
        return element;
    }


    public List<T> findByFieldsExceptId(HashMap<String,String> map,int id){
        Criteria crit = createEntityCriteria();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            crit.add(Restrictions.eq(key, value));
        }

        crit.add(Restrictions.ne("id", id));
        List<T> elements = (List<T>)crit.list();
        return elements;
    }

 
}

/*
findById(id);
findByName(name);
findByNameExceptId(name,id);
save(element);
*/
