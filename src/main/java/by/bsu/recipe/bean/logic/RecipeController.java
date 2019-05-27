package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.model.PreparedRecipeBean;
import by.bsu.recipe.bean.model.RecipeBean;
import by.bsu.recipe.bean.model.UserBean;
import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class RecipeController implements Serializable {

    private static final String INDEX_REDIRECT = "/index.xhtml?faces-redirect=true";

    private static final String RECIPE_REDIRECT = "/show-recipe.xhtml?faces-redirect=true&recipeId=";

    @ManagedProperty(value = "#{recipeBean}")
    private RecipeBean recipeBean;

    @ManagedProperty(value = "#{preparedRecipeBean}")
    private PreparedRecipeBean preparedRecipeBean;

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    @ManagedProperty(value = "#{param.recipeId}")
    private Integer recipeId;

    private RecipeDao recipeDao = DaoFactory.createRecipeDao();

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public PreparedRecipeBean getPreparedRecipeBean() {
        return preparedRecipeBean;
    }

    public void setPreparedRecipeBean(PreparedRecipeBean preparedRecipeBean) {
        this.preparedRecipeBean = preparedRecipeBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }


    public RecipeBean getRecipeBean() {
        return recipeBean;
    }

    public void setRecipeBean(RecipeBean recipeBean) {
        this.recipeBean = recipeBean;
    }

    public String createOrUpdate() {
        preparedRecipeBean.getRecipe().setUser(userBean.getUser());
        recipeDao.saveOrUpdate(preparedRecipeBean.getRecipe());
        return INDEX_REDIRECT;
    }

    public String remove() {
        recipeDao.removeById(recipeId);
        return INDEX_REDIRECT;
    }

    public String newRecipe() {
        return "/new-recipe.xhtml?faces-redirect=true";
    }

}
