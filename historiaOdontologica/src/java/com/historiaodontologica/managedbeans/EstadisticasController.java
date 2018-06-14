package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.IndiceCOP;
import com.historiaodontologica.entidades.ObsOdontograma;
import com.historiaodontologica.sessionbeans.ObsOdontogramaFacade;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
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
@Named(value = "estadisticasController")
@SessionScoped
public class EstadisticasController implements Serializable {

    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;

    private List<IndiceCOP> listaCOP;
    private List<ObsOdontograma> listaIndicesCOP;

    private double porcentajeCariados = 0;
    private double porcentajeObturados = 0;
    private double porcentajePerdidos = 0;
    private double totalNOsanos = 0;
    private double porcentajeSanos = 0;

    private PieChartModel modeloResultadosCariados;
    private PieChartModel modeloResultadosObturados;
    private PieChartModel modeloResultadosPerdidos;

    public EstadisticasController() {
        listaCOP = new ArrayList<>();
        listaIndicesCOP = new ArrayList<>();
        modeloResultadosCariados = new PieChartModel();
        modeloResultadosObturados = new PieChartModel();
        modeloResultadosPerdidos = new PieChartModel();
    }

    @PostConstruct
    public void init() {
        //listaFacultades = ejbFacultad.findAll();
        //listaProgramas = ejbProgramas.findAll();
    }

    public List<IndiceCOP> getListaCOP() {
        return listaCOP;
    }

    public void setListaCOP(List<IndiceCOP> listaCOP) {
        this.listaCOP = listaCOP;
    }

    public PieChartModel getModeloResultadosCariados() {
        return modeloResultadosCariados;
    }

    public void setModeloResultadosCariados(PieChartModel modeloResultadosCariados) {
        this.modeloResultadosCariados = modeloResultadosCariados;
    }

    public PieChartModel getModeloResultadosObturados() {
        return modeloResultadosObturados;
    }

    public void setModeloResultadosObturados(PieChartModel modeloResultadosObturados) {
        this.modeloResultadosObturados = modeloResultadosObturados;
    }

    public PieChartModel getModeloResultadosPerdidos() {
        return modeloResultadosPerdidos;
    }

    public void setModeloResultadosPerdidos(PieChartModel modeloResultadosPerdidos) {
        this.modeloResultadosPerdidos = modeloResultadosPerdidos;
    }

    public void generarIndiceCOP() {
        this.indiceGeneralCOP();
    }

    public void indiceGeneralCOP() {

        listaIndicesCOP = ejbObsOdontograma.findAll();
        IndiceCOP indiceCOP = new IndiceCOP();
        indiceCOP.setArea("General");

        int contCariados = 0;
        int contObturados = 0;
        int contPerdidos = 0;
        for (int i = 0; i < listaIndicesCOP.size(); i++) {
            if (!listaIndicesCOP.get(i).getCaries().equalsIgnoreCase("0")) {
                contCariados++;
                indiceCOP.setCariados(contCariados);
            }
            if (!listaIndicesCOP.get(i).getObturados().equalsIgnoreCase("0")) {
                contObturados++;
                indiceCOP.setObturados(contObturados);
            }
            if (!listaIndicesCOP.get(i).getPerdidos().equalsIgnoreCase("0")) {
                contPerdidos++;
                indiceCOP.setPerdidos(contPerdidos);
            }

        }
        listaCOP.add(indiceCOP);

        porcentajeCariados = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getCariados());
        porcentajeObturados = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getObturados());
        porcentajePerdidos = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getPerdidos());
        totalNOsanos = porcentajeCariados + porcentajeObturados + porcentajePerdidos;

        cargarModelosGrafica();
    }

    private double calcularporcentaje(int totalpacientes, int dientes) {
        double resultado = (double) dientes / totalpacientes;
        return resultado * 100;
    }
    
    private void cargarModelosGrafica() {
        modeloResultadosCariados.set("Cariados", porcentajeCariados);
        modeloResultadosCariados.set("Sanos", 100 - porcentajeCariados);

        modeloResultadosObturados.set("Obturados", porcentajeObturados);
        modeloResultadosObturados.set("Sanos", 100 - porcentajeObturados);

        modeloResultadosPerdidos.set("Perdidos", porcentajePerdidos);
        modeloResultadosPerdidos.set("Sanos", 100 - porcentajePerdidos);

        modeloResultadosCariados.setTitle("Porcentajes Cariados");
        modeloResultadosCariados.setLegendPosition("e");
        modeloResultadosCariados.setShowDataLabels(true);

        modeloResultadosObturados.setTitle("Porcentajes Obturados");
        modeloResultadosObturados.setLegendPosition("e");
        modeloResultadosObturados.setShowDataLabels(true);

        modeloResultadosPerdidos.setTitle("Porcentajes Perdidos");
        modeloResultadosPerdidos.setLegendPosition("e");
        modeloResultadosPerdidos.setShowDataLabels(true);
    }
}
