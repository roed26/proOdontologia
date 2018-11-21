package com.historiaodontologica.managedbeans;

import com.historiaodontologica.cifrado.Cifrar;
import com.historiaodontologica.entidades.GrupoUsuarioTipo;
import com.historiaodontologica.entidades.GrupoUsuarioTipoPK;
import com.historiaodontologica.entidades.TipoUsuario;
import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.GrupoUsuarioTipoFacade;
import com.historiaodontologica.sessionbeans.TipoUsuarioFacade;
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import com.historiaodontologica.validadores.ValidarEdicionUsuarios;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

@Named("usuariosSistemaController")
@SessionScoped
public class UsuariosSistemaController implements Serializable {

    @EJB
    private UsuariosSistemaFacade usuarioEJB;
    @EJB
    private GrupoUsuarioTipoFacade ejbGrupoUsuarioTipo;
    @EJB
    private TipoUsuarioFacade ejbTipoUsuario;

    private List<UsuariosSistema> items = null;
    private UsuariosSistema usuarioSistema;
    private TipoUsuario tipoUsuario;

    private boolean mostrarContrasena;
    
    private String contrasena;
    private String confirmarContrasena;
    private String telefono;
    private String extension;
    private String celular;
    private String datoBusqueda;

    public UsuariosSistemaController() {
        mostrarContrasena = true;
    }

    @PostConstruct
    public void init() {
        inicirObjetoUsuraio();

    }

    public UsuariosSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuariosSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
        this.tipoUsuario =this.ejbGrupoUsuarioTipo.buscarPorNombreUsuario(usuarioSistema.getNombreusuario()).get(0).getTipoUsuario();
    }

    public void seleccionarUsuario(UsuariosSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
        this.tipoUsuario = ejbTipoUsuario.find(this.ejbGrupoUsuarioTipo.buscarPorNombreUsuario(usuarioSistema.getNombreusuario()).get(0).getTipoUsuario());
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    public void inicirObjetoUsuraio() {
        usuarioSistema = new UsuariosSistema();
        usuarioSistema.setEstado(1);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDatoBusqueda() {
        return datoBusqueda;
    }

    public void setDatoBusqueda(String datoBusqueda) {
        this.datoBusqueda = datoBusqueda;
    }

    public boolean isMostrarContrasena() {
        return mostrarContrasena;
    }

    public void setMostrarContrasena(boolean mostrarContrasena) {
        this.mostrarContrasena = mostrarContrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfirmarContrasena() {
        return confirmarContrasena;
    }

    public void setConfirmarContrasena(String confirmarContrasena) {
        this.confirmarContrasena = confirmarContrasena;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosSistemaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void registrarUsuario() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        usuarioSistema.setContrasena(Cifrar.sha256(usuarioSistema.getContrasena()));
    
        usuarioEJB.create(usuarioSistema);

        GrupoUsuarioTipo gut = new GrupoUsuarioTipo();
        GrupoUsuarioTipoPK gutPk = new GrupoUsuarioTipoPK();
        gutPk.setIdTipo(tipoUsuario.getId());
        gutPk.setIdUsuario(usuarioSistema.getId());

        gut.setGrupoUsuarioTipoPK(gutPk);
        gut.setNombreUsuario(usuarioSistema.getNombreusuario());
        ejbGrupoUsuarioTipo.create(gut);

        items = usuarioEJB.findAll();
        inicirObjetoUsuraio();
        requestContext.execute("PF('RegistroExitoso').show()");

    }

    public void buscarUsuario() {
        this.items = usuarioEJB.buscarUsuarioEjb(this.datoBusqueda.toLowerCase());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosSistemaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosSistemaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            usuarioSistema = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuariosSistema> getItems() {
        if (items == null) {
            items = usuarioEJB.findAll();
        }
        return items;
    }

    public void mostrarModificarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = false;
        requestContext.update("UsuariosSistemaEditForm");
    }

    public void cancelarActualizarContrasena() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.mostrarContrasena = true;
        this.contrasena = "";
        this.confirmarContrasena = "";
        requestContext.update("UsuariosSistemaEditForm");
    }

    public void cambiarContrasena() {
        ValidarEdicionUsuarios validarEdicionUsuario = new ValidarEdicionUsuarios();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (validarEdicionUsuario.validarContrasenaConConfirmacion(this.contrasena, this.confirmarContrasena)) {

            this.usuarioSistema.setContrasena(Cifrar.sha256(this.contrasena));

            this.usuarioEJB.edit(this.usuarioSistema);

            this.mostrarContrasena = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info. Se cambio la contraseña correctamente.", ""));

        }
        requestContext.update("UsuariosSistemaEditForm");

    }

    public void editarUsuarioSistema() {

        String cargo = "";
        usuarioSistema.setContrasena(Cifrar.sha256(usuarioSistema.getContrasena()));
        cargo = tipoUsuario.getNombre();

        usuarioEJB.edit(usuarioSistema);

        GrupoUsuarioTipo gut = new GrupoUsuarioTipo();
        GrupoUsuarioTipoPK gutPk = new GrupoUsuarioTipoPK();
        gutPk.setIdTipo(tipoUsuario.getId());
        gutPk.setIdUsuario(usuarioSistema.getId());

        gut.setGrupoUsuarioTipoPK(gutPk);
        gut.setNombreUsuario(usuarioSistema.getNombreusuario());
        ejbGrupoUsuarioTipo.edit(gut);

        usuarioSistema = new UsuariosSistema();

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ActualizacionExitosa').show()");

        items = usuarioEJB.findAll();
    }


    private void iniciarVariables() {
        this.mostrarContrasena = true;

    }
public void inicializar(){
 this.usuarioSistema= new UsuariosSistema();
}
    private void persist(PersistAction persistAction, String successMessage) {
        if (usuarioSistema != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    usuarioEJB.edit(usuarioSistema);
                } else {
                    usuarioEJB.remove(usuarioSistema);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UsuariosSistema getUsuariosSistema(java.lang.Integer id) {
        return usuarioEJB.find(id);
    }

    public List<UsuariosSistema> getItemsAvailableSelectMany() {
        return usuarioEJB.findAll();
    }

    public List<UsuariosSistema> getItemsAvailableSelectOne() {
        return usuarioEJB.findAll();
    }

    @FacesConverter(forClass = UsuariosSistema.class)
    public static class UsuariosSistemaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosSistemaController controller = (UsuariosSistemaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosSistemaController");
            return controller.getUsuariosSistema(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UsuariosSistema) {
                UsuariosSistema o = (UsuariosSistema) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuariosSistema.class.getName()});
                return null;
            }
        }

    }

}
