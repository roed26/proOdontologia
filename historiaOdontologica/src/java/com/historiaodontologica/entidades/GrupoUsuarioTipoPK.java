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
 * @author ROED26
 */
@Embeddable
public class GrupoUsuarioTipoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo")
    private int idTipo;

    public GrupoUsuarioTipoPK() {
    }

    public GrupoUsuarioTipoPK(int idUsuario, int idTipo) {
        this.idUsuario = idUsuario;
        this.idTipo = idTipo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idTipo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuarioTipoPK)) {
            return false;
        }
        GrupoUsuarioTipoPK other = (GrupoUsuarioTipoPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idTipo != other.idTipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.GrupoUsuarioTipoPK[ idUsuario=" + idUsuario + ", idTipo=" + idTipo + " ]";
    }
    
}
