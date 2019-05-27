package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.entity.Dish;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {

    private Session session;
    private Transaction transaction;

    public DishDaoImpl(Session session) {
        super(session, Dish.class);
        this.session = session;
        this.transaction = session.getTransaction();
    }

    @Override
    public Dish findByTitle(String title) {
        Criteria titleCriteria = session.createCriteria(Dish.class);
        titleCriteria.add(Restrictions.eq("title", title));
        return (Dish) titleCriteria.uniqueResult();
    }
}
