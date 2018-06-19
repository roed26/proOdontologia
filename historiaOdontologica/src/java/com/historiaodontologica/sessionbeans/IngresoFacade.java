/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.Ingreso;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mi
 */
@Stateless
public class IngresoFacade extends AbstractFacade<Ingreso> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoFacade() {
        super(Ingreso.class);
    }
    
    public List<Ingreso> listadoIngresoFecha(Date fechaInicio, Date fechaFin) {
        Query query = getEntityManager().createNamedQuery("Ingreso.findByConsultaFechaIngreso");
        query.setParameter("desde", fechaInicio);
        query.setParameter("hasta", fechaFin);
        List<Ingreso> resultList = query.getResultList();
        return resultList;
    }
    
}
