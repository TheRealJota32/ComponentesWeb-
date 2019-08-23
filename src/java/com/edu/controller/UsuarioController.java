package com.edu.controller;

import edu.seguridad.model.Usuario;
import edu.seguridad.service.SeguridadUsuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author josepabloramirez
 */
@ManagedBean(name = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private static final long serialVersionUID = 1L;

    SeguridadUsuario su = new SeguridadUsuario();

    //Variables for login
    private String user;
    private String password;
    private Usuario currentUser;

    //Variables for signup
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String pass;

    //Variables for account verification ans password
    private String codigoAcc;
    private String codigoPass;
    private String newPass;

    //Variable for user list
    List<Usuario> usuarios = new ArrayList<>();
    private Usuario selectedUser;
    private String buttonValue;

    public UsuarioController() {
    }

    public String login() {
        currentUser = su.loginClient(this.user, this.password);
        if (currentUser != null) {
            System.out.println(currentUser.getRol().getNombre());
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .addResponseCookie("RolName", currentUser.getRol().getNombre(), null);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
            return null;
        }
        return "galery?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "index?faces-redirect=true";
    }

    public String signup() {
        currentUser = su.signUp(this.nombre, this.apellido, this.correo, this.username, this.pass);
        currentUser = su.loginClient(this.username, this.password);
        su.addAppForClient(currentUser.getIdUsuario(), 1);

        if (currentUser != null) {
            System.out.println(currentUser.getRol().getNombre());
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .addResponseCookie("RolName", currentUser.getRol().getNombre(), null);
        } else {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Invalid User"));
            return null;
        }
        return "galery?faces-redirect=true";
    }

    public void verifyAccount() {
        su.verifyAccount(this.correo, this.codigoAcc);
    }

    public String verifyPassword() {
        su.verifyPass(this.correo, this.codigoPass, this.newPass);
        return "index?faces-redirect=true";
    }

    @PostConstruct
    public void users() {
        this.usuarios = su.users();
    }

    public void updateRol() {
        su.updateRol(this.selectedUser.getIdUsuario(), this.buttonValue);
        FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modified User"));
    }

    public void selectedUserRol(Usuario u) {
        if (this.selectedUser != null) {
            this.selectedUser = u;
            this.buttonValue = this.selectedUser.getRol().getNombre();
        }
    }

    public void sendEmail() {
        su.sendEmail(this.correo);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCodigoAcc() {
        return codigoAcc;
    }

    public void setCodigoAcc(String codigoAcc) {
        this.codigoAcc = codigoAcc;
    }

    public String getCodigoPass() {
        return codigoPass;
    }

    public void setCodigoPass(String codigoPass) {
        this.codigoPass = codigoPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Usuario selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }

}
