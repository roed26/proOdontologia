/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.DiagnosticoOdo;
import com.historiaodontologica.entidades.TipoDiagnostico;
import com.historiaodontologica.managedbeans.exceptions.NonexistentEntityException;
import com.historiaodontologica.managedbeans.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author ROED26
 */
public class DiagnosticoOdoController implements Serializable {

    public DiagnosticoOdoController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiagnosticoOdo diagnosticoOdo) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ActualizacionOdo idActualizacion = diagnosticoOdo.getIdActualizacion();
            if (idActualizacion != null) {
                idActualizacion = em.getReference(idActualizacion.getClass(), idActualizacion.getId());
                diagnosticoOdo.setIdActualizacion(idActualizacion);
            }
            TipoDiagnostico tipodiagnostico = diagnosticoOdo.getTipodiagnostico();
            if (tipodiagnostico != null) {
                tipodiagnostico = em.getReference(tipodiagnostico.getClass(), tipodiagnostico.getId());
                diagnosticoOdo.setTipodiagnostico(tipodiagnostico);
            }
            em.persist(diagnosticoOdo);
            if (idActualizacion != null) {
                idActualizacion.getDiagnosticoOdoCollection().add(diagnosticoOdo);
                idActualizacion = em.merge(idActualizacion);
            }
            if (tipodiagnostico != null) {
                tipodiagnostico.getDiagnosticoOdoCollection().add(diagnosticoOdo);
                tipodiagnostico = em.merge(tipodiagnostico);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiagnosticoOdo diagnosticoOdo) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DiagnosticoOdo persistentDiagnosticoOdo = em.find(DiagnosticoOdo.class, diagnosticoOdo.getId());
            ActualizacionOdo idActualizacionOld = persistentDiagnosticoOdo.getIdActualizacion();
            ActualizacionOdo idActualizacionNew = diagnosticoOdo.getIdActualizacion();
            TipoDiagnostico tipodiagnosticoOld = persistentDiagnosticoOdo.getTipodiagnostico();
            TipoDiagnostico tipodiagnosticoNew = diagnosticoOdo.getTipodiagnostico();
            if (idActualizacionNew != null) {
                idActualizacionNew = em.getReference(idActualizacionNew.getClass(), idActualizacionNew.getId());
                diagnosticoOdo.setIdActualizacion(idActualizacionNew);
            }
            if (tipodiagnosticoNew != null) {
                tipodiagnosticoNew = em.getReference(tipodiagnosticoNew.getClass(), tipodiagnosticoNew.getId());
                diagnosticoOdo.setTipodiagnostico(tipodiagnosticoNew);
            }
            diagnosticoOdo = em.merge(diagnosticoOdo);
            if (idActualizacionOld != null && !idActualizacionOld.equals(idActualizacionNew)) {
                idActualizacionOld.getDiagnosticoOdoCollection().remove(diagnosticoOdo);
                idActualizacionOld = em.merge(idActualizacionOld);
            }
            if (idActualizacionNew != null && !idActualizacionNew.equals(idActualizacionOld)) {
                idActualizacionNew.getDiagnosticoOdoCollection().add(diagnosticoOdo);
                idActualizacionNew = em.merge(idActualizacionNew);
            }
            if (tipodiagnosticoOld != null && !tipodiagnosticoOld.equals(tipodiagnosticoNew)) {
                tipodiagnosticoOld.getDiagnosticoOdoCollection().remove(diagnosticoOdo);
                tipodiagnosticoOld = em.merge(tipodiagnosticoOld);
            }
            if (tipodiagnosticoNew != null && !tipodiagnosticoNew.equals(tipodiagnosticoOld)) {
                tipodiagnosticoNew.getDiagnosticoOdoCollection().add(diagnosticoOdo);
                tipodiagnosticoNew = em.merge(tipodiagnosticoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diagnosticoOdo.getId();
                if (findDiagnosticoOdo(id) == null) {
                    throw new NonexistentEntityException("The diagnosticoOdo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            DiagnosticoOdo diagnosticoOdo;
            try {
                diagnosticoOdo = em.getReference(DiagnosticoOdo.class, id);
                diagnosticoOdo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnosticoOdo with id " + id + " no longer exists.", enfe);
            }
            ActualizacionOdo idActualizacion = diagnosticoOdo.getIdActualizacion();
            if (idActualizacion != null) {
                idActualizacion.getDiagnosticoOdoCollection().remove(diagnosticoOdo);
                idActualizacion = em.merge(idActualizacion);
            }
            TipoDiagnostico tipodiagnostico = diagnosticoOdo.getTipodiagnostico();
            if (tipodiagnostico != null) {
                tipodiagnostico.getDiagnosticoOdoCollection().remove(diagnosticoOdo);
                tipodiagnostico = em.merge(tipodiagnostico);
            }
            em.remove(diagnosticoOdo);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DiagnosticoOdo> findDiagnosticoOdoEntities() {
        return findDiagnosticoOdoEntities(true, -1, -1);
    }

    public List<DiagnosticoOdo> findDiagnosticoOdoEntities(int maxResults, int firstResult) {
        return findDiagnosticoOdoEntities(false, maxResults, firstResult);
    }

    private List<DiagnosticoOdo> findDiagnosticoOdoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiagnosticoOdo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public DiagnosticoOdo findDiagnosticoOdo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiagnosticoOdo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosticoOdoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiagnosticoOdo> rt = cq.from(DiagnosticoOdo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
