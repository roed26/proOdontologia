/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "obs_odontograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObsOdontograma.findAll", query = "SELECT o FROM ObsOdontograma o"),
    @NamedQuery(name = "ObsOdontograma.findById", query = "SELECT o FROM ObsOdontograma o WHERE o.id = :id"),
    @NamedQuery(name = "ObsOdontograma.findByOclusion", query = "SELECT o FROM ObsOdontograma o WHERE o.oclusion = :oclusion"),
    @NamedQuery(name = "ObsOdontograma.findByCaries", query = "SELECT o FROM ObsOdontograma o WHERE o.caries = :caries"),
    @NamedQuery(name = "ObsOdontograma.findByPerdidos", query = "SELECT o FROM ObsOdontograma o WHERE o.perdidos = :perdidos"),
    @NamedQuery(name = "ObsOdontograma.findByObturados", query = "SELECT o FROM ObsOdontograma o WHERE o.obturados = :obturados"),
    @NamedQuery(name = "ObsOdontograma.findByActualizacion", query = "SELECT o FROM ObsOdontograma o WHERE o.idActualizacion = :actualizacion")
})
public class ObsOdontograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 7)
    @Column(name = "OCLUSION")
    private String oclusion;
    @Size(max = 2)
    @Column(name = "CARIES")
    private String caries;
    @Size(max = 2)
    @Column(name = "PERDIDOS")
    private String perdidos;
    @Size(max = 2)
    @Column(name = "OBTURADOS")
    private String obturados;
    @Lob
    @Size(max = 65535)
    @Column(name = "OBS")
    private String obs;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public ObsOdontograma() {
    }

    public ObsOdontograma(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOclusion() {
        return oclusion;
    }

    public void setOclusion(String oclusion) {
        this.oclusion = oclusion;
    }

    public String getCaries() {
        return caries;
    }

    public void setCaries(String caries) {
        this.caries = caries;
    }

    public String getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(String perdidos) {
        this.perdidos = perdidos;
    }

    public String getObturados() {
        return obturados;
    }

    public void setObturados(String obturados) {
        this.obturados = obturados;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public ActualizacionOdo getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(ActualizacionOdo idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObsOdontograma)) {
            return false;
        }
        ObsOdontograma other = (ObsOdontograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.ObsOdontograma[ id=" + id + " ]";
    }
    
}
