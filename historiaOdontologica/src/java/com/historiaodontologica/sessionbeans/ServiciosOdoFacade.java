/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ServiciosOdo;
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
public class ServiciosOdoFacade extends AbstractFacade<ServiciosOdo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiciosOdoFacade() {
        super(ServiciosOdo.class);
    }

    public List<ServiciosOdo> buscarServicioOdoEjb(String buscarServicioOdo) {
        Query query = getEntityManager().createNamedQuery("ServiciosOdo.findByServiciosOdo");
        query.setParameter("busqueda", "%" + buscarServicioOdo + "%");
        List<ServiciosOdo> resultList = query.getResultList();
        return resultList;
    }

}
