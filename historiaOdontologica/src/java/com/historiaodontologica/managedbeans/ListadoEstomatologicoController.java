package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.ListadoEstomatologico;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.ListadoEstomatologicoFacade;

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

@Named("listadoEstomatologicoController")
@SessionScoped
public class ListadoEstomatologicoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.ListadoEstomatologicoFacade ejbFacade;
    private List<ListadoEstomatologico> items = null;
    private ListadoEstomatologico selected;

    public ListadoEstomatologicoController() {
    }

    public ListadoEstomatologico getSelected() {
        return selected;
    }

    public void setSelected(ListadoEstomatologico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ListadoEstomatologicoFacade getFacade() {
        return ejbFacade;
    }

    public ListadoEstomatologico prepareCreate() {
        selected = new ListadoEstomatologico();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleEstomatologico").getString("ListadoEstomatologicoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleEstomatologico").getString("ListadoEstomatologicoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleEstomatologico").getString("ListadoEstomatologicoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ListadoEstomatologico> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEstomatologico").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEstomatologico").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ListadoEstomatologico getListadoEstomatologico(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ListadoEstomatologico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ListadoEstomatologico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ListadoEstomatologico.class)
    public static class ListadoEstomatologicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ListadoEstomatologicoController controller = (ListadoEstomatologicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "listadoEstomatologicoController");
            return controller.getListadoEstomatologico(getKey(value));
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
            if (object instanceof ListadoEstomatologico) {
                ListadoEstomatologico o = (ListadoEstomatologico) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ListadoEstomatologico.class.getName()});
                return null;
            }
        }

    }

}
