/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.MotivoOdo;
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
public class MotivoOdoFacade extends AbstractFacade<MotivoOdo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotivoOdoFacade() {
        super(MotivoOdo.class);
    }
    
    public List<MotivoOdo> buscarPorActualizacion(ActualizacionOdo actualizacionOdo){
        Query query = getEntityManager().createNamedQuery("MotivoOdo.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<MotivoOdo> resultList = query.getResultList();

        return resultList;
    
    }
    
}
