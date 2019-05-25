package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.model.DishBean;
import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ReferencedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DishController {

    @ManagedProperty(value = "#{dishBean}")
    private DishBean dishBean;

    private DishDao dishDao = DaoFactory.createDishDao();

    public DishBean getDishBean() {
        return dishBean;
    }

    public void setDishBean(DishBean dishBean) {
        this.dishBean = dishBean;
    }

    public String selectDish(Integer id) {
        Dish dish = dishDao.findById(id);
        if(dish != null) {
            dishBean.setDish(dish);
            return "/dish_preview";
        }
        return "/";
    }

}
