package by.bsu.recipe.database.dao;

import by.bsu.recipe.entity.AbstractEntity;

import java.io.Closeable;
import java.io.Serializable;
import java.util.List;

public interface Dao<T extends AbstractEntity> extends Serializable, Closeable {

    List<T> findAll();

    void saveOrUpdate(T entity);

    void removeById(Integer id);

    T findById(Integer id);

}
