/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myApp3.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import myApp3.models.MenuTipoUsuario;

/**
 *
 * @author Juan
 */
@Stateless
public class MenuTipoUsuarioFacade extends AbstractFacade<MenuTipoUsuario> {
    @PersistenceContext(unitName = "myApp3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuTipoUsuarioFacade() {
        super(MenuTipoUsuario.class);
    }
    
}
