package by.bsu.recipe.bean.model;

import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.entity.Recipe;
import by.bsu.recipe.exception.ServiceException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PreparedRecipeBean {

    private Recipe recipe;
    private String dishTitle;

    @ManagedProperty(value = "#{recipeBean}")
    private RecipeBean recipeBean;

    @PostConstruct
    private void init() {
        recipe = new Recipe();
        recipe.setDish(new Dish());
    }

    public Recipe getRecipe() {
        return recipe;
    }


    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getDishTitle() {
        return dishTitle;
    }

    public void setDishTitle(String dishTitle) throws ServiceException {
        this.dishTitle = dishTitle;
        this.recipe.getDish().setTitle(dishTitle);
    }

    public RecipeBean getRecipeBean() {
        return recipeBean;
    }

    public void setRecipeBean(RecipeBean recipeBean) {
        this.recipeBean = recipeBean;
    }
}
