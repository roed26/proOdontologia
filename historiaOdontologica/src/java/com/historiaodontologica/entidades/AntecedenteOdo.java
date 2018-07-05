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
@Table(name = "antecedente_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AntecedenteOdo.findAll", query = "SELECT a FROM AntecedenteOdo a"),
    @NamedQuery(name = "AntecedenteOdo.findById", query = "SELECT a FROM AntecedenteOdo a WHERE a.id = :id"),
    @NamedQuery(name = "AntecedenteOdo.findByNombre", query = "SELECT a FROM AntecedenteOdo a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AntecedenteOdo.findByResultado", query = "SELECT a FROM AntecedenteOdo a WHERE a.resultado = :resultado"),
    @NamedQuery(name = "AntecedenteOdo.findByTipo", query = "SELECT a FROM AntecedenteOdo a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AntecedenteOdo.findByActualizacion", query = "SELECT a FROM AntecedenteOdo a WHERE a.idActualizacion = :actualizacion"),
    @NamedQuery(name = "AntecedenteOdo.findByActualizacionAlergia", query = "SELECT a FROM AntecedenteOdo a WHERE a.idActualizacion = :actualizacion AND a.nombre = :alergia AND a.tipo = :tipo")
})
public class AntecedenteOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 2)
    @Column(name = "RESULTADO")
    private String resultado;
    @Size(max = 2)
    @Column(name = "TIPO")
    private String tipo;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;
    @JoinColumn(name = "ID_ANT", referencedColumnName = "ID")
    @ManyToOne
    private ListadoAntOdon idAnt;

    public AntecedenteOdo() {
    }

    public AntecedenteOdo(Integer id) {
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ActualizacionOdo getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(ActualizacionOdo idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public ListadoAntOdon getIdAnt() {
        return idAnt;
    }

    public void setIdAnt(ListadoAntOdon idAnt) {
        this.idAnt = idAnt;
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
        if (!(object instanceof AntecedenteOdo)) {
            return false;
        }
        AntecedenteOdo other = (AntecedenteOdo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.AntecedenteOdo[ id=" + id + " ]";
    }
    
}
