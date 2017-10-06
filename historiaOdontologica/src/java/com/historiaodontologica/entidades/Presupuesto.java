/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mi
 */
@Entity
@Table(name = "presupuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presupuesto.findAll", query = "SELECT p FROM Presupuesto p"),
    @NamedQuery(name = "Presupuesto.findByIdPresupuesto", query = "SELECT p FROM Presupuesto p WHERE p.idPresupuesto = :idPresupuesto"),
    @NamedQuery(name = "Presupuesto.findByFecha", query = "SELECT p FROM Presupuesto p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Presupuesto.findByPaciente", query = "SELECT p FROM Presupuesto p WHERE p.idPaciente = :paciente")})

public class Presupuesto implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPresupuesto")
    private Collection<AbonoOdo> abonoOdoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_presupuesto")
    private Integer idPresupuesto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_paciente", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Paciente idPaciente;
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private UsuariosSistema idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presupuesto")
    private Collection<DetalleServicio> detalleServicioCollection;

    public Presupuesto() {
    }

    public Presupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Presupuesto(Integer idPresupuesto, Date fecha) {
        this.idPresupuesto = idPresupuesto;
        this.fecha = fecha;
    }

    public Integer getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(Integer idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public UsuariosSistema getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuariosSistema idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<DetalleServicio> getDetalleServicioCollection() {
        return detalleServicioCollection;
    }

    public void setDetalleServicioCollection(Collection<DetalleServicio> detalleServicioCollection) {
        this.detalleServicioCollection = detalleServicioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPresupuesto != null ? idPresupuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Presupuesto)) {
            return false;
        }
        Presupuesto other = (Presupuesto) object;
        if ((this.idPresupuesto == null && other.idPresupuesto != null) || (this.idPresupuesto != null && !this.idPresupuesto.equals(other.idPresupuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Presupuesto[ idPresupuesto=" + idPresupuesto + " ]";
    }
    @XmlTransient
    public Collection<AbonoOdo> getAbonoOdoCollection() {
        return abonoOdoCollection;
    }

    public void setAbonoOdoCollection(Collection<AbonoOdo> abonoOdoCollection) {
        this.abonoOdoCollection = abonoOdoCollection;
    }

}
