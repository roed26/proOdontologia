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
@Table(name = "listado_estomatologico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListadoEstomatologico.findAll", query = "SELECT l FROM ListadoEstomatologico l"),
    @NamedQuery(name = "ListadoEstomatologico.findById", query = "SELECT l FROM ListadoEstomatologico l WHERE l.id = :id"),
    @NamedQuery(name = "ListadoEstomatologico.findByNombre", query = "SELECT l FROM ListadoEstomatologico l WHERE l.nombre = :nombre")})
public class ListadoEstomatologico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idEstomatologico")
    private Collection<ExaEstomatologico> exaEstomatologicoCollection;

    public ListadoEstomatologico() {
    }

    public ListadoEstomatologico(Integer id) {
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

    @XmlTransient
    public Collection<ExaEstomatologico> getExaEstomatologicoCollection() {
        return exaEstomatologicoCollection;
    }

    public void setExaEstomatologicoCollection(Collection<ExaEstomatologico> exaEstomatologicoCollection) {
        this.exaEstomatologicoCollection = exaEstomatologicoCollection;
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
        if (!(object instanceof ListadoEstomatologico)) {
            return false;
        }
        ListadoEstomatologico other = (ListadoEstomatologico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.ListadoEstomatologico[ id=" + id + " ]";
    }
    
}
