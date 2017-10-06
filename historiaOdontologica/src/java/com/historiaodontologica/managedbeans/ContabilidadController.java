package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.entidades.Egreso;
import com.historiaodontologica.entidades.Ingreso;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.DetalleServicioFacade;

import java.io.Serializable;
import java.util.ArrayList;
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

@Named("contabilidadController")
@SessionScoped
public class ContabilidadController implements Serializable {

    @EJB
    private com.historiaodontologica.sessionbeans.IngresoFacade ejbIngreso;
    @EJB
    private com.historiaodontologica.sessionbeans.EgresoFacade ejbEgreso;
    private List<Ingreso> itemsIngreso = null;
    private List<Egreso> itemsEgreso = null;
    private Ingreso selectedIngreso;
    private Egreso selectedEgreso;
    private double ganancia;
    private double perdida;

    public ContabilidadController() {
        this.ganancia = 0;
        this.perdida = 0;
        itemsIngreso = new ArrayList<>();
        itemsEgreso = new ArrayList<>();
    }

    public double getPerdida() {
        return perdida;
    }

    public void setPerdida(double perdida) {
        this.perdida = perdida;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    public List<Ingreso> getItemsIngreso() {
        return itemsIngreso;
    }

    public void setItemsIngreso(List<Ingreso> itemsIngreso) {
        this.itemsIngreso = itemsIngreso;
    }

    public List<Egreso> getItemsEgreso() {
        return itemsEgreso;
    }

    public void setItemsEgreso(List<Egreso> itemsEgreso) {
        this.itemsEgreso = itemsEgreso;
    }

    public void calcurarGanancias() {
        double totalEgresos = 0, totalIngresos = 0;
        ganancia = 0;
        perdida = 0;
        itemsEgreso = ejbEgreso.findAll();
        itemsIngreso = ejbIngreso.findAll();
        for (int i = 0; i < itemsEgreso.size(); i++) {
            totalEgresos = (totalEgresos + itemsEgreso.get(i).getValorEgreso());
        }
        for (int j = 0; j < itemsIngreso.size(); j++) {
            totalIngresos = (totalIngresos + itemsIngreso.get(j).getValorIngreso());
        }

        if (totalIngresos > totalEgresos) {
            ganancia = totalIngresos - totalEgresos;
        } else if (totalIngresos < totalEgresos) {
            perdida = (totalIngresos - totalEgresos) * -1;
        } else {
            ganancia = 0;
            perdida = 0;
        }

    }

    public void cargarIngresos(Ingreso ingreso) {

    }
}
