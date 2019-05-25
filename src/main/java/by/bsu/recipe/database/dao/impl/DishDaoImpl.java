package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.entity.Recipe;
import by.bsu.recipe.entity.User;
import by.bsu.recipe.factory.DaoFactory;
import org.hibernate.Session;

import java.util.List;

public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {

    public DishDaoImpl(Session session) {
        super(session, Dish.class);
    }

}
