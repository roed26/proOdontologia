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
@Table(name = "evolucion_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvolucionOdo.findAll", query = "SELECT e FROM EvolucionOdo e"),
    @NamedQuery(name = "EvolucionOdo.findById", query = "SELECT e FROM EvolucionOdo e WHERE e.id = :id"),
    @NamedQuery(name = "EvolucionOdo.findByActualizacion", query = "SELECT e FROM EvolucionOdo e WHERE e.idActualizacion = :actualizacion")
})
public class EvolucionOdo implements Serializable {

    @Column(name = "NUMERO_DIENTE")
    private Integer numeroDiente;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "EVOLUCION")
    private String evolucion;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public EvolucionOdo() {
    }

    public EvolucionOdo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(String evolucion) {
        this.evolucion = evolucion;
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
        if (!(object instanceof EvolucionOdo)) {
            return false;
        }
        EvolucionOdo other = (EvolucionOdo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.EvolucionOdo[ id=" + id + " ]";
    }

    public Integer getNumeroDiente() {
        return numeroDiente;
    }

    public void setNumeroDiente(Integer numeroDiente) {
        this.numeroDiente = numeroDiente;
    }
    
}
