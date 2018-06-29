/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DiagnosticoTipo;
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
import javax.faces.application.FacesMessage;
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
    private List<DiagnosticoTipo> listaDiagnosticosTipo;

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

    //contador dientes
    private boolean contCariesD18;
    private boolean contCariesD17;
    private boolean contCariesD16;
    private boolean contCariesD15;
    private boolean contCariesD14;
    private boolean contCariesD13;
    private boolean contCariesD12;
    private boolean contCariesD11;
    private boolean contCariesD21;
    private boolean contCariesD22;
    private boolean contCariesD23;
    private boolean contCariesD24;
    private boolean contCariesD25;
    private boolean contCariesD26;
    private boolean contCariesD27;
    private boolean contCariesD28;
    private boolean contCariesD48;
    private boolean contCariesD47;

    private boolean contCariesD46;
    private boolean contCariesD45;
    private boolean contCariesD44;
    private boolean contCariesD43;
    private boolean contCariesD42;
    private boolean contCariesD41;
    private boolean contCariesD31;
    private boolean contCariesD32;
    private boolean contCariesD33;
    private boolean contCariesD34;
    private boolean contCariesD35;
    private boolean contCariesD36;
    private boolean contCariesD37;
    private boolean contCariesD38;

    private boolean contCariesD55;
    private boolean contCariesD54;
    private boolean contCariesD53;
    private boolean contCariesD52;
    private boolean contCariesD51;
    private boolean contCariesD61;
    private boolean contCariesD62;
    private boolean contCariesD63;
    private boolean contCariesD64;
    private boolean contCariesD65;

    private boolean contCariesD85;
    private boolean contCariesD84;
    private boolean contCariesD83;
    private boolean contCariesD82;
    private boolean contCariesD81;
    private boolean contCariesD71;
    private boolean contCariesD72;
    private boolean contCariesD73;
    private boolean contCariesD74;
    private boolean contCariesD75;

    private boolean contPerdidosD18;
    private boolean contPerdidosD17;
    private boolean contPerdidosD16;
    private boolean contPerdidosD15;
    private boolean contPerdidosD14;
    private boolean contPerdidosD13;
    private boolean contPerdidosD12;
    private boolean contPerdidosD11;
    private boolean contPerdidosD21;
    private boolean contPerdidosD22;
    private boolean contPerdidosD23;
    private boolean contPerdidosD24;
    private boolean contPerdidosD25;
    private boolean contPerdidosD26;
    private boolean contPerdidosD27;
    private boolean contPerdidosD28;
    private boolean contPerdidosD48;
    private boolean contPerdidosD47;

    private boolean contPerdidosD46;
    private boolean contPerdidosD45;
    private boolean contPerdidosD44;
    private boolean contPerdidosD43;
    private boolean contPerdidosD42;
    private boolean contPerdidosD41;
    private boolean contPerdidosD31;
    private boolean contPerdidosD32;
    private boolean contPerdidosD33;
    private boolean contPerdidosD34;
    private boolean contPerdidosD35;
    private boolean contPerdidosD36;
    private boolean contPerdidosD37;
    private boolean contPerdidosD38;

    private boolean contPerdidosD55;
    private boolean contPerdidosD54;
    private boolean contPerdidosD53;
    private boolean contPerdidosD52;
    private boolean contPerdidosD51;
    private boolean contPerdidosD61;
    private boolean contPerdidosD62;
    private boolean contPerdidosD63;
    private boolean contPerdidosD64;
    private boolean contPerdidosD65;

    private boolean contPerdidosD85;
    private boolean contPerdidosD84;
    private boolean contPerdidosD83;
    private boolean contPerdidosD82;
    private boolean contPerdidosD81;
    private boolean contPerdidosD71;
    private boolean contPerdidosD72;
    private boolean contPerdidosD73;
    private boolean contPerdidosD74;
    private boolean contPerdidosD75;

    private boolean contObturadosD18;
    private boolean contObturadosD17;
    private boolean contObturadosD16;
    private boolean contObturadosD15;
    private boolean contObturadosD14;
    private boolean contObturadosD13;
    private boolean contObturadosD12;
    private boolean contObturadosD11;
    private boolean contObturadosD21;
    private boolean contObturadosD22;
    private boolean contObturadosD23;
    private boolean contObturadosD24;
    private boolean contObturadosD25;
    private boolean contObturadosD26;
    private boolean contObturadosD27;
    private boolean contObturadosD28;
    private boolean contObturadosD48;
    private boolean contObturadosD47;

    private boolean contObturadosD46;
    private boolean contObturadosD45;
    private boolean contObturadosD44;
    private boolean contObturadosD43;
    private boolean contObturadosD42;
    private boolean contObturadosD41;
    private boolean contObturadosD31;
    private boolean contObturadosD32;
    private boolean contObturadosD33;
    private boolean contObturadosD34;
    private boolean contObturadosD35;
    private boolean contObturadosD36;
    private boolean contObturadosD37;
    private boolean contObturadosD38;

    private boolean contObturadosD55;
    private boolean contObturadosD54;
    private boolean contObturadosD53;
    private boolean contObturadosD52;
    private boolean contObturadosD51;
    private boolean contObturadosD61;
    private boolean contObturadosD62;
    private boolean contObturadosD63;
    private boolean contObturadosD64;
    private boolean contObturadosD65;

    private boolean contObturadosD85;
    private boolean contObturadosD84;
    private boolean contObturadosD83;
    private boolean contObturadosD82;
    private boolean contObturadosD81;
    private boolean contObturadosD71;
    private boolean contObturadosD72;
    private boolean contObturadosD73;
    private boolean contObturadosD74;
    private boolean contObturadosD75;

    private DiagnosticoTipo diagnosticoTipo;

    public EvolucionHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.conAcompaniante = false;
        listaDiagnosticosTipo = new ArrayList<>();

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

    public List<DiagnosticoTipo> getListaDiagnosticosTipo() {
        return listaDiagnosticosTipo;
    }

    public void setListaDiagnosticosTipo(List<DiagnosticoTipo> listaDiagnosticosTipo) {
        this.listaDiagnosticosTipo = listaDiagnosticosTipo;
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
        registrarDiagnostico();//registro diagnostico
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

    public void cambiarImgOdontogramaArriba(int posDienteList) {
        if (diagnosticoDiente.compareTo("caries") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
            contarCaries(posDienteList);
        } else if (diagnosticoDiente.compareTo("normal") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_normal.png");
        } else if (diagnosticoDiente.compareTo("amalgama") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_amalgama.png");
            contarObturados(posDienteList);
        } else if (diagnosticoDiente.compareTo("obst_plastica") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_obst_plastica.png");
            contarObturados(posDienteList);
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
            contarDientesPerdidos(posDienteList);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.update("aperturaHistoriaOdontologica");
        } else if (diagnosticoDiente.compareTo("faltante_ext") == 0) {
            listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
            listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
            listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
            contarDientesPerdidos(posDienteList);
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

    private void contarCaries(int posDiente) {
        if (posDiente == 0 && !contCariesD18) {
            contCariesD18 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 1 && !contCariesD17) {
            contCariesD17 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 2 && !contCariesD16) {
            contCariesD16 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 3 && !contCariesD15) {
            contCariesD15 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 4 && !contCariesD14) {
            contCariesD14 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 5 && !contCariesD13) {
            contCariesD13 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 6 && !contCariesD12) {
            contCariesD12 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 7 && !contCariesD11) {
            contCariesD11 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 8 && !contCariesD21) {
            contCariesD21 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 9 && !contCariesD22) {
            contCariesD22 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 10 && !contCariesD23) {
            contCariesD23 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 11 && !contCariesD24) {
            contCariesD24 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 12 && !contCariesD25) {
            contCariesD25 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 13 && !contCariesD26) {
            contCariesD26 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 14 && !contCariesD27) {
            contCariesD27 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 15 && !contCariesD28) {
            contCariesD28 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesSuperior = Integer.parseInt(cuadroSintesis.getCariadosSup()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosSup("" + contadorCariesSuperior);
        } else if (posDiente == 16 && !contCariesD48) {
            contCariesD48 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 17 && !contCariesD47) {
            contCariesD47 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 18 && !contCariesD46) {
            contCariesD46 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 19 && !contCariesD45) {
            contCariesD45 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 20 && !contCariesD44) {
            contCariesD44 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 21 && !contCariesD43) {
            contCariesD43 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 22 && !contCariesD42) {
            contCariesD42 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 23 && !contCariesD41) {
            contCariesD41 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 24 && !contCariesD31) {
            contCariesD31 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 25 && !contCariesD32) {
            contCariesD32 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 26 && !contCariesD33) {
            contCariesD33 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 27 && !contCariesD34) {
            contCariesD34 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 28 && !contCariesD35) {
            contCariesD35 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 29 && !contCariesD36) {
            contCariesD36 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 30 && !contCariesD37) {
            contCariesD37 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 31 && !contCariesD38) {
            contCariesD38 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            int contadorCariesInferior = Integer.parseInt(cuadroSintesis.getCariadosInf()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
            cuadroSintesis.setCariadosInf("" + contadorCariesInferior);
        } else if (posDiente == 32 && !contCariesD55) {
            contCariesD55 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 33 && !contCariesD54) {
            contCariesD54 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 34 && !contCariesD53) {
            contCariesD53 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 35 && !contCariesD52) {
            contCariesD52 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 36 && !contCariesD51) {
            contCariesD51 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 37 && !contCariesD61) {
            contCariesD61 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 38 && !contCariesD62) {
            contCariesD62 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 39 && !contCariesD63) {
            contCariesD63 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 40 && !contCariesD64) {
            contCariesD64 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 41 && !contCariesD65) {
            contCariesD65 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 42 && !contCariesD85) {
            contCariesD85 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 43 && !contCariesD84) {
            contCariesD84 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 44 && !contCariesD83) {
            contCariesD83 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 45 && !contCariesD82) {
            contCariesD82 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 46 && !contCariesD81) {
            contCariesD81 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 47 && !contCariesD71) {
            contCariesD71 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 48 && !contCariesD72) {
            contCariesD72 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 49 && !contCariesD73) {
            contCariesD73 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 50 && !contCariesD74) {
            contCariesD74 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        } else if (posDiente == 51 && !contCariesD75) {
            contCariesD75 = true;
            int contadorCaries = Integer.parseInt(obsOdontograma.getCaries()) + 1;
            obsOdontograma.setCaries("" + contadorCaries);
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("evolucionHistoriaOdontologica");

    }

    private void contarObturados(int posDiente) {
        if (posDiente == 0 && !contObturadosD18) {
            contObturadosD18 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 1 && !contObturadosD17) {
            contObturadosD17 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 2 && !contObturadosD16) {
            contObturadosD16 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 3 && !contObturadosD15) {
            contObturadosD15 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 4 && !contObturadosD14) {
            contObturadosD14 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 5 && !contObturadosD13) {
            contObturadosD13 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 6 && !contObturadosD12) {
            contObturadosD12 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 7 && !contObturadosD11) {
            contObturadosD11 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 8 && !contObturadosD21) {
            contObturadosD21 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 9 && !contObturadosD22) {
            contObturadosD22 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 10 && !contObturadosD23) {
            contObturadosD23 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 11 && !contObturadosD24) {
            contObturadosD24 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 12 && !contObturadosD25) {
            contObturadosD25 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 13 && !contObturadosD26) {
            contObturadosD26 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 14 && !contObturadosD27) {
            contObturadosD27 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 15 && !contObturadosD28) {
            contObturadosD28 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosSuperios = Integer.parseInt(cuadroSintesis.getObturacionSup()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionSup("" + contadorObturadosSuperios);
        } else if (posDiente == 16 && !contObturadosD48) {
            contObturadosD48 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 17 && !contObturadosD47) {
            contObturadosD47 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 18 && !contObturadosD46) {
            contObturadosD46 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 19 && !contObturadosD45) {
            contObturadosD45 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 20 && !contObturadosD44) {
            contObturadosD44 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 21 && !contObturadosD43) {
            contObturadosD43 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 22 && !contObturadosD42) {
            contObturadosD42 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 23 && !contObturadosD41) {
            contObturadosD41 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 24 && !contObturadosD31) {
            contObturadosD31 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 25 && !contObturadosD32) {
            contObturadosD32 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 26 && !contObturadosD33) {
            contObturadosD33 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 27 && !contObturadosD34) {
            contObturadosD34 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 28 && !contObturadosD35) {
            contObturadosD35 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 29 && !contObturadosD36) {
            contObturadosD36 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 30 && !contObturadosD37) {
            contObturadosD37 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 31 && !contObturadosD38) {
            contObturadosD38 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            int contadorObturadosInferior = Integer.parseInt(cuadroSintesis.getObturacionInf()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
            cuadroSintesis.setObturacionInf("" + contadorObturadosInferior);
        } else if (posDiente == 32 && !contObturadosD55) {
            contObturadosD55 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 33 && !contObturadosD54) {
            contObturadosD54 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 34 && !contObturadosD53) {
            contObturadosD53 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 35 && !contObturadosD52) {
            contObturadosD52 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 36 && !contObturadosD51) {
            contObturadosD51 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 37 && !contObturadosD61) {
            contObturadosD61 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 38 && !contObturadosD62) {
            contObturadosD62 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 39 && !contObturadosD63) {
            contObturadosD63 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 40 && !contObturadosD64) {
            contObturadosD64 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 41 && !contObturadosD65) {
            contObturadosD65 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 42 && !contObturadosD85) {
            contObturadosD85 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 43 && !contObturadosD84) {
            contObturadosD84 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 44 && !contObturadosD83) {
            contObturadosD83 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 45 && !contObturadosD82) {
            contObturadosD82 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 46 && !contObturadosD81) {
            contObturadosD81 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 47 && !contObturadosD71) {
            contObturadosD71 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 48 && !contObturadosD72) {
            contObturadosD72 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 49 && !contObturadosD73) {
            contObturadosD73 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 50 && !contObturadosD74) {
            contObturadosD74 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        } else if (posDiente == 51 && !contObturadosD75) {
            contObturadosD75 = true;
            int contadorObturados = Integer.parseInt(obsOdontograma.getObturados()) + 1;
            obsOdontograma.setObturados("" + contadorObturados);
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("evolucionHistoriaOdontologica");

    }

    public void contarDientesPerdidos(int posDiente) {
        if (posDiente == 0 && !contPerdidosD18) {
            contPerdidosD18 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 1 && !contPerdidosD17) {
            contPerdidosD17 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 2 && !contPerdidosD16) {
            contPerdidosD16 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 3 && !contPerdidosD15) {
            contPerdidosD15 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 4 && !contPerdidosD14) {
            contPerdidosD14 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 5 && !contPerdidosD13) {
            contPerdidosD13 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 6 && !contPerdidosD12) {
            contPerdidosD12 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 7 && !contPerdidosD11) {
            contPerdidosD11 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 8 && !contPerdidosD21) {
            contPerdidosD21 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 9 && !contPerdidosD22) {
            contPerdidosD22 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 10 && !contPerdidosD23) {
            contPerdidosD23 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 11 && !contPerdidosD24) {
            contPerdidosD24 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 12 && !contPerdidosD25) {
            contPerdidosD25 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 13 && !contPerdidosD26) {
            contPerdidosD26 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 14 && !contPerdidosD27) {
            contPerdidosD27 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 15 && !contPerdidosD28) {
            contPerdidosD28 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosSuperior = Integer.parseInt(cuadroSintesis.getFaltantesSup()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesSup("" + contadorPerdidosSuperior);
        } else if (posDiente == 16 && !contPerdidosD48) {
            contPerdidosD48 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 17 && !contPerdidosD47) {
            contPerdidosD47 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 18 && !contPerdidosD46) {
            contPerdidosD46 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 19 && !contPerdidosD45) {
            contPerdidosD45 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 20 && !contPerdidosD44) {
            contPerdidosD44 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 21 && !contPerdidosD43) {
            contPerdidosD43 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 22 && !contPerdidosD42) {
            contPerdidosD42 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 23 && !contPerdidosD41) {
            contPerdidosD41 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 24 && !contPerdidosD31) {
            contPerdidosD31 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 25 && !contPerdidosD32) {
            contPerdidosD32 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 26 && !contPerdidosD33) {
            contPerdidosD33 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 27 && !contPerdidosD34) {
            contPerdidosD34 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 28 && !contPerdidosD35) {
            contPerdidosD35 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 29 && !contPerdidosD36) {
            contPerdidosD36 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 30 && !contPerdidosD37) {
            contPerdidosD37 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 31 && !contPerdidosD38) {
            contPerdidosD38 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            int contadorPerdidosInferior = Integer.parseInt(cuadroSintesis.getFaltantesInf()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
            cuadroSintesis.setFaltantesInf("" + contadorPerdidosInferior);
        } else if (posDiente == 32 && !contPerdidosD55) {
            contPerdidosD55 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 33 && !contPerdidosD54) {
            contPerdidosD54 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 34 && !contPerdidosD53) {
            contPerdidosD53 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 35 && !contPerdidosD52) {
            contPerdidosD52 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 36 && !contPerdidosD51) {
            contPerdidosD51 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 37 && !contPerdidosD61) {
            contPerdidosD61 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 38 && !contPerdidosD62) {
            contPerdidosD62 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 39 && !contPerdidosD63) {
            contPerdidosD63 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 40 && !contPerdidosD64) {
            contPerdidosD64 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 41 && !contPerdidosD65) {
            contPerdidosD65 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 42 && !contPerdidosD85) {
            contPerdidosD85 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 43 && !contPerdidosD84) {
            contPerdidosD84 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 44 && !contPerdidosD83) {
            contPerdidosD83 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 45 && !contPerdidosD82) {
            contPerdidosD82 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 46 && !contPerdidosD81) {
            contPerdidosD81 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 47 && !contPerdidosD71) {
            contPerdidosD71 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 48 && !contPerdidosD72) {
            contPerdidosD72 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 49 && !contPerdidosD73) {
            contPerdidosD73 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 50 && !contPerdidosD74) {
            contPerdidosD74 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        } else if (posDiente == 51 && !contPerdidosD75) {
            contPerdidosD75 = true;
            int contadorPerdidos = Integer.parseInt(obsOdontograma.getPerdidos()) + 1;
            obsOdontograma.setPerdidos("" + contadorPerdidos);
        }

        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("evolucionHistoriaOdontologica");
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

    /*public void cambiarImgOdontograma(String posicion, int posDienteList) {

        switch (posicion) {
            case "arriba":
                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
                    contarCaries(posDienteList);
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
                    contarCaries(posDienteList);
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
                    contarCaries(posDienteList);
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
                    contarCaries(posDienteList);
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
                    contarCaries(posDienteList);
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

    }*/
    public void volverAInicio(CargarVistaController cargarVistaController) {
        cargarVistaController.cargarInicio();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('registroEvolucionExitosoDialog').hide()");

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
    public void cambiarImgOdontograma(String posicion, int posDienteList) {
        switch (posicion) {
            case "arriba":
                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
                    contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_amalgama.png");
                    contarObturados(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_obst_plastica.png");
                    contarObturados(posDienteList);
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
                    contarDientesPerdidos(posDienteList);
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    contarDientesPerdidos(posDienteList);
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
                    listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
                    listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "abajo":
                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_caries.png");
                    contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_amalgama.png");
                    contarObturados(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_obst_plastica.png");
                    contarObturados(posDienteList);
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
                    contarDientesPerdidos(posDienteList);
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    contarDientesPerdidos(posDienteList);
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
                    listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
                    listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "izquierda":

                if (diagnosticoDiente.compareTo("caries") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_caries.png");
                    contarCaries(posDienteList);
                } else if (diagnosticoDiente.compareTo("normal") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_normal.png");
                } else if (diagnosticoDiente.compareTo("amalgama") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_amalgama.png");
                    contarObturados(posDienteList);
                } else if (diagnosticoDiente.compareTo("obst_plastica") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_plastica.png");
                    contarObturados(posDienteList);
                } else if (diagnosticoDiente.compareTo("obst_plastica") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_plastica.png");
                } else if (diagnosticoDiente.compareTo("obst_temporal") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_temporal.png");
                } else if (diagnosticoDiente.compareTo("sellante") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_sellante.png");
                } else if (diagnosticoDiente.compareTo("sinsellante") == 0) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_sinsellante.png");
                } else if (diagnosticoDiente.compareTo("faltante") == 0) {
                    contarDientesPerdidos(posDienteList);
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("faltante_ext") == 0) {
                    contarDientesPerdidos(posDienteList);
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante_ext.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante_ext.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante_ext.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("exodoncia") == 0) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("exodoncia") == 0) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_exodoncia.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_exodoncia.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_exodoncia.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_exodoncia.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_exodoncia.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("protesis") == 0) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesis.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesis.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesis.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesis.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesis.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("protesistotal") == 0) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_protesistotal.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_protesistotal.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_protesistotal.png");
                    listaOdontogramaIzquierda.set(posDienteList, "izq_protesistotal.png");
                    listaOdontogramaDerecha.set(posDienteList, "der_protesistotal.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("necendodoncia") == 0) {
                    listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("ttoendodoncia") == 0) {
                    listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.compareTo("bolsa_per") == 0) {
                    listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "derecha":
                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_caries.png");
                    contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    contarObturados(posDienteList);
                    listaOdontogramaDerecha.set(posDienteList, "der_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    contarObturados(posDienteList);
                    listaOdontogramaDerecha.set(posDienteList, "der_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    contarDientesPerdidos(posDienteList);
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    contarDientesPerdidos(posDienteList);
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
                    listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
                    listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                }

                break;
            case "centro":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_caries.png");
                    contarCaries(posDienteList);
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    contarObturados(posDienteList);
                    listaOdontogramaCentro.set(posDienteList, "centro_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    contarObturados(posDienteList);
                    listaOdontogramaCentro.set(posDienteList, "centro_obst_plastica.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_temporal")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_obst_temporal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sellante")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_sellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("sinsellante")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_sinsellante.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante")) {
                    contarDientesPerdidos(posDienteList);
                    listaOdontogramaArriba.set(posDienteList, "arriba_faltante.png");
                    listaOdontogramaAbajo.set(posDienteList, "abajo_faltante.png");
                    listaOdontogramaCentro.set(posDienteList, "centro_faltante.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("faltante_ext")) {
                    contarDientesPerdidos(posDienteList);
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
                    listaOdontogramaFueraNecEndodoncia.set(posDienteList, "necendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("ttoendodoncia")) {
                    listaOdontogramaFueraEndodoncia.set(posDienteList, "ttoendodoncia_A.png");
                    RequestContext requestContext = RequestContext.getCurrentInstance();
                    requestContext.update("evolucionHistoriaOdontologica");
                } else if (diagnosticoDiente.equalsIgnoreCase("bolsa_per")) {
                    listaOdontogramaFueraBolsaPer.set(posDienteList, "bolsa_per_A.png");
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

        requestcontext.update("evolucionHistoriaOdontologica:datalistDiagnosticosTipo");
        requestcontext.execute("PF('seleccionarTipoDiagnostico').hide()");
        requestcontext.execute("PF('agregarDiagnosticoDialog').show()");

    }

    public void eliminarDiagnosticoTipoDeLista() {
        RequestContext requestcontext = RequestContext.getCurrentInstance();

        listaDiagnosticosTipo.remove(this.diagnosticoTipo);
        requestcontext.update("evolucionHistoriaOdontologica:datalistDiagnosticosTipo");
        requestcontext.execute("PF('dlgMensajeAdventencia').hide()");

    }

    public void abrirMensajeAdvertencia(DiagnosticoTipo diagnosticoTipo) {
        this.diagnosticoTipo = diagnosticoTipo;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Adventencia", "¿Esta seguro de eliminar el diagnóstico?"));
        requestContext.execute("PF('dlgMensajeAdventencia').show()");

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

}
