package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.FechaContabilidad;
import com.historiaodontologica.clases.ReportesMedicos;
import com.historiaodontologica.entidades.Ingreso;
import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.IngresoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

@Named("ingresoController")
@SessionScoped
public class IngresoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.IngresoFacade ejbFacade;
    private List<Ingreso> items = null;
    private Ingreso selected;
    private ReportesMedicos reportesMedicos;

    public IngresoController() {
        reportesMedicos = new ReportesMedicos();
    }

    public ReportesMedicos getReportesMedicos() {
        return reportesMedicos;
    }

    public void setReportesMedicos(ReportesMedicos reportesMedicos) {
        this.reportesMedicos = reportesMedicos;
    }

    public Date getFechadesde() {
        return reportesMedicos.getFechadesde();
    }

    public void setFechadesde(Date fechadesde) {
        reportesMedicos.setFechadesde(fechadesde);
    }

    public Date getFechahasta() {
        return reportesMedicos.getFechahasta();
    }

    public void setFechahasta(Date fechahasta) {
        reportesMedicos.setFechahasta(fechahasta);
    }

    public Ingreso getSelected() {
        return selected;
    }

    public void setSelected(Ingreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IngresoFacade getFacade() {
        return ejbFacade;
    }

    public Ingreso prepareCreate() {
        selected = new Ingreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("IngresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void registrarIngresos() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        ejbFacade.create(selected);
        items = ejbFacade.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El ingreso se registro con exito."));
        requestContext.execute("PF('IngresoCreateDialog').hide()");
        requestContext.execute("PF('mensajeRegistroExitoso').show()");

        selected = new Ingreso();
    }

    public List<Ingreso> getItems() {
        if (items == null || getFechadesde() == null || getFechahasta() == null) {
            items = getFacade().findAll();
        } else {
            prueba();
        }
        return items;
    }

    public void filtrar() {
//        prueba();
        if (getFechadesde().after(getFechahasta())) {
            FacesContext.getCurrentInstance().addMessage("IngresoListForm:fechadesde", new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Rango de fechas no permitido, la fecha DESDE no debe ser mayor a la fecha HASTA"));
        }
    }

    public void prueba() {
        items = getFacade().listadoIngresoFecha(reportesMedicos.getFechadesde(), reportesMedicos.getFechahasta());
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("IngresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleEgresoIngreso").getString("IngresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public int contarCaracteres() {
        int cont = 0;
        if (selected.getDescripcionIngreso() != null) {
            cont = selected.getDescripcionIngreso().length();
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

    public Ingreso getIngreso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ingreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ingreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ingreso.class)
    public static class IngresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IngresoController controller = (IngresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ingresoController");
            return controller.getIngreso(getKey(value));
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
            if (object instanceof Ingreso) {
                Ingreso o = (Ingreso) object;
                return getStringKey(o.getIdIngreso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ingreso.class.getName()});
                return null;
            }
        }

    }

}
