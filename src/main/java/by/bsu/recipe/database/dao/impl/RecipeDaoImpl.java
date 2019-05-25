package by.bsu.recipe.database.dao.impl;

import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.entity.Recipe;
import org.hibernate.Session;

import java.util.List;

public class RecipeDaoImpl extends AbstractDao<Recipe> implements RecipeDao {

    public RecipeDaoImpl(Session session) {
        super(session, Recipe.class);
    }

}
