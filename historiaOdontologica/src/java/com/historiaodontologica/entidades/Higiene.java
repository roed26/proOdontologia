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
@Table(name = "higiene")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Higiene.findAll", query = "SELECT h FROM Higiene h")
    ,
    @NamedQuery(name = "Higiene.findById", query = "SELECT h FROM Higiene h WHERE h.id = :id")
    ,
    @NamedQuery(name = "Higiene.findByHigiene", query = "SELECT h FROM Higiene h WHERE h.higiene = :higiene")
    ,
    @NamedQuery(name = "Higiene.findByFrecuenciaCep", query = "SELECT h FROM Higiene h WHERE h.frecuenciaCep = :frecuenciaCep")
    ,
    @NamedQuery(name = "Higiene.findByGradoRiesgo", query = "SELECT h FROM Higiene h WHERE h.gradoRiesgo = :gradoRiesgo")
    ,
    @NamedQuery(name = "Higiene.findByPigmentacion", query = "SELECT h FROM Higiene h WHERE h.pigmentacion = :pigmentacion")
    ,
    @NamedQuery(name = "Higiene.findBySedaDental", query = "SELECT h FROM Higiene h WHERE h.sedaDental = :sedaDental")
    ,
    @NamedQuery(name = "Higiene.findByHabitos", query = "SELECT h FROM Higiene h WHERE h.habitos = :habitos")
    ,
@NamedQuery(name = "Higiene.findByActualizacion", query = "SELECT h FROM Higiene h WHERE h.idActualizacion = :actualizacion")
})
public class Higiene implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 7)
    @Column(name = "HIGIENE")
    private String higiene;
    @Size(max = 3)
    @Column(name = "FRECUENCIA_CEP")
    private String frecuenciaCep;
    @Size(max = 1)
    @Column(name = "GRADO_RIESGO")
    private String gradoRiesgo;
    @Size(max = 2)
    @Column(name = "PIGMENTACION")
    private String pigmentacion;
    @Size(max = 2)
    @Column(name = "SEDA_DENTAL")
    private String sedaDental;
    @Size(max = 200)
    @Column(name = "HABITOS")
    private String habitos;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public Higiene() {
    }

    public Higiene(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHigiene() {
        return higiene;
    }

    public void setHigiene(String higiene) {
        this.higiene = higiene;
    }

    public String getFrecuenciaCep() {
        return frecuenciaCep;
    }

    public void setFrecuenciaCep(String frecuenciaCep) {
        this.frecuenciaCep = frecuenciaCep;
    }

    public String getGradoRiesgo() {
        return gradoRiesgo;
    }

    public void setGradoRiesgo(String gradoRiesgo) {
        this.gradoRiesgo = gradoRiesgo;
    }

    public String getPigmentacion() {
        return pigmentacion;
    }

    public void setPigmentacion(String pigmentacion) {
        this.pigmentacion = pigmentacion;
    }

    public String getSedaDental() {
        return sedaDental;
    }

    public void setSedaDental(String sedaDental) {
        this.sedaDental = sedaDental;
    }

    public String getHabitos() {
        return habitos;
    }

    public void setHabitos(String habitos) {
        this.habitos = habitos;
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
        if (!(object instanceof Higiene)) {
            return false;
        }
        Higiene other = (Higiene) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Higiene[ id=" + id + " ]";
    }

}
