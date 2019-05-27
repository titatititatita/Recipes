package by.bsu.recipe.database.dao;

import by.bsu.recipe.entity.Dish;

public interface DishDao extends Dao<Dish> {

    Dish findByTitle(String title);

}
