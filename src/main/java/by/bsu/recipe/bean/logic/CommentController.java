package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.model.PreparedCommentBean;
import by.bsu.recipe.bean.model.UserBean;
import by.bsu.recipe.database.dao.CommentDao;
import by.bsu.recipe.database.dao.RecipeDao;
import by.bsu.recipe.entity.Comment;
import by.bsu.recipe.entity.Recipe;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class CommentController {

    private static final String RECIPE_REDIRECT = "/show-recipe.xhtml?faces-redirect=true&recipeId=";
    @ManagedProperty(value = "#{preparedCommentBean}")
    private PreparedCommentBean preparedCommentBean;
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;
    @ManagedProperty(value = "#{param.recipeId}")
    private Integer recipeId;
    private CommentDao commentDao = DaoFactory.createCommenDao();
    private RecipeDao recipeDao = DaoFactory.createRecipeDao();

    public String create() {
        System.out.println(recipeId);
        Recipe recipe = recipeDao.findById(recipeId);
        Comment comment = preparedCommentBean.getComment();
        comment.setUser(userBean.getUser());
        comment.setRecipe(recipe);
        System.out.println(comment);
        commentDao.saveOrUpdate(comment);
        return RECIPE_REDIRECT + recipeId;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public PreparedCommentBean getPreparedCommentBean() {
        return preparedCommentBean;
    }

    public void setPreparedCommentBean(PreparedCommentBean preparedCommentBean) {
        this.preparedCommentBean = preparedCommentBean;
    }
}
