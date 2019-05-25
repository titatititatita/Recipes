package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private Session session;

    public UserDaoImpl(Session session) {
        super(session, User.class);
        this.session = session;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        Criteria criteria = session.createCriteria(User.class);
        return (User) criteria.add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }


}
