package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.entity.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class RecipeDaoImpl extends AbstractDao<Recipe> implements RecipeDao {

    private Session session;
    private Transaction transaction;
    private DishDao dishDao;

    public RecipeDaoImpl(Session session, DishDao dishDao) {
        super(session, Recipe.class);
        this.session = session;
        this.transaction = session.getTransaction();
        this.dishDao = dishDao;
    }

    public void saveOrUpdate(Recipe recipe) {
        Dish parentDish = recipe.getDish();
        System.out.println(parentDish.getTitle());
        Dish dish = dishDao.findByTitle(parentDish.getTitle());
        if (dish == null) {
            dishDao.saveOrUpdate(parentDish);
            dish = dishDao.findByTitle(parentDish.getTitle());
        }
        recipe.setDish(dish);
        super.saveOrUpdate(recipe);
    }

    public void removeById(Integer id) {
        try {
            transaction.begin();
            Recipe object = findById(id);
            Dish parent = object.getDish();
            if(parent.getRecipeList().size() == 1) {
                session.delete(parent);
            } else {
                session.delete(object);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

}
