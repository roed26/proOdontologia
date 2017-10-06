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
@Table(name = "cuadro_sintesis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuadroSintesis.findAll", query = "SELECT c FROM CuadroSintesis c"),
    @NamedQuery(name = "CuadroSintesis.findById", query = "SELECT c FROM CuadroSintesis c WHERE c.id = :id"),
    @NamedQuery(name = "CuadroSintesis.findByPresentesSup", query = "SELECT c FROM CuadroSintesis c WHERE c.presentesSup = :presentesSup"),
    @NamedQuery(name = "CuadroSintesis.findByFaltantesSup", query = "SELECT c FROM CuadroSintesis c WHERE c.faltantesSup = :faltantesSup"),
    @NamedQuery(name = "CuadroSintesis.findByCariadosSup", query = "SELECT c FROM CuadroSintesis c WHERE c.cariadosSup = :cariadosSup"),
    @NamedQuery(name = "CuadroSintesis.findByObturacionSup", query = "SELECT c FROM CuadroSintesis c WHERE c.obturacionSup = :obturacionSup"),
    @NamedQuery(name = "CuadroSintesis.findByExtraIndSup", query = "SELECT c FROM CuadroSintesis c WHERE c.extraIndSup = :extraIndSup"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisFijaSup", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisFijaSup = :protesisFijaSup"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisRemSup", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisRemSup = :protesisRemSup"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisTotSup", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisTotSup = :protesisTotSup"),
    @NamedQuery(name = "CuadroSintesis.findByTratamientoCondSup", query = "SELECT c FROM CuadroSintesis c WHERE c.tratamientoCondSup = :tratamientoCondSup"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasNSup", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasNSup = :anomaliasNSup"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasForSup", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasForSup = :anomaliasForSup"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasPosSup", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasPosSup = :anomaliasPosSup"),
    @NamedQuery(name = "CuadroSintesis.findByEnfermedadPeriodentalSup", query = "SELECT c FROM CuadroSintesis c WHERE c.enfermedadPeriodentalSup = :enfermedadPeriodentalSup"),
    @NamedQuery(name = "CuadroSintesis.findByNucleosSup", query = "SELECT c FROM CuadroSintesis c WHERE c.nucleosSup = :nucleosSup"),
    @NamedQuery(name = "CuadroSintesis.findByPresentesInf", query = "SELECT c FROM CuadroSintesis c WHERE c.presentesInf = :presentesInf"),
    @NamedQuery(name = "CuadroSintesis.findByFaltantesInf", query = "SELECT c FROM CuadroSintesis c WHERE c.faltantesInf = :faltantesInf"),
    @NamedQuery(name = "CuadroSintesis.findByCariadosInf", query = "SELECT c FROM CuadroSintesis c WHERE c.cariadosInf = :cariadosInf"),
    @NamedQuery(name = "CuadroSintesis.findByObturacionInf", query = "SELECT c FROM CuadroSintesis c WHERE c.obturacionInf = :obturacionInf"),
    @NamedQuery(name = "CuadroSintesis.findByExtraIndInf", query = "SELECT c FROM CuadroSintesis c WHERE c.extraIndInf = :extraIndInf"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisFijaInf", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisFijaInf = :protesisFijaInf"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisRemInf", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisRemInf = :protesisRemInf"),
    @NamedQuery(name = "CuadroSintesis.findByProtesisTotInf", query = "SELECT c FROM CuadroSintesis c WHERE c.protesisTotInf = :protesisTotInf"),
    @NamedQuery(name = "CuadroSintesis.findByTratamientoCondInf", query = "SELECT c FROM CuadroSintesis c WHERE c.tratamientoCondInf = :tratamientoCondInf"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasNInf", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasNInf = :anomaliasNInf"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasForInf", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasForInf = :anomaliasForInf"),
    @NamedQuery(name = "CuadroSintesis.findByAnomaliasPosInf", query = "SELECT c FROM CuadroSintesis c WHERE c.anomaliasPosInf = :anomaliasPosInf"),
    @NamedQuery(name = "CuadroSintesis.findByEnfermedadPeriodentalInf", query = "SELECT c FROM CuadroSintesis c WHERE c.enfermedadPeriodentalInf = :enfermedadPeriodentalInf"),
    @NamedQuery(name = "CuadroSintesis.findByNucleosInf", query = "SELECT c FROM CuadroSintesis c WHERE c.nucleosInf = :nucleosInf")})
public class CuadroSintesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2)
    @Column(name = "PRESENTES_SUP")
    private String presentesSup;
    @Size(max = 2)
    @Column(name = "FALTANTES_SUP")
    private String faltantesSup;
    @Size(max = 2)
    @Column(name = "CARIADOS_SUP")
    private String cariadosSup;
    @Size(max = 2)
    @Column(name = "OBTURACION_SUP")
    private String obturacionSup;
    @Size(max = 2)
    @Column(name = "EXTRA_IND_SUP")
    private String extraIndSup;
    @Size(max = 2)
    @Column(name = "PROTESIS_FIJA_SUP")
    private String protesisFijaSup;
    @Size(max = 2)
    @Column(name = "PROTESIS_REM_SUP")
    private String protesisRemSup;
    @Size(max = 2)
    @Column(name = "PROTESIS_TOT_SUP")
    private String protesisTotSup;
    @Size(max = 2)
    @Column(name = "TRATAMIENTO_COND_SUP")
    private String tratamientoCondSup;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_N_SUP")
    private String anomaliasNSup;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_FOR_SUP")
    private String anomaliasForSup;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_POS_SUP")
    private String anomaliasPosSup;
    @Size(max = 2)
    @Column(name = "ENFERMEDAD_PERIODENTAL_SUP")
    private String enfermedadPeriodentalSup;
    @Size(max = 2)
    @Column(name = "NUCLEOS_SUP")
    private String nucleosSup;
    @Size(max = 2)
    @Column(name = "PRESENTES_INF")
    private String presentesInf;
    @Size(max = 2)
    @Column(name = "FALTANTES_INF")
    private String faltantesInf;
    @Size(max = 2)
    @Column(name = "CARIADOS_INF")
    private String cariadosInf;
    @Size(max = 2)
    @Column(name = "OBTURACION_INF")
    private String obturacionInf;
    @Size(max = 2)
    @Column(name = "EXTRA_IND_INF")
    private String extraIndInf;
    @Size(max = 2)
    @Column(name = "PROTESIS_FIJA_INF")
    private String protesisFijaInf;
    @Size(max = 2)
    @Column(name = "PROTESIS_REM_INF")
    private String protesisRemInf;
    @Size(max = 2)
    @Column(name = "PROTESIS_TOT_INF")
    private String protesisTotInf;
    @Size(max = 2)
    @Column(name = "TRATAMIENTO_COND_INF")
    private String tratamientoCondInf;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_N_INF")
    private String anomaliasNInf;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_FOR_INF")
    private String anomaliasForInf;
    @Size(max = 2)
    @Column(name = "ANOMALIAS_POS_INF")
    private String anomaliasPosInf;
    @Size(max = 2)
    @Column(name = "ENFERMEDAD_PERIODENTAL_INF")
    private String enfermedadPeriodentalInf;
    @Size(max = 2)
    @Column(name = "NUCLEOS_INF")
    private String nucleosInf;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public CuadroSintesis() {
    }

    public CuadroSintesis(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPresentesSup() {
        return presentesSup;
    }

    public void setPresentesSup(String presentesSup) {
        this.presentesSup = presentesSup;
    }

    public String getFaltantesSup() {
        return faltantesSup;
    }

    public void setFaltantesSup(String faltantesSup) {
        this.faltantesSup = faltantesSup;
    }

    public String getCariadosSup() {
        return cariadosSup;
    }

    public void setCariadosSup(String cariadosSup) {
        this.cariadosSup = cariadosSup;
    }

    public String getObturacionSup() {
        return obturacionSup;
    }

    public void setObturacionSup(String obturacionSup) {
        this.obturacionSup = obturacionSup;
    }

    public String getExtraIndSup() {
        return extraIndSup;
    }

    public void setExtraIndSup(String extraIndSup) {
        this.extraIndSup = extraIndSup;
    }

    public String getProtesisFijaSup() {
        return protesisFijaSup;
    }

    public void setProtesisFijaSup(String protesisFijaSup) {
        this.protesisFijaSup = protesisFijaSup;
    }

    public String getProtesisRemSup() {
        return protesisRemSup;
    }

    public void setProtesisRemSup(String protesisRemSup) {
        this.protesisRemSup = protesisRemSup;
    }

    public String getProtesisTotSup() {
        return protesisTotSup;
    }

    public void setProtesisTotSup(String protesisTotSup) {
        this.protesisTotSup = protesisTotSup;
    }

    public String getTratamientoCondSup() {
        return tratamientoCondSup;
    }

    public void setTratamientoCondSup(String tratamientoCondSup) {
        this.tratamientoCondSup = tratamientoCondSup;
    }

    public String getAnomaliasNSup() {
        return anomaliasNSup;
    }

    public void setAnomaliasNSup(String anomaliasNSup) {
        this.anomaliasNSup = anomaliasNSup;
    }

    public String getAnomaliasForSup() {
        return anomaliasForSup;
    }

    public void setAnomaliasForSup(String anomaliasForSup) {
        this.anomaliasForSup = anomaliasForSup;
    }

    public String getAnomaliasPosSup() {
        return anomaliasPosSup;
    }

    public void setAnomaliasPosSup(String anomaliasPosSup) {
        this.anomaliasPosSup = anomaliasPosSup;
    }

    public String getEnfermedadPeriodentalSup() {
        return enfermedadPeriodentalSup;
    }

    public void setEnfermedadPeriodentalSup(String enfermedadPeriodentalSup) {
        this.enfermedadPeriodentalSup = enfermedadPeriodentalSup;
    }

    public String getNucleosSup() {
        return nucleosSup;
    }

    public void setNucleosSup(String nucleosSup) {
        this.nucleosSup = nucleosSup;
    }

    public String getPresentesInf() {
        return presentesInf;
    }

    public void setPresentesInf(String presentesInf) {
        this.presentesInf = presentesInf;
    }

    public String getFaltantesInf() {
        return faltantesInf;
    }

    public void setFaltantesInf(String faltantesInf) {
        this.faltantesInf = faltantesInf;
    }

    public String getCariadosInf() {
        return cariadosInf;
    }

    public void setCariadosInf(String cariadosInf) {
        this.cariadosInf = cariadosInf;
    }

    public String getObturacionInf() {
        return obturacionInf;
    }

    public void setObturacionInf(String obturacionInf) {
        this.obturacionInf = obturacionInf;
    }

    public String getExtraIndInf() {
        return extraIndInf;
    }

    public void setExtraIndInf(String extraIndInf) {
        this.extraIndInf = extraIndInf;
    }

    public String getProtesisFijaInf() {
        return protesisFijaInf;
    }

    public void setProtesisFijaInf(String protesisFijaInf) {
        this.protesisFijaInf = protesisFijaInf;
    }

    public String getProtesisRemInf() {
        return protesisRemInf;
    }

    public void setProtesisRemInf(String protesisRemInf) {
        this.protesisRemInf = protesisRemInf;
    }

    public String getProtesisTotInf() {
        return protesisTotInf;
    }

    public void setProtesisTotInf(String protesisTotInf) {
        this.protesisTotInf = protesisTotInf;
    }

    public String getTratamientoCondInf() {
        return tratamientoCondInf;
    }

    public void setTratamientoCondInf(String tratamientoCondInf) {
        this.tratamientoCondInf = tratamientoCondInf;
    }

    public String getAnomaliasNInf() {
        return anomaliasNInf;
    }

    public void setAnomaliasNInf(String anomaliasNInf) {
        this.anomaliasNInf = anomaliasNInf;
    }

    public String getAnomaliasForInf() {
        return anomaliasForInf;
    }

    public void setAnomaliasForInf(String anomaliasForInf) {
        this.anomaliasForInf = anomaliasForInf;
    }

    public String getAnomaliasPosInf() {
        return anomaliasPosInf;
    }

    public void setAnomaliasPosInf(String anomaliasPosInf) {
        this.anomaliasPosInf = anomaliasPosInf;
    }

    public String getEnfermedadPeriodentalInf() {
        return enfermedadPeriodentalInf;
    }

    public void setEnfermedadPeriodentalInf(String enfermedadPeriodentalInf) {
        this.enfermedadPeriodentalInf = enfermedadPeriodentalInf;
    }

    public String getNucleosInf() {
        return nucleosInf;
    }

    public void setNucleosInf(String nucleosInf) {
        this.nucleosInf = nucleosInf;
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
        if (!(object instanceof CuadroSintesis)) {
            return false;
        }
        CuadroSintesis other = (CuadroSintesis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.CuadroSintesis[ id=" + id + " ]";
    }
    
}
