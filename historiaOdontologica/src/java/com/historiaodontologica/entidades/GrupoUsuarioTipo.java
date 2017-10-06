/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "grupo_usuario_tipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoUsuarioTipo.findAll", query = "SELECT g FROM GrupoUsuarioTipo g"),
    @NamedQuery(name = "GrupoUsuarioTipo.findByIdUsuario", query = "SELECT g FROM GrupoUsuarioTipo g WHERE g.grupoUsuarioTipoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "GrupoUsuarioTipo.findByIdTipo", query = "SELECT g FROM GrupoUsuarioTipo g WHERE g.grupoUsuarioTipoPK.idTipo = :idTipo"),
    @NamedQuery(name = "GrupoUsuarioTipo.findByNombreUsuario", query = "SELECT g FROM GrupoUsuarioTipo g WHERE g.nombreUsuario = :nombreUsuario")})
public class GrupoUsuarioTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GrupoUsuarioTipoPK grupoUsuarioTipoPK;
    @Size(max = 20)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @JoinColumn(name = "id_tipo", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TipoUsuario tipoUsuario;
    @JoinColumn(name = "id_usuario", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private UsuariosSistema usuariosSistema;

    public GrupoUsuarioTipo() {
    }

    public GrupoUsuarioTipo(GrupoUsuarioTipoPK grupoUsuarioTipoPK) {
        this.grupoUsuarioTipoPK = grupoUsuarioTipoPK;
    }

    public GrupoUsuarioTipo(int idUsuario, int idTipo) {
        this.grupoUsuarioTipoPK = new GrupoUsuarioTipoPK(idUsuario, idTipo);
    }

    public GrupoUsuarioTipoPK getGrupoUsuarioTipoPK() {
        return grupoUsuarioTipoPK;
    }

    public void setGrupoUsuarioTipoPK(GrupoUsuarioTipoPK grupoUsuarioTipoPK) {
        this.grupoUsuarioTipoPK = grupoUsuarioTipoPK;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public UsuariosSistema getUsuariosSistema() {
        return usuariosSistema;
    }

    public void setUsuariosSistema(UsuariosSistema usuariosSistema) {
        this.usuariosSistema = usuariosSistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupoUsuarioTipoPK != null ? grupoUsuarioTipoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoUsuarioTipo)) {
            return false;
        }
        GrupoUsuarioTipo other = (GrupoUsuarioTipo) object;
        if ((this.grupoUsuarioTipoPK == null && other.grupoUsuarioTipoPK != null) || (this.grupoUsuarioTipoPK != null && !this.grupoUsuarioTipoPK.equals(other.grupoUsuarioTipoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.GrupoUsuarioTipo[ grupoUsuarioTipoPK=" + grupoUsuarioTipoPK + " ]";
    }
    
}
