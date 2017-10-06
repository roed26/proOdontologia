package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DiagnosticoCantidad;
import com.historiaodontologica.clases.IndiceCOP;
import com.historiaodontologica.entidades.DiagnosticoOdo;
import com.historiaodontologica.entidades.ObsOdontograma;
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
@Named(value = "estadisticasController")
@SessionScoped
public class EstadisticasController implements Serializable {

 /*   @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;
    

    private List<DiagnosticoOdo> diagnosticos;
    private List<DiagnosticoCantidad> diagnosticosSuma;
    private List<IndiceCOP> listaCOP;
    private List<ObsOdontograma> listaIndicesCOP;
    private List<List<DiagnosticoCantidad>> listaResultados;
    private List<IndiceCOP> listaCOPfacultades;
    private List<IndiceCOP> listaCOPprograma;

    private double porcentajeCariados = 0;
    private double porcentajeObturados = 0;
    private double porcentajePerdidos = 0;
    private double totalNOsanos = 0;
    private double porcentajeSanos = 0;

    private PieChartModel modeloResultadosCariados;
    private PieChartModel modeloResultadosObturados;
    private PieChartModel modeloResultadosPerdidos;

    public EstadisticasController() {
        diagnosticos = new ArrayList<>();
        diagnosticosSuma = new ArrayList<>();
        //listaFacultades = new ArrayList<>();
        listaResultados = new ArrayList<>();
        listaCOP = new ArrayList<>();
        //listafacultadCOP = new ArrayList<>();
        listaCOPfacultades = new ArrayList<>();
        listaCOPprograma = new ArrayList<>();
        listaIndicesCOP= new ArrayList<>();
        modeloResultadosCariados = new PieChartModel();
        modeloResultadosObturados = new PieChartModel();
        modeloResultadosPerdidos = new PieChartModel();

    }

    @PostConstruct
    public void init() {
        //listaFacultades = ejbFacultad.findAll();
        //listaProgramas = ejbProgramas.findAll();
    }

    public List<DiagnosticoCantidad> getDiagnosticosSuma() {
        return diagnosticosSuma;
    }

    public void setDiagnosticosSuma(List<DiagnosticoCantidad> diagnosticosSuma) {
        this.diagnosticosSuma = diagnosticosSuma;
    }

    

    public List<IndiceCOP> getListaCOP() {
        return listaCOP;
    }

    public void setListaCOP(List<IndiceCOP> listaCOP) {
        this.listaCOP = listaCOP;
    }

    public List<IndiceCOP> getListaCOPfacultades() {
        return listaCOPfacultades;
    }

    public void setListaCOPfacultades(List<IndiceCOP> listaCOPfacultades) {
        this.listaCOPfacultades = listaCOPfacultades;
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

    

    public List<IndiceCOP> getListaCOPprograma() {
        return listaCOPprograma;
    }

    public void setListaCOPprogramas(List<IndiceCOP> listaCOPprograma) {
        this.listaCOPprograma = listaCOPprograma;
    }

    public List<List<DiagnosticoCantidad>> getListaResultados() {
        return listaResultados;
    }

    public void setListaResultados(List<List<DiagnosticoCantidad>> listaResultados) {
        this.listaResultados = listaResultados;
    }

    public void generarEstadisticaFacultadDiagnostico() {
        this.diagnosticos = ejbDiagnosticoOdo.findAll();
        cargarNombreDiagnosticos();
        //cargarListaDiagnosticosAFacultad();
  /*      for (int i = 0; i < listaFacultades.size(); i++) {
            for (int j = 0; j < diagnosticos.size(); j++) {
                String facultad = diagnosticos.get(j).getIdActualizacion().getIdPaciente().getFacultad().getNombre();
                if(){
                
                }
            }
        }
*/
     /*   for (int i = 0; i < diagnosticosSuma.size(); i++) {
            for (int j = 0; j < diagnosticos.size(); j++) {

                if (diagnosticosSuma.get(i).getDiagnostico().equalsIgnoreCase(diagnosticos.get(j).getDx())) {
                    int cont = diagnosticosSuma.get(i).getCantidad();
                    cont = cont + 1;
                    diagnosticosSuma.get(i).setCantidad(cont);
                }
                if (this.diagnosticos.get(i).getDx1() != null) {
                    if (diagnosticosSuma.get(i).getDiagnostico().equalsIgnoreCase(diagnosticos.get(j).getDx1())) {
                        int cont = diagnosticosSuma.get(i).getCantidad();
                        cont = cont + 1;
                        diagnosticosSuma.get(i).setCantidad(cont);
                    }
                }
                if (this.diagnosticos.get(i).getDx2() != null) {
                    if (diagnosticosSuma.get(i).getDiagnostico().equalsIgnoreCase(diagnosticos.get(j).getDx2())) {
                        int cont = diagnosticosSuma.get(i).getCantidad();
                        cont = cont + 1;
                        diagnosticosSuma.get(i).setCantidad(cont);
                    }
                }
                if (this.diagnosticos.get(i).getDx3() != null) {
                    if (diagnosticosSuma.get(i).getDiagnostico().equalsIgnoreCase(diagnosticos.get(j).getDx3())) {
                        int cont = diagnosticosSuma.get(i).getCantidad();
                        cont = cont + 1;
                        diagnosticosSuma.get(i).setCantidad(cont);
                    }
                }
            }
        }
    }

    private void cargarNombreDiagnosticos() {
        List<String> list = new ArrayList<>();
        List<String> listNombre = new ArrayList<>();
        for (int i = 0; i < this.diagnosticos.size(); i++) {
            if (!this.diagnosticos.get(i).getDx().equalsIgnoreCase("")) {
                list.add(this.diagnosticos.get(i).getDx());
                listNombre.add(this.diagnosticos.get(i).getNdx());
            }
            if (!this.diagnosticos.get(i).getDx1().equalsIgnoreCase("")) {
                list.add(this.diagnosticos.get(i).getDx1());
                listNombre.add(this.diagnosticos.get(i).getNdx1());
            }
            if (!this.diagnosticos.get(i).getDx2().equalsIgnoreCase("")) {
                list.add(this.diagnosticos.get(i).getDx2());
                listNombre.add(this.diagnosticos.get(i).getNdx2());
            }
            if (!this.diagnosticos.get(i).getDx3().equalsIgnoreCase("")) {
                list.add(this.diagnosticos.get(i).getDx3());
                listNombre.add(this.diagnosticos.get(i).getNdx3());
            }
        }
        Set<String> s = new LinkedHashSet<>(list);
        Set<String> nombresDiagnosticos = new LinkedHashSet<>(listNombre);
        list.clear();
        listNombre.clear();

        list.addAll(s);

        for (int i = 0; i < list.size(); i++) {
            DiagnosticoCantidad diagnosticoCantidad = new DiagnosticoCantidad();
            diagnosticoCantidad.setDiagnostico(list.get(i));
            diagnosticosSuma.add(diagnosticoCantidad);
        }

        for (int i = 0; i < listaFacultades.size(); i++) {
            listaResultados.add(diagnosticosSuma);
        }
    }

    private void cargarListaDiagnosticosAFacultad() {

    }

    public void generarIndiceCOP() {
        this.indiceGeneralCOP();
        this.indicefacultadCOP();
        this.generarCOPprogramas();

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

    public void indicefacultadCOP() {

        listafacultadCOP = ejbFacultad.findAll();
        String facultad = "";
        int contadorCariados = 0;
        int contadorObturados = 0;
        int contadorPerdidos = 0;

        for (int i = 0; i < listafacultadCOP.size(); i++) {
            IndiceCOP indiceCOP = new IndiceCOP();
            indiceCOP.setArea(listafacultadCOP.get(i).getNombre());
            listaCOPfacultades.add(indiceCOP);
        }

        for (int i = 0; i < listaIndicesCOP.size(); i++) {

            facultad = listaIndicesCOP.get(i).getIdActualizacion().getIdPaciente().getFacultad().getNombre();

            for (int j = 0; j < listaCOPfacultades.size(); j++) {
                if (facultad.equals(listaCOPfacultades.get(j).getArea())) {

                    if (!listaIndicesCOP.get(i).getCaries().equalsIgnoreCase("0")) {
                        contadorCariados = listaCOPfacultades.get(j).getCariados();
                        contadorCariados++;
                        listaCOPfacultades.get(j).setCariados(contadorCariados);

                    }
                    if (!listaIndicesCOP.get(i).getObturados().equalsIgnoreCase("0")) {
                        contadorObturados = listaCOPfacultades.get(j).getObturados();
                        contadorObturados++;
                        listaCOPfacultades.get(j).setObturados(contadorObturados);
                    }
                    if (!listaIndicesCOP.get(i).getPerdidos().equalsIgnoreCase("0")) {
                        contadorPerdidos = listaCOPfacultades.get(j).getPerdidos();
                        contadorPerdidos++;
                        listaCOPfacultades.get(j).setPerdidos(contadorPerdidos);
                    }
                }
            }
        }
    }

    public void generarCOPprogramas() {

        listaProgramas = ejbProgramas.findAll();

        String programa = "";
        int contadorCariados = 0;
        int contadorObturados = 0;
        int contadorPerdidos = 0;

        for (int i = 0; i < listaProgramas.size(); i++) {
            IndiceCOP indiceCOP = new IndiceCOP();
            indiceCOP.setArea(listaProgramas.get(i).getNombre());
            listaCOPprograma.add(indiceCOP);
        }

        for (int i = 0; i < listaIndicesCOP.size(); i++) {

            programa = listaIndicesCOP.get(i).getIdActualizacion().getIdPaciente().getPrograma().getNombre();

            for (int j = 0; j < listaCOPprograma.size(); j++) {
                if (programa.equals(listaCOPprograma.get(j).getArea())) {

                    if (!listaIndicesCOP.get(i).getCaries().equalsIgnoreCase("0")) {
                        contadorCariados = listaCOPprograma.get(j).getCariados();
                        contadorCariados++;
                        listaCOPprograma.get(j).setCariados(contadorCariados);

                    }
                    if (!listaIndicesCOP.get(i).getObturados().equalsIgnoreCase("0")) {
                        contadorObturados = listaCOPprograma.get(j).getObturados();
                        contadorObturados++;
                        listaCOPprograma.get(j).setObturados(contadorObturados);
                    }
                    if (!listaIndicesCOP.get(i).getPerdidos().equalsIgnoreCase("0")) {
                        contadorPerdidos = listaCOPprograma.get(j).getPerdidos();
                        contadorPerdidos++;
                        listaCOPprograma.get(j).setPerdidos(contadorPerdidos);
                    }
                }
            }
        }
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
*/
}
