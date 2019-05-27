package by.bsu.recipe.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "recipe")
public class Recipe extends AbstractEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    @Type(type = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "complexity", nullable = false)
    private int complexity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Comment> commentList;

    public Recipe() {
    }


    public Recipe(String title, String description, User user, int complexity, Dish dish, List<Comment> commentList) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.complexity = complexity;
        this.dish = dish;
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", complexity=" + complexity +
                ", dish=" + dish +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Recipe recipe = (Recipe) o;
        return complexity == recipe.complexity &&
                Objects.equals(title, recipe.title) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(user, recipe.user) &&
                Objects.equals(dish, recipe.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, user, complexity, dish);
    }
}
