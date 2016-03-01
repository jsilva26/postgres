/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.controllers;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan
 */
public class SessionUtil {
    
    //se crean las variables de sesión
    public static void addSession(Integer userId, String userNombre, Integer tipo, String userTipo){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(true);
        
        sesion.setAttribute("userLog", userId);
        sesion.setAttribute("userNombre", userNombre);
        sesion.setAttribute("userTipoId", tipo);
        sesion.setAttribute("userTipo", userTipo);
    }
    
    //se cierra la sesión
    public static void closeSession(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        ((HttpSession)ctx.getSession(false)).invalidate();
    }
    
    //Recupera el código del usuario logueado
    public static Integer getUserLog(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        Integer userLog = (Integer)sesion.getAttribute("userLog");
        return userLog;
    }
    
    //Recuper el nombre del usuario logueado
    public static String getUserNombreLog(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        String userNombre = (String)sesion.getAttribute("userNombre");
        return userNombre;
    }
    
    //Recuper el Id de tipo de usuario logueado
    public static Integer getTipoLog(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        Integer tipo = (Integer)sesion.getAttribute("userLog");
        return tipo;
    }
    
    //Recupera el nombre del tipo de usuario
    public static String getUserTipoLog(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession)context.getExternalContext().getSession(false);
        String userTipo = (String)sesion.getAttribute("userTipo");
        return userTipo;
    }
    
    //Recupera el nombre de la página actual que se está visitando
    public static String getPagina(){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        return ctx.getRequestPathInfo();
    }
    
    //Método para redirigir a página del sitio
    public static void redirectTo(String url){
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath = ((ServletContext)ctx.getContext()).getContextPath();
        
        try{
            ctx.redirect(ctxPath + url);
        }catch(IOException ex){
            
        }
    }
    
    public static void addErrorMessage(String msg){
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }
}
