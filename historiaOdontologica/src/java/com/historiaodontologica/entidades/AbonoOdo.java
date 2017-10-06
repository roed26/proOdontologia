/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mi
 */
@Entity
@Table(name = "abono_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AbonoOdo.findAll", query = "SELECT a FROM AbonoOdo a"),
    @NamedQuery(name = "AbonoOdo.findByIdAbono", query = "SELECT a FROM AbonoOdo a WHERE a.idAbono = :idAbono"),
    @NamedQuery(name = "AbonoOdo.findByFecha", query = "SELECT a FROM AbonoOdo a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AbonoOdo.findByValor", query = "SELECT a FROM AbonoOdo a WHERE a.valor = :valor"),
    @NamedQuery(name = "AbonoOdo.findByDescripcion", query = "SELECT a FROM AbonoOdo a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AbonoOdo.findByPresupuesto", query = "SELECT a FROM AbonoOdo a WHERE a.idPresupuesto = :presupuesto")})
public class AbonoOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_abono")
    private Integer idAbono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private float valor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "id_presupuesto", referencedColumnName = "id_presupuesto")
    @ManyToOne(optional = false)
    private Presupuesto idPresupuesto;
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UsuariosSistema idUsuario;

    public AbonoOdo() {
    }

    public AbonoOdo(Integer idAbono) {
        this.idAbono = idAbono;
    }

    public AbonoOdo(Integer idAbono, Date fecha, float valor, String descripcion) {
        this.idAbono = idAbono;
        this.fecha = fecha;
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public Integer getIdAbono() {
        return idAbono;
    }

    public void setIdAbono(Integer idAbono) {
        this.idAbono = idAbono;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Presupuesto getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Presupuesto idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public UsuariosSistema getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosSistema idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAbono != null ? idAbono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonoOdo)) {
            return false;
        }
        AbonoOdo other = (AbonoOdo) object;
        if ((this.idAbono == null && other.idAbono != null) || (this.idAbono != null && !this.idAbono.equals(other.idAbono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.AbonoOdo[ idAbono=" + idAbono + " ]";
    }
    
}
