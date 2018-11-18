/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.sessionbeans;

import com.historiaodontologica.entidades.Paciente;
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
public class PacienteFacade extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "historiaOdontologicaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteFacade() {
        super(Paciente.class);
    }

    public List<Paciente> buscarPacienteEjb(String buscarPaciente) {
        Query query = getEntityManager().createNamedQuery("Paciente.findByPacientesActivos");
        query.setParameter("busqueda", "%" + buscarPaciente + "%");
        List<Paciente> resultList = query.getResultList();
        return resultList;
    }
    public boolean buscarPacienteIdentificacionEjb(String identificacion) {
        Query query = getEntityManager().createNamedQuery("Paciente.findByIdentificacion");
        query.setParameter("identificacion",  identificacion );
        List<Paciente> resultList = query.getResultList();
        if(resultList.size()>0){
        return true;
        }else{
            return false;
        }
    }
}
