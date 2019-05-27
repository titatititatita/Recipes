package by.bsu.recipe.factory;

import by.bsu.recipe.database.dao.CommentDao;
import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.database.dao.impl.CommentDaoImpl;
import by.bsu.recipe.database.dao.impl.DishDaoImpl;
import by.bsu.recipe.database.dao.impl.RecipeDaoImpl;
import by.bsu.recipe.database.dao.impl.UserDaoImpl;
import by.bsu.recipe.database.util.HibernateSessionFactoryUtil;
import org.hibernate.Session;

public class DaoFactory {

    public static UserDao createUserDao() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return new UserDaoImpl(session);
    }

    public static DishDao createDishDao() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return new DishDaoImpl(session);
    }

    public static RecipeDao createRecipeDao() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        DishDao dishDao = DaoFactory.createDishDao();
        return new RecipeDaoImpl(session, dishDao);
    }

    public static CommentDao createCommenDao() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return new CommentDaoImpl(session);
    }
}
