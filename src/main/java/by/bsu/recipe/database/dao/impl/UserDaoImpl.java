package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.entity.User;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private Session session;

    public UserDaoImpl(Session session) {
        super(session, User.class);
        this.session = session;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        System.out.println("Find by login and password invoke");
        Criteria criteria = session.createCriteria(User.class);
        String hashedPassword = DigestUtils.sha256Hex(password);
        System.out.println("Password:"+password);
        System.out.println("Hashed password:"+hashedPassword);
        return (User) criteria.add(Restrictions.eq("login", login))
                .add(Restrictions.eq("password", hashedPassword))
                .uniqueResult();
    }


}
