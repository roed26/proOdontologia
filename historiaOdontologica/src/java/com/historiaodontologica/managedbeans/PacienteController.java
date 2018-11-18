package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.entidades.TipoIdentificacion;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.PacienteFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

@Named("pacienteController")
@SessionScoped
public class PacienteController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.PacienteFacade ejbFacade;
    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    private List<Paciente> items = null;
    private Paciente selected;
    private String busquedaPaciente;
    private TipoIdentificacion tipoIdentificacion;

    public PacienteController() {

    }

    @PostConstruct
    public void init() {
        limpiarCampos();
        items = ejbFacade.findAll();
    }

    public Paciente getSelected() {
        return selected;
    }

    public void setSelected(Paciente selected) {
        this.selected = selected;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {

    }

    public void iniciarPacienteVer() {
        selected = new Paciente();
        selected.setSexo('F');
        selected.setEstado("1");

    }

    private PacienteFacade getFacade() {
        return ejbFacade;
    }

    public String getBusquedaPaciente() {
        return busquedaPaciente;
    }

    public void setBusquedaPaciente(String busquedaPaciente) {
        this.busquedaPaciente = busquedaPaciente;
    }

    public Paciente prepareCreate() {
        selected = new Paciente();
        selected.setEstado("1");
        selected.setTipoIdentificacion(tipoIdentificacion);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PacienteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PacienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PacienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Paciente> getItems() {

        items = getFacade().findAll();

        return items;
    }

    public void buscarPaciente() {
        this.items = getFacade().buscarPacienteEjb(this.busquedaPaciente.toLowerCase());

    }

    public List<Paciente> getListaPacientesActivos() {
        return items;
    }
    
    public List<Paciente> getListaPacientesActivosEvolucion() {
        List<Paciente> lista = new ArrayList<>();

        items = ejbFacade.findAll();

        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBoolActivo(items.get(i).getId());
            if (existeHistoria == true) {
                lista.add(items.get(i));
            }
        }
        return lista;
    }
    

    public List<Paciente> getListaPacientesActivosAtencion() {
        List<Paciente> lista = new ArrayList<>();

        items = ejbFacade.findAll();

        for (int i = 0; i < items.size(); i++) {
            boolean existeHistoria = ejbActualizacionOdo.buscarPorPacienteBool(items.get(i).getId());
            if (items.get(i).getEstado().equalsIgnoreCase("1") && existeHistoria != true) {
                lista.add(items.get(i));
            }
        }
        return lista;
    }

    public void cargarPacientes() {
        items = ejbFacade.findAll();
    }

    //registro de un nuevo paciente 
    public void registrarPaciente() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        selected.setTipoIdentificacion(tipoIdentificacion);
        asignarFechaApertura();
        ejbFacade.create(selected);
        items = ejbFacade.findAll();
        requestContext.execute("PF('RegistroExitoso').show()");

        selected = new Paciente();
        selected.setEstado("1");

    }

    //Nuevos cambios 
    public void seleccionarPacienteEdicion(Paciente paciente) {
        this.selected = paciente;
        this.tipoIdentificacion = paciente.getTipoIdentificacion();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteEditForm");
        requestContext.execute("PF('seleccionPacienteDialog').show()");
    }

    public void editarPaciente() {
        selected.setTipoIdentificacion(tipoIdentificacion);
        ejbFacade.edit(selected);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ActualizacionExitosa').show()");

        selected = new Paciente();
        selected.setEstado("1");
    }

    public void limpiarCampos() {
        selected = new Paciente();
        selected.setSexo('F');
        selected.setEstado("1");
    }

    public void seleccionar_CargarPaciente(Paciente selected) {
        this.selected = selected;
        this.tipoIdentificacion = selected.getTipoIdentificacion();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteEditForm");
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

    }

    public void seleccionarVer(Paciente selected) {
        this.selected = selected;
        this.tipoIdentificacion = selected.getTipoIdentificacion();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteViewForm");
        requestContext.execute("PF('seleccionPacienteDialogView').show()");

    }

    public void limpiarcamposformulario() {
        this.limpiarCampos();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteEditForm");
        requestContext.execute("PF('ActualizacionExitosa').hide()");
    }

    private void asignarFechaApertura() {
        GregorianCalendar c = new GregorianCalendar();
        selected.setFechaRegistro(c.getTime());
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Paciente getPaciente(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Paciente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Paciente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Paciente.class)
    public static class PacienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PacienteController controller = (PacienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pacienteController");
            return controller.getPaciente(getKey(value));
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
            if (object instanceof Paciente) {
                Paciente o = (Paciente) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Paciente.class.getName()});
                return null;
            }
        }

    }

}
