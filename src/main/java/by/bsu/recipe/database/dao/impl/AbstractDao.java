package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.Dao;
import by.bsu.recipe.entity.AbstractEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> implements Dao<T> {

    private Transaction transaction;
    private Session session;
    private Class<T> classType;

    public AbstractDao(Session session, Class<T> classType) {
        this.session = session;
        this.transaction = session.getTransaction();
        this.classType = classType;
    }

    public void saveOrUpdate(T object) {
        try {
            transaction.begin();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public T findById(Integer id) {
        return (T) session.get(classType, id);
    }

    public void removeById(Integer id) {
        try {
            transaction.begin();
            T object = findById(id);
            session.delete(object);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    public List<T> findAll() {
        Criteria criteria = session.createCriteria(classType);
        return (List<T>) criteria.list();
    }

    public void close() {
        session.close();
    }

}


