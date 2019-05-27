package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.CommentDao;
import by.bsu.recipe.entity.Comment;
import org.hibernate.Session;

public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {
    public CommentDaoImpl(Session session) {
        super(session, Comment.class);
    }
}
