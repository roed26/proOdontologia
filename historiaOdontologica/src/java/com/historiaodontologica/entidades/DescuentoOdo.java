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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mi
 */
@Entity
@Table(name = "descuento_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DescuentoOdo.findAll", query = "SELECT d FROM DescuentoOdo d"),
    @NamedQuery(name = "DescuentoOdo.findByIdDescuento", query = "SELECT d FROM DescuentoOdo d WHERE d.idDescuento = :idDescuento"),
    @NamedQuery(name = "DescuentoOdo.findByNumeroDescuento", query = "SELECT d FROM DescuentoOdo d WHERE d.numeroDescuento = :numeroDescuento")})
public class DescuentoOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_descuento")
    private Integer idDescuento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_descuento")
    private int numeroDescuento;

    public DescuentoOdo() {
    }

    public DescuentoOdo(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public DescuentoOdo(Integer idDescuento, int numeroDescuento) {
        this.idDescuento = idDescuento;
        this.numeroDescuento = numeroDescuento;
    }

    public Integer getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(Integer idDescuento) {
        this.idDescuento = idDescuento;
    }

    public int getNumeroDescuento() {
        return numeroDescuento;
    }

    public void setNumeroDescuento(int numeroDescuento) {
        this.numeroDescuento = numeroDescuento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescuento != null ? idDescuento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DescuentoOdo)) {
            return false;
        }
        DescuentoOdo other = (DescuentoOdo) object;
        if ((this.idDescuento == null && other.idDescuento != null) || (this.idDescuento != null && !this.idDescuento.equals(other.idDescuento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.DescuentoOdo[ idDescuento=" + idDescuento + " ]";
    }
    
}
