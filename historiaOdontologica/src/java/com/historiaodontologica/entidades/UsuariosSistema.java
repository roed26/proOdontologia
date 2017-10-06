/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ROED26
 */
@Entity
@Table(name = "usuarios_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosSistema.findAll", query = "SELECT u FROM UsuariosSistema u"),
    @NamedQuery(name = "UsuariosSistema.findById", query = "SELECT u FROM UsuariosSistema u WHERE u.id = :id"),
    @NamedQuery(name = "UsuariosSistema.findByIdentificacion", query = "SELECT u FROM UsuariosSistema u WHERE u.identificacion = :identificacion"),
    @NamedQuery(name = "UsuariosSistema.findByNombres", query = "SELECT u FROM UsuariosSistema u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "UsuariosSistema.findByApellidos", query = "SELECT u FROM UsuariosSistema u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "UsuariosSistema.findByNombreusuario", query = "SELECT u FROM UsuariosSistema u WHERE u.nombreusuario = :nombreusuario"),
    @NamedQuery(name = "UsuariosSistema.findByContrasena", query = "SELECT u FROM UsuariosSistema u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "UsuariosSistema.findByEstado", query = "SELECT u FROM UsuariosSistema u WHERE u.estado = :estado"),
    @NamedQuery(name = "UsuariosSistema.findByTelefono", query = "SELECT u FROM UsuariosSistema u WHERE u.telefono = :telefono")})
public class UsuariosSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NOMBREUSUARIO")
    private String nombreusuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "CONTRASENA")
    private String contrasena;
    @Column(name = "ESTADO")
    private Integer estado;
    @Size(max = 10)
    @Column(name = "TELEFONO")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuariosSistema")
    private List<GrupoUsuarioTipo> grupoUsuarioTipoList;

    public UsuariosSistema() {
    }

    public UsuariosSistema(Integer id) {
        this.id = id;
    }

    public UsuariosSistema(Integer id, String identificacion, String nombres, String apellidos, String nombreusuario, String contrasena) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public List<GrupoUsuarioTipo> getGrupoUsuarioTipoList() {
        return grupoUsuarioTipoList;
    }

    public void setGrupoUsuarioTipoList(List<GrupoUsuarioTipo> grupoUsuarioTipoList) {
        this.grupoUsuarioTipoList = grupoUsuarioTipoList;
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
        if (!(object instanceof UsuariosSistema)) {
            return false;
        }
        UsuariosSistema other = (UsuariosSistema) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaodontologica.entidades.UsuariosSistema[ id=" + id + " ]";
    }
    
}
