package com.edu.controller;

import edu.seguridad.service.SeguridadAppRol;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "appRolControllerController")
@SessionScoped
public class AppRolController implements Serializable {

    private static final long serialVersionUID = 1L;

    SeguridadAppRol ar = new SeguridadAppRol();

    //Variables for appRol
    private String app;
    private String rol;

    public void appRol() {
        ar.addAppRol(this.app, this.rol);
    }

    public void addRol() {
        ar.addRol(this.rol);
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
