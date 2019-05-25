package by.bsu.recipe.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish extends AbstractEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipeList;

    public Dish() {
    }

    public Dish(String title, List<Recipe> recipeList) {
        this.title = title;
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "title='" + title + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dish dish = (Dish) o;
        return Objects.equals(title, dish.title) &&
                Objects.equals(recipeList, dish.recipeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, recipeList);
    }
}
