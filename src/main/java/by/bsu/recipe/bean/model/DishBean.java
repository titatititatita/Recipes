package by.bsu.recipe.bean.model;

import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.factory.DaoFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
public class DishBean {

    private DishDao dishDao = DaoFactory.createDishDao();
    private List<Dish> allDishes;
    private Dish dish;

    @ManagedProperty(value = "#{param.dishId}")
    private Integer dishId;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public List<Dish> getAllDishes() {
        return allDishes;
    }

    public void setAllDishes(List<Dish> allDishes) {
        this.allDishes = allDishes;
    }

    @PostConstruct
    private void init() {
        if (dishId != null) {
            dish = dishDao.findById(dishId);
        }
        allDishes = dishDao.findAll();
    }
}
