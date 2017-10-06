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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "listado_examen_oral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListadoExamenOral.findAll", query = "SELECT l FROM ListadoExamenOral l"),
    @NamedQuery(name = "ListadoExamenOral.findById", query = "SELECT l FROM ListadoExamenOral l WHERE l.id = :id"),
    @NamedQuery(name = "ListadoExamenOral.findByNombre", query = "SELECT l FROM ListadoExamenOral l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "ListadoExamenOral.findByPulpar", query = "SELECT l FROM ListadoExamenOral l WHERE l.pulpar = :pulpar"),
    @NamedQuery(name = "ListadoExamenOral.findByDentarios", query = "SELECT l FROM ListadoExamenOral l WHERE l.dentarios = :dentarios"),
    @NamedQuery(name = "ListadoExamenOral.findByPeriodontales", query = "SELECT l FROM ListadoExamenOral l WHERE l.periodontales = :periodontales")})
public class ListadoExamenOral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 1)
    @Column(name = "PULPAR")
    private String pulpar;
    @Size(max = 1)
    @Column(name = "DENTARIOS")
    private String dentarios;
    @Size(max = 1)
    @Column(name = "PERIODONTALES")
    private String periodontales;

    public ListadoExamenOral() {
    }

    public ListadoExamenOral(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPulpar() {
        return pulpar;
    }

    public void setPulpar(String pulpar) {
        this.pulpar = pulpar;
    }

    public String getDentarios() {
        return dentarios;
    }

    public void setDentarios(String dentarios) {
        this.dentarios = dentarios;
    }

    public String getPeriodontales() {
        return periodontales;
    }

    public void setPeriodontales(String periodontales) {
        this.periodontales = periodontales;
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
        if (!(object instanceof ListadoExamenOral)) {
            return false;
        }
        ListadoExamenOral other = (ListadoExamenOral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.ListadoExamenOral[ id=" + id + " ]";
    }
    
}
