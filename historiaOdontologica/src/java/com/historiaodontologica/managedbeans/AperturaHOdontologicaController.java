/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DetalleServicioPresupuesto;
import com.historiaodontologica.clases.DiagnosticoTipo;
import com.historiaodontologica.clases.OdontogramaTemp;
import com.historiaodontologica.clases.RespuestasAntecedentesFamiliares;
import com.historiaodontologica.clases.RespuestasAntecedentesPersonales;
import com.historiaodontologica.clases.RespuestasExamenEstomatologico;
import com.historiaodontologica.clases.RespuestasExamenOral;
import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.AntecedenteOdo;
import com.historiaodontologica.entidades.CuadroSintesis;
import com.historiaodontologica.entidades.DescuentoOdo;
import com.historiaodontologica.entidades.DetalleServicio;
import com.historiaodontologica.entidades.DetalleServicioPK;
import com.historiaodontologica.entidades.DiagnosticoOdo;
import com.historiaodontologica.entidades.Diagnosticocie10Odo;
import com.historiaodontologica.entidades.EvolucionOdo;
import com.historiaodontologica.entidades.ExaEstomatologico;
import com.historiaodontologica.entidades.ExaOral;
import com.historiaodontologica.entidades.Higiene;
import com.historiaodontologica.entidades.ListadoAntOdon;
import com.historiaodontologica.entidades.ListadoEstomatologico;
import com.historiaodontologica.entidades.ListadoExamenOral;
import com.historiaodontologica.entidades.MotivoOdo;
import com.historiaodontologica.entidades.ObsAntOdo;
import com.historiaodontologica.entidades.ObsExaOral;
import com.historiaodontologica.entidades.ObsOdontograma;
import com.historiaodontologica.entidades.Odontograma;
import com.historiaodontologica.entidades.Paciente;
import com.historiaodontologica.entidades.Presupuesto;
import com.historiaodontologica.entidades.ServiciosOdo;
import com.historiaodontologica.entidades.TipoDiagnostico;
import com.historiaodontologica.entidades.TipoIdentificacion;
import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.AntOdoFacade;
import com.historiaodontologica.sessionbeans.CuadroSintesisFacade;
import com.historiaodontologica.sessionbeans.DetalleServicioFacade;
import com.historiaodontologica.sessionbeans.DiagnosticoOdoFacade;
import com.historiaodontologica.sessionbeans.Diagnosticocie10OdoFacade;
import com.historiaodontologica.sessionbeans.EvolucionOdoFacade;
import com.historiaodontologica.sessionbeans.ExaEstomatologicoFacade;
import com.historiaodontologica.sessionbeans.ExaOralFacade;
import com.historiaodontologica.sessionbeans.HigieneFacade;
import com.historiaodontologica.sessionbeans.ListadoAntOdonFacade;
import com.historiaodontologica.sessionbeans.ListadoEstomatologicoFacade;
import com.historiaodontologica.sessionbeans.ListadoExamenOralFacade;
import com.historiaodontologica.sessionbeans.MotivoOdoFacade;
import com.historiaodontologica.sessionbeans.ObsAntOdoFacade;
import com.historiaodontologica.sessionbeans.ObsExaOralFacade;
import com.historiaodontologica.sessionbeans.ObsOdontogramaFacade;
import com.historiaodontologica.sessionbeans.OdontogramaFacade;
import com.historiaodontologica.sessionbeans.PacienteFacade;
import com.historiaodontologica.sessionbeans.PresupuestoFacade;
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author ROED26
 */
@Named(value = "aperturaHOdontologicaController")
@SessionScoped
public class AperturaHOdontologicaController implements Serializable {

    @EJB
    private ListadoAntOdonFacade ejbListadoAntecedentesOdon;
    @EJB
    private ListadoEstomatologicoFacade ejbListadoEstomatologicos;
    @EJB
    private ListadoExamenOralFacade ejbListadoExamenOral;
    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private UsuariosSistemaFacade usuarioEJB;
    @EJB
    private MotivoOdoFacade ejbMotivoOdo;
    @EJB
    private AntOdoFacade ejbAntOdo;
    @EJB
    private ObsAntOdoFacade ejbObsAntOdo;
    @EJB
    private ExaEstomatologicoFacade ejbExaEstomatologico;
    @EJB
    private ExaOralFacade ejbExaOral;
    @EJB
    private ObsExaOralFacade ejbObsExaOral;
    @EJB
    private HigieneFacade ejbHigiene;
    @EJB
    private EvolucionOdoFacade ejbEvolucionOdo;
    @EJB
    private Diagnosticocie10OdoFacade ejbDiagnosticocie10Odo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    @EJB
    private OdontogramaFacade ejbOdontograma;
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;
    @EJB
    private CuadroSintesisFacade ejbCuadroSintesis;
    @EJB
    private PresupuestoFacade ejbPresupuesto;
    @EJB
    private DetalleServicioFacade ejbDetalleServicio;
    @EJB
    private PacienteFacade ejbPaciente;

    //variables booleanas
    private boolean conAcompaniante;
    private boolean conAntecedentesFamiliares;
    private boolean conAntecedentesPersonales;
    private boolean conExamenEstomatologico;
    private boolean conExamenPulpar;
    private boolean conExamenTejidosDent;
    private boolean conAlteraciones;
    private boolean pacienteSeleccionado;
    private boolean seleccionDienteNormal;
    private boolean seleccionDienteCaries;
    private boolean seleccionDienteAmalgama;
    private boolean seleccionDienteObstPlastica;
    private boolean seleccionDienteObstTemporal;
    private boolean seleccionDienteConSellante;
    private boolean seleccionDienteSinSellante;
    private boolean seleccionDienteFaltante;
    private boolean seleccionDienteFaltanteExt;
    private boolean seleccionDienteExodoncia;
    private boolean seleccionDienteProtesis;
    private boolean seleccionDienteProtesisTotal;
    private boolean seleccionDienteNecendodoncia;
    private boolean seleccionDienteTtoEndodoncia;
    private boolean seleccionDienteBolsaPer;

    private List<Boolean> contadoresCariesDiente;
    private List<Boolean> contadoresPerdidosDiente;
    private List<Boolean> contadoresObturadosDiente;

    //listas
    private List<ListadoAntOdon> listadoAntecedentesFamiliares;
    private List<ListadoAntOdon> listadoAntecedentesPersonales;
    private List<ListadoEstomatologico> listadoEstomatologicos;
    private List<ListadoExamenOral> listadoExamenOralPulpar;
    private List<ListadoExamenOral> listadoExamenOralDentario;
    private List<ListadoExamenOral> listadoExamenOralPeriodontal;
    private List<String> listadoRespuestasAntFam;
    private List<String> listadoRespuestasAntPer;
    private List<String> listadoRespuestasExmEst;
    private List<String> listadoObservacionesExmEst;
    private List<String> listadoRespuestasExamenOral;
    private List<DiagnosticoTipo> listaDiagnosticosTipo;
    private ArrayList<DetalleServicioPresupuesto> listaServicios;
    private ArrayList<DetalleServicioPresupuesto> listaServiciosTemporal;
    private DescuentoOdo descuento;
    private DetalleServicioPresupuesto detallePresupuestoDes;
    private DetalleServicioPresupuesto detalleServicioPresupuestoEliminar;
    double totalPagar = 0;
    private List<OdontogramaTemp> odontogramaPaciente;
    private List<OdontogramaTemp> odontogramaPacienteAdultoSuperior;
    private List<OdontogramaTemp> odontogramaPacienteAdultoInferior;
    private List<OdontogramaTemp> odontogramaPacienteInfantilSuperior;
    private List<OdontogramaTemp> odontogramaPacienteInfantilInferior;

    //String
    private String respuestaAntOdo = "No";
    private String motivoConsulta;
    private String descripcionEvolucion;
    private String busquedaPaciente;
    private String fechaRegistro;
    private String tipoActualizacion;
    private String observacionAntecendetesFam;
    private String observacionAntecendetesPer;
    private String observacionGenExmEst;
    private String diagnosticoDiente;
    private String diagnosticoDX1;
       
    //fechas
    private Date fechaApertura;
    private SimpleDateFormat formatoFecha;

    //numeros
    private int contCaries = 0;
    private int diente;

    //objetos 
    private Paciente paciente;
    private Paciente pacienteApertura;
    private Higiene higiene;
    private TipoIdentificacion tipoIdentificacion;
    private CuadroSintesis cuadroSintesis;
    private ActualizacionOdo actualizacionOdo;
    private RespuestasAntecedentesFamiliares respuestasAntecedentesFamiliares;
    private RespuestasAntecedentesPersonales respuestasAntecedentesPersonales;
    private RespuestasExamenEstomatologico respuestasExamenEstomatologico;
    private RespuestasExamenOral respuestasExamenOral;
    private ObsOdontograma obsOdontograma;
    private DiagnosticoTipo diagnosticoTipo;

    public AperturaHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;
        this.seleccionDienteNormal = false;
        this.seleccionDienteCaries = false;
        this.seleccionDienteAmalgama = false;
        this.seleccionDienteObstPlastica = false;
        this.seleccionDienteObstTemporal = false;
        this.seleccionDienteConSellante = false;
        this.seleccionDienteSinSellante = false;
        this.seleccionDienteFaltante = false;
        this.seleccionDienteFaltanteExt = false;
        this.seleccionDienteExodoncia = false;
        this.seleccionDienteProtesis = false;
        this.seleccionDienteProtesisTotal = false;
        this.seleccionDienteNecendodoncia = false;
        this.seleccionDienteTtoEndodoncia = false;
        this.seleccionDienteBolsaPer = false;

        //inicilizacion string
        
        this.higiene = new Higiene();
        listadoAntecedentesFamiliares = new ArrayList<>();
        listadoAntecedentesPersonales = new ArrayList<>();
        listadoEstomatologicos = new ArrayList<>();
        listadoExamenOralPulpar = new ArrayList<>();
        listadoExamenOralDentario = new ArrayList<>();
        listadoExamenOralPeriodontal = new ArrayList<>();
        listadoRespuestasAntFam = new ArrayList<>();
        listadoRespuestasAntPer = new ArrayList<>();
        listadoRespuestasExmEst = new ArrayList<>();
        listadoRespuestasExamenOral = new ArrayList<>();
        listadoObservacionesExmEst = new ArrayList<>();
        listaDiagnosticosTipo = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaServiciosTemporal = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
        tipoIdentificacion = new TipoIdentificacion();
        pacienteApertura = new Paciente();
        odontogramaPaciente = new ArrayList<>();
        contadoresCariesDiente = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        inicializarVariables();
        this.actualizacionOdo = new ActualizacionOdo();
        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.cuadroSintesis = new CuadroSintesis();

        this.diagnosticoDiente = "normal";
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
        inicializarCuadroSintesis();

    }

    public ActualizacionOdo getActualizacionOdo() {
        return actualizacionOdo;
    }

    public void setActualizacionOdo(ActualizacionOdo actualizacionOdo) {
        this.actualizacionOdo = actualizacionOdo;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public CuadroSintesis getCuadroSintesis() {
        return cuadroSintesis;
    }

    public void setCuadroSintesis(CuadroSintesis cuadroSintesis) {
        this.cuadroSintesis = cuadroSintesis;
    }

    public ObsOdontograma getObsOdontograma() {
        return obsOdontograma;
    }

    public void setObsOdontograma(ObsOdontograma obsOdontograma) {
        this.obsOdontograma = obsOdontograma;
    }

    public List<ListadoAntOdon> getListadoAntecedentesFamiliares() {
        return listadoAntecedentesFamiliares;
    }

    public void setListadoAntecedentesFamiliares(List<ListadoAntOdon> listadoAntecedentesFamiliares) {
        this.listadoAntecedentesFamiliares = listadoAntecedentesFamiliares;
    }

    public List<ListadoAntOdon> getListadoAntecedentesPersonales() {
        return listadoAntecedentesPersonales;
    }

    public void setListadoAntecedentesPersonales(List<ListadoAntOdon> listadoAntecedentesPersonales) {
        this.listadoAntecedentesPersonales = listadoAntecedentesPersonales;
    }

    public List<ListadoEstomatologico> getListadoEstomatologicos() {
        return listadoEstomatologicos;
    }

    public void setListadoEstomatologicos(List<ListadoEstomatologico> listadoEstomatologicos) {
        this.listadoEstomatologicos = listadoEstomatologicos;
    }

    public List<ListadoExamenOral> getListadoExamenOralPulpar() {
        return listadoExamenOralPulpar;
    }

    public void setListadoExamenOralPulpar(List<ListadoExamenOral> listadoExamenOralPulpar) {
        this.listadoExamenOralPulpar = listadoExamenOralPulpar;
    }

    public List<ListadoExamenOral> getListadoExamenOralDentario() {
        return listadoExamenOralDentario;
    }

    public void setListadoExamenOralDentario(List<ListadoExamenOral> listadoExamenOralDentario) {
        this.listadoExamenOralDentario = listadoExamenOralDentario;
    }

    public List<ListadoExamenOral> getListadoExamenOralPeriodontal() {
        return listadoExamenOralPeriodontal;
    }

    public List<DiagnosticoTipo> getListaDiagnosticosTipo() {
        return listaDiagnosticosTipo;
    }

    public void setListaDiagnosticosTipo(List<DiagnosticoTipo> listaDiagnosticosTipo) {
        this.listaDiagnosticosTipo = listaDiagnosticosTipo;
    }

    public List<OdontogramaTemp> getOdontogramaPaciente() {
        return odontogramaPaciente;
    }

    public void setOdontogramaPaciente(List<OdontogramaTemp> odontogramaPaciente) {
        this.odontogramaPaciente = odontogramaPaciente;
    }

    public Paciente getPacienteApertura() {
        return pacienteApertura;
    }

    public void setPacienteApertura(Paciente pacienteApertura) {
        this.pacienteApertura = pacienteApertura;
    }

    public String getDiagnosticoDX1() {
        return diagnosticoDX1;
    }

    public void setDiagnosticoDX1(String diagnosticoDX1) {
        this.diagnosticoDX1 = diagnosticoDX1;
    }
    

    public ArrayList<DetalleServicioPresupuesto> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(ArrayList<DetalleServicioPresupuesto> listaServicios) {
        this.listaServicios = listaServicios;
    }


    public DescuentoOdo getDescuento() {
        return descuento;
    }

    public void setDescuento(DescuentoOdo descuento) {
        this.descuento = descuento;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteAdultoSuperior() {
        return odontogramaPacienteAdultoSuperior;
    }

    public void setOdontogramaPacienteAdultoSuperior(List<OdontogramaTemp> odontogramaPacienteAdultoSuperior) {
        this.odontogramaPacienteAdultoSuperior = odontogramaPacienteAdultoSuperior;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteAdultoInferior() {
        return odontogramaPacienteAdultoInferior;
    }

    public void setOdontogramaPacienteAdultoInferior(List<OdontogramaTemp> odontogramaPacienteAdultoInferior) {
        this.odontogramaPacienteAdultoInferior = odontogramaPacienteAdultoInferior;
    }

    public String getDescripcionEvolucion() {
        return descripcionEvolucion;
    }

    public void setDescripcionEvolucion(String descripcionEvolucion) {
        this.descripcionEvolucion = descripcionEvolucion;
    }

    public int getDiente() {
        return diente;
    }

    public void setDiente(int diente) {
        this.diente = diente;
    }

    public void setListadoExamenOralPeriodontal(List<ListadoExamenOral> listadoExamenOralPeriodontal) {
        this.listadoExamenOralPeriodontal = listadoExamenOralPeriodontal;
    }

    public RespuestasAntecedentesFamiliares getRespuestasAntecedentesFamiliares() {
        return respuestasAntecedentesFamiliares;
    }

    public void setRespuestasAntecedentesFamiliares(RespuestasAntecedentesFamiliares respuestasAntecedentesFamiliares) {
        this.respuestasAntecedentesFamiliares = respuestasAntecedentesFamiliares;
    }

    public RespuestasExamenOral getRespuestasExamenOral() {
        return respuestasExamenOral;
    }

    public void setRespuestasExamenOral(RespuestasExamenOral respuestasExamenOral) {
        this.respuestasExamenOral = respuestasExamenOral;
    }

    public String getRespuestaAntOdo() {
        return respuestaAntOdo;
    }

    public void setRespuestaAntOdo(String respuestaAntOdo) {
        this.respuestaAntOdo = respuestaAntOdo;
    }

    public String getObservacionAntecendetesFam() {
        return observacionAntecendetesFam;
    }

    public void setObservacionAntecendetesFam(String observacionAntecendetesFam) {
        this.observacionAntecendetesFam = observacionAntecendetesFam;
    }

    public boolean isConAcompaniante() {
        return conAcompaniante;
    }

    public void setConAcompaniante(boolean conAcompaniante) {
        this.conAcompaniante = conAcompaniante;
    }

    public boolean isSeleccionDienteCaries() {
        return seleccionDienteCaries;
    }

    public void setSeleccionDienteCaries(boolean seleccionDienteCaries) {
        this.seleccionDienteCaries = seleccionDienteCaries;
    }

    public boolean isSeleccionDienteConSellante() {
        return seleccionDienteConSellante;
    }

    public void setSeleccionDienteConSellante(boolean seleccionDienteConSellante) {
        this.seleccionDienteConSellante = seleccionDienteConSellante;
    }

    public boolean isSeleccionDienteSinSellante() {
        return seleccionDienteSinSellante;
    }

    public void setSeleccionDienteSinSellante(boolean seleccionDienteSinSellante) {
        this.seleccionDienteSinSellante = seleccionDienteSinSellante;
    }

    public boolean isSeleccionDienteFaltante() {
        return seleccionDienteFaltante;
    }

    public void setSeleccionDienteFaltante(boolean seleccionDienteFaltante) {
        this.seleccionDienteFaltante = seleccionDienteFaltante;
    }

    public boolean isSeleccionDienteFaltanteExt() {
        return seleccionDienteFaltanteExt;
    }

    public void setSeleccionDienteFaltanteExt(boolean seleccionDienteFaltanteExt) {
        this.seleccionDienteFaltanteExt = seleccionDienteFaltanteExt;
    }

    public boolean isSeleccionDienteExodoncia() {
        return seleccionDienteExodoncia;
    }

    public void setSeleccionDienteExodoncia(boolean seleccionDienteExodoncia) {
        this.seleccionDienteExodoncia = seleccionDienteExodoncia;
    }

    public boolean isSeleccionDienteProtesis() {
        return seleccionDienteProtesis;
    }

    public void setSeleccionDienteProtesis(boolean seleccionDienteProtesis) {
        this.seleccionDienteProtesis = seleccionDienteProtesis;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteInfantilSuperior() {
        return odontogramaPacienteInfantilSuperior;
    }

    public void setOdontogramaPacienteInfantilSuperior(List<OdontogramaTemp> odontogramaPacienteInfantilSuperior) {
        this.odontogramaPacienteInfantilSuperior = odontogramaPacienteInfantilSuperior;
    }

    public List<OdontogramaTemp> getOdontogramaPacienteInfantilInferior() {
        return odontogramaPacienteInfantilInferior;
    }

    public void setOdontogramaPacienteInfantilInferior(List<OdontogramaTemp> odontogramaPacienteInfantilInferior) {
        this.odontogramaPacienteInfantilInferior = odontogramaPacienteInfantilInferior;
    }

    public boolean isSeleccionDienteProtesisTotal() {
        return seleccionDienteProtesisTotal;
    }

    public void setSeleccionDienteProtesisTotal(boolean seleccionDienteProtesisTotal) {
        this.seleccionDienteProtesisTotal = seleccionDienteProtesisTotal;
    }

    public boolean isSeleccionDienteNecendodoncia() {
        return seleccionDienteNecendodoncia;
    }

    public void setSeleccionDienteNecendodoncia(boolean seleccionDienteNecendodoncia) {
        this.seleccionDienteNecendodoncia = seleccionDienteNecendodoncia;
    }

    public boolean isSeleccionDienteTtoEndodoncia() {
        return seleccionDienteTtoEndodoncia;
    }

    public void setSeleccionDienteTtoEndodoncia(boolean seleccionDienteTtoEndodoncia) {
        this.seleccionDienteTtoEndodoncia = seleccionDienteTtoEndodoncia;
    }

    public boolean isSeleccionDienteBolsaPer() {
        return seleccionDienteBolsaPer;
    }

    public void setSeleccionDienteBolsaPer(boolean seleccionDienteBolsaPer) {
        this.seleccionDienteBolsaPer = seleccionDienteBolsaPer;
    }

    public boolean isConExamenTejidosDent() {
        return conExamenTejidosDent;
    }

    public boolean isSeleccionDienteObstTemporal() {
        return seleccionDienteObstTemporal;
    }

    public void setSeleccionDienteObstTemporal(boolean seleccionDienteObstTemporal) {
        this.seleccionDienteObstTemporal = seleccionDienteObstTemporal;
    }

    public void setConExamenTejidosDent(boolean conExamenTejidosDent) {
        this.conExamenTejidosDent = conExamenTejidosDent;
    }

    public String getBusquedaPaciente() {
        return busquedaPaciente;
    }

    public void setBusquedaPaciente(String busquedaPaciente) {
        this.busquedaPaciente = busquedaPaciente;
    }

    public RespuestasExamenEstomatologico getRespuestasExamenEstomatologico() {
        return respuestasExamenEstomatologico;
    }

    public void setRespuestasExamenEstomatologico(RespuestasExamenEstomatologico respuestasExamenEstomatologico) {
        this.respuestasExamenEstomatologico = respuestasExamenEstomatologico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public boolean isConAntecedentesFamiliares() {
        return conAntecedentesFamiliares;
    }

    public void setConAntecedentesFamiliares(boolean conAntecedentesFamiliares) {
        this.conAntecedentesFamiliares = conAntecedentesFamiliares;
    }

    public boolean isConAntecedentesPersonales() {
        return conAntecedentesPersonales;
    }

    public boolean isConExamenPulpar() {
        return conExamenPulpar;
    }

    public void setConExamenPulpar(boolean conExamenPulpar) {
        this.conExamenPulpar = conExamenPulpar;
    }

    public boolean isConExamenEstomatologico() {
        return conExamenEstomatologico;
    }

    public boolean isConAlteraciones() {
        return conAlteraciones;
    }

    public boolean isSeleccionDienteObstPlastica() {
        return seleccionDienteObstPlastica;
    }

    public void setSeleccionDienteObstPlastica(boolean seleccionDienteObstPlastica) {
        this.seleccionDienteObstPlastica = seleccionDienteObstPlastica;
    }

    public boolean isSeleccionDienteNormal() {
        return seleccionDienteNormal;
    }

    public void setSeleccionDienteNormal(boolean seleccionDienteNormal) {
        this.seleccionDienteNormal = seleccionDienteNormal;
    }

    public void setConAlteraciones(boolean conAlteraciones) {
        this.conAlteraciones = conAlteraciones;
    }

    public boolean isSeleccionDienteAmalgama() {
        return seleccionDienteAmalgama;
    }

    public void setSeleccionDienteAmalgama(boolean seleccionDienteAmalgama) {
        this.seleccionDienteAmalgama = seleccionDienteAmalgama;
    }

    public void setConExamneEstomatologico(boolean conExamenEstomatologico) {
        this.conExamenEstomatologico = conExamenEstomatologico;
    }

    public void setConAntecedentesPersonales(boolean conAntecedentesPersonales) {
        this.conAntecedentesPersonales = conAntecedentesPersonales;
    }

    public String getObservacionAntecendetesPer() {
        return observacionAntecendetesPer;
    }

    public void setObservacionAntecendetesPer(String observacionAntecendetesPer) {
        this.observacionAntecendetesPer = observacionAntecendetesPer;
    }

    public String getObservacionGenExmEst() {
        return observacionGenExmEst;
    }

    public void setObservacionGenExmEst(String observacionGenExmEst) {
        this.observacionGenExmEst = observacionGenExmEst;
    }

    public RespuestasAntecedentesPersonales getRespuestasAntecedentesPersonales() {
        return respuestasAntecedentesPersonales;
    }

    public void setRespuestasAntecedentesPersonales(RespuestasAntecedentesPersonales respuestasAntecedentesPersonales) {
        this.respuestasAntecedentesPersonales = respuestasAntecedentesPersonales;
    }

    public Higiene getHigiene() {
        return higiene;
    }

    public void setHigiene(Higiene higiene) {
        this.higiene = higiene;
    }

    public boolean isPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(boolean pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }

    public SimpleDateFormat getFormatoFecha() {
        return formatoFecha;
    }

    public void setFormatoFecha(SimpleDateFormat formatoFecha) {
        this.formatoFecha = formatoFecha;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void abrirMensajeAdvertencia(DiagnosticoTipo diagnosticoTipo) {
        this.diagnosticoTipo = diagnosticoTipo;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Adventencia", "¿Esta seguro de eliminar el diagnóstico?"));
        requestContext.execute("PF('dlgMensajeAdventencia').show()");

    }

    public void abrirMensajeAdvertenciaServicio(DetalleServicioPresupuesto detallePresupuesto) {
        this.detalleServicioPresupuestoEliminar = detallePresupuesto;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Adventencia", "¿Esta seguro de eliminar el servicio?"));
        requestContext.execute("PF('dlgMensajeAdventenciaServicio').show()");

    }

    public void seleccionarPaciente(Paciente paciente) {
        this.tipoActualizacion = "Apertura";
        this.pacienteSeleccionado = true;
        this.paciente = paciente;
        this.fechaRegistro = formatoFecha.format(this.paciente.getFechaRegistro());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("InformacionPaciente");
        requestContext.update("aperturaHistoriaOdontologica");
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

    }

    public void registrarSeleccionarPaciente() {
        pacienteApertura.setTipoIdentificacion(tipoIdentificacion);
        pacienteApertura.setEstado("1");
        pacienteApertura.setFechaRegistro(asignarFechaApertura());
        ejbPaciente.create(pacienteApertura);
        this.tipoActualizacion = "Apertura";
        this.pacienteSeleccionado = true;
        this.paciente = pacienteApertura;
        this.fechaRegistro = formatoFecha.format(this.paciente.getFechaRegistro());
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        pacienteApertura  = new Paciente();
        requestContext.update("InformacionPaciente");
        requestContext.update("aperturaHistoriaOdontologica");
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

    }

    private Date asignarFechaApertura() {
        GregorianCalendar c = new GregorianCalendar();
        return c.getTime();
    }

    public void cambiarEstadoBool() {
        this.pacienteSeleccionado = false;

    }

    public void respuesta(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAcompaniante = true;
        } else {
            conAcompaniante = false;
        }

    }

    public void respuestaAcompanante(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAcompaniante = true;
        } else {
            conAcompaniante = false;
        }

    }

    public void respuestaAntecedenteF(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAntecedentesFamiliares = true;
        } else {
            conAntecedentesFamiliares = false;
        }

    }

    public void iniciarOdontograma() {

    }

    public void respuestaExamenE(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenEstomatologico = true;
        } else {
            conExamenEstomatologico = false;
        }

    }

    public void respuestaExamenPulpar(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenPulpar = true;
        } else {
            conExamenPulpar = false;
        }

    }

    public void respuestaTejidosDentarios(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conExamenTejidosDent = true;
        } else {
            conExamenTejidosDent = false;
        }
    }

    public void respuestaAlteraciones(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAlteraciones = true;
        } else {
            conAlteraciones = false;
        }

    }

    public void respuestaAntecedenteP(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAntecedentesPersonales = true;
        } else {
            conAntecedentesPersonales = false;
        }

    }

    public void cargarAntecedentesFamiliares() {
        List<ListadoAntOdon> listaAntecedentesOdon = ejbListadoAntecedentesOdon.findAll();

        for (ListadoAntOdon listadoAntOdon : listaAntecedentesOdon) {

            if (listadoAntOdon.getFamiliar().equalsIgnoreCase("S")) {
                listadoAntecedentesFamiliares.add(listadoAntOdon);
            }
        }

    }

    public void cargarAntecedentesPersonales() {
        List<ListadoAntOdon> listaAntecedentesOdon = ejbListadoAntecedentesOdon.findAll();
        for (ListadoAntOdon listadoAntOdon : listaAntecedentesOdon) {
            if (listadoAntOdon.getPersonal().equalsIgnoreCase("S")) {
                listadoAntecedentesPersonales.add(listadoAntOdon);
            }
        }

    }

    public void cargarEstomatologicos() {
        this.listadoEstomatologicos = ejbListadoEstomatologicos.findAll();

    }

    public void cargarListaExamenOralPulpar() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getPulpar().equalsIgnoreCase("S")) {
                listadoExamenOralPulpar.add(listadoExamenOral);
            }
        }

    }

    public void cargarListaExamenOralDentario() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getDentarios().equalsIgnoreCase("S")) {
                listadoExamenOralDentario.add(listadoExamenOral);
            }
        }

    }

    public void cargarListaExamenOralPeriodontal() {
        List<ListadoExamenOral> listaExamenOral = ejbListadoExamenOral.findAll();
        for (ListadoExamenOral listadoExamenOral : listaExamenOral) {
            if (listadoExamenOral.getPeriodontales().equalsIgnoreCase("S")) {
                listadoExamenOralPeriodontal.add(listadoExamenOral);
            }
        }

    }

    public void guardarAperturaHistoria() {
        registrarActualizacion();//registro actualizacion 
        registrarMotivoConsulta();//registro motivo de consulta
        registrarAntecedentesFamiliares();//Registro antecendentes familiares
        registrarAntecedentesPersonales();//Registro antecendentes personales
        registrarExamenEstomatologico();//Registro examen estomatologico
        registrarExamenOral();//registro examen oral
        registrarHigieneOral();//registro de higiene oral
        registrarOdontogramaCopia();//registro odontograma
        registrarDiagnostico();//registro diagnostico
        registrarEvolucion();//registro evolucion
        registrarObservacionOdontograma();
        registrarCuadroSintesis();
        registrarPresupuesto();

        RequestContext requestContext = RequestContext.getCurrentInstance();

        reiniciarVariables();

        requestContext.update("aperturaHistoriaOdontologica");
        requestContext.execute("PF('registroExitosoDialog').show()");
    }

    private void asignarFecha() {
        GregorianCalendar c = new GregorianCalendar();
        fechaApertura = c.getTime();

    }

    public void cargarRespuestasAntecedentesFam() {
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta1());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta2());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta3());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta4());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta5());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta6());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta7());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta8());
        this.listadoRespuestasAntFam.add(respuestasAntecedentesFamiliares.getRespuesta9());
    }

    public void cargarRespuestasAntecedentesPer() {
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta1());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta2());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta3());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta4());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta5());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta6());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta7());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta8());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta9());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta10());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta11());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta12());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta13());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta14());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta15());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta16());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta17());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta18());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta19());
        this.listadoRespuestasAntPer.add(respuestasAntecedentesPersonales.getRespuesta20());
    }

    public void cargarRespuestasExamenEst() {
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta1());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta2());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta3());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta4());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta5());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta6());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta7());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta8());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta9());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta10());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta11());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta12());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta13());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta14());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta15());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta16());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta17());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta18());
        this.listadoRespuestasExmEst.add(respuestasExamenEstomatologico.getRespuesta19());

    }

    public void cargarRespuestasObsExamenEst() {
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion1());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion2());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion3());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion4());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion5());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion6());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion7());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion8());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion9());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion10());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion11());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion12());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion13());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion14());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion15());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion16());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion17());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion18());
        this.listadoObservacionesExmEst.add(respuestasExamenEstomatologico.getObservacion19());
    }

    public void cargarRespuestasExamenOral() {
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta1());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta2());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta3());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta4());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta5());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta6());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta7());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta8());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta9());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta10());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta11());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta12());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta13());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta14());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta15());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getRespuesta16());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionExamenPulpar());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionTejidosDentarios());
        this.listadoRespuestasExamenOral.add(respuestasExamenOral.getObservacionAlteracionesPer());

    }

    public void volverAInicio(CargarVistaController cargarVistaController) {
        cargarVistaController.cargarInicio();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('registroExitosoDialog').hide()");

    }

    public void cambiarSeleccionDiagnosticoDent(String diagnosticoDiente) {
        this.diagnosticoDiente = diagnosticoDiente;

        inicializarSeleccion();
        if (this.diagnosticoDiente.compareTo("normal") == 0) {
            seleccionDienteNormal = true;
        } else if (this.diagnosticoDiente.compareTo("caries") == 0) {
            seleccionDienteCaries = true;
        } else if (this.diagnosticoDiente.compareTo("amalgama") == 0) {
            seleccionDienteAmalgama = true;
        } else if (this.diagnosticoDiente.compareTo("obst_plastica") == 0) {
            seleccionDienteObstPlastica = true;
        } else if (this.diagnosticoDiente.compareTo("obst_temporal") == 0) {
            seleccionDienteObstTemporal = true;
        } else if (this.diagnosticoDiente.compareTo("sellante") == 0) {
            this.seleccionDienteConSellante = true;
        } else if (this.diagnosticoDiente.compareTo("sinsellante") == 0) {
            this.seleccionDienteSinSellante = true;
        } else if (this.diagnosticoDiente.compareTo("faltante") == 0) {
            this.seleccionDienteFaltante = true;
        } else if (this.diagnosticoDiente.compareTo("faltante_ext") == 0) {
            this.seleccionDienteFaltanteExt = true;
        } else if (this.diagnosticoDiente.compareTo("exodoncia") == 0) {
            this.seleccionDienteExodoncia = true;
        } else if (this.diagnosticoDiente.compareTo("protesis") == 0) {
            this.seleccionDienteProtesis = true;
        } else if (this.diagnosticoDiente.compareTo("protesistotal") == 0) {
            this.seleccionDienteProtesisTotal = true;
        } else if (this.diagnosticoDiente.compareTo("necendodoncia") == 0) {
            this.seleccionDienteNecendodoncia = true;
        } else if (this.diagnosticoDiente.compareTo("ttoendodoncia") == 0) {
            this.seleccionDienteTtoEndodoncia = true;
        } else if (this.diagnosticoDiente.compareTo("bolsa_per") == 0) {
            this.seleccionDienteBolsaPer = true;
        }
        /*switch (diagnosticoDiente) {
            case "normal":
                seleccionDienteNormal = true;
                break;
            case "caries":
                seleccionDienteCaries = true;
                break;
            case "amalgama":
                seleccionDienteAmalgama = true;
                break;
            case "obst_plastica":
                seleccionDienteObstPlastica = true;
                break;
            case "obst_temporal":
                seleccionDienteObstTemporal = true;
                break;
            case "sellante":
                this.seleccionDienteConSellante = true;
                break;
            case "sinsellante":
                this.seleccionDienteSinSellante = true;
                break;
            case "faltante":
                this.seleccionDienteFaltante = true;
                break;
            case "faltante_ext":
                this.seleccionDienteFaltanteExt = true;
                break;
            case "exodoncia":
                this.seleccionDienteExodoncia = true;
                break;
            case "protesis":
                this.seleccionDienteProtesis = true;
                break;
            case "protesistotal":
                this.seleccionDienteProtesisTotal = true;
                break;
            case "necendodoncia":
                this.seleccionDienteNecendodoncia = true;
                break;
            case "ttoendodoncia":
                this.seleccionDienteTtoEndodoncia = true;
                break;
            case "bolsa_per":
                this.seleccionDienteBolsaPer = true;
                break;
        }
         */
    }

    public void cambiarImgOdontogramaF(String posicion, int posDienteList, String lista) {
        OdontogramaTemp odontogramaTemp = new OdontogramaTemp();
        String nombreImagen = posicion;

        if (diagnosticoDiente.equalsIgnoreCase("caries")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarCariesCopia(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            verificarConteoCOP(posDienteList, lista);

        } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }

                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
            contarDientesPerdidos(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

            contarDientesPerdidos(posDienteList, lista);

        } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {

            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoSuperior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteAdultoSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteAdultoInferior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteAdultoInferior.set(posDienteList, odontogramaTemp);
            }
        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("aperturaHistoriaOdontologica:odontogramaGrid");
        requestContext.update("aperturaHistoriaOdontologica:pnlCuadro");
        requestContext.update("aperturaHistoriaOdontologica:pnlOclusion");
    }

    public void cambiarImgOdontogramaInfantil(String posicion, int posDienteList, String lista) {
        OdontogramaTemp odontogramaTemp = new OdontogramaTemp();
        String nombreImagen = posicion;

        if (diagnosticoDiente.equalsIgnoreCase("caries")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }

                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_caries.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_caries.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

            contarCariesCopia(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }

                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_normal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_normal.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }

                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_amalgama.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_amalgama.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_plastica.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_plastica.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
            contarObturados(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }

                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_obst_temporal.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_obst_temporal.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sellante.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                if (nombreImagen.compareTo("arriba") == 0) {
                    odontogramaTemp.setArriba(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("abajo") == 0) {
                    odontogramaTemp.setAbajo(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("centro") == 0) {
                    odontogramaTemp.setCentro(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("der") == 0) {
                    odontogramaTemp.setDerecha(nombreImagen + "_sinsellante.png");
                } else if (nombreImagen.compareTo("izq") == 0) {
                    odontogramaTemp.setIzquierda(nombreImagen + "_sinsellante.png");
                }
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante.png");
                odontogramaTemp.setAbajo("abajo_faltante.png");
                odontogramaTemp.setCentro("centro_faltante.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
            contarDientesPerdidos(posDienteList, lista);
        } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_faltante_ext.png");
                odontogramaTemp.setAbajo("abajo_faltante_ext.png");
                odontogramaTemp.setCentro("centro_faltante_ext.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

            contarDientesPerdidos(posDienteList, lista);

        } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_exodoncia.png");
                odontogramaTemp.setAbajo("abajo_exodoncia.png");
                odontogramaTemp.setCentro("centro_exodoncia.png");
                odontogramaTemp.setIzquierda("izq_exodoncia.png");
                odontogramaTemp.setDerecha("der_exodoncia.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {

            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesis.png");
                odontogramaTemp.setAbajo("abajo_protesis.png");
                odontogramaTemp.setCentro("centro_protesis.png");
                odontogramaTemp.setIzquierda("izq_protesis.png");
                odontogramaTemp.setDerecha("der_protesis.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setArriba("arriba_protesistotal.png");
                odontogramaTemp.setAbajo("abajo_protesistotal.png");
                odontogramaTemp.setCentro("centro_protesistotal.png");
                odontogramaTemp.setIzquierda("izq_protesistotal.png");
                odontogramaTemp.setDerecha("der_protesistotal.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setAfueraNecEnd("necendodoncia_A.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
        } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setAfueraEnd("ttoendodoncia_A.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }

        } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
            if (lista.compareTo("AdultoSuperior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilSuperior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteInfantilSuperior.set(posDienteList, odontogramaTemp);
            } else if (lista.compareTo("AdultoInferior") == 0) {
                odontogramaTemp = odontogramaPacienteInfantilInferior.get(posDienteList);
                odontogramaTemp.setAfueraBolsaPer("bolsa_per_A.png");
                odontogramaPacienteInfantilInferior.set(posDienteList, odontogramaTemp);
            }
        }
    }

    private void verificarConteoCOP(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        boolean validacionDienteNormalSuperior = false;
        boolean validacionDienteConCariesSuperior = false;
        boolean validacionDienteConObturacionSuperior = false;
        boolean validacionDienteNormalInferior = false;
        boolean validacionDienteConCariesInferior = false;
        boolean validacionDienteConObturacionInferior = false;
        if (posDiente >= 0 && posDiente <= 15) {
            validacionDienteNormalSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_normal.png") == 0
                    && odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_normal.png") == 0;
            validacionDienteConCariesSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_caries.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_caries.png") == 0;
            validacionDienteConObturacionSuperior = odontogramaPacienteAdultoSuperior.get(posDiente).getAbajo().compareTo("abajo_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getArriba().compareTo("arriba_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getIzquierda().compareTo("izq_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getDerecha().compareTo("der_amalgama.png") == 0
                    || odontogramaPacienteAdultoSuperior.get(posDiente).getCentro().compareTo("centro_amalgama.png") == 0;
        } else if (posDiente > 15 && posDiente <= 31) {
            validacionDienteNormalInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_normal.png") == 0
                    && odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_normal.png") == 0;
            validacionDienteConCariesInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_caries.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_caries.png") == 0;
            validacionDienteConObturacionInferior = odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_amalgama.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_amalgama.png") == 0
                    ||odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_obst_plastica.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_obst_plastica.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_obst_plastica.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_obst_plastica.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_obst_plastica.png") == 0
                    ||odontogramaPacienteAdultoInferior.get(posDiente).getAbajo().compareTo("abajo_obst_temporal.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getArriba().compareTo("arriba_obst_temporal.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getIzquierda().compareTo("izq_obst_temporal.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getDerecha().compareTo("der_obst_temporal.png") == 0
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_obst_temporal.png") == 0
                    ;
        }

        if (posDiente >= 0 && posDiente <= 15 && validacionDienteNormalSuperior || posDiente >= 0 && posDiente <= 15 && validacionDienteConObturacionSuperior && !validacionDienteConCariesSuperior) {
            if (contadoresCariesDiente.get(posDiente)) {
                this.contadoresCariesDiente.set(posDiente, false);
                int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) - 1;
                int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) - 1;
                this.obsOdontograma.setCaries("" + contadorCaries);
                this.cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
            } else if (contadoresObturadosDiente.get(posDiente)) {
                contadoresObturadosDiente.set(posDiente, false);
                int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) - 1;
                obsOdontograma.setObturados("" + contadorObturados);
                cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
            } else if (contadoresPerdidosDiente.get(posDiente)) {
                contadoresPerdidosDiente.set(posDiente, false);
                int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorPerdidosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) - 1;
                obsOdontograma.setPerdidos("" + contadorPerdidos);
                cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperios);
            }

        } else if (posDiente > 15 && posDiente <= 31 && validacionDienteNormalInferior || posDiente > 15 && posDiente <= 31 && validacionDienteConCariesInferior && !validacionDienteConObturacionInferior) {
            if (contadoresCariesDiente.get(posDiente)) {
                contadoresCariesDiente.set(posDiente, false);
                int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) - 1;
                int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) - 1;
                obsOdontograma.setCaries("" + contadorCaries);
                cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
            } else if (contadoresObturadosDiente.get(posDiente)) {
                contadoresObturadosDiente.set(posDiente, false);
                int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) - 1;
                obsOdontograma.setObturados("" + contadorObturados);
                cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
            } else if (contadoresPerdidosDiente.get(posDiente)) {
                contadoresPerdidosDiente.set(posDiente, false);
                int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) - 1;
                int contadorPerdidosInf = Integer.parseInt(cuadroSintesis.getObturacionInf()) - 1;
                obsOdontograma.setPerdidos("" + contadorPerdidos);
                cuadroSintesis.setFaltantesInf("" + contadorPerdidosInf);
            }
        }

    }

    private void contarCariesCopia(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresCariesDiente.get(posDiente)) {
            contadoresCariesDiente.set(posDiente, true);
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresCariesDiente.get(posDiente)) {
            contadoresCariesDiente.set(posDiente, true);
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente > 31 && posDiente <= 51 && !contadoresCariesDiente.get(posDiente)) {
            contadoresCariesDiente.set(posDiente, true);
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        }
    }

    private void contarObturados(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresObturadosDiente.get(posDiente)) {
            contadoresObturadosDiente.set(posDiente, true);
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresObturadosDiente.get(posDiente)) {
            contadoresObturadosDiente.set(posDiente, true);
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente > 31 && posDiente <= 51 && !contadoresObturadosDiente.get(posDiente)) {
            contadoresObturadosDiente.set(posDiente, true);
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        }
    }

    public void contarDientesPerdidos(int posDiente, String posicion) {
        if (posicion.compareTo("AdultoInferior") == 0) {
            posDiente = posDiente + 16;
        }
        if (posDiente >= 0 && posDiente <= 15 && !contadoresPerdidosDiente.get(posDiente)) {
            contadoresPerdidosDiente.set(posDiente, true);
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorPerdidosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperios);
        } else if (posDiente > 15 && posDiente <= 31 && !contadoresPerdidosDiente.get(posDiente)) {
            contadoresPerdidosDiente.set(posDiente, true);
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorPerdidosInf = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInf);
        } else if (posDiente > 31 && posDiente <= 51 && !contadoresPerdidosDiente.get(posDiente)) {
            contadoresPerdidosDiente.set(posDiente, true);
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        }
    }

    private UsuariosSistema usuarioDeLaSesion() {
        UsuariosSistema usuario = new UsuariosSistema();
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        if (req.getUserPrincipal() != null) {
            usuario = this.usuarioEJB.buscarPorNombreUsuario(req.getUserPrincipal().getName()).get(0);
        }
        return usuario;
    }

    private void inicializarOdontograma() {
        this.odontogramaPacienteAdultoSuperior = new ArrayList<>();
        this.odontogramaPacienteAdultoInferior = new ArrayList<>();
        this.odontogramaPacienteInfantilSuperior = new ArrayList<>();
        this.odontogramaPacienteInfantilInferior = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            OdontogramaTemp odontogramaTemp = new OdontogramaTemp();
            odontogramaTemp.setArriba("arriba_normal.png");
            odontogramaTemp.setAbajo("abajo_normal.png");
            odontogramaTemp.setIzquierda("izq_normal.png");
            odontogramaTemp.setDerecha("der_normal.png");
            odontogramaTemp.setCentro("centro_normal.png");
            odontogramaTemp.setAfueraEnd("fuera.png");
            odontogramaTemp.setAfueraNecEnd("fuera.png");
            odontogramaTemp.setAfueraBolsaPer("fuera.png");
            odontogramaPaciente.add(odontogramaTemp);
            if (i >= 0 && i < 16) {
                odontogramaPacienteAdultoSuperior.add(odontogramaTemp);
            }
            if (i >= 16 && i < 32) {
                odontogramaPacienteAdultoInferior.add(odontogramaTemp);
            }

            if (i >= 32 && i < 42) {
                odontogramaPacienteInfantilSuperior.add(odontogramaTemp);
            }
            if (i >= 42 && i < 52) {
                odontogramaPacienteInfantilInferior.add(odontogramaTemp);
            }

        }
    }

    public void inicializarVariables() {
        this.contadoresCariesDiente = new ArrayList<>(52);
        this.contadoresObturadosDiente = new ArrayList<>(52);
        this.contadoresPerdidosDiente = new ArrayList<>(52);
        this.actualizacionOdo = new ActualizacionOdo();
        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.cuadroSintesis = new CuadroSintesis();
        this.diagnosticoDiente = "normal";
        for (int i = 0; i < 52; i++) {
            contadoresCariesDiente.add(false);
            contadoresObturadosDiente.add(false);
            contadoresPerdidosDiente.add(false);
        }

        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
        inicializarCuadroSintesis();
    }

    public void inicializarVariablesCancelar(CargarVistaController cargarVistaController) {
        cargarVistaController.cargarInicio();
        reiniciarVariables();
    }

    public boolean comprobarInicioApertura() {
        boolean iniciada = false;
        if (conAcompaniante == true) {
            iniciada = true;
        } else {
            if (motivoConsulta != null) {
                if (!motivoConsulta.equalsIgnoreCase("")) {
                    iniciada = true;
                } else if (motivoConsulta.equalsIgnoreCase("")) {
                    iniciada = false;
                }
            }
            if (motivoConsulta == null) {
                iniciada = false;
            }
        }

        if (iniciada == true) {
            FacesContext.getCurrentInstance().addMessage("msgAdvertenciaApertura", new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Usted tiene una apertura iniciada, ¿Desea continuar o inicar una nueva apertura de historia?"));
        }

        return iniciada;
    }

    private String idDiagnostico(String diagnostico) {
        List<Diagnosticocie10Odo> diagnosticosCieOdo = new ArrayList<>();
        diagnosticosCieOdo = ejbDiagnosticocie10Odo.findAll();
        String idDiagnosticoCie = "";

        for (int i = 0; i < diagnosticosCieOdo.size(); i++) {
            if (diagnostico.equalsIgnoreCase(diagnosticosCieOdo.get(i).getNombre())) {
                idDiagnosticoCie = diagnosticosCieOdo.get(i).getId();
            }
        }

        return idDiagnosticoCie;
    }

    private void registrarActualizacion() {
        if (conAcompaniante != true) {
            this.actualizacionOdo = new ActualizacionOdo();
            this.actualizacionOdo.setIdPaciente(paciente);
            this.actualizacionOdo.setAcompanante("Sin acompañante");
            this.actualizacionOdo.setTelefono("");
            this.actualizacionOdo.setCelular("");
            this.actualizacionOdo.setParentesco("");
            this.actualizacionOdo.setTipo(tipoActualizacion);
            this.actualizacionOdo.setIdUsuario(usuarioDeLaSesion());
            asignarFecha();
            this.actualizacionOdo.setFecha(fechaApertura);
        } else {
            this.actualizacionOdo.setIdPaciente(paciente);
            this.actualizacionOdo.setTipo(tipoActualizacion);
            this.actualizacionOdo.setIdUsuario(usuarioDeLaSesion());
            asignarFecha();
            this.actualizacionOdo.setFecha(fechaApertura);
            this.conAcompaniante = false;
        }

        this.ejbActualizacionOdo.create(actualizacionOdo);
    }

    private void registrarMotivoConsulta() {
        MotivoOdo motivoOdo = new MotivoOdo();

        motivoOdo.setIdActualizacion(actualizacionOdo);
        motivoOdo.setMotivo(motivoConsulta);

        ejbMotivoOdo.create(motivoOdo);
    }

    private void registrarAntecedentesFamiliares() {
        cargarRespuestasAntecedentesFam();
        for (int i = 0; i < listadoRespuestasAntFam.size(); i++) {
            AntecedenteOdo antOdo = new AntecedenteOdo();
            antOdo.setIdActualizacion(actualizacionOdo);
            antOdo.setTipo("FA");
            antOdo.setResultado(listadoRespuestasAntFam.get(i));
            antOdo.setNombre(listadoAntecedentesFamiliares.get(i).getNombre());
            ejbAntOdo.create(antOdo);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionAntecendetesFam);
        obsAntOdo.setTipo("FA");
        ejbObsAntOdo.create(obsAntOdo);
    }

    private void registrarAntecedentesPersonales() {
        cargarRespuestasAntecedentesPer();
        for (int i = 0; i < listadoRespuestasAntPer.size(); i++) {
            AntecedenteOdo antOdo = new AntecedenteOdo();
            antOdo.setIdActualizacion(actualizacionOdo);
            antOdo.setTipo("PE");
            antOdo.setResultado(listadoRespuestasAntPer.get(i));
            antOdo.setNombre(listadoAntecedentesPersonales.get(i).getNombre());
            ejbAntOdo.create(antOdo);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionAntecendetesPer);
        obsAntOdo.setTipo("PE");
        ejbObsAntOdo.create(obsAntOdo);
    }

    private void registrarExamenEstomatologico() {
        cargarRespuestasExamenEst();
        cargarRespuestasObsExamenEst();
        for (int i = 0; i < listadoRespuestasExmEst.size(); i++) {
            ExaEstomatologico exaEstomatologico = new ExaEstomatologico();
            exaEstomatologico.setIdActualizacion(actualizacionOdo);
            exaEstomatologico.setNombre(listadoEstomatologicos.get(i).getNombre());
            exaEstomatologico.setResultado(listadoRespuestasExmEst.get(i));
            exaEstomatologico.setObs(listadoObservacionesExmEst.get(i));

            ejbExaEstomatologico.create(exaEstomatologico);
        }
        ObsAntOdo obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionGenExmEst);
        obsAntOdo.setTipo("ES");
        ejbObsAntOdo.create(obsAntOdo);
    }

    private void registrarExamenOral() {
        cargarRespuestasExamenOral();
        int contExamenPulpar = 0, contExamenDentario = 0, contPeriodontal = 0;
        for (int i = 0; i < listadoRespuestasExamenOral.size(); i++) {
            ExaOral examenOral = new ExaOral();
            examenOral.setIdActualizacion(actualizacionOdo);
            if (i >= 0 && i < 4) {
                examenOral.setCampo(listadoExamenOralPulpar.get(contExamenPulpar).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PU");
                ejbExaOral.create(examenOral);
                contExamenPulpar++;

            } else if (i >= 4 && i < 9) {
                examenOral.setCampo(listadoExamenOralDentario.get(contExamenDentario).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("DE");
                ejbExaOral.create(examenOral);
                contExamenDentario++;
            } else if (i >= 9 && i < 16) {
                examenOral.setCampo(listadoExamenOralPeriodontal.get(contPeriodontal).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PE");
                ejbExaOral.create(examenOral);
                contPeriodontal++;
            } else if (i == 16) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("PU");
                ejbObsExaOral.create(obsExaOral);
            } else if (i == 17) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("DE");
                ejbObsExaOral.create(obsExaOral);
            } else if (i == 18) {
                ObsExaOral obsExaOral = new ObsExaOral();
                obsExaOral.setIdActualizacion(actualizacionOdo);
                obsExaOral.setObs(listadoRespuestasExamenOral.get(i));
                obsExaOral.setTipo("PE");
                ejbObsExaOral.create(obsExaOral);
            }
        }
    }

    private void registrarHigieneOral() {
        this.higiene.setIdActualizacion(actualizacionOdo);
        this.ejbHigiene.create(higiene);
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    /*
    private void registrarOdontograma() {
        int contDientesArribaIzq = 18, contDientesArribaDer = 21, contDientesAbajoIzq = 48, contDientesAbajoDer = 31;
        int contDientesCentroAIzq = 55, contDientesCentroADer = 61, contDientesCentroAbIzq = 85, contDientesCentroAbDer = 71;
        for (int i = 0; i < 52; i++) {
            Odontograma odontograma = new Odontograma();
            if (i >= 0 && i < 8) {
                odontograma.setDiente("" + contDientesArribaIzq);
                contDientesArribaIzq--;
            } else if (i >= 8 && i < 16) {
                odontograma.setDiente("" + contDientesArribaDer);
                contDientesArribaDer++;
            } else if (i >= 16 && i < 24) {
                odontograma.setDiente("" + contDientesAbajoIzq);
                contDientesAbajoIzq--;
            } else if (i >= 24 && i < 32) {
                odontograma.setDiente("" + contDientesAbajoDer);
                contDientesAbajoDer++;
            } else if (i >= 32 && i < 37) {
                odontograma.setDiente("" + contDientesCentroAIzq);
                contDientesCentroAIzq--;
            } else if (i >= 37 && i < 42) {
                odontograma.setDiente("" + contDientesCentroADer);
                contDientesCentroADer++;
            } else if (i >= 42 && i < 47) {
                odontograma.setDiente("" + contDientesCentroAbIzq);
                contDientesCentroAbIzq--;
            } else if (i >= 47 && i < 52) {
                odontograma.setDiente("" + contDientesCentroAbDer);
                contDientesCentroAbDer++;
            }

            odontograma.setIdActualizacion(actualizacionOdo);
            odontograma.setImgAbajo(listaOdontogramaAbajo.get(i));
            odontograma.setImgArriba(listaOdontogramaArriba.get(i));
            odontograma.setImgCentro(listaOdontogramaCentro.get(i));
            odontograma.setImgIzq(listaOdontogramaIzquierda.get(i));
            odontograma.setImgDer(listaOdontogramaDerecha.get(i));
            odontograma.setImgFueraBolPeri(listaOdontogramaFueraBolsaPer.get(i));
            odontograma.setImgFueraEnd(listaOdontogramaFueraEndodoncia.get(i));
            odontograma.setImgFueraNEnd(listaOdontogramaFueraNecEndodoncia.get(i));
            ejbOdontograma.create(odontograma);
        }
    }
     */
    private void registrarOdontogramaCopia() {
        int contDientesArribaIzq = 18, contDientesArribaDer = 21, contDientesAbajoIzq = 48, contDientesAbajoDer = 31;
        int contDientesCentroAIzq = 55, contDientesCentroADer = 61, contDientesCentroAbIzq = 85, contDientesCentroAbDer = 71;
        for (int i = 0; i < 16; i++) {
            Odontograma odontograma = new Odontograma();
            if (i >= 0 && i < 8) {
                odontograma.setDiente("" + contDientesArribaIzq);
                contDientesArribaIzq--;
            } else if (i >= 8 && i < 16) {
                odontograma.setDiente("" + contDientesArribaDer);
                contDientesArribaDer++;
            }
            odontograma.setIdActualizacion(actualizacionOdo);
            odontograma.setImgAbajo(odontogramaPacienteAdultoSuperior.get(i).getAbajo());
            odontograma.setImgArriba(odontogramaPacienteAdultoSuperior.get(i).getArriba());
            odontograma.setImgCentro(odontogramaPacienteAdultoSuperior.get(i).getCentro());
            odontograma.setImgIzq(odontogramaPacienteAdultoSuperior.get(i).getIzquierda());
            odontograma.setImgDer(odontogramaPacienteAdultoSuperior.get(i).getDerecha());
            odontograma.setImgFueraBolPeri(odontogramaPacienteAdultoSuperior.get(i).getAfueraBolsaPer());
            odontograma.setImgFueraEnd(odontogramaPacienteAdultoSuperior.get(i).getAfueraEnd());
            odontograma.setImgFueraNEnd(odontogramaPacienteAdultoSuperior.get(i).getAfueraNecEnd());
            ejbOdontograma.create(odontograma);
        }
        for (int i = 0; i < 16; i++) {
            Odontograma odontograma = new Odontograma();
            if (i >= 0 && i < 8) {
                odontograma.setDiente("" + contDientesAbajoIzq);
                contDientesAbajoIzq--;
            } else if (i >= 8 && i < 16) {
                odontograma.setDiente("" + contDientesAbajoDer);
                contDientesAbajoDer++;
            }

            odontograma.setIdActualizacion(actualizacionOdo);
            odontograma.setImgAbajo(odontogramaPacienteAdultoInferior.get(i).getAbajo());
            odontograma.setImgArriba(odontogramaPacienteAdultoInferior.get(i).getArriba());
            odontograma.setImgCentro(odontogramaPacienteAdultoInferior.get(i).getCentro());
            odontograma.setImgIzq(odontogramaPacienteAdultoInferior.get(i).getIzquierda());
            odontograma.setImgDer(odontogramaPacienteAdultoInferior.get(i).getDerecha());
            odontograma.setImgFueraBolPeri(odontogramaPacienteAdultoInferior.get(i).getAfueraBolsaPer());
            odontograma.setImgFueraEnd(odontogramaPacienteAdultoInferior.get(i).getAfueraEnd());
            odontograma.setImgFueraNEnd(odontogramaPacienteAdultoInferior.get(i).getAfueraNecEnd());
            ejbOdontograma.create(odontograma);
        }
        if (!getEsAdulto()) {
            for (int i = 0; i < 10; i++) {
                Odontograma odontograma = new Odontograma();
                if (i >= 0 && i < 5) {
                    odontograma.setDiente("" + contDientesCentroAIzq);
                    contDientesCentroAIzq--;
                } else if (i >= 5 && i < 10) {
                    odontograma.setDiente("" + contDientesCentroADer);
                    contDientesCentroADer++;
                }

                odontograma.setIdActualizacion(actualizacionOdo);
                odontograma.setImgAbajo(odontogramaPacienteInfantilSuperior.get(i).getAbajo());
                odontograma.setImgArriba(odontogramaPacienteInfantilSuperior.get(i).getArriba());
                odontograma.setImgCentro(odontogramaPacienteInfantilSuperior.get(i).getCentro());
                odontograma.setImgIzq(odontogramaPacienteInfantilSuperior.get(i).getIzquierda());
                odontograma.setImgDer(odontogramaPacienteInfantilSuperior.get(i).getDerecha());
                odontograma.setImgFueraBolPeri(odontogramaPacienteInfantilSuperior.get(i).getAfueraBolsaPer());
                odontograma.setImgFueraEnd(odontogramaPacienteInfantilSuperior.get(i).getAfueraEnd());
                odontograma.setImgFueraNEnd(odontogramaPacienteInfantilSuperior.get(i).getAfueraNecEnd());
                ejbOdontograma.create(odontograma);
            }
            for (int i = 0; i < 10; i++) {
                Odontograma odontograma = new Odontograma();
                if (i >= 0 && i < 5) {
                    odontograma.setDiente("" + contDientesCentroAbIzq);
                    contDientesCentroAbIzq--;
                } else if (i >= 5 && i < 10) {
                    odontograma.setDiente("" + contDientesCentroAbDer);
                    contDientesCentroAbDer++;
                }

                odontograma.setIdActualizacion(actualizacionOdo);
                odontograma.setImgAbajo(odontogramaPacienteInfantilInferior.get(i).getAbajo());
                odontograma.setImgArriba(odontogramaPacienteInfantilInferior.get(i).getArriba());
                odontograma.setImgCentro(odontogramaPacienteInfantilInferior.get(i).getCentro());
                odontograma.setImgIzq(odontogramaPacienteInfantilInferior.get(i).getIzquierda());
                odontograma.setImgDer(odontogramaPacienteInfantilInferior.get(i).getDerecha());
                odontograma.setImgFueraBolPeri(odontogramaPacienteInfantilInferior.get(i).getAfueraBolsaPer());
                odontograma.setImgFueraEnd(odontogramaPacienteInfantilInferior.get(i).getAfueraEnd());
                odontograma.setImgFueraNEnd(odontogramaPacienteInfantilInferior.get(i).getAfueraNecEnd());
                ejbOdontograma.create(odontograma);
            }
        }
    }

    public void seleccionarDiagnostico(Diagnosticocie10Odo diagnostico) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        diagnosticoTipo = new DiagnosticoTipo();

        diagnosticoTipo.setDiagnosticoCie10(diagnostico);
        requestcontext.execute("PF('agregarDiagnosticoDialog').hide()");
        requestcontext.execute("PF('seleccionarTipoDiagnostico').show()");

    }

    public void agregarDiagnosticoTipo(TipoDiagnostico tipoDiagnostico) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        diagnosticoTipo.setDiagnostico(tipoDiagnostico);

        if (!validarExistenciaEnLista(diagnosticoTipo)) {
            listaDiagnosticosTipo.add(diagnosticoTipo);
        }

        requestcontext.update("aperturaHistoriaOdontologica:datalistDiagnosticosTipo");
        requestcontext.execute("PF('seleccionarTipoDiagnostico').hide()");
        requestcontext.execute("PF('agregarDiagnosticoDialog').show()");

    }

    public void eliminarDiagnosticoTipo(DiagnosticoTipo diagnosticoTipo) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaDiagnosticosTipo.remove(diagnosticoTipo);
        requestcontext.update("aperturaHistoriaOdontologica:datalistDiagnosticosTipo");

    }

    public void eliminarDiagnosticoTipoDeLista() {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaDiagnosticosTipo.remove(this.diagnosticoTipo);
        requestcontext.update("aperturaHistoriaOdontologica:datalistDiagnosticosTipo");
        requestcontext.execute("PF('dlgMensajeAdventencia').hide()");

    }

    public void eliminarServicioDeLista() {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaServicios.remove(this.detalleServicioPresupuestoEliminar);
        calcularPrecioTotal();
        requestcontext.update("aperturaHistoriaOdontologica:datalistTratamiento");
        requestcontext.update("aperturaHistoriaOdontologica:panelPago");
        requestcontext.execute("PF('dlgMensajeAdventenciaServicio').hide()");

    }

    private void registrarDiagnostico() {

        for (int i = 0; i < listaDiagnosticosTipo.size(); i++) {
            DiagnosticoOdo diagnosticoOdo = new DiagnosticoOdo();
            diagnosticoOdo.setIdActualizacion(actualizacionOdo);
            diagnosticoOdo.setDx(listaDiagnosticosTipo.get(i).getDiagnosticoCie10().getId());
            diagnosticoOdo.setNdx(listaDiagnosticosTipo.get(i).getDiagnosticoCie10().getNombre());
            diagnosticoOdo.setTipodiagnostico(listaDiagnosticosTipo.get(i).getDiagnostico());
            ejbDiagnosticoOdo.create(diagnosticoOdo);
        }

    }

    private void registrarEvolucion() {
        EvolucionOdo evolucionOdontologica = new EvolucionOdo();
        evolucionOdontologica.setEvolucion(descripcionEvolucion);
        evolucionOdontologica.setNumeroDiente(diente);
        evolucionOdontologica.setIdActualizacion(actualizacionOdo);

        ejbEvolucionOdo.create(evolucionOdontologica);
    }

    public void reiniciarVariables() {
        this.actualizacionOdo = new ActualizacionOdo();
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;
        this.motivoConsulta = "";
        this.descripcionEvolucion = "";
        this.higiene = new Higiene();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        totalPagar = 0;
        this.paciente = new Paciente();
        this.pacienteSeleccionado = false;

        listadoAntecedentesFamiliares = new ArrayList<>();
        listadoAntecedentesPersonales = new ArrayList<>();
        listadoEstomatologicos = new ArrayList<>();
        listadoExamenOralPulpar = new ArrayList<>();
        listadoExamenOralDentario = new ArrayList<>();
        listadoExamenOralPeriodontal = new ArrayList<>();
        listadoRespuestasAntFam = new ArrayList<>();
        listadoRespuestasAntPer = new ArrayList<>();
        listadoRespuestasExmEst = new ArrayList<>();
        listadoRespuestasExamenOral = new ArrayList<>();
        listadoObservacionesExmEst = new ArrayList<>();
        listaDiagnosticosTipo = new ArrayList<>();
        listaServicios = new ArrayList<>();
        listaServiciosTemporal = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();
        inicializarCuadroSintesis();
    }

    private void inicializarCuadroSintesis() {
        this.cuadroSintesis.setPresentesSup("0");
        this.cuadroSintesis.setPresentesInf("0");
        this.cuadroSintesis.setFaltantesSup("0");
        this.cuadroSintesis.setFaltantesInf("0");
        this.cuadroSintesis.setCariadosSup("0");
        this.cuadroSintesis.setCariadosInf("0");
        this.cuadroSintesis.setExtraIndSup("0");
        this.cuadroSintesis.setExtraIndInf("0");
        this.cuadroSintesis.setObturacionSup("0");
        this.cuadroSintesis.setObturacionInf("0");
        this.cuadroSintesis.setProtesisFijaSup("0");
        this.cuadroSintesis.setProtesisFijaInf("0");
        this.cuadroSintesis.setProtesisRemSup("0");
        this.cuadroSintesis.setProtesisRemInf("0");
        this.cuadroSintesis.setProtesisTotSup("0");
        this.cuadroSintesis.setProtesisTotInf("0");
        this.cuadroSintesis.setTratamientoCondSup("0");
        this.cuadroSintesis.setTratamientoCondInf("0");
        this.cuadroSintesis.setAnomaliasNSup("0");
        this.cuadroSintesis.setAnomaliasNInf("0");
        this.cuadroSintesis.setAnomaliasForSup("0");
        this.cuadroSintesis.setAnomaliasForInf("0");
        this.cuadroSintesis.setAnomaliasPosSup("0");
        this.cuadroSintesis.setAnomaliasPosInf("0");
        this.cuadroSintesis.setEnfermedadPeriodentalSup("0");
        this.cuadroSintesis.setEnfermedadPeriodentalInf("0");
        this.cuadroSintesis.setNucleosSup("0");
        this.cuadroSintesis.setNucleosInf("0");

    }

    private void registrarObservacionOdontograma() {
        obsOdontograma.setIdActualizacion(actualizacionOdo);
        ejbObsOdontograma.create(obsOdontograma);
    }

    private void registrarCuadroSintesis() {
        this.cuadroSintesis.setIdActualizacion(actualizacionOdo);
        ejbCuadroSintesis.create(cuadroSintesis);
    }

    private void registrarPresupuesto() {
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setIdPaciente(paciente);
        presupuesto.setIdUsuario(usuarioDeLaSesion());
        presupuesto.setFecha(fechaApertura);

        ejbPresupuesto.create(presupuesto);
        for (int i = 0; i < listaServicios.size(); i++) {
            DetalleServicioPK detalleServicioPK = new DetalleServicioPK();
            detalleServicioPK.setIdPresupuesto(presupuesto.getIdPresupuesto());
            DetalleServicio detalleServicio = new DetalleServicio();
            detalleServicio.setCantidad(listaServicios.get(i).getCantidad());
            detalleServicio.setDetalleServicioPK(detalleServicioPK);
            detalleServicio.setIdServiciosOdo(listaServicios.get(i).getServicioOdo());
            detalleServicio.setPrecio(listaServicios.get(i).getPrecio());

            ejbDetalleServicio.create(detalleServicio);
        }
    }

    public boolean getEsAdulto() {
        boolean adulto = true;
        if (pacienteSeleccionado) {
            Date fechaDeNacimiento = paciente.getFechaNacimiento();
            Date fechaActual = asignarFechaApertura();

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaNac = LocalDate.parse(formatoFecha.format(fechaDeNacimiento), fmt);
            LocalDate ahora = LocalDate.now();

            Period periodo = Period.between(fechaNac, ahora);

            if (periodo.getYears() < 10) {
                adulto = false;
            }

        }

        return adulto;

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

    public void seleccionarTrataminto(ServiciosOdo serviciosOdo) {
        RequestContext requestcontext = RequestContext.getCurrentInstance();
        DetalleServicioPresupuesto detalleServicio = new DetalleServicioPresupuesto();
        detalleServicio.setServicioOdo(serviciosOdo);
        detalleServicio.setCantidad(1);
        detalleServicio.calcularPrecio();

        if (!existeServicioSeleccionado(serviciosOdo)) {
            listaServiciosTemporal.add(detalleServicio);
            copiarListas();
            calcularPrecioTotal();
            requestcontext.update("aperturaHistoriaOdontologica:datalistTratamiento");
            requestcontext.update("aperturaHistoriaOdontologica:panelPago");
        }

    }

    private boolean validarExistenciaEnLista(DiagnosticoTipo diagnosticoTipo) {
        boolean existe = false;

        for (int i = 0; i < listaDiagnosticosTipo.size(); i++) {
            if (listaDiagnosticosTipo.get(i).getDiagnosticoCie10().getNombre().equalsIgnoreCase(diagnosticoTipo.getDiagnosticoCie10().getNombre())) {
                existe = true;
            }
        }

        return existe;
    }

    private void inicializarSeleccion() {
        seleccionDienteNormal = false;
        seleccionDienteCaries = false;
        seleccionDienteAmalgama = false;
        seleccionDienteObstPlastica = false;
        seleccionDienteObstTemporal = false;
        this.seleccionDienteConSellante = false;
        this.seleccionDienteSinSellante = false;
        this.seleccionDienteFaltante = false;
        this.seleccionDienteFaltanteExt = false;
        this.seleccionDienteExodoncia = false;
        this.seleccionDienteProtesis = false;
        this.seleccionDienteProtesisTotal = false;
        this.seleccionDienteNecendodoncia = false;
        this.seleccionDienteTtoEndodoncia = false;
        this.seleccionDienteBolsaPer = false;
    }

    private boolean existeServicioSeleccionado(ServiciosOdo serviciosOdo) {
        boolean existe = false;
        for (int i = 0; i < listaServiciosTemporal.size(); i++) {
            if (listaServiciosTemporal.get(i).getServicioOdo().equals(serviciosOdo)) {
                existe = true;
            }
        }
        return existe;
    }

}
