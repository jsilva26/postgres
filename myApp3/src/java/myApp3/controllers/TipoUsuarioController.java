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
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;
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
    private TipoUsuarioDAO dao;
    
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
    
    //A continuación métodos de ayuda para acceder al Bean por otras Clases
    public SelectItem[] getItemsAvailableSelectOne(){
        return getSelectItems(dao.findAll(), true);
    }
    
    //Genera una lista con los items seleccionados (uno o muchos según selectOne). Para tablas relacionadas
    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne){
        int size = selectOne ? entities.size()+1: entities.size();
        SelectItem[] items = new SelectItem[size];
        int i=0;
        if(selectOne){
            items[0] = new SelectItem("", "---");
            i++;
        }
        for(Object x: entities){
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }
    
    //Clase par conversones, se deben implementar todos los métodos
    @FacesConverter(forClass = TipoUsuario.class)
    public static class TipoUsuarioControllerConverter implements Converter{
        
        java.lang.Integer getKey(String value){
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }//Fin para java.lang.Integer.getKey
        
        String getStringKey(java.lang.Integer value){
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }//Fin de getStringKey
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value){
            if(value==null || value.length() == 0){
                return null;
            }
            TipoUsuarioController controller = (TipoUsuarioController)facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "tipoUsuarioController");
            return controller.dao.find(getKey(value));
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object){
            if(object==null){
                return null;
            }
            if(object instanceof TipoUsuario){
                TipoUsuario o = (TipoUsuario)object;
                return getStringKey(o.getIdTipoUsuario());
            }
            else{
                throw new IllegalArgumentException("object " + object + " no es del tipo " + object.getClass().getName() + "; tipo esperado: " + TipoUsuarioController.class.getName());
            }
        }
    }
    
}
