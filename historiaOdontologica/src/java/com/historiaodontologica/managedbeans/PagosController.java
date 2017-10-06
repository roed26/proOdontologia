package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DiagnosticoCantidad;
import com.historiaodontologica.clases.IndiceCOP;
import com.historiaodontologica.clases.ServicioPrestado;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.DiagnosticoOdoFacade;
import com.historiaodontologica.sessionbeans.ObsOdontogramaFacade;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author ROED26
 */
@Named(value = "pagosController")
@SessionScoped
public class PagosController implements Serializable {

    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;

    private List<ServicioPrestado> listaServicios;

    public PagosController() {

    }

    @PostConstruct
    public void init() {

    }

    public List<ServicioPrestado> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioPrestado> listaServicios) {
        this.listaServicios = listaServicios;
    }

}
