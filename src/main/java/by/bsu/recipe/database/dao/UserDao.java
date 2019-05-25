package by.bsu.recipe.database.dao;

import by.bsu.recipe.entity.User;

public interface UserDao extends Dao<User> {
    User findByLoginAndPassword(String login, String password);

}
