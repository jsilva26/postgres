/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findAllOrdenMenu", query = "SELECT m FROM Menu m order by m.raiz, m.orden"),
    @NamedQuery(name = "Menu.findByIdMenu", query = "SELECT m FROM Menu m WHERE m.idMenu = :idMenu"),
    @NamedQuery(name = "Menu.findByNombre", query = "SELECT m FROM Menu m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Menu.findByImagen", query = "SELECT m FROM Menu m WHERE m.imagen = :imagen"),
    @NamedQuery(name = "Menu.findByUrl", query = "SELECT m FROM Menu m WHERE m.url = :url"),
    @NamedQuery(name = "Menu.findByAction", query = "SELECT m FROM Menu m WHERE m.action = :action"),
    @NamedQuery(name = "Menu.findByRaiz", query = "SELECT m FROM Menu m WHERE m.raiz = :raiz"),
    @NamedQuery(name = "Menu.findByOrden", query = "SELECT m FROM Menu m WHERE m.orden = :orden"),
    @NamedQuery(name = "Menu.findByCreated", query = "SELECT m FROM Menu m WHERE m.created = :created"),
    @NamedQuery(name = "Menu.findByUpdated", query = "SELECT m FROM Menu m WHERE m.updated = :updated")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu")
    private Integer idMenu;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100, message = "Debe introducir un nombre")
    @Column(name = "nombre")
    private String nombre;
    
    @Size(max = 100)
    @Column(name = "imagen")
    private String imagen;
    
    @Size(max = 100)
    @Column(name = "url")
    private String url;
   
    @Size(max = 100)
    @Column(name = "action")
    private String action;
    
    @Basic(optional = false)
    @NotNull(message = "Debe ingresar Raíz")
    @Size(min = 1, max = 100)
    @Column(name = "raiz")
    private String raiz;
    
    @Basic(optional = false)
    @NotNull(message = "Debe ingresar Orden")
    @Size(min = 1, max = 100)
    @Column(name = "orden")
    private String orden;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuId")
    private List<MenuTipoUsuario> menuTipoUsuarioList;

    public Menu() {
    }

    public Menu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Menu(Integer idMenu, String nombre, String imagen, String url, String action, String raiz, String orden, Date created, Date updated) {
        this.idMenu = idMenu;
        this.nombre = nombre;
        this.imagen = imagen;
        this.url = url;
        this.action = action;
        this.raiz = raiz;
        this.orden = orden;
        this.created = created;
        this.updated = updated;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String raiz) {
        this.raiz = raiz;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @XmlTransient
    public List<MenuTipoUsuario> getMenuTipoUsuarioList() {
        return menuTipoUsuarioList;
    }

    public void setMenuTipoUsuarioList(List<MenuTipoUsuario> menuTipoUsuarioList) {
        this.menuTipoUsuarioList = menuTipoUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre + "( " + idMenu + " )";
    }
    
}
