/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.historiaodontologica.clases.RespuestasAntecedentesFamiliares;
import com.historiaodontologica.clases.RespuestasAntecedentesPersonales;
import com.historiaodontologica.clases.RespuestasExamenEstomatologico;
import com.historiaodontologica.clases.RespuestasExamenOral;
import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.AntecedenteOdo;
import com.historiaodontologica.entidades.CuadroSintesis;
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
import com.historiaodontologica.entidades.TipoDiagnostico;
import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.AntOdoFacade;
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
import com.historiaodontologica.sessionbeans.OdontogramaFacade;

import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ROED26
 */
@Named(value = "evolucionHOdontologicaController")
@SessionScoped
public class EvolucionHOdontologicaController implements Serializable {

    @EJB
    private ActualizacionOdoFacade ejbActualizacionOdo;
    @EJB
    private UsuariosSistemaFacade usuarioEJB;
    @EJB
    private EvolucionOdoFacade ejbEvolucionOdo;
    @EJB
    private Diagnosticocie10OdoFacade ejbDiagnosticocie10Odo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;
    @EJB
    private OdontogramaFacade ejbOdontograma;

    //variables booleanas
    private boolean conAcompaniante;
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
    //listas
    private List<String> listaOdontogramaArriba;
    private List<String> listaOdontogramaAbajo;
    private List<String> listaOdontogramaIzquierda;
    private List<String> listaOdontogramaDerecha;
    private List<String> listaOdontogramaCentro;
    private List<String> listaOdontogramaFueraBolsaPer;
    private List<String> listaOdontogramaFueraEndodoncia;
    private List<String> listaOdontogramaFueraNecEndodoncia;

    //String
    private String respuestaAntOdo = "No";
    private String descripcionEvolucion;
    private String busquedaPaciente;
    private String fechaRegistro;
    private String tipoActualizacion;
    private String observacionAntecendetesFam;
    private String observacionAntecendetesPer;
    private String observacionGenExmEst;
    private String diagnosticoDiente;
    private String diagnosticoPrincipal;
    private String diagnosticoDX1;
    private String diagnosticoDX2;
    private String diagnosticoDX3;
    private String arriba = "arriba_normal.png";
    private String abajo = "abajo_normal.png";
    private String izquierda = "izq_normal.png";
    private String derecha = "der_normal.png";
    private String centro = "centro_normal.png";

    //fechas
    private Date fechaApertura;
    private SimpleDateFormat formatoFecha;

    //numero
    private int diente;
    
    //objetos 
    private Paciente paciente;
    private CuadroSintesis cuadroSintesis;
    private ActualizacionOdo actualizacionOdo;
    private TipoDiagnostico tipoDiagnostico;
    private ObsOdontograma obsOdontograma;

    public EvolucionHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.conAcompaniante = false;

    }

    @PostConstruct
    private void init() {
        this.actualizacionOdo = new ActualizacionOdo();

        this.tipoDiagnostico = new TipoDiagnostico();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.cuadroSintesis = new CuadroSintesis();
        inicializarOdontograma();
        inicializarCuadroSintesis();

    }

    public ActualizacionOdo getActualizacionOdo() {
        return actualizacionOdo;
    }

    public void setActualizacionOdo(ActualizacionOdo actualizacionOdo) {
        this.actualizacionOdo = actualizacionOdo;
    }

    public List<String> getListaOdontogramaArriba() {
        return listaOdontogramaArriba;
    }

    public void setListaOdontogramaArriba(List<String> listaOdontogramaArriba) {
        this.listaOdontogramaArriba = listaOdontogramaArriba;
    }

    public List<String> getListaOdontogramaAbajo() {
        return listaOdontogramaAbajo;
    }

    public void setListaOdontogramaAbajo(List<String> listaOdontogramaAbajo) {
        this.listaOdontogramaAbajo = listaOdontogramaAbajo;
    }

    public List<String> getListaOdontogramaIzquierda() {
        return listaOdontogramaIzquierda;
    }

    public void setListaOdontogramaIzquierda(List<String> listaOdontogramaIzquierda) {
        this.listaOdontogramaIzquierda = listaOdontogramaIzquierda;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getDiagnosticoDX1() {
        return diagnosticoDX1;
    }

    public void setDiagnosticoDX1(String diagnosticoDX1) {
        this.diagnosticoDX1 = diagnosticoDX1;
    }

    public int getDiente() {
        return diente;
    }

    public void setDiente(int diente) {
        this.diente = diente;
    }

    public String getDiagnosticoDX2() {
        return diagnosticoDX2;
    }

    public void setDiagnosticoDX2(String diagnosticoDX2) {
        this.diagnosticoDX2 = diagnosticoDX2;
    }

    public String getDiagnosticoDX3() {
        return diagnosticoDX3;
    }

    public void setDiagnosticoDX3(String diagnosticoDX3) {
        this.diagnosticoDX3 = diagnosticoDX3;
    }

    public String getDescripcionEvolucion() {
        return descripcionEvolucion;
    }

    public void setDescripcionEvolucion(String descripcionEvolucion) {
        this.descripcionEvolucion = descripcionEvolucion;
    }

    public boolean isSeleccionDienteNormal() {
        return seleccionDienteNormal;
    }

    public void setSeleccionDienteNormal(boolean seleccionDienteNormal) {
        this.seleccionDienteNormal = seleccionDienteNormal;
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

    public List<String> getListaOdontogramaFueraBolsaPer() {
        return listaOdontogramaFueraBolsaPer;
    }

    public void setListaOdontogramaFueraBolsaPer(List<String> listaOdontogramaFueraBolsaPer) {
        this.listaOdontogramaFueraBolsaPer = listaOdontogramaFueraBolsaPer;
    }

    public List<String> getListaOdontogramaFueraEndodoncia() {
        return listaOdontogramaFueraEndodoncia;
    }

    public void setListaOdontogramaFueraEndodoncia(List<String> listaOdontogramaFueraEndodoncia) {
        this.listaOdontogramaFueraEndodoncia = listaOdontogramaFueraEndodoncia;
    }

    public List<String> getListaOdontogramaFueraNecEndodoncia() {
        return listaOdontogramaFueraNecEndodoncia;
    }

    public void setListaOdontogramaFueraNecEndodoncia(List<String> listaOdontogramaFueraNecEndodoncia) {
        this.listaOdontogramaFueraNecEndodoncia = listaOdontogramaFueraNecEndodoncia;
    }

    public boolean isSeleccionDienteCaries() {
        return seleccionDienteCaries;
    }

    public void setSeleccionDienteCaries(boolean seleccionDienteCaries) {
        this.seleccionDienteCaries = seleccionDienteCaries;
    }

    public boolean isSeleccionDienteAmalgama() {
        return seleccionDienteAmalgama;
    }

    public void setSeleccionDienteAmalgama(boolean seleccionDienteAmalgama) {
        this.seleccionDienteAmalgama = seleccionDienteAmalgama;
    }

    public boolean isSeleccionDienteObstPlastica() {
        return seleccionDienteObstPlastica;
    }

    public void setSeleccionDienteObstPlastica(boolean seleccionDienteObstPlastica) {
        this.seleccionDienteObstPlastica = seleccionDienteObstPlastica;
    }

    public boolean isSeleccionDienteObstTemporal() {
        return seleccionDienteObstTemporal;
    }

    public void setSeleccionDienteObstTemporal(boolean seleccionDienteObstTemporal) {
        this.seleccionDienteObstTemporal = seleccionDienteObstTemporal;
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

    public List<String> getListaOdontogramaDerecha() {
        return listaOdontogramaDerecha;
    }

    public void setListaOdontogramaDerecha(List<String> listaOdontogramaDerecha) {
        this.listaOdontogramaDerecha = listaOdontogramaDerecha;
    }

    public List<String> getListaOdontogramaCentro() {
        return listaOdontogramaCentro;
    }

    public void setListaOdontogramaCentro(List<String> listaOdontogramaCentro) {
        this.listaOdontogramaCentro = listaOdontogramaCentro;
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

    public String getBusquedaPaciente() {
        return busquedaPaciente;
    }

    public void setBusquedaPaciente(String busquedaPaciente) {
        this.busquedaPaciente = busquedaPaciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoDiagnostico getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(TipoDiagnostico tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
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

    //url diente
    public String getArriba() {
        return arriba;
    }

    public void setArriba(String arriba) {
        this.arriba = arriba;
    }

    public String getAbajo() {
        return abajo;
    }

    public void setAbajo(String abajo) {
        this.abajo = abajo;
    }

    public String getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(String izquierda) {
        this.izquierda = izquierda;
    }

    public String getDerecha() {
        return derecha;
    }

    public void setDerecha(String derecha) {
        this.derecha = derecha;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public void seleccionarPaciente(Paciente paciente) {
        this.tipoActualizacion = "Evolución";
        this.pacienteSeleccionado = true;
        this.paciente = paciente;
        this.fechaRegistro = formatoFecha.format(this.paciente.getFechaRegistro());
        this.actualizacionOdo = ejbActualizacionOdo.buscarUltimaActualizacionPorPaciente(paciente.getId());
        llenarOdontograma();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        requestContext.update("InformacionPaciente");
        requestContext.update("evolucionHistoriaOdontologica");
        requestContext.execute("PF('seleccionPacienteDialog').hide()");

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

    public void guardarEvolucionHistoria() {
        registrarActualizacion();//registro actualizacion 
        registrarOdontograma();//registro odontograma
        registrarDiagnosticos();//registro diagnostico
        registrarEvolucion();//registro evolucion

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('registroEvolucionExitosoDialog').show()");
    }

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

    private void asignarFecha() {
        GregorianCalendar c = new GregorianCalendar();
        fechaApertura = c.getTime();
    }

    public void cambiarSeleccionDiagnosticoDent(String dianosticoDiente) {
        this.diagnosticoDiente = dianosticoDiente;
    }

    public void cambiarImgOdontogramaArriba(int posDienteList) {
        if (diagnosticoDiente.compareTo("caries") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
            //contarCaries(posDienteList);
        } else if (diagnosticoDiente.compareTo("normal") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_normal.png");
        } else if (diagnosticoDiente.compareTo("amalgama") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_amalgama.png");
            //contarObturados(posDienteList);
        } else if (diagnosticoDiente.compareTo("obst_plastica") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_obst_plastica.png");
            //contarObturados(posDienteList);
        } else if (diagnosticoDiente.compareTo("obst_temporal") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_obst_temporal.png");
        } else if (diagnosticoDiente.compareTo("sellante") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_sellante.png");
        } else if (diagnosticoDiente.compareTo("sinsellante") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_sinsellante.png");
        } else if (diagnosticoDiente.compareTo("faltante") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
            listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
            //contarDientesPerdidos(posDienteList);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("faltante_ext") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
            listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
            //contarDientesPerdidos(posDienteList);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("exodoncia") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
            listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
            listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
            listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("exodoncia") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
            listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
            listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
            listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("protesis") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
            listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
            listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
            listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("protesistotal") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
            listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
            listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
            listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("necendodoncia") == 0) {
            listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("ttoendodoncia") == 0) {
            listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("bolsa_per") == 0) {
            listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        }
    }

    public String retornarNombreImagen(int posicion, String lista) {
        String nombreImagen = "";

        if (lista.compareTo("Izquierda") == 0) {
            nombreImagen = listaOdontogramaIzquierda.get(posicion);
        } else if (lista.compareTo("Derecha") == 0) {
            nombreImagen = listaOdontogramaDerecha.get(posicion);
        } else if (lista.compareTo("Arriba") == 0) {
            nombreImagen = listaOdontogramaArriba.get(posicion);
        } else if (lista.compareTo("Abajo") == 0) {
            nombreImagen = listaOdontogramaAbajo.get(posicion);
        } else if (lista.compareTo("Centro") == 0) {
            nombreImagen = listaOdontogramaCentro.get(posicion);
        }

        return nombreImagen;
    }

    public void cambiarImgOdontograma(String posicion, int posDienteList) {

        switch (posicion) {
            case "arriba":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
                    // contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_necendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_necendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_necendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_necendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_necendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_ttoendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_ttoendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_ttoendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_ttoendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_ttoendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "abajo":
                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_caries.png");
                    //contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_necendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_necendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_necendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_necendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_necendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_ttoendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_ttoendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_ttoendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_ttoendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_ttoendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "izquierda":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_caries.png");
                    //  contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_necendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_necendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_necendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_necendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_necendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_ttoendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_ttoendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_ttoendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_ttoendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_ttoendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "derecha":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_caries.png");
                    //contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_necendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_necendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_necendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_necendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_necendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_ttoendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_ttoendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_ttoendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_ttoendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_ttoendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "centro":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_caries.png");
                    //contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("exodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesis")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("protesistotal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("necendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_necendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_necendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_necendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_necendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_necendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_ttoendodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_ttoendodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_ttoendodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_ttoendodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_ttoendodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;

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
        this.listaOdontogramaArriba = new ArrayList<>();
        this.listaOdontogramaAbajo = new ArrayList<>();
        this.listaOdontogramaIzquierda = new ArrayList<>();
        this.listaOdontogramaDerecha = new ArrayList<>();
        this.listaOdontogramaCentro = new ArrayList<>();
        this.listaOdontogramaFueraBolsaPer = new ArrayList<>();
        this.listaOdontogramaFueraEndodoncia = new ArrayList<>();
        this.listaOdontogramaFueraNecEndodoncia = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            this.listaOdontogramaArriba.add("arriba_normal.png");
            this.listaOdontogramaAbajo.add("abajo_normal.png");
            this.listaOdontogramaIzquierda.add("izq_normal.png");
            this.listaOdontogramaDerecha.add("der_normal.png");
            this.listaOdontogramaCentro.add("centro_normal.png");
            this.listaOdontogramaFueraBolsaPer.add("fuera.png");
            this.listaOdontogramaFueraEndodoncia.add("fuera.png");
            this.listaOdontogramaFueraNecEndodoncia.add("fuera.png");

        }
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

    private void llenarOdontograma() {
        this.listaOdontogramaArriba = new ArrayList<>();
        this.listaOdontogramaAbajo = new ArrayList<>();
        this.listaOdontogramaIzquierda = new ArrayList<>();
        this.listaOdontogramaDerecha = new ArrayList<>();
        this.listaOdontogramaCentro = new ArrayList<>();
        this.listaOdontogramaFueraBolsaPer = new ArrayList<>();
        this.listaOdontogramaFueraEndodoncia = new ArrayList<>();
        this.listaOdontogramaFueraNecEndodoncia = new ArrayList<>();
        List<Odontograma> odontogramaActual = new ArrayList<>();
        odontogramaActual = ejbOdontograma.buscarPorActualizacion(actualizacionOdo);
        for (int i = 0; i < 52; i++) {
            this.listaOdontogramaArriba.add(odontogramaActual.get(i).getImgArriba());
            this.listaOdontogramaAbajo.add(odontogramaActual.get(i).getImgAbajo());
            this.listaOdontogramaIzquierda.add(odontogramaActual.get(i).getImgIzq());
            this.listaOdontogramaDerecha.add(odontogramaActual.get(i).getImgDer());
            this.listaOdontogramaCentro.add(odontogramaActual.get(i).getImgCentro());
            this.listaOdontogramaFueraBolsaPer.add(odontogramaActual.get(i).getImgFueraBolPeri());
            this.listaOdontogramaFueraEndodoncia.add(odontogramaActual.get(i).getImgFueraEnd());
            this.listaOdontogramaFueraNecEndodoncia.add(odontogramaActual.get(i).getImgFueraNEnd());

        }

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

    private void registrarEvolucion() {
        EvolucionOdo evolucionOdontologica = new EvolucionOdo();
        evolucionOdontologica.setEvolucion(descripcionEvolucion);
        evolucionOdontologica.setNumeroDiente(diente);
        evolucionOdontologica.setIdActualizacion(actualizacionOdo);

        ejbEvolucionOdo.create(evolucionOdontologica);

        this.actualizacionOdo = new ActualizacionOdo();
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

    private void registrarDiagnosticos() {
        /*  DiagnosticoOdo diagnosticoOdo = new DiagnosticoOdo();
        diagnosticoOdo.setIdActualizacion(actualizacionOdo);
        diagnosticoOdo.setDx(idDiagnostico(diagnosticoPrincipal));
        diagnosticoOdo.setNdx(diagnosticoPrincipal);

        if (diagnosticoDX1.equalsIgnoreCase("No")) {
            diagnosticoOdo.setDx1("");
            diagnosticoOdo.setNdx1("");
        } else {
            diagnosticoOdo.setDx1(idDiagnostico(diagnosticoDX1));
            diagnosticoOdo.setNdx1(diagnosticoDX1);
        }

        if (diagnosticoDX2.equalsIgnoreCase("No")) {
            diagnosticoOdo.setDx2("");
            diagnosticoOdo.setNdx2("");
        } else {
            diagnosticoOdo.setDx2(idDiagnostico(diagnosticoDX2));
            diagnosticoOdo.setNdx2(diagnosticoDX2);
        }

        if (diagnosticoDX3.equalsIgnoreCase("No")) {
            diagnosticoOdo.setDx3("");
            diagnosticoOdo.setNdx3("");
        } else {
            diagnosticoOdo.setDx3(idDiagnostico(diagnosticoDX3));
            diagnosticoOdo.setNdx3(diagnosticoDX3);
        }

        diagnosticoOdo.setTipodiagnostico(tipoDiagnostico);
        ejbDiagnosticoOdo.create(diagnosticoOdo);*/
    }

}
