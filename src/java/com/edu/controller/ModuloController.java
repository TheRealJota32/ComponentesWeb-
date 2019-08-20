package com.edu.controller;

import edu.seguridad.model.Modulo;
import edu.seguridad.service.SeguridadModulo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "moduloController")
@SessionScoped
public class ModuloController implements Serializable {

    private static final long serialVersionUID = 1L;

    SeguridadModulo sm = new SeguridadModulo();

    //Variables for update
    private int idModulo;
    private String newName;

    //Variables for new Function
    private int idModFunc;
    private String funcName;

    //Variable for modulos
    private List<Modulo> modulos = new ArrayList<>();

    public void updateModulo() {
        sm.updateModulo(this.idModulo, this.newName);
    }

    public void addFunction() {
        sm.addFunction(this.idModFunc, this.funcName);
    }

    public List<Modulo> modulos() {
        modulos = sm.getModulos();
        return modulos;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public int getIdModFunc() {
        return idModFunc;
    }

    public void setIdModFunc(int idModFunc) {
        this.idModFunc = idModFunc;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void setModulos(List<Modulo> modulos) {
        this.modulos = modulos;
    }

}
