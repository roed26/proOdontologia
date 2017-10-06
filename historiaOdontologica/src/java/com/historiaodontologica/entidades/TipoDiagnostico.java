/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "tipo_diagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDiagnostico.findAll", query = "SELECT t FROM TipoDiagnostico t"),
    @NamedQuery(name = "TipoDiagnostico.findById", query = "SELECT t FROM TipoDiagnostico t WHERE t.id = :id"),
    @NamedQuery(name = "TipoDiagnostico.findByNombre", query = "SELECT t FROM TipoDiagnostico t WHERE t.nombre = :nombre")})
public class TipoDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "tipodiagnostico")
    private Collection<DiagnosticoOdo> diagnosticoOdoCollection;

    public TipoDiagnostico() {
    }

    public TipoDiagnostico(Integer id) {
        this.id = id;
    }

    public TipoDiagnostico(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @XmlTransient
    public Collection<DiagnosticoOdo> getDiagnosticoOdoCollection() {
        return diagnosticoOdoCollection;
    }

    public void setDiagnosticoOdoCollection(Collection<DiagnosticoOdo> diagnosticoOdoCollection) {
        this.diagnosticoOdoCollection = diagnosticoOdoCollection;
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
        if (!(object instanceof TipoDiagnostico)) {
            return false;
        }
        TipoDiagnostico other = (TipoDiagnostico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.TipoDiagnostico[ id=" + id + " ]";
    }
    
}
