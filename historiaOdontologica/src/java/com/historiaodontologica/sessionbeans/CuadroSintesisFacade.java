/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.CuadroSintesis;
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
public class CuadroSintesisFacade extends AbstractFacade<CuadroSintesis> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuadroSintesisFacade() {
        super(CuadroSintesis.class);
    }
    
    public List<CuadroSintesis> buscarPorActualizacion(ActualizacionOdo actualizacionOdo) {
        Query query = getEntityManager().createNamedQuery("CuadroSintesis.findByActualizacion");
        query.setParameter("actualizacion", actualizacionOdo);
        List<CuadroSintesis> resultList = query.getResultList();

        return resultList;

    }   
}
