/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jymon
 */
@Entity
@Table(name = "egreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e"),
    @NamedQuery(name = "Egreso.findByIdEgreso", query = "SELECT e FROM Egreso e WHERE e.idEgreso = :idEgreso"),
    @NamedQuery(name = "Egreso.findByFechaEgreso", query = "SELECT e FROM Egreso e WHERE e.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "Egreso.findByDescripcionEgreso", query = "SELECT e FROM Egreso e WHERE e.descripcionEgreso = :descripcionEgreso"),
    @NamedQuery(name = "Egreso.findByValorEgreso", query = "SELECT e FROM Egreso e WHERE e.valorEgreso = :valorEgreso")})
public class Egreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_egreso")
    private Integer idEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion_egreso")
    private String descripcionEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_egreso")
    private double valorEgreso;

    public Egreso() {
    }

    public Egreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Egreso(Integer idEgreso, Date fechaEgreso, String descripcionEgreso, double valorEgreso) {
        this.idEgreso = idEgreso;
        this.fechaEgreso = fechaEgreso;
        this.descripcionEgreso = descripcionEgreso;
        this.valorEgreso = valorEgreso;
    }

    public Integer getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public String getDescripcionEgreso() {
        return descripcionEgreso;
    }

    public void setDescripcionEgreso(String descripcionEgreso) {
        this.descripcionEgreso = descripcionEgreso;
    }

    public double getValorEgreso() {
        return valorEgreso;
    }

    public void setValorEgreso(double valorEgreso) {
        this.valorEgreso = valorEgreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEgreso != null ? idEgreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egreso)) {
            return false;
        }
        Egreso other = (Egreso) object;
        if ((this.idEgreso == null && other.idEgreso != null) || (this.idEgreso != null && !this.idEgreso.equals(other.idEgreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Egreso[ idEgreso=" + idEgreso + " ]";
    }
    
}
