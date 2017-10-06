package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.GrupoUsuarioTipo;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.GrupoUsuarioTipoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("grupoUsuarioTipoController")
@SessionScoped
public class GrupoUsuarioTipoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.GrupoUsuarioTipoFacade ejbFacade;
    private List<GrupoUsuarioTipo> items = null;
    private GrupoUsuarioTipo selected;

    public GrupoUsuarioTipoController() {
    }

    public GrupoUsuarioTipo getSelected() {
        return selected;
    }

    public void setSelected(GrupoUsuarioTipo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getGrupoUsuarioTipoPK().setIdTipo(selected.getTipoUsuario().getId());
        selected.getGrupoUsuarioTipoPK().setIdUsuario(selected.getUsuariosSistema().getId());
    }

    protected void initializeEmbeddableKey() {
        selected.setGrupoUsuarioTipoPK(new com.historiaodontologica.entidades.GrupoUsuarioTipoPK());
    }

    private GrupoUsuarioTipoFacade getFacade() {
        return ejbFacade;
    }

    public GrupoUsuarioTipo prepareCreate() {
        selected = new GrupoUsuarioTipo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTipoUsuario").getString("GrupoUsuarioTipoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTipoUsuario").getString("GrupoUsuarioTipoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTipoUsuario").getString("GrupoUsuarioTipoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<GrupoUsuarioTipo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoUsuario").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoUsuario").getString("PersistenceErrorOccured"));
            }
        }
    }

    public GrupoUsuarioTipo getGrupoUsuarioTipo(com.historiaodontologica.entidades.GrupoUsuarioTipoPK id) {
        return getFacade().find(id);
    }

    public List<GrupoUsuarioTipo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<GrupoUsuarioTipo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = GrupoUsuarioTipo.class)
    public static class GrupoUsuarioTipoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GrupoUsuarioTipoController controller = (GrupoUsuarioTipoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grupoUsuarioTipoController");
            return controller.getGrupoUsuarioTipo(getKey(value));
        }

        com.historiaodontologica.entidades.GrupoUsuarioTipoPK getKey(String value) {
            com.historiaodontologica.entidades.GrupoUsuarioTipoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.historiaodontologica.entidades.GrupoUsuarioTipoPK();
            key.setIdUsuario(Integer.parseInt(values[0]));
            key.setIdTipo(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.historiaodontologica.entidades.GrupoUsuarioTipoPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdUsuario());
            sb.append(SEPARATOR);
            sb.append(value.getIdTipo());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GrupoUsuarioTipo) {
                GrupoUsuarioTipo o = (GrupoUsuarioTipo) object;
                return getStringKey(o.getGrupoUsuarioTipoPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), GrupoUsuarioTipo.class.getName()});
                return null;
            }
        }

    }

}
