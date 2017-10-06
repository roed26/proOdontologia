/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

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
public class PresupuestoFacade extends AbstractFacade<Presupuesto> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PresupuestoFacade() {
        super(Presupuesto.class);
    }

    public Presupuesto buscarPorPaciente(Paciente paciente) {
        Query query = getEntityManager().createNamedQuery("Presupuesto.findByPaciente");
        query.setParameter("paciente", paciente);
        List<Presupuesto> resultList = query.getResultList();

        if(resultList.size()>0){
            return resultList.get(resultList.size()-1);
        }else {return null;}
    }
    

}
