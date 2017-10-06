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
 * @author Mi
 */
@Entity
@Table(name = "ingreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i"),
    @NamedQuery(name = "Ingreso.findByIdIngreso", query = "SELECT i FROM Ingreso i WHERE i.idIngreso = :idIngreso"),
    @NamedQuery(name = "Ingreso.findByFechaIngreso", query = "SELECT i FROM Ingreso i WHERE i.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Ingreso.findByDescripcionIngreso", query = "SELECT i FROM Ingreso i WHERE i.descripcionIngreso = :descripcionIngreso"),
    @NamedQuery(name = "Ingreso.findByValorIngreso", query = "SELECT i FROM Ingreso i WHERE i.valorIngreso = :valorIngreso")})
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ingreso")
    private Integer idIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion_ingreso")
    private String descripcionIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_ingreso")
    private double valorIngreso;

    public Ingreso() {
    }

    public Ingreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Ingreso(Integer idIngreso, Date fechaIngreso, String descripcionIngreso, double valorIngreso) {
        this.idIngreso = idIngreso;
        this.fechaIngreso = fechaIngreso;
        this.descripcionIngreso = descripcionIngreso;
        this.valorIngreso = valorIngreso;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDescripcionIngreso() {
        return descripcionIngreso;
    }

    public void setDescripcionIngreso(String descripcionIngreso) {
        this.descripcionIngreso = descripcionIngreso;
    }

    public double getValorIngreso() {
        return valorIngreso;
    }

    public void setValorIngreso(double valorIngreso) {
        this.valorIngreso = valorIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngreso != null ? idIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.idIngreso == null && other.idIngreso != null) || (this.idIngreso != null && !this.idIngreso.equals(other.idIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Ingreso[ idIngreso=" + idIngreso + " ]";
    }
    
}
