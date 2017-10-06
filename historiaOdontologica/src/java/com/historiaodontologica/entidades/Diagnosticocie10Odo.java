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
import javax.persistence.Lob;
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
@Table(name = "diagnosticocie10_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diagnosticocie10Odo.findAll", query = "SELECT d FROM Diagnosticocie10Odo d"),
    @NamedQuery(name = "Diagnosticocie10Odo.findById", query = "SELECT d FROM Diagnosticocie10Odo d WHERE d.id = :id"),
    @NamedQuery(name = "Diagnosticocie10Odo.findByNombreId", query = "SELECT d FROM Diagnosticocie10Odo d WHERE LOWER(CONCAT(CONCAT(d.id,' '),d.nombre)) LIKE :nombreId")
})
public class Diagnosticocie10Odo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "NOMBRE")
    private String nombre;

    public Diagnosticocie10Odo() {
    }

    public Diagnosticocie10Odo(String id) {
        this.id = id;
    }

    public Diagnosticocie10Odo(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        if (!(object instanceof Diagnosticocie10Odo)) {
            return false;
        }
        Diagnosticocie10Odo other = (Diagnosticocie10Odo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Diagnosticocie10Odo[ id=" + id + " ]";
    }
    
}
