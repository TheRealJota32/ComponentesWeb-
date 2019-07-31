package com.edu.controller;

import edu.seguridad.model.Funcionalidad;
import edu.seguridad.service.SeguridadFuncionalidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "FuncionalidadController")
@SessionScoped
public class FuncionalidadController implements Serializable {

    private static final long serialVersionUID = 1L;

    SeguridadFuncionalidad sf = new SeguridadFuncionalidad();

    //Variables for UpdateFunction
    private int idFunc;
    private String name;

    //Variable for functions
    private List<Funcionalidad> funcionalidades = new ArrayList<>();

    public void updateFunc() {
        sf.updateFunction(this.idFunc, this.name);
    }

    public List<Funcionalidad> getFunc() {
        funcionalidades = null;
        funcionalidades = sf.getFunctions(this.idFunc);
        return funcionalidades;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Funcionalidad> getFuncionalidades() {
        return funcionalidades;
    }

    public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
        this.funcionalidades = funcionalidades;
    }

}
