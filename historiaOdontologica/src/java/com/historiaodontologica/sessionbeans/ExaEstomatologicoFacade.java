/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.ExaEstomatologico;
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
public class ExaEstomatologicoFacade extends AbstractFacade<ExaEstomatologico> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExaEstomatologicoFacade() {
        super(ExaEstomatologico.class);
    }
    public List<ExaEstomatologico> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("ExaEstomatologico.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<ExaEstomatologico> resultList = query.getResultList();

        return resultList;

    }
}
