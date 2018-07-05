/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.ObsAntOdo;
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
public class ObsAntOdoFacade extends AbstractFacade<ObsAntOdo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObsAntOdoFacade() {
        super(ObsAntOdo.class);
    }

    public ObsAntOdo buscarPorActualizacionAlergia(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("ObsAntOdo.findByActualizacionObservacion");
        query.setParameter("actualizacion", actualizacionOdo);
        query.setParameter("tipo", "PE");
        List<ObsAntOdo> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }

    }

}
