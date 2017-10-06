/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mi
 */
@Embeddable
public class DetalleServicioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_detalle_servicio")
    private int idDetalleServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_presupuesto")
    private int idPresupuesto;

    public DetalleServicioPK() {
    }

    public DetalleServicioPK(int idDetalleServicio, int idPresupuesto) {
        this.idDetalleServicio = idDetalleServicio;
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdDetalleServicio() {
        return idDetalleServicio;
    }

    public void setIdDetalleServicio(int idDetalleServicio) {
        this.idDetalleServicio = idDetalleServicio;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idDetalleServicio;
        hash += (int) idPresupuesto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicioPK)) {
            return false;
        }
        DetalleServicioPK other = (DetalleServicioPK) object;
        if (this.idDetalleServicio != other.idDetalleServicio) {
            return false;
        }
        if (this.idPresupuesto != other.idPresupuesto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.DetalleServicioPK[ idDetalleServicio=" + idDetalleServicio + ", idPresupuesto=" + idPresupuesto + " ]";
    }
    
}
