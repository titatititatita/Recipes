package by.bsu.recipe.bean.model;

import by.bsu.recipe.entity.Role;
import by.bsu.recipe.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private boolean admin;
    private boolean authorized;
    private User user;

    @PostConstruct
    private void init() {
        user = new User();
        authorized = false;
        admin = false;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.admin = Role.ADMIN.equals(user.getRole());
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }
}
