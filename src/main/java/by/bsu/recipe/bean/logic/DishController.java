package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.model.DishBean;
import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class DishController implements Serializable {

    private static final String INDEX_REDIRECT = "/index.xhtml?faces-redirect=true";
    private static final String SHOW_DISH_REDIRECT = "/show.xhtml?faces-redirect=true&dishId=";

    @ManagedProperty(value = "#{dishBean}")
    private DishBean dishBean;

    @ManagedProperty(value = "#{param.dishId}")
    private Integer dishId;

    private DishDao dishDao = DaoFactory.createDishDao();

    public DishBean getDishBean() {
        return dishBean;
    }

    public void setDishBean(DishBean dishBean) {
        this.dishBean = dishBean;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String openDish() {
        if (dishId != null) {
            return SHOW_DISH_REDIRECT + dishId;
        }
        return INDEX_REDIRECT;
    }

}
