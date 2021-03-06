package com.kkolcz.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class AbstractDao<T> implements Dao<T>{

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    protected SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T findById(Integer id) {
        return (T) getSession().get(persistentClass, id);
    }

    @Override
    public List findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<T> list = (List<T>) criteria.list();
        return list;
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void save(T entity) {
        getSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }


    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }

    @Override
    public void delete(int id) {
        T object = findById(id);
        if(object != null) {
            getSession().delete(object);
        }
    }

    @Override
    public void delete(T object) {
        if(object != null) {
            getSession().delete(object);
        }
    }

    @Override
    public void merge(T object) {
        getSession().merge(object);
    }
}
