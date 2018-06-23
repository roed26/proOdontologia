package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.IndiceCOP;
import com.historiaodontologica.clases.ReportesMedicos;
import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.ObsOdontograma;
import com.historiaodontologica.entidades.Odontograma;
import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.ObsOdontogramaFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;

    private List<IndiceCOP> listaCOP;

    private List<ObsOdontograma> odontIndicesCOP;
    private List<ActualizacionOdo> filtroIndicesCOP;
    private List<ReportesMedicos> listContGenero;

    private double porcentajeCariados = 0;
    private double porcentajeObturados = 0;
    private double porcentajePerdidos = 0;
    private double totalNOsanos = 0;
    private int contHombres = 0;
    private int contMujeres = 0;
    private int contCariados = 0;
    private int contObturados = 0;
    private int contPerdidos = 0;
    private int edad = 0;
    private double porcentajeSanos = 0;
    private ReportesMedicos reportesMedicos;
    private IndiceCOP indiceCOP;
    private SimpleDateFormat formatoFecha;

    private PieChartModel modeloResultadosCariados;
    private PieChartModel modeloResultadosObturados;
    private PieChartModel modeloResultadosPerdidos;

    public EstadisticasController() {
        listContGenero = new ArrayList<>();
        listaCOP = new ArrayList<>();
        filtroIndicesCOP = new ArrayList<>();
        modeloResultadosCariados = new PieChartModel();
        modeloResultadosObturados = new PieChartModel();
        modeloResultadosPerdidos = new PieChartModel();
        reportesMedicos = new ReportesMedicos();
        indiceCOP = new IndiceCOP();
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    }

    @PostConstruct
    public void init() {
        odontIndicesCOP = ejbObsOdontograma.findAll();
    }

    public int getContHombres(int posicion) {
        return listaCOP.get(posicion).getContHombres();
    }

    public List<ActualizacionOdo> getFiltroIndicesCOP() {
        return filtroIndicesCOP;
    }
     
    public void setFiltroIndicesCOP(List<ActualizacionOdo> filtroIndicesCOP) {
        this.filtroIndicesCOP = filtroIndicesCOP;
    }

    public Date getFechadesde() {
        return reportesMedicos.getFechadesde();
    }

    public void setFechadesde(Date fechadesde) {
        reportesMedicos.setFechadesde(fechadesde);
    }

    public List<ReportesMedicos> getListContGenero() {
        return listContGenero;
    }

    public void setListContGenero(List<ReportesMedicos> listContGenero) {
        this.listContGenero = listContGenero;
    }

    public Date getFechahasta() {
        return reportesMedicos.getFechahasta();
    }

    public void setFechahasta(Date fechahasta) {
        reportesMedicos.setFechahasta(fechahasta);
    }

    public ReportesMedicos getReportesMedicos() {
        return reportesMedicos;
    }

    public void setReportesMedicos(ReportesMedicos reportesMedicos) {
        this.reportesMedicos = reportesMedicos;
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
        listaCOP.clear();
        this.indiceGeneralCOP(0, 6);
        this.indiceGeneralCOP(7, 14);
        this.indiceGeneralCOP(15, 120);
    }

    public void indiceGeneralCOP(int edadMin, int edadMax) {
        indiceCOP = new IndiceCOP();
        List<ObsOdontograma> listaIndicesCOP = new ArrayList<>();
        indiceCOP.setArea("General");
        contHombres = 0;
        contMujeres = 0;

        if (getFechadesde() == null || getFechahasta() == null) {
            filtroIndicesCOP = ejbActualizacionOdo.findAll();
        } else {
            filtroIndicesCOP = ejbActualizacionOdo.listadoCOPFecha(reportesMedicos.getFechadesde(), reportesMedicos.getFechahasta());
        }
        for (ActualizacionOdo actualizacion : filtroIndicesCOP) {
            if (calcularEdad(actualizacion.getIdPaciente()) >= edadMin && calcularEdad(actualizacion.getIdPaciente()) <= edadMax) {
                if (actualizacion.getIdPaciente().getSexo().equals('M')) {
                    indiceCOP.aumentarContHombres();
                } else {
                    indiceCOP.aumentarContMujeres();
                }
                for (ObsOdontograma odo : actualizacion.getObsOdontogramaCollection()) {
                    if (!odo.getCaries().equalsIgnoreCase("0")) {
                        contCariados++;
                        indiceCOP.setCariados(contCariados);
                    }
                    if (!odo.getObturados().equalsIgnoreCase("0")) {
                        contObturados++;
                        indiceCOP.setObturados(contObturados);
                    }
                    if (!odo.getPerdidos().equalsIgnoreCase("0")) {
                        contPerdidos++;
                        indiceCOP.setPerdidos(contPerdidos);
                    }
                }
            }
        }
        listaCOP.add(indiceCOP);

//        porcentajeCariados = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getCariados());
//        porcentajeObturados = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getObturados());
//        porcentajePerdidos = calcularporcentaje(listaIndicesCOP.size(), indiceCOP.getPerdidos());
//        totalNOsanos = porcentajeCariados + porcentajeObturados + porcentajePerdidos;
//
//        cargarModelosGrafica();
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

    public int calcularEdad(Paciente paciente) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNac = LocalDate.parse(formatoFecha.format(paciente.getFechaNacimiento()), fmt);
        LocalDate ahora = LocalDate.now();

        Period periodo = Period.between(fechaNac, ahora);
        int edad = periodo.getYears();

        return edad;
    }
}
