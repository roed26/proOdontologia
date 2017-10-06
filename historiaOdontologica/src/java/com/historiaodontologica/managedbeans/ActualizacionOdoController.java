package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;

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

@Named("actualizacionOdoController")
@SessionScoped
public class ActualizacionOdoController implements Serializable {

    @EJB
    private ActualizacionOdoFacade ejbFacade;
    private List<ActualizacionOdo> items = null;
    private ActualizacionOdo selected;

    public ActualizacionOdoController() {
        selected= new ActualizacionOdo();
    }

    public ActualizacionOdo getSelected() {
        return selected;
    }

    public void setSelected(ActualizacionOdo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ActualizacionOdoFacade getFacade() {
        return ejbFacade;
    }

    public ActualizacionOdo prepareCreate() {
        selected = new ActualizacionOdo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleActualizacionOdo").getString("ActualizacionOdoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleActualizacionOdo").getString("ActualizacionOdoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleActualizacionOdo").getString("ActualizacionOdoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ActualizacionOdo> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleActualizacionOdo").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleActualizacionOdo").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ActualizacionOdo getActualizacionOdo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ActualizacionOdo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ActualizacionOdo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ActualizacionOdo.class)
    public static class ActualizacionOdoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ActualizacionOdoController controller = (ActualizacionOdoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "actualizacionOdoController");
            return controller.getActualizacionOdo(getKey(value));
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
            if (object instanceof ActualizacionOdo) {
                ActualizacionOdo o = (ActualizacionOdo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ActualizacionOdo.class.getName()});
                return null;
            }
        }

    }

}
