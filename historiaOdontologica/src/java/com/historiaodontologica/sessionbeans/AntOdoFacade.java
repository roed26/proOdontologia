/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.AntecedenteOdo;
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
public class AntOdoFacade extends AbstractFacade<AntecedenteOdo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntOdoFacade() {
        super(AntecedenteOdo.class);
    }

    public List<AntecedenteOdo> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("AntecedenteOdo.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<AntecedenteOdo> resultList = query.getResultList();

        return resultList;

    }

    public AntecedenteOdo buscarPorActualizacionAlergia(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("AntecedenteOdo.findByActualizacionAlergia");
        query.setParameter("actualizacion", actualizacionOdo);
        query.setParameter("alergia", "ALERGIAS");
        query.setParameter("tipo", "PE");
        List<AntecedenteOdo> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }

    }
}
