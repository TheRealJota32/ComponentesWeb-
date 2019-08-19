package com.edu.controller;

import edu.seguridad.model.Usuario;
import edu.seguridad.service.SeguridadUsuario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
    private Usuario newUser;

    //Variables for account verification ans password
    private String codigoAcc;
    private String codigoPass;
    private String newPass;
    
    public UsuarioController() {
    }
    
    public String login() {
        currentUser = su.loginClient(this.user, this.password);
        System.out.println(currentUser.getRol().getNombre());
        return "galery";
    }
    
    public String signup() {
        su.signUp(this.nombre, this.apellido, this.correo, this.username, this.pass);
        newUser = su.loginClient(this.username, this.pass);
        su.addAppForClient(newUser.getIdUsuario(), 1);
        return "galery";
        
    }
    
    public void verifyAccount() {
        su.verifyAccount(this.correo, this.codigoAcc);
    }
    
    public void verifyPassword() {
        su.verifyPass(this.correo, this.codigoPass, this.newPass);
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
    
    public Usuario getNewUser() {
        return newUser;
    }
    
    public void setNewUser(Usuario newUser) {
        this.newUser = newUser;
    }
    
    public void redirect(String url) {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance().getExternalContext().getRequest();
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            request.getContextPath()
                            + "/faces/views/" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
