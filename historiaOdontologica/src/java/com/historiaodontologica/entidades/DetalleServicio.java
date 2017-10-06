/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "detalle_servicio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleServicio.findAll", query = "SELECT d FROM DetalleServicio d"),
    @NamedQuery(name = "DetalleServicio.findByIdDetalleServicio", query = "SELECT d FROM DetalleServicio d WHERE d.detalleServicioPK.idDetalleServicio = :idDetalleServicio"),
    @NamedQuery(name = "DetalleServicio.findByIdPresupuesto", query = "SELECT d FROM DetalleServicio d WHERE d.detalleServicioPK.idPresupuesto = :idPresupuesto"),
    @NamedQuery(name = "DetalleServicio.findByCantidad", query = "SELECT d FROM DetalleServicio d WHERE d.cantidad = :cantidad"),
    @NamedQuery(name = "DetalleServicio.findByPrecio", query = "SELECT d FROM DetalleServicio d WHERE d.precio = :precio"),
    @NamedQuery(name = "DetalleServicio.findByPresupuesto", query = "SELECT d FROM DetalleServicio d WHERE d.presupuesto = :presupuesto")})
public class DetalleServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetalleServicioPK detalleServicioPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private float precio;
    @JoinColumn(name = "id_presupuesto", referencedColumnName = "id_presupuesto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Presupuesto presupuesto;
    @JoinColumn(name = "id_servicios_odo", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private ServiciosOdo idServiciosOdo;

    public DetalleServicio() {
    }

    public DetalleServicio(DetalleServicioPK detalleServicioPK) {
        this.detalleServicioPK = detalleServicioPK;
    }

    public DetalleServicio(DetalleServicioPK detalleServicioPK, int cantidad, float precio) {
        this.detalleServicioPK = detalleServicioPK;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleServicio(int idDetalleServicio, int idPresupuesto) {
        this.detalleServicioPK = new DetalleServicioPK(idDetalleServicio, idPresupuesto);
    }

    public DetalleServicioPK getDetalleServicioPK() {
        return detalleServicioPK;
    }

    public void setDetalleServicioPK(DetalleServicioPK detalleServicioPK) {
        this.detalleServicioPK = detalleServicioPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Presupuesto getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }

    public ServiciosOdo getIdServiciosOdo() {
        return idServiciosOdo;
    }

    public void setIdServiciosOdo(ServiciosOdo idServiciosOdo) {
        this.idServiciosOdo = idServiciosOdo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detalleServicioPK != null ? detalleServicioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleServicio)) {
            return false;
        }
        DetalleServicio other = (DetalleServicio) object;
        if ((this.detalleServicioPK == null && other.detalleServicioPK != null) || (this.detalleServicioPK != null && !this.detalleServicioPK.equals(other.detalleServicioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.DetalleServicio[ detalleServicioPK=" + detalleServicioPK + " ]";
    }
    
}
