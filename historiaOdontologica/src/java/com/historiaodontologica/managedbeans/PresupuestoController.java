package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DetalleServicioPresupuesto;
import com.historiaodontologica.entidades.DescuentoOdo;
import com.historiaodontologica.entidades.Presupuesto;
import com.historiaodontologica.entidades.ServiciosOdo;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.PresupuestoFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;

@Named("presupuestoController")
@SessionScoped
public class PresupuestoController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.PresupuestoFacade ejbFacade;
    private List<Presupuesto> items = null;
    private Presupuesto selected;
    private ArrayList<DetalleServicioPresupuesto> listaServicios;
    private ArrayList<DetalleServicioPresupuesto> listaServiciosTemporal;
    private DescuentoOdo descuento;
    private DetalleServicioPresupuesto detallePresupuestoDes;
    double totalPagar = 0;

    public PresupuestoController() {
        listaServicios = new ArrayList<>();
        listaServiciosTemporal = new ArrayList<>();
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public List<DetalleServicioPresupuesto> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(ArrayList<DetalleServicioPresupuesto> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public Presupuesto getSelected() {
        return selected;
    }

    public void setSelected(Presupuesto selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PresupuestoFacade getFacade() {
        return ejbFacade;
    }

    public Presupuesto prepareCreate() {
        selected = new Presupuesto();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/BundlePresupuesto").getString("PresupuestoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/BundlePresupuesto").getString("PresupuestoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/BundlePresupuesto").getString("PresupuestoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Presupuesto> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public void seleccionarTrataminto(ServiciosOdo serviciosOdo) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        DetalleServicioPresupuesto detalleServicio = new DetalleServicioPresupuesto();
        detalleServicio.setServicioOdo(serviciosOdo);
        detalleServicio.setCantidad(1);
        detalleServicio.calcularPrecio();

        listaServiciosTemporal.add(detalleServicio);
        copiarListas();
        calcularPrecioTotal();
        requestcontext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestcontext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void sumaCantidad(DetalleServicioPresupuesto detalleServicioP) {

        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            if (listaServicios.get(i).equals(detalleServicioP)) {
                int cantidadActual = detalleServicioP.getCantidad();
                detalleServicioP.setCantidad(cantidadActual + 1);
                detalleServicioP.calcularPrecio();
                listaServiciosTemporal.set(i, detalleServicioP);
            }
        
        }
        copiarListas();
        calcularPrecioTotal();    
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestContext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void menosCantidad(DetalleServicioPresupuesto detalleServicioP) {

        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            if (listaServicios.get(i).equals(detalleServicioP)) {
                int cantidadActual = detalleServicioP.getCantidad();
                detalleServicioP.setCantidad(cantidadActual - 1);
                detalleServicioP.calcularPrecio();
                listaServiciosTemporal.set(i, detalleServicioP);
            }
            
        }       
        copiarListas();
        calcularPrecioTotal();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestContext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void calcularPrecioTotal() {
        totalPagar = 0;
        for (int i = 0; i < listaServicios.size(); i++) {
            float total = listaServicios.get(i).getPrecio();
            totalPagar = totalPagar + total;
        }
    }

    public void descuentoPresupuesto(ValueChangeEvent e) {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        copiarListas();
        if (!e.equals("0")) {
            for (int i = 0; i < listaServicios.size(); i++) {
                int numDescuento = Integer.parseInt(e.getNewValue().toString());
                float valorDescuento = listaServicios.get(i).getPrecio();
                float descuentoTotal = (float) (valorDescuento - (valorDescuento * (0.01 * numDescuento)));
                detallePresupuestoDes = new DetalleServicioPresupuesto();
                detallePresupuestoDes = listaServicios.get(i);
                detallePresupuestoDes.setPrecio(descuentoTotal);
                listaServicios.set(i, detallePresupuestoDes);

            }
        }

        calcularPrecioTotal();
        requestContext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestContext.update("aperturaHistoriaOdontologica:panelPago");

    }

    public void copiarListas() {
        listaServicios = new ArrayList<>();
        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            listaServicios.add(crearCopiaObjeto(listaServiciosTemporal.get(i)));
        }
    }

    public DetalleServicioPresupuesto crearCopiaObjeto(DetalleServicioPresupuesto detalleServicioPresupuesto) {
        DetalleServicioPresupuesto copia = new DetalleServicioPresupuesto();
        copia.setCantidad(detalleServicioPresupuesto.getCantidad());
        copia.setPrecio(detalleServicioPresupuesto.getPrecio());
        copia.setServicioOdo(copiaServicio(detalleServicioPresupuesto.getServicioOdo()));

        return copia;
    }

    public ServiciosOdo copiaServicio(ServiciosOdo serviciosOdo) {
        ServiciosOdo serviciosOdoCopia = new ServiciosOdo();
        serviciosOdoCopia.setId(serviciosOdo.getId());
        serviciosOdoCopia.setNombre(serviciosOdo.getNombre());
        serviciosOdoCopia.setPrecio(serviciosOdo.getPrecio());
        return serviciosOdoCopia;

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

    public Presupuesto getPresupuesto(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Presupuesto> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Presupuesto> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Presupuesto.class)
    public static class PresupuestoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PresupuestoController controller = (PresupuestoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "presupuestoController");
            return controller.getPresupuesto(getKey(value));
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
            if (object instanceof Presupuesto) {
                Presupuesto o = (Presupuesto) object;
                return getStringKey(o.getIdPresupuesto());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Presupuesto.class.getName()});
                return null;
            }
        }

    }

}
