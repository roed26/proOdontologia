/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import java.util.Date;
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
public class ActualizacionOdoFacade extends AbstractFacade<ActualizacionOdo> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActualizacionOdoFacade() {
        super(ActualizacionOdo.class);
    }

    public ActualizacionOdo buscarPorFecha(Date fecha) {

        Query query = getEntityManager().createNamedQuery("ActualizacionOdo.findByFecha");
        query.setParameter("fecha", fecha);
        List<ActualizacionOdo> resultList = query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }

    }

    public List<ActualizacionOdo> buscarPorPaciente(int idPaciente) {
        Query query = getEntityManager().createNamedQuery("ActualizacionOdo.findByPaciente");
        query.setParameter("idPaciente", idPaciente);
        List<ActualizacionOdo> resultList = query.getResultList();

        return resultList;

    }
    public ActualizacionOdo buscarUltimaActualizacionPorPaciente(int idPaciente) {
        Query query = getEntityManager().createNamedQuery("ActualizacionOdo.findByPaciente");
        query.setParameter("idPaciente", idPaciente);
        List<ActualizacionOdo> resultList = query.getResultList();
        int nActualizaciones=resultList.size();
        return resultList.get(nActualizaciones-1);

    }
    
    public boolean buscarPorPacienteBool(int idPaciente) {
        Query query = getEntityManager().createNamedQuery("ActualizacionOdo.findByPaciente");
        query.setParameter("idPaciente", idPaciente);
        List<ActualizacionOdo> resultList = query.getResultList();

        if (resultList.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

}
