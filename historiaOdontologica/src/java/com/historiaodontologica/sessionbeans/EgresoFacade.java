/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.Egreso;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jymon
 */
@Stateless
public class EgresoFacade extends AbstractFacade<Egreso> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EgresoFacade() {
        super(Egreso.class);
    }
    public List<Egreso> listadoEgresoFecha(Date fechaInicio, Date fechaFin) {
            Query query = getEntityManager().createNamedQuery("Egreso.findByConsultaFechaEgreso");
        query.setParameter("desde", fechaInicio);
        query.setParameter("hasta", fechaFin);
        List<Egreso> resultList = query.getResultList();
        return resultList;
    }
    
}
