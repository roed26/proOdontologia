package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.Diagnosticocie10Odo;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.Diagnosticocie10OdoFacade;

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

@Named("diagnosticocie10OdoController")
@SessionScoped
public class Diagnosticocie10OdoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.Diagnosticocie10OdoFacade ejbFacade;
    private List<Diagnosticocie10Odo> items = null;
    private Diagnosticocie10Odo selected;

    private String busquedaDiagnostico;

    public Diagnosticocie10OdoController() {
    }

    public Diagnosticocie10Odo getSelected() {
        return selected;
    }

    public void setSelected(Diagnosticocie10Odo selected) {
        this.selected = selected;
    }

    public String getBusquedaDiagnostico() {
        return busquedaDiagnostico;
    }

    public void setBusquedaDiagnostico(String busquedaDiagnostico) {
        this.busquedaDiagnostico = busquedaDiagnostico;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private Diagnosticocie10OdoFacade getFacade() {
        return ejbFacade;
    }

    public Diagnosticocie10Odo prepareCreate() {
        selected = new Diagnosticocie10Odo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleDiagnostico10").getString("Diagnosticocie10OdoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleDiagnostico10").getString("Diagnosticocie10OdoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleDiagnostico10").getString("Diagnosticocie10OdoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Diagnosticocie10Odo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void buscarPorNombreId() {
        this.items = getFacade().buscarPorNombreId(this.busquedaDiagnostico.toLowerCase());
        this.busquedaDiagnostico = "";
    }

    public void reiniciarCampoBusqueda() {
        this.busquedaDiagnostico = "";
        this.items = ejbFacade.findAll();

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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleDiagnostico10").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleDiagnostico10").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Diagnosticocie10Odo getDiagnosticocie10Odo(java.lang.String id) {
        return getFacade().find(id);
    }

    public List<Diagnosticocie10Odo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Diagnosticocie10Odo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Diagnosticocie10Odo.class)
    public static class Diagnosticocie10OdoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Diagnosticocie10OdoController controller = (Diagnosticocie10OdoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "diagnosticocie10OdoController");
            return controller.getDiagnosticocie10Odo(getKey(value));
        }

        java.lang.String getKey(String value) {
            java.lang.String key;
            key = value;
            return key;
        }

        String getStringKey(java.lang.String value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Diagnosticocie10Odo) {
                Diagnosticocie10Odo o = (Diagnosticocie10Odo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Diagnosticocie10Odo.class.getName()});
                return null;
            }
        }

    }

}
