package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class NewJSFManagedBeanApplication {

    String appVariable = "Application Controller Data";

    public NewJSFManagedBeanApplication() {
    }

    public String getAppVariable() {
        return appVariable;
    }

    public void setAppVariable(String appVariable) {
        this.appVariable = appVariable;
    }

}
