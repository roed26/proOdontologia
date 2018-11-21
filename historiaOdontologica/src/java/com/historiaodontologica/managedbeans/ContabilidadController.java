package com.historiaodontologica.managedbeans;

import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.entidades.Egreso;
import com.historiaodontologica.entidades.Ingreso;
import com.historiaodontologica.managedbeans.util.JsfUtil;
import com.historiaodontologica.managedbeans.util.JsfUtil.PersistAction;
import com.historiaodontologica.sessionbeans.DetalleServicioFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
    private String ganancia;
    private float ganancias;
    private String perdida;
    //private DecimalFormat df;
    private NumberFormat nf;

    public ContabilidadController() {
        this.ganancia = "";
        this.ganancias = 0;
        this.perdida = "";
        itemsIngreso = new ArrayList<>();
        itemsEgreso = new ArrayList<>();
        Locale locale = new Locale("es", "CO");
        //df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        nf = NumberFormat.getCurrencyInstance(locale);
        //df.setMaximumFractionDigits(340);
    }

    //    public DecimalFormat getDf() {
//        return df;
//    }
//
//    public void setDf(DecimalFormat df) {
//        this.df = df;
//    }
    public NumberFormat getNf() {
        return nf;
    }

    public void setNf(NumberFormat nf) {
        this.nf = nf;
    }

    public float getGanancias() {
        return ganancias;
    }

    public void setGanancias(float ganancias) {
        this.ganancias = ganancias;
    }

    public String getGanancia() {
        return ganancia;
    }

    public void setGanancia(String ganancia) {
        this.ganancia = ganancia;
    }

    public String getPerdida() {
        return perdida;
    }

    public void setPerdida(String perdida) {
        this.perdida = perdida;
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
        double totalEgresos = 0, totalIngresos = 0, per = 0, gan = 0;
        ganancia = "";
        perdida = "";
        itemsEgreso = ejbEgreso.findAll();
        itemsIngreso = ejbIngreso.findAll();
        for (int i = 0; i < itemsEgreso.size(); i++) {
            totalEgresos = (totalEgresos + itemsEgreso.get(i).getValorEgreso());
        }
        for (int j = 0; j < itemsIngreso.size(); j++) {
            totalIngresos = (totalIngresos + itemsIngreso.get(j).getValorIngreso());
        }

        if (totalIngresos > totalEgresos) {
            gan = totalIngresos - totalEgresos;
            ganancia = String.valueOf(gan);
            ganancias = (float) gan;
            System.out.println("KKKKKKKKK" + ganancias);
        } else if (totalIngresos < totalEgresos) {
            per = (totalIngresos - totalEgresos) * -1;
            perdida = String.valueOf(per);
            System.out.println("KKKKKKKKK" + perdida);
        } else {
            ganancia = "0";
            perdida = "0";
        }

    }

    public void cargarIngresos(Ingreso ingreso) {

    }
}
