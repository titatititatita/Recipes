package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.backed.ErrorBean;
import by.bsu.recipe.bean.model.UserBean;
import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.entity.User;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.directory.SearchResult;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    @ManagedProperty(value = "#{errorBean}")
    private ErrorBean errorBean;

    private UserDao userDao = DaoFactory.createUserDao();

    public ErrorBean getErrorBean() {
        return errorBean;
    }

    public void setErrorBean(ErrorBean errorBean) {
        this.errorBean = errorBean;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public String login() {
        User tempUser = userBean.getUser();
        User user = userDao.findByLoginAndPassword(tempUser.getLogin(), tempUser.getPassword());
        if (user == null) {
            userBean.setUser(new User());
            errorBean.addError("Invalid login or password", "User is not found");
            return "/?faces-redirect=true";
        } else {
            userBean.setUser(user);
            return "index/?faces-redirect=true";
        }
    }
}
