package by.bsu.recipe.bean.backed;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class ErrorBean {

    public void addError(String error, String details) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                error, details);
        facesContext.addMessage(null, facesMessage);
    }

}
