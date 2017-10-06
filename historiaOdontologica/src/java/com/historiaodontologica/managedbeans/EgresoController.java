package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.Egreso;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.EgresoFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

@Named("egresoController")
@SessionScoped
public class EgresoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.EgresoFacade ejbFacade;
    private List<Egreso> items = null;
    private Egreso selected;

    public EgresoController() {
    }

    public Egreso getSelected() {
        return selected;
    }

    public void setSelected(Egreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EgresoFacade getFacade() {
        return ejbFacade;
    }

    public Egreso prepareCreate() {
        selected = new Egreso();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void registrarEgresos(){
        RequestContext requestContext = RequestContext.getCurrentInstance();
        ejbFacade.create(selected);
        items = ejbFacade.findAll();
        requestContext.execute("PF('EgresoCreateDialog').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El egreso se registro con exito."));
        requestContext.execute("PF('mensajeRegistroExitoso').show()");
        
        selected = new Egreso();
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("EgresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("EgresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Egreso> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public int contarCaracteres(){
        int cont=0;
        if(selected.getDescripcionEgreso() != null){
            cont = selected.getDescripcionEgreso().length();
        }
        return cont;   
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Egreso getEgreso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Egreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Egreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Egreso.class)
    public static class EgresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EgresoController controller = (EgresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "egresoController");
            return controller.getEgreso(getKey(value));
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
            if (object instanceof Egreso) {
                Egreso o = (Egreso) object;
                return getStringKey(o.getIdEgreso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Egreso.class.getName()});
                return null;
            }
        }

    }

}
