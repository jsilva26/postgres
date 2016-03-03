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
import myApp3.dao.MenuTipoUsuarioDAO;
import myApp3.models.MenuTipoUsuario;

/**
 *
 * @author Juan
 */
@ManagedBean
@SessionScoped
public class MenuTipoUsuarioController implements Serializable {

    /**
     * Creates a new instance of MenuTipoUsuarioController
     */
    public MenuTipoUsuarioController() {
    }

    @EJB
    private MenuTipoUsuarioDAO dao;
    private MenuTipoUsuario current;
    
    public MenuTipoUsuario getCurrent(){
        if(current == null){
            current = new MenuTipoUsuario();
        }
        return current;
    }
    
    public String index() {
        return "/menu_tipoUsuario/index";
    } // Fin public String index
    
    public List<MenuTipoUsuario> listado() {        
        return dao.findAll();
    } // Fin public List<Usuario> listado
    
    public String create() {
        current = new MenuTipoUsuario();
        return "/menu_tipoUsuario/new";
    } // Fin public String create
    
    public String agregar() {
        Date d = new Date();
        current.setCreated(d);
        current.setUpdated(d);
        dao.create(current);
        return "/menu_tipoUsuario/index";
    } // Fin public String agregar
    
    public String edit(int codigo) {
        current = dao.find(codigo);
        return "/menu_tipoUsuario/edit";
    } // Fin public Tipousuario edit
    
    public String guardar() {
        Date d = new Date();
        current.setUpdated(d);
        dao.edit(current);
        return "/menu_tipoUsuario/index";
    } // Fin public String guardar
    
    public String eliminar(int codigo) {
        current = dao.find(codigo);
        dao.remove(current);
        return "/menu_tipoUsuario/index";
    } // Fin public String eliminar
}
