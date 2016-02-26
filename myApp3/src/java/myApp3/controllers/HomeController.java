/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class HomeController implements Serializable {

    /**
     * Creates a new instance of HomeController
     */
    public HomeController() {
    }
    
    public String index(){
        return "/index";
    }
    
    public String acercaDe(){
        return "/home/acerca_de";
    }
    
    public String infoDelPie(){ 
        String nombre = null;
        String tipo = null;
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
