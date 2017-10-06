package com.historiaodontologica.managedbeans;

import com.historiaodontologica.sessionbeans.TipodiagnosticoFacade;
import com.historiaodontologica.entidades.TipoDiagnostico;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;

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

@Named("tipodiagnosticoController")
@SessionScoped
public class TipoDiagnosticoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.TipodiagnosticoFacade ejbFacade;
    private List<TipoDiagnostico> items = null;
    private TipoDiagnostico selected;

    public TipoDiagnosticoController() {
    }

    public TipoDiagnostico getSelected() {
        return selected;
    }

    public void setSelected(TipoDiagnostico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private TipodiagnosticoFacade getFacade() {
        return ejbFacade;
    }

    public TipoDiagnostico prepareCreate() {
        selected = new TipoDiagnostico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleTipoDiagnostico").getString("TipodiagnosticoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleTipoDiagnostico").getString("TipodiagnosticoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleTipoDiagnostico").getString("TipodiagnosticoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<TipoDiagnostico> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoDiagnostico").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleTipoDiagnostico").getString("PersistenceErrorOccured"));
            }
        }
    }

    public TipoDiagnostico getTipodiagnostico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<TipoDiagnostico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<TipoDiagnostico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = TipoDiagnostico.class)
    public static class TipodiagnosticoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoDiagnosticoController controller = (TipoDiagnosticoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipodiagnosticoController");
            return controller.getTipodiagnostico(getKey(value));
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
            if (object instanceof TipoDiagnostico) {
                TipoDiagnostico o = (TipoDiagnostico) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), TipoDiagnostico.class.getName()});
                return null;
            }
        }

    }

}
