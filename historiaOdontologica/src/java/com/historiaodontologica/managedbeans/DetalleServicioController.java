package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.DetalleServicioFacade;

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

@Named("detalleServicioController")
@SessionScoped
public class DetalleServicioController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.DetalleServicioFacade ejbFacade;
    private List<DetalleServicio> items = null;
    private DetalleServicio selected;

    public DetalleServicioController() {
    }

    public DetalleServicio getSelected() {
        return selected;
    }

    public void setSelected(DetalleServicio selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getDetalleServicioPK().setIdPresupuesto(selected.getPresupuesto().getIdPresupuesto());
    }

    protected void initializeEmbeddableKey() {
        selected.setDetalleServicioPK(new com.historiaodontologica.entidades.DetalleServicioPK());
    }

    private DetalleServicioFacade getFacade() {
        return ejbFacade;
    }

    public DetalleServicio prepareCreate() {
        selected = new DetalleServicio();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePresupuesto").getString("DetalleServicioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePresupuesto").getString("DetalleServicioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePresupuesto").getString("DetalleServicioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetalleServicio> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePresupuesto").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundlePresupuesto").getString("PersistenceErrorOccured"));
            }
        }
    }

    public DetalleServicio getDetalleServicio(com.historiaodontologica.entidades.DetalleServicioPK id) {
        return getFacade().find(id);
    }

    public List<DetalleServicio> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetalleServicio> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = DetalleServicio.class)
    public static class DetalleServicioControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleServicioController controller = (DetalleServicioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleServicioController");
            return controller.getDetalleServicio(getKey(value));
        }

        com.historiaodontologica.entidades.DetalleServicioPK getKey(String value) {
            com.historiaodontologica.entidades.DetalleServicioPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.historiaodontologica.entidades.DetalleServicioPK();
            key.setIdDetalleServicio(Integer.parseInt(values[0]));
            key.setIdPresupuesto(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.historiaodontologica.entidades.DetalleServicioPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getIdDetalleServicio());
            sb.append(SEPARATOR);
            sb.append(value.getIdPresupuesto());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetalleServicio) {
                DetalleServicio o = (DetalleServicio) object;
                return getStringKey(o.getDetalleServicioPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetalleServicio.class.getName()});
                return null;
            }
        }

    }

}
