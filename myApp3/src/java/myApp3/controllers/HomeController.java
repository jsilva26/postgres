/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Size;
import myApp3.dao.UsuarioDAO;
import myApp3.models.Usuario;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class HomeController implements Serializable {
    
    @EJB
    private UsuarioDAO usuarioEJB;
    
    @Size(min=1, message="Debe ingresar un usuario")
    private String usuario;
    
    @Size(min=1, message="Debe ingresar el password")
    private String password;
    
    @Size(min=1, message="Debe ingresar el password actual")
    private String passAct;
    
    @Size(min=1, message="Debe ingresar el password nuevo")
    private String passNew;
    
    @Size(min=1, message="Debe confirmar el password nuevo")
    private String passRepetido;
    
    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassAct() {
        return passAct;
    }

    public void setPassAct(String passAct) {
        this.passAct = passAct;
    }
    
    public String getPassNew() {
        return passNew;
    }

    public void setPassNew(String passNew) {
        this.passNew = passNew;
    }
    
    public String getPassRepetido(){
        return passRepetido;
    }
    
    public void setPassRepetido(String passRepetido){
        this.passRepetido = passRepetido;
    }
    
    public String index(){
        return "/index";
    }
    
    public String login(){
        return "/home/login";
    }
    
    public String acercaDe(){
        return "/home/acerca_de";
    }
    
    public String ingresar(){
        Usuario login = usuarioEJB.getLogin(usuario, password);
        //si el login no es correcto
        if(login == null){
            SessionUtil.addErrorMessage("Usuario o password incorrectos");
            return null;
        }
        
        //Cierra la sesion y la crea con el nuevo usuario logueado
        SessionUtil.closeSession();
        SessionUtil.addSession(login.getIdUsuario(), login.getNombre(), login.getTipoUsuarioId().getIdTipoUsuario(), login.getTipoUsuarioId().getNombre());
        return "/index";
    }
    
    public String logout(){
        SessionUtil.closeSession();
        return "/index";
    }
    
    public String cambio_clave(){
        return "/home/cambio_clave";
    }
    
    public String cambiar_pass(){
        //si la clave nueva y repetida no coinden error
        if(!(passNew.equals(passRepetido))){
            SessionUtil.addErrorMessage("El nuevo password y la confirmación no coinciden, intente de nuevo");
            return null;
        }
        
        //Recupera el usuario actual para conocer su clave
        Usuario current = usuarioEJB.find(SessionUtil.getUserLog());
        String passUser = current.getPassword();
        
        //La clave del usuario debe ser la actual
        if(!(passAct.equals(passUser))){
            SessionUtil.addErrorMessage("Password actual incorrecto, intente de nuevo");
            return null;
        }
        
        current.setPassword(passNew);
        usuarioEJB.edit(current);
        return "/index";
    }
    
    public Boolean logueado() {
        Integer userLog = SessionUtil.getUserLog();
        return !(userLog == null);
    } // Fin public Boolean logueado
    
    public String infoDelPie(){ 
        String nombre = SessionUtil.getUserNombreLog();
        String tipo = SessionUtil.getUserTipoLog();
        String usuario ="";
            if(nombre!=null && tipo!=null){
            usuario=nombre+"( "+tipo+" )";
        }
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy h:mm a");
        Date d = new Date();
        String fechaStr= sdf.format(d);
        return usuario + " - " + fechaStr + " - Desarrollado en Java EE 7";
    }//Está lógica se escribe aquí porque no es logica que se conecta a bd, de ser el caso se tendría en otro paquete
    
}
