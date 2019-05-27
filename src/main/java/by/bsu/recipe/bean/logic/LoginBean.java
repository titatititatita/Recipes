package by.bsu.recipe.bean.logic;

import by.bsu.recipe.bean.backed.ErrorBean;
import by.bsu.recipe.bean.model.UserBean;
import by.bsu.recipe.database.dao.UserDao;
import by.bsu.recipe.entity.User;
import by.bsu.recipe.factory.DaoFactory;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private static final String INDEX_PAGE_REDIRECT = "/index.html/?faces-redirect=true";
    private static final String LOGIN_PAGE_REDIRECT = "/login.xhtml/?faces-redirect=true";

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
            return LOGIN_PAGE_REDIRECT;
        } else {
            userBean.setUser(user);
            userBean.setAuthorized(true);
            return INDEX_PAGE_REDIRECT;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return INDEX_PAGE_REDIRECT;
    }
}
