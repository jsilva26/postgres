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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import myApp3.dao.UsuarioDAO;
import myApp3.models.Usuario;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioDAO dao;
    
    private Usuario current;
    
    public Usuario getCurrent(){
        if(current == null){
            current = new Usuario();
        }
        return current;
    }
    /**
     * Creates a new instance of TipoUsuarioController
     */
    public UsuarioController() {
              
    }
    
    public String index(){
            return "/usuario/index";
    }
    
    //Devuelve una lista de tipo usuario, va a ser accesible desde paginas server faces
    public List<Usuario> listado(){
        return dao.findAll();
    }
    
    public String create(){
        current = new Usuario();
        return "/usuario/new";
    }
    
    public String agregar(){
        Date d = new Date();
        current.setUpdated(d);
        current.setCreated(d);
        dao.create(current);
        return "/usuario/index";
    }
    
    public String edit(int id){
        current = dao.find(id);
        return "/usuario/edit";
    }
    
    public String guardar(){
        Date d= new Date();
        current.setUpdated(d);
        //System.out.println("id: " + current.getIdTipoUsuario());
        //current.setCreated(current.getCreated());
        //System.out.println("created: " + current.getCreated());
        dao.edit(current);
        return "/usuario/index";
    }
    
    public String eliminar(int id){
        current = dao.find(id);
        try{
            dao.remove(current);
        }catch(Exception e){
            SessionUtil.addErrorMessage("No se puede eliminar registro, posibles datos asociados");
        }
        return "/usuario/index";
    }
    
}
