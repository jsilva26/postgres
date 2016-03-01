/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.controllers;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import myApp3.dao.TipoUsuarioDAO;
import myApp3.models.TipoUsuario;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class TipoUsuarioController implements Serializable {

    @EJB
    TipoUsuarioDAO dao = new TipoUsuarioDAO();
    
    private TipoUsuario current;
    
    public TipoUsuario getCurrent(){
        if(current == null){
            current = new TipoUsuario();
        }
        return current;
    }
    /**
     * Creates a new instance of TipoUsuarioController
     */
    public TipoUsuarioController() {
              
    }
    
    public String index(){
            return "/tipoUsuario/index";
    }
    
    //Devuelve una lista de tipo usuario, va a ser accesible desde paginas server faces
    public List<TipoUsuario> listado(){
        return dao.findAll();
    }
    
    public String create(){
        current = new TipoUsuario();
        return "/tipoUsuario/new";
    }
    
    public String agregar(){
        Date d = new Date();
        current.setUpdated(d);
        current.setCreated(d);
        dao.create(current);
        return "/tipoUsuario/index";
    }
    
    public String edit(int id){
        current = dao.find(id);
        return "/tipoUsuario/edit";
    }
    
    public String guardar(){
        Date d= new Date();
        current.setUpdated(d);
        //System.out.println("id: " + current.getIdTipoUsuario());
        //current.setCreated(current.getCreated());
        //System.out.println("created: " + current.getCreated());
        dao.edit(current);
        return "/tipoUsuario/index";
    }
    
    public String eliminar(int id){
        current = dao.find(id);
        try{
            dao.remove(current);
        }catch(Exception e){
            SessionUtil.addErrorMessage("No se puede eliminar registro, posibles datos asociados");
        }
        return "/tipoUsuario/index";
    }
    
}
