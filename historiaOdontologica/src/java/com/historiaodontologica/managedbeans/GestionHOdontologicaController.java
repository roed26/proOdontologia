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
import com.historiaodontologica.entidades.UsuariosSistema;
import com.historiaodontologica.sessionbeans.ActualizacionOdoFacade;
import com.historiaodontologica.sessionbeans.AntOdoFacade;
import com.historiaodontologica.sessionbeans.CuadroSintesisFacade;
import com.historiaodontologica.sessionbeans.DiagnosticoOdoFacade;
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
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
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
@Named(value = "gestionHOdontologicaController")
@SessionScoped
public class GestionHOdontologicaController implements Serializable {

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
    private OdontogramaFacade ejbOdontograma;
    @EJB
    private ObsOdontogramaFacade ejbObsOdontograma;
    @EJB
    private CuadroSintesisFacade ejbCuadroSintesis;
    @EJB
    private EvolucionOdoFacade ejbEvolucionOdo;
    @EJB
    private DiagnosticoOdoFacade ejbDiagnosticoOdo;

    //variables booleanas
    private boolean conAcompaniante;
    private boolean conAntecedentesFamiliares;
    private boolean conAntecedentesPersonales;
    private boolean conExamenEstomatologico;
    private boolean conExamenPulpar;
    private boolean conExamenTejidosDent;
    private boolean conAlteraciones;
    private boolean pacienteSeleccionado;

    //listas
    private List<ListadoAntOdon> listadoAntecedentesFamiliares;
    private List<ListadoAntOdon> listadoAntecedentesPersonales;
    private List<ListadoEstomatologico> listadoEstomatologicos;
    private List<ListadoExamenOral> listadoExamenOralPulpar;
    private List<ListadoExamenOral> listadoExamenOralDentario;
    private List<ListadoExamenOral> listadoExamenOralPeriodontal;
    private List<ActualizacionOdo> listaActualizaciones;
    private List<String> listadoRespuestasAntFam;
    private List<String> listadoRespuestasAntPer;
    private List<String> listadoRespuestasExmEst;
    private List<String> listadoObservacionesExmEst;
    private List<String> listadoRespuestasExamenOral;
    private List<String> listaOdontogramaArriba;
    private List<String> listaOdontogramaAbajo;
    private List<String> listaOdontogramaIzquierda;
    private List<String> listaOdontogramaDerecha;
    private List<String> listaOdontogramaCentro;

    //String
    private String respuestaAntOdo = "No";
    private String motivoConsulta;
    private String busquedaPaciente;
    private String fechaRegistro;
    private String tipoActualizacion;
    private String observacionAntecendetesFam;
    private String observacionAntecendetesPer;
    private String observacionGenExmEst;
    private String diagnosticoDiente;
    private String arriba = "arriba_normal.png";
    private String abajo = "abajo_normal.png";
    private String izquierda = "izq_normal.png";
    private String derecha = "der_normal.png";
    private String centro = "centro_normal.png";

    //fechas
    private Date fechaApertura;
    private SimpleDateFormat formatoFecha;

    //objetos 
    private Paciente paciente;
    private Higiene higiene;
    private ActualizacionOdo actualizacionOdo;
    private RespuestasAntecedentesFamiliares respuestasAntecedentesFamiliares;
    private RespuestasAntecedentesPersonales respuestasAntecedentesPersonales;
    private RespuestasExamenEstomatologico respuestasExamenEstomatologico;
    private RespuestasExamenOral respuestasExamenOral;

    public GestionHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.conAcompaniante = false;
        this.conAntecedentesFamiliares = false;
        this.conAntecedentesPersonales = false;
        this.conExamenEstomatologico = false;
        this.conExamenPulpar = false;
        this.conExamenTejidosDent = false;
        this.conAlteraciones = false;
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
        listaActualizaciones = new ArrayList<>();
        respuestasAntecedentesFamiliares = new RespuestasAntecedentesFamiliares();
        respuestasAntecedentesPersonales = new RespuestasAntecedentesPersonales();
        respuestasExamenEstomatologico = new RespuestasExamenEstomatologico();
        respuestasExamenOral = new RespuestasExamenOral();
    }

    @PostConstruct
    private void init() {
        this.actualizacionOdo = new ActualizacionOdo();
        this.higiene = new Higiene();
        cargarAntecedentesFamiliares();
        cargarAntecedentesPersonales();
        cargarEstomatologicos();
        cargarListaExamenOralPulpar();
        cargarListaExamenOralDentario();
        cargarListaExamenOralPeriodontal();
        inicializarOdontograma();

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

    public List<ActualizacionOdo> getListaActualizaciones() {
        return listaActualizaciones;
    }

    public void setListaActualizaciones(List<ActualizacionOdo> listaActualizaciones) {
        this.listaActualizaciones = listaActualizaciones;
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

    public boolean isConExamenTejidosDent() {
        return conExamenTejidosDent;
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

    public void setConAlteraciones(boolean conAlteraciones) {
        this.conAlteraciones = conAlteraciones;
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
        this.pacienteSeleccionado = true;
        this.paciente = paciente;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        Application application = context.getApplication();
        ViewHandler viewHandler = application.getViewHandler();
        UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
        context.setViewRoot(viewRoot);
        context.renderResponse();
        this.listaActualizaciones = ejbActualizacionOdo.buscarPorPaciente(paciente.getId());

        for (int i = 0; i < listaActualizaciones.size(); i++) {
            ActualizacionOdo actualizacionActual = listaActualizaciones.get(i);
            Collection<MotivoOdo> collectionMotivo = new ArrayList<>(ejbMotivoOdo.buscarPorActualizacion(actualizacionActual));
            Collection<Odontograma> collectionOdontograma = new ArrayList<>(ejbOdontograma.buscarPorActualizacion(actualizacionActual));
            Collection<ObsOdontograma> collectionObsOdontograma = new ArrayList<>(ejbObsOdontograma.buscarPorActualizacion(actualizacionActual));
            Collection<CuadroSintesis> cuadroSintesisCollection = new ArrayList<>(ejbCuadroSintesis.buscarPorActualizacion(actualizacionActual));
            Collection<EvolucionOdo> evolucionOdontologico = new ArrayList<>(ejbEvolucionOdo.buscarPorActualizacion(actualizacionActual));
            Collection<DiagnosticoOdo> diagnosticoOdontologico = new ArrayList<>(ejbDiagnosticoOdo.buscarPorActualizacion(actualizacionActual));
            if (i == 0) {

                Collection<AntecedenteOdo> collectionAntecendetesOdo = new ArrayList<>(ejbAntOdo.buscarPorActualizacion(actualizacionActual));
                Collection<ExaEstomatologico> collectionExamenEstomatologico = new ArrayList<>(ejbExaEstomatologico.buscarPorActualizacion(actualizacionActual));
                Collection<ExaOral> collectionExamenOral = new ArrayList<>(ejbExaOral.buscarPorActualizacion(actualizacionActual));
                Collection<ObsExaOral> obsExaOralCollection = new ArrayList<>(ejbObsExaOral.buscarPorActualizacion(actualizacionActual));
                Collection<Higiene> collectionHigiene = new ArrayList<>(ejbHigiene.buscarPorActualizacion(actualizacionActual));

                actualizacionActual.setMotivoOdoCollection(collectionMotivo);
                actualizacionActual.setAntecedenteOdoCollection(collectionAntecendetesOdo);
                actualizacionActual.setExaEstomatologicoCollection(collectionExamenEstomatologico);
                actualizacionActual.setExaOralCollection(collectionExamenOral);
                actualizacionActual.setObsExaOralCollection(obsExaOralCollection);
                actualizacionActual.setHigieneCollection(collectionHigiene);
                actualizacionActual.setOdontogramaCollection(collectionOdontograma);
                actualizacionActual.setObsOdontogramaCollection(collectionObsOdontograma);
                actualizacionActual.setCuadroSintesisCollection(cuadroSintesisCollection);
                actualizacionActual.setEvolucionOdoCollection(evolucionOdontologico);
                actualizacionActual.setDiagnosticoOdoCollection(diagnosticoOdontologico);
            } else {

                actualizacionActual.setMotivoOdoCollection(collectionMotivo);
                actualizacionActual.setOdontogramaCollection(collectionOdontograma);
                actualizacionActual.setObsOdontogramaCollection(collectionObsOdontograma);
                actualizacionActual.setCuadroSintesisCollection(cuadroSintesisCollection);
                actualizacionActual.setEvolucionOdoCollection(evolucionOdontologico);
                actualizacionActual.setDiagnosticoOdoCollection(diagnosticoOdontologico);
            }

        }

        requestContext.update("InformacionPaciente");
        requestContext.update("listaHistoriaOdontologica");
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

    public void respuestaAntecedenteF(ValueChangeEvent e) {
        if (e.getNewValue().equals("Si")) {
            conAntecedentesFamiliares = true;
        } else {
            conAntecedentesFamiliares = false;
        }

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
        //registro actualizacion 
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

        //registro motivo de consulta
        MotivoOdo motivoOdo = new MotivoOdo();

        motivoOdo.setIdActualizacion(actualizacionOdo);
        motivoOdo.setMotivo(motivoConsulta);

        ejbMotivoOdo.create(motivoOdo);
        //Registro antecendentes familiares
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

        //Registro antecendentes personales
        cargarRespuestasAntecedentesPer();
        for (int i = 0; i < listadoRespuestasAntPer.size(); i++) {
            AntecedenteOdo antOdo = new AntecedenteOdo();
            antOdo.setIdActualizacion(actualizacionOdo);
            antOdo.setTipo("PE");
            antOdo.setResultado(listadoRespuestasAntPer.get(i));
            antOdo.setNombre(listadoAntecedentesPersonales.get(i).getNombre());
            ejbAntOdo.create(antOdo);
        }
        obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionAntecendetesPer);
        obsAntOdo.setTipo("PE");
        ejbObsAntOdo.create(obsAntOdo);

        //Registro examen estomatologico
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
        obsAntOdo = new ObsAntOdo();
        obsAntOdo.setIdActualizacion(actualizacionOdo);
        obsAntOdo.setObs(observacionGenExmEst);
        obsAntOdo.setTipo("ES");
        ejbObsAntOdo.create(obsAntOdo);

        //registro examen oral e higiene oral
        cargarRespuestasExamenOral();
        for (int i = 0; i < listadoRespuestasExamenOral.size(); i++) {
            ExaOral examenOral = new ExaOral();
            examenOral.setIdActualizacion(actualizacionOdo);
            if (i >= 0 && i < 4) {
                examenOral.setCampo(listadoExamenOralPulpar.get(i).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PU");
                ejbExaOral.create(examenOral);
            } else if (i >= 4 && i < 9) {
                examenOral.setCampo(listadoExamenOralDentario.get(i).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("DE");
                ejbExaOral.create(examenOral);
            } else if (i >= 9 && i < 16) {
                examenOral.setCampo(listadoExamenOralPeriodontal.get(i).getNombre());
                examenOral.setValor(listadoRespuestasExamenOral.get(i));
                examenOral.setTipo("PE");
                ejbExaOral.create(examenOral);
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

        //registro de higiene oral
        this.higiene.setIdActualizacion(actualizacionOdo);

        this.ejbHigiene.create(higiene);

        this.actualizacionOdo = new ActualizacionOdo();

        //ActualizacionOdo=this.ejbActualizacionOdo.buscarPorFecha(fechaApertura);
    }

    public boolean getEsAdulto() {
        boolean adulto = true;
        if (pacienteSeleccionado) {
            Date fechaDeNacimiento = paciente.getFechaNacimiento();
            Date fechaActual = fechaActual();

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

    public void abrirSeleccionarPaciente() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("PacienteListForm:datalist");
        requestContext.execute("PF('seleccionPacienteDialog').show()");
    }

    private Date fechaActual() {
        GregorianCalendar c = new GregorianCalendar();
        return c.getTime();
    }

    public List<Odontograma> listaOdontograma(ActualizacionOdo actualizacionOdo) {
        List<Odontograma> lista = ejbOdontograma.buscarPorActualizacion(actualizacionOdo);
        //actualizacionOdo.getOdontogramaCollection();
        return lista;

    }

    public List<Odontograma> listaOdontogramaL(ActualizacionOdo actualizacionOdo) {
        List<Odontograma> lista = new ArrayList<>();
        lista = ejbOdontograma.buscarPorActualizacion(actualizacionOdo);

        //actualizacionOdo.getOdontogramaCollection();
        return lista;

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

    public void cambiarSeleccionDiagnosticoDent(String dianosticoDiente) {
        this.diagnosticoDiente = dianosticoDiente;
    }

    public void verHistoria() {
        //listaActualizaciones

    }

    public void cambiarImgOdontograma(String posicion, int posDienteList) {

        switch (posicion) {
            case "arriba":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_caries.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaArriba.set(posDienteList, "arriba_obst_plastica.png");
                }
                break;
            case "abajo":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_caries.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaAbajo.set(posDienteList, "abajo_obst_plastica.png");
                }

                break;
            case "izquierda":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_caries.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaIzquierda.set(posDienteList, "izq_obst_plastica.png");
                }

                break;
            case "derecha":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_caries.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaDerecha.set(posDienteList, "der_obst_plastica.png");
                }

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    derecha = "der_caries.png";

                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    derecha = "der_normal.png";
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    derecha = "der_amalgama.png";
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    derecha = "der_obst_plastica.png";
                }

                break;
            case "centro":

                if (diagnosticoDiente.equalsIgnoreCase("caries")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_caries.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("normal")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_normal.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("amalgama")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_amalgama.png");
                } else if (diagnosticoDiente.equalsIgnoreCase("obst_plastica")) {
                    listaOdontogramaCentro.set(posDienteList, "centro_obst_plastica.png");
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

        for (int i = 0; i < 32; i++) {
            this.listaOdontogramaArriba.add("arriba_normal.png");
            this.listaOdontogramaAbajo.add("abajo_normal.png");
            this.listaOdontogramaIzquierda.add("izq_normal.png");
            this.listaOdontogramaDerecha.add("der_normal.png");
            this.listaOdontogramaCentro.add("centro_normal.png");

        }
    }

}
