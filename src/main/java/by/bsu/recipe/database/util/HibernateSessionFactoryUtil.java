package by.bsu.recipe.database.util;

import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.entity.Recipe;
import by.bsu.recipe.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateSessionFactoryUtil {
    private static volatile SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        SessionFactory localInstance = sessionFactory;
        if (localInstance == null) {
            synchronized (SessionFactory.class) {
                localInstance = sessionFactory;
                if(localInstance == null) {
                    sessionFactory = createFactory();
                }
            }
        }
        return sessionFactory;
    }

    private static SessionFactory createFactory() {
        Configuration configuration = createConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return configuration.buildSessionFactory(builder.build());
    }

    private static Configuration createConfiguration() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Recipe.class);
        configuration.addAnnotatedClass(Dish.class);
        return configuration;
    }
}