package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.AbonoOdo;
import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.entidades.Ingreso;
import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.entidades.Presupuesto;
import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.AbonoOdoFacade;
import com.historiaodontologica.sessionbeans.DetalleServicioFacade;
import com.historiaodontologica.sessionbeans.IngresoFacade;
import com.historiaodontologica.sessionbeans.PresupuestoFacade;
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
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
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

@Named("abonoOdoController")
@SessionScoped
public class AbonoOdoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.AbonoOdoFacade ejbFacade;
    @EJB
    private PresupuestoFacade ejbPresupuesto;
    @EJB
    private DetalleServicioFacade ejbDetalle;
    @EJB
    private UsuariosSistemaFacade ejbUsuario;
    @EJB
    private IngresoFacade ejbIngreso;

    private List<AbonoOdo> items = null;
    private List<DetalleServicio> itemsDetalle = null;
    private AbonoOdo selected;
    private Ingreso selectedIngreso;
    private Presupuesto selectedPresupuesto;
    private UsuariosSistema selectedUsuariosSistema;
    private DetalleServicio selectetDetalleServicio;
    private Paciente selectedPaciente;
    private boolean pacienteSeleccionado;
    private float TotalPresupuesto;
    private float TotalAbonado;
    private float SaldoFinal;
    private Date today;

    public AbonoOdoController() {
        this.pacienteSeleccionado = false;
        this.TotalPresupuesto = 0;
        this.TotalAbonado = 0;
        this.SaldoFinal = 0;
        this.selected = new AbonoOdo();
    }

    public boolean isPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public Date getToday() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public void setPacienteSeleccionado(boolean pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }

    public float getSaldoFinal() {
        return SaldoFinal;
    }

    public void setSaldoFinal(float SaldoFinal) {
        this.SaldoFinal = SaldoFinal;
    }

    public float getTotalAbonado() {
        return TotalAbonado;
    }

    public void setTotalAbonado(float TotalAbonado) {
        this.TotalAbonado = TotalAbonado;
    }

    public AbonoOdo getSelected() {
        return selected;
    }

    public void setSelected(AbonoOdo selected) {
        this.selected = selected;
    }

    public float getTotalPresupuesto() {
        return TotalPresupuesto;
    }

    public void setTotalPresupuesto(float TotalPresupuesto) {
        this.TotalPresupuesto = TotalPresupuesto;
    }

    public UsuariosSistema getSelectedUsuariosSistema() {
        return selectedUsuariosSistema;
    }

    public void setSelectedUsuariosSistema(UsuariosSistema selectedUsuariosSistema) {
        this.selectedUsuariosSistema = selectedUsuariosSistema;
    }

    public List<DetalleServicio> getItemsDetalle() {
        return itemsDetalle;
    }

    public void setItemsDetalle(List<DetalleServicio> itemsDetalle) {
        this.itemsDetalle = itemsDetalle;
    }

    public Presupuesto getSelectedPresupuesto() {
        return selectedPresupuesto;
    }

    public void setSelectedPresupuesto(Presupuesto selectedPresupuesto) {
        this.selectedPresupuesto = selectedPresupuesto;
    }

    public DetalleServicio getSelectetDetalleServicio() {
        return selectetDetalleServicio;
    }

    public void setSelectetDetalleServicio(DetalleServicio selectetDetalleServicio) {
        this.selectetDetalleServicio = selectetDetalleServicio;
    }

    public Paciente getSelectedPaciente() {
        return selectedPaciente;
    }

    public void setSelectedPaciente(Paciente selectedPaciente) {
        this.selectedPaciente = selectedPaciente;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AbonoOdoFacade getFacade() {
        return ejbFacade;
    }

    public Ingreso getSelectedIngreso() {
        return selectedIngreso;
    }

    public void setSelectedIngreso(Ingreso selectedIngreso) {
        this.selectedIngreso = selectedIngreso;
    }

    public UsuariosSistemaFacade getEjbUsuario() {
        return ejbUsuario;
    }

    public void setEjbUsuario(UsuariosSistemaFacade ejbUsuario) {
        this.ejbUsuario = ejbUsuario;
    }

    public void iniciarAbono() {
        selected = new AbonoOdo();
        TotalPresupuesto = 0;
        TotalAbonado = 0;
        SaldoFinal = 0;
        selectedPaciente = new Paciente();
    }

    public AbonoOdo prepareCreate() {
        selected = new AbonoOdo();
        initializeEmbeddableKey();
        return selected;
    }

    public void cargarAbonos() {
        items = ejbFacade.findAll();
    }

    public int contarCaracteres() {
        int cont = 0;
        if (selected.getDescripcion() != null) {
            cont = selected.getDescripcion().length();
        }
        return cont;
    }

    public void registrarAbono() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        selected.setIdPresupuesto(selectedPresupuesto);
        selected.setIdUsuario(usuarioDeLaSesion());
        ejbFacade.create(selected);
        cargarAbonos();
        selectedIngreso = new Ingreso();
        selectedIngreso.setFechaIngreso(selected.getFecha());
        selectedIngreso.setDescripcionIngreso(selected.getDescripcion());
        selectedIngreso.setValorIngreso(selected.getValor());
        ejbIngreso.create(selectedIngreso);
        //requestContext.execute("PF('RegistroExitoso').show()");
        this.selectedPresupuesto = ejbPresupuesto.buscarPorPaciente(selectedPaciente);
        itemsDetalle = ejbDetalle.buscarPorPresupuestoDetalle(selectedPresupuesto);
        items = ejbFacade.buscarPorPresupustoAbono(selectedPresupuesto);
        estadoPagos();
        requestContext.update("AbonoOdoListForm");

        selected = new AbonoOdo();
    }

    public void estadoPagos() {
        TotalPresupuesto = 0;
        TotalAbonado = 0;
        SaldoFinal = 0;
        for (int i = 0; i < itemsDetalle.size(); i++) {
            TotalPresupuesto = TotalPresupuesto + itemsDetalle.get(i).getPrecio();
        }

        for (int i = 0; i < items.size(); i++) {
            TotalAbonado = TotalAbonado + items.get(i).getValor();
        }

        if (TotalPresupuesto < TotalAbonado) {
            SaldoFinal = 0;
        } else {
            SaldoFinal = TotalPresupuesto - TotalAbonado;
        }
    }

    public void editarAbono() {
        ejbFacade.edit(selected);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('ActualizacionExitosa').show()");
        estadoPagos();
        selected = new AbonoOdo();
    }

    private UsuariosSistema usuarioDeLaSesion() {
        UsuariosSistema usuario = new UsuariosSistema();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            usuario = this.ejbUsuario.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
        return usuario;
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundleAbonos").getString("AbonoOdoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundleAbonos").getString("AbonoOdoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AbonoOdo> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void seleccionarPaciente(Paciente paciente) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.selectedPaciente = paciente;
        this.selectedPresupuesto = ejbPresupuesto.buscarPorPaciente(selectedPaciente);
        itemsDetalle = ejbDetalle.buscarPorPresupuestoDetalle(selectedPresupuesto);
        items = ejbFacade.buscarPorPresupustoAbono(selectedPresupuesto);
        pacienteSeleccionado = true;
        requestContext.update("informacionPaciente");
        requestContext.update("PresupuestoListForm");
        requestContext.update("AbonoOdoListForm");
        requestContext.update("EstadoDePagos");

        for (int i = 0; i < itemsDetalle.size(); i++) {
            TotalPresupuesto = TotalPresupuesto + itemsDetalle.get(i).getPrecio();
        }

        for (int i = 0; i < items.size(); i++) {
            TotalAbonado = TotalAbonado + items.get(i).getValor();
        }

        if (TotalPresupuesto < TotalAbonado) {
            SaldoFinal = 0;
        } else {
            SaldoFinal = TotalPresupuesto - TotalAbonado;
        }
    }

    public void seleccionar_CargarAbono(AbonoOdo selected) {
        this.selected = selected;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("AbonoOdoEditForm");
        requestContext.execute("PF('AbonoOdoEditDialog').show()");

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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAbonos").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/BundleAbonos").getString("PersistenceErrorOccured"));
            }
        }
    }

    public AbonoOdo getAbonoOdo(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AbonoOdo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AbonoOdo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AbonoOdo.class)
    public static class AbonoOdoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AbonoOdoController controller = (AbonoOdoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "abonoOdoController");
            return controller.getAbonoOdo(getKey(value));
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
            if (object instanceof AbonoOdo) {
                AbonoOdo o = (AbonoOdo) object;
                return getStringKey(o.getIdAbono());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AbonoOdo.class.getName()});
                return null;
            }
        }

    }

}
