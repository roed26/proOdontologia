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
@Table(name = "exa_oral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExaOral.findAll", query = "SELECT e FROM ExaOral e")
    ,
    @NamedQuery(name = "ExaOral.findById", query = "SELECT e FROM ExaOral e WHERE e.id = :id")
    ,
    @NamedQuery(name = "ExaOral.findByCampo", query = "SELECT e FROM ExaOral e WHERE e.campo = :campo")
    ,
    @NamedQuery(name = "ExaOral.findByValor", query = "SELECT e FROM ExaOral e WHERE e.valor = :valor")
    ,
    @NamedQuery(name = "ExaOral.findByTipo", query = "SELECT e FROM ExaOral e WHERE e.tipo = :tipo")
    ,
    @NamedQuery(name = "ExaOral.findByIdExa", query = "SELECT e FROM ExaOral e WHERE e.idExa = :idExa")
    ,
    @NamedQuery(name = "ExaOral.findByActualizacion", query = "SELECT e FROM ExaOral e WHERE e.idActualizacion = :actualizacion")
})
public class ExaOral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "CAMPO")
    private String campo;
    @Size(max = 2)
    @Column(name = "VALOR")
    private String valor;
    @Size(max = 2)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 2)
    @Column(name = "ID_EXA")
    private String idExa;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public ExaOral() {
    }

    public ExaOral(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdExa() {
        return idExa;
    }

    public void setIdExa(String idExa) {
        this.idExa = idExa;
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
        if (!(object instanceof ExaOral)) {
            return false;
        }
        ExaOral other = (ExaOral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.ExaOral[ id=" + id + " ]";
    }

}
