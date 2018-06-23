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
@Table(name = "odontograma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Odontograma.findAll", query = "SELECT o FROM Odontograma o"),
    @NamedQuery(name = "Odontograma.findById", query = "SELECT o FROM Odontograma o WHERE o.id = :id"),
    @NamedQuery(name = "Odontograma.findByDiente", query = "SELECT o FROM Odontograma o WHERE o.diente = :diente"),
    @NamedQuery(name = "Odontograma.findByImgArriba", query = "SELECT o FROM Odontograma o WHERE o.imgArriba = :imgArriba"),
    @NamedQuery(name = "Odontograma.findByImgAbajo", query = "SELECT o FROM Odontograma o WHERE o.imgAbajo = :imgAbajo"),
    @NamedQuery(name = "Odontograma.findByImgCentro", query = "SELECT o FROM Odontograma o WHERE o.imgCentro = :imgCentro"),
    @NamedQuery(name = "Odontograma.findByImgIzq", query = "SELECT o FROM Odontograma o WHERE o.imgIzq = :imgIzq"),
    @NamedQuery(name = "Odontograma.findByImgDer", query = "SELECT o FROM Odontograma o WHERE o.imgDer = :imgDer"),
    @NamedQuery(name = "Odontograma.findByActualizacion", query = "SELECT o FROM Odontograma o WHERE o.idActualizacion = :actualizacion")
})
public class Odontograma implements Serializable {

    @Size(max = 100)
    @Column(name = "IMG_FUERA_BOL_PERl")
    private String imgFueraBolPeri;
    @Size(max = 100)
    @Column(name = "IMG_FUERA_END")
    private String imgFueraEnd;
    @Size(max = 100)
    @Column(name = "IMG_FUERA_N_END")
    private String imgFueraNEnd;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 2)
    @Column(name = "DIENTE")
    private String diente;
    @Size(max = 100)
    @Column(name = "IMG_ARRIBA")
    private String imgArriba;
    @Size(max = 100)
    @Column(name = "IMG_ABAJO")
    private String imgAbajo;
    @Size(max = 100)
    @Column(name = "IMG_CENTRO")
    private String imgCentro;
    @Size(max = 100)
    @Column(name = "IMG_IZQ")
    private String imgIzq;
    @Size(max = 100)
    @Column(name = "IMG_DER")
    private String imgDer;
    @JoinColumn(name = "ID_ACTUALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private ActualizacionOdo idActualizacion;

    public Odontograma() {
    }

    public Odontograma(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiente() {
        return diente;
    }

    public void setDiente(String diente) {
        this.diente = diente;
    }

    public String getImgArriba() {
        return imgArriba;
    }

    public void setImgArriba(String imgArriba) {
        this.imgArriba = imgArriba;
    }

    public String getImgAbajo() {
        return imgAbajo;
    }

    public void setImgAbajo(String imgAbajo) {
        this.imgAbajo = imgAbajo;
    }

    public String getImgCentro() {
        return imgCentro;
    }

    public void setImgCentro(String imgCentro) {
        this.imgCentro = imgCentro;
    }

    public String getImgIzq() {
        return imgIzq;
    }

    public void setImgIzq(String imgIzq) {
        this.imgIzq = imgIzq;
    }

    public String getImgDer() {
        return imgDer;
    }

    public void setImgDer(String imgDer) {
        this.imgDer = imgDer;
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
        if (!(object instanceof Odontograma)) {
            return false;
        }
        Odontograma other = (Odontograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.Odontograma[ id=" + id + " ]";
    }

    public String getImgFueraBolPeri() {
        return imgFueraBolPeri;
    }

    public void setImgFueraBolPeri(String imgFueraBolPeri) {
        this.imgFueraBolPeri = imgFueraBolPeri;
    }

    public String getImgFueraEnd() {
        return imgFueraEnd;
    }

    public void setImgFueraEnd(String imgFueraEnd) {
        this.imgFueraEnd = imgFueraEnd;
    }

    public String getImgFueraNEnd() {
        return imgFueraNEnd;
    }

    public void setImgFueraNEnd(String imgFueraNEnd) {
        this.imgFueraNEnd = imgFueraNEnd;
    }
    
}
