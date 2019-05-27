package by.bsu.recipe.bean.model;

import by.bsu.recipe.entity.Comment;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class PreparedCommentBean {

    private Comment comment;

    @PostConstruct
    private void init() {
        comment = new Comment();
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
