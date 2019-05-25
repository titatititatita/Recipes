package by.bsu.recipe.bean.model;

import by.bsu.recipe.database.dao.DishDao;
import by.bsu.recipe.entity.Dish;
import by.bsu.recipe.factory.DaoFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean
@SessionScoped
public class DishBean {

    private DishDao dishDao = DaoFactory.createDishDao();
    private List<Dish> allDishes;
    private Dish dish;

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
    private void initDishes() {
        allDishes = dishDao.findAll();
    }
}
