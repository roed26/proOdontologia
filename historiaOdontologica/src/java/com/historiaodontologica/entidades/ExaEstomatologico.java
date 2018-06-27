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
@Table(name = "exa_estomatologico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExaEstomatologico.findAll", query = "SELECT e FROM ExaEstomatologico e"),
    @NamedQuery(name = "ExaEstomatologico.findById", query = "SELECT e FROM ExaEstomatologico e WHERE e.id = :id"),
    @NamedQuery(name = "ExaEstomatologico.findByNombre", query = "SELECT e FROM ExaEstomatologico e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "ExaEstomatologico.findByResultado", query = "SELECT e FROM ExaEstomatologico e WHERE e.resultado = :resultado"),
    @NamedQuery(name = "ExaEstomatologico.findByObs", query = "SELECT e FROM ExaEstomatologico e WHERE e.obs = :obs"),
    @NamedQuery(name = "ExaEstomatologico.findByActualizacion", query = "SELECT e FROM ExaEstomatologico e WHERE e.idActualizacion = :actualizacion")
})
public class ExaEstomatologico implements Serializable {

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
    @Size(max = 200)
    @Column(name = "OBS")
    private String obs;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;
    @JoinColumn(name = "ID_ESTOMATOLOGICO", referencedColumnName = "ID")
    @ManyToOne
    private ListadoEstomatologico idEstomatologico;

    public ExaEstomatologico() {
    }

    public ExaEstomatologico(Integer id) {
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

    public ListadoEstomatologico getIdEstomatologico() {
        return idEstomatologico;
    }

    public void setIdEstomatologico(ListadoEstomatologico idEstomatologico) {
        this.idEstomatologico = idEstomatologico;
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
        if (!(object instanceof ExaEstomatologico)) {
            return false;
        }
        ExaEstomatologico other = (ExaEstomatologico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.ExaEstomatologico[ id=" + id + " ]";
    }
    
}
