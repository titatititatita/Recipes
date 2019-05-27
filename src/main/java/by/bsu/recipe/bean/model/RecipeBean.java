package by.bsu.recipe.bean.model;


import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.entity.Recipe;
import by.bsu.recipe.factory.DaoFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class RecipeBean implements Serializable {

    @ManagedProperty(value = "#{param.recipeId}")
    private Integer recipeId;
    private Recipe recipe;

    private RecipeDao recipeDao = DaoFactory.createRecipeDao();

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @PostConstruct
    private void init() {
        if (recipeId != null) {
            recipe = recipeDao.findById(recipeId);
        }
    }


}
