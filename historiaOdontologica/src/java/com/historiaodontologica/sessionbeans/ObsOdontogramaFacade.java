/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.ObsOdontograma;
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
public class ObsOdontogramaFacade extends AbstractFacade<ObsOdontograma> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObsOdontogramaFacade() {
        super(ObsOdontograma.class);
    }
    
    public List<ObsOdontograma> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("ObsOdontograma.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<ObsOdontograma> resultList = query.getResultList();

        return resultList;

    }   
}
