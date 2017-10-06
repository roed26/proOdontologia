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
import javax.persistence.Lob;
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
@Table(name = "diagnostico_odo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoOdo.findAll", query = "SELECT d FROM DiagnosticoOdo d"),
    @NamedQuery(name = "DiagnosticoOdo.findById", query = "SELECT d FROM DiagnosticoOdo d WHERE d.id = :id"),
    @NamedQuery(name = "DiagnosticoOdo.findByDx", query = "SELECT d FROM DiagnosticoOdo d WHERE d.dx = :dx")})
public class DiagnosticoOdo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 8)
    @Column(name = "DX")
    private String dx;
    @Lob
    @Size(max = 65535)
    @Column(name = "NDX")
    private String ndx;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;
    @JoinColumn(name = "TIPODIAGNOSTICO", referencedColumnName = "ID")
    @ManyToOne
    private TipoDiagnostico tipodiagnostico;

    public DiagnosticoOdo() {
    }

    public DiagnosticoOdo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getNdx() {
        return ndx;
    }

    public void setNdx(String ndx) {
        this.ndx = ndx;
    }

    public ActualizacionOdo getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(ActualizacionOdo idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public TipoDiagnostico getTipodiagnostico() {
        return tipodiagnostico;
    }

    public void setTipodiagnostico(TipoDiagnostico tipodiagnostico) {
        this.tipodiagnostico = tipodiagnostico;
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
        if (!(object instanceof DiagnosticoOdo)) {
            return false;
        }
        DiagnosticoOdo other = (DiagnosticoOdo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.DiagnosticoOdo[ id=" + id + " ]";
    }
    
}
