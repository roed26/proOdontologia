/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.entidades.Presupuesto;
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
public class DetalleServicioFacade extends AbstractFacade<DetalleServicio> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleServicioFacade() {
        super(DetalleServicio.class);
    }
    
    public List <DetalleServicio> buscarPorPresupuestoDetalle(Presupuesto presupuesto) {
        Query query = getEntityManager().createNamedQuery("DetalleServicio.findByPresupuesto");
        query.setParameter("presupuesto", presupuesto);
        List<DetalleServicio> resultList = query.getResultList();
        return resultList;
    }
    
}
