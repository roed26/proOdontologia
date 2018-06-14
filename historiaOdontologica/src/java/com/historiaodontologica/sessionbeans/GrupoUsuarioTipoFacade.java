/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.GrupoUsuarioTipo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ROED26
 */
@Stateless
public class GrupoUsuarioTipoFacade extends AbstractFacade<GrupoUsuarioTipo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoUsuarioTipoFacade() {
        super(GrupoUsuarioTipo.class);
    }
    
    public List<GrupoUsuarioTipo> buscarPorNombreUsuario(String nombreUsuario)
    {
        Query query = getEntityManager().createNamedQuery("GrupoUsuarioTipo.findByNombreUsuario");
        query.setParameter("nombreUsuario",nombreUsuario);
        List<GrupoUsuarioTipo> resultList = query.getResultList();
        return resultList;
    }
    
}
