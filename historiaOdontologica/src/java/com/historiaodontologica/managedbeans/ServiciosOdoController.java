package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.AbonoOdo;
import com.historiaodontologica.entidades.ServiciosOdo;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.ServiciosOdoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.context.RequestContext;

@Named("serviciosOdoController")
@SessionScoped
public class ServiciosOdoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.ServiciosOdoFacade ejbFacade;

    private List<ServiciosOdo> items = null;
    private ServiciosOdo selected;
    private String busquedaServicioOdo;

    public ServiciosOdoController() {
        this.selected = new ServiciosOdo();

    }

    @PostConstruct
    public void init() {
        this.selected = new ServiciosOdo();
    }

    public ServiciosOdo getSelected() {
        return selected;
    }

    public void setSelected(ServiciosOdo selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ServiciosOdoFacade getFacade() {
        return ejbFacade;
    }

    public ServiciosOdo prepareCreate() {
        selected = new ServiciosOdo();
        initializeEmbeddableKey();
        return selected;
    }

    public void registrarServicio_Odo() {

        RequestContext requestContext = RequestContext.getCurrentInstance();
        ejbFacade.create(selected);
        //requestContext.execute("PF('RegistroExitoso').show()");
        items = ejbFacade.findAll();
        requestContext.update("ServiciosOdoListForm:datalist");
        selected = new ServiciosOdo();
    }

    public void seleccionarServicio_Odo(ServiciosOdo selected) {
        this.selected = selected;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("ServiciosOdoEditForm");
        requestContext.execute("PF('ServiciosOdoEditDialog').show()");

    }

    public void reiniciarServicio_Odo() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.selected = new ServiciosOdo();
    }

    public void ventanaEliminar(ServiciosOdo selected) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.selected = selected;
        requestContext.execute("PF('eliminarServicio').show()");
    }

    public void eliminarServicio() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        if (this.selected != null) {
            if (true) {
                this.ejbFacade.remove(this.selected);
                requestContext.update("ServiciosOdoListForm");
                requestContext.execute("PF('eliminarServicio').hide()");
                requestContext.execute("PF('eliminacionCorrecta').show()");
                this.selected = new ServiciosOdo();
                this.items = getFacade().findAll();
            } else {
                this.selected = new ServiciosOdo();
                requestContext.execute("PF('eliminarServicio').hide()");
                requestContext.execute("PF('noSePuedeEliminar').show()");
            }
        }
    }

    public void buscarServicioOdo() {
        this.items = getFacade().buscarServicioOdoEjb(this.busquedaServicioOdo.toLowerCase());
    }

    public String getBusquedaServicioOdo() {
        return busquedaServicioOdo;
    }

    public void setBusquedaServicioOdo(String busquedaServicioOdo) {
        this.busquedaServicioOdo = busquedaServicioOdo;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleServicios").getString("ServiciosOdoUpdated"));
        this.selected = new ServiciosOdo();
    }

    public List<ServiciosOdo> getItems() {

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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleServicios").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleServicios").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ServiciosOdo getServiciosOdo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ServiciosOdo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ServiciosOdo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ServiciosOdo.class)
    public static class ServiciosOdoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ServiciosOdoController controller = (ServiciosOdoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "serviciosOdoController");
            return controller.getServiciosOdo(getKey(value));
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
            if (object instanceof ServiciosOdo) {
                ServiciosOdo o = (ServiciosOdo) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ServiciosOdo.class.getName()});
                return null;
            }
        }

    }

}
