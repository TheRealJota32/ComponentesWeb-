package com.edu.controller;

import edu.seguridad.model.Rol;
import edu.seguridad.service.SeguridadRol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "rolController")
@SessionScoped
public class RolController implements Serializable {

    private static final long serialVersionUID = 1L;

    SeguridadRol sr = new SeguridadRol();

    //Variables for RolModulo
    private int idRol;
    private String nombreMod;

    //Variable for oldModulo
    private int idModulo;

    //Variable for updateRol
    private String newNameRol;

    //Variable for rols
    private List<Rol> roles = new ArrayList<>();

    public void addRolModulo() {
        sr.addRolForModulo(this.idRol, this.nombreMod);
    }

    public void oldModulo() {
        sr.addOldRol(this.idRol, this.idModulo);
    }

    public void updateRol() {
        sr.updateRol(idRol, this.newNameRol);
    }

    public List<Rol> roles() {
        roles = sr.getRol();
        return roles;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreMod() {
        return nombreMod;
    }

    public void setNombreMod(String nombreMod) {
        this.nombreMod = nombreMod;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNewNameRol() {
        return newNameRol;
    }

    public void setNewNameRol(String newNameRol) {
        this.newNameRol = newNameRol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

}
