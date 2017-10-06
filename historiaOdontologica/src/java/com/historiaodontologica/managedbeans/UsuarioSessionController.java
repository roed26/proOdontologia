package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.sessionbeans.GrupoUsuarioTipoFacade;
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import com.historiaodontologica.utilidades.Utilidades;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@SessionScoped
public class UsuarioSessionController implements Serializable {

    @EJB
    private GrupoUsuarioTipoFacade ejbUsuarioTipo;
    @EJB
    private UsuariosSistemaFacade ejbUsuarioSistema;

    private String nombreDeUsuario;
    private String contrasenia;
    private String nombreMostrar;
    private String identificacion;
    private boolean haySesion;
    private boolean errorSesion;

    public UsuarioSessionController() {

    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public void setNombreMostrar(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public boolean isHaySesion() {
        return haySesion;
    }

    public void setHaySesion(boolean haySesion) {
        this.haySesion = haySesion;
    }

    public boolean isErrorSesion() {
        return errorSesion;
    }

    public void setErrorSesion(boolean errorSesion) {
        this.errorSesion = errorSesion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void login() throws IOException, ServletException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            try {
                req.login(this.nombreDeUsuario, this.contrasenia);
                req.getServletContext().log("Autenticacion exitosa");
                haySesion = true;
                this.errorSesion = false;
                if (this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getGrupoUsuarioTipoPK().getIdTipo()== 1) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/historiaOdontologica/faces/usuario/principal.xhtml");
                    identificacion = this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariosSistema().getIdentificacion();
                } else if (this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getGrupoUsuarioTipoPK().getIdTipo()== 2) {
                    //FacesContext.getCurrentInstance().getExternalContext().redirect("/mcd-ami-saa/faces/plantillaOperario/operarioMain.xhtml");
                    //identificacion = this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariosSistema().getIdentificacion();
                }
            } catch (ServletException e) {

                this.errorSesion = true;

            }
        }        
        else if (this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getGrupoUsuarioTipoPK().getIdTipo() == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/historiaOdontologica/faces/usuario/principal.xhtml");
            identificacion = this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariosSistema().getIdentificacion();
        } else if (this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getGrupoUsuarioTipoPK().getIdTipo() == 2) {
            //FacesContext.getCurrentInstance().getExternalContext().redirect("/mcd-ami-saa/faces/plantillaOperario/operarioMain.xhtml");
            identificacion = this.ejbUsuarioTipo.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariosSistema().getIdentificacion();
        }
    }

    public void logout() throws IOException, ServletException {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.logout();
            req.getSession().invalidate();
            fc.getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/historiaOdontologica/");

        } catch (ServletException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED", "Logout failed on backend"));
        }

    }

    public boolean esusuarioSinSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return true;
        }
        return false;
    }

    public boolean esusuarioConSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return false;

        } else {
            /*if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getMuUsuariogrupoPK().getGruId().equals(2))
            {
                return true;
            }*/
            return false;
        }

    }

    public boolean esAdministrador() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return false;

        } else {
            /* if(this.usuarioGrupoEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0).getUsuariogrupoPK().getGruid().equals("admin"))
            {
                return true;
            }*/
            return false;
        }

    }

    public String nombreUsuario() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() == null) {
            return "";
        } else {
            UsuariosSistema usuario = ejbUsuarioSistema.buscarPorNombreDeUsuarioObjeto(req.getUserPrincipal().getName());
            if (usuario == null) {
                return "";
            } else {
                return usuario.getNombres() + " " + usuario.getApellidos();
            }

        }
    }

    public StreamedContent getImagenPorDefecto() {
        return Utilidades.getImagenPorDefecto("foto");
    }
}
