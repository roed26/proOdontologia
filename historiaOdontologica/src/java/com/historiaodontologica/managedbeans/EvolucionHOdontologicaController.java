/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import com.historiaodontologica.clases.DiagnosticoTipo;
import com.historiaodontologica.clases.OdontogramaTemp;
import com.historiaodontologica.entidades.ActualizacionOdo;
import com.historiaodontologica.entidades.AntecedenteOdo;
import com.historiaodontologica.entidades.CuadroSintesis;
import com.historiaodontologica.entidades.DiagnosticoOdo;
import com.historiaodontologica.entidades.Diagnosticocie10Odo;
import com.historiaodontologica.entidades.EvolucionOdo;
import com.historiaodontologica.entidades.ObsAntOdo;
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
import com.historiaodontologica.sessionbeans.ObsAntOdoFacade;
import com.historiaodontologica.sessionbeans.OdontogramaFacade;
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
    @EJB
    private AntOdoFacade ejbAntecedente;
    @EJB
    private ObsAntOdoFacade ejbObservaiconAntecedentes;

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

    private List<DiagnosticoTipo> listaDiagnosticosTipo;
    private List<OdontogramaTemp> odontogramaPaciente;
    private List<OdontogramaTemp> odontogramaPacienteAdultoSuperior;
    private List<OdontogramaTemp> odontogramaPacienteAdultoInferior;
    private List<OdontogramaTemp> odontogramaPacienteInfantilSuperior;
    private List<OdontogramaTemp> odontogramaPacienteInfantilInferior;
    private List<Boolean> contadoresCariesDiente;
    private List<Boolean> contadoresPerdidosDiente;
    private List<Boolean> contadoresObturadosDiente;

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
    private String alergias = "";

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

    private DiagnosticoTipo diagnosticoTipo;

    public EvolucionHOdontologicaController() {
        this.formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        this.conAcompaniante = false;
        listaDiagnosticosTipo = new ArrayList<>();
        inicializarVariables();

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
        inicializarVariables();

    }

    public ActualizacionOdo getActualizacionOdo() {
        return actualizacionOdo;
    }

    public void setActualizacionOdo(ActualizacionOdo actualizacionOdo) {
        this.actualizacionOdo = actualizacionOdo;
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

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
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
        if (comprobarAlergias()) {
            requestContext.execute("PF('AlergiasPacienteDialog').show()");
        }

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
        requestContext.update("evolucionHistoriaOdontologica:odontogramaGrid");
        requestContext.update("evolucionHistoriaOdontologica:pnlCuadro");
        requestContext.update("evolucionHistoriaOdontologica:pnlOclusion");
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
                    || odontogramaPacienteAdultoInferior.get(posDiente).getCentro().compareTo("centro_amalgama.png") == 0;
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

    public void volverAInicio(CargarVistaController cargarVistaController) {
        inicializarVariables();
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
        this.odontogramaPaciente = new ArrayList<>();

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

        this.odontogramaPacienteAdultoSuperior = new ArrayList<>();
        this.odontogramaPacienteAdultoInferior = new ArrayList<>();
        this.odontogramaPacienteInfantilSuperior = new ArrayList<>();
        this.odontogramaPacienteInfantilInferior = new ArrayList<>();
        List<Odontograma> odontogramaActual = new ArrayList<>();
        odontogramaActual = ejbOdontograma.buscarPorActualizacion(actualizacionOdo);
        if (odontogramaActual.size() == 32) {
            for (int i = 0; i < 32; i++) {
                OdontogramaTemp odontograma = new OdontogramaTemp();
                odontograma.setArriba(odontogramaActual.get(i).getImgArriba());
                odontograma.setAbajo(odontogramaActual.get(i).getImgAbajo());
                odontograma.setIzquierda(odontogramaActual.get(i).getImgIzq());
                odontograma.setDerecha(odontogramaActual.get(i).getImgDer());
                odontograma.setCentro(odontogramaActual.get(i).getImgCentro());
                odontograma.setAfueraBolsaPer(odontogramaActual.get(i).getImgFueraBolPeri());
                odontograma.setAfueraEnd(odontogramaActual.get(i).getImgFueraEnd());
                odontograma.setAfueraNecEnd(odontogramaActual.get(i).getImgFueraNEnd());
                if (i >= 0 && i < 16) {
                    odontogramaPacienteAdultoSuperior.add(odontograma);
                } else if (i >= 16 && i < 32) {
                    this.odontogramaPacienteAdultoInferior.add(odontograma);
                }

            }
        } else {
            for (int i = 0; i < 52; i++) {
                OdontogramaTemp odontograma = new OdontogramaTemp();
                odontograma.setArriba(odontogramaActual.get(i).getImgArriba());
                odontograma.setAbajo(odontogramaActual.get(i).getImgAbajo());
                odontograma.setIzquierda(odontogramaActual.get(i).getImgIzq());
                odontograma.setDerecha(odontogramaActual.get(i).getImgDer());
                odontograma.setCentro(odontogramaActual.get(i).getImgCentro());
                odontograma.setAfueraBolsaPer(odontogramaActual.get(i).getImgFueraBolPeri());
                odontograma.setAfueraEnd(odontogramaActual.get(i).getImgFueraEnd());
                odontograma.setAfueraNecEnd(odontogramaActual.get(i).getImgFueraNEnd());

                if (i >= 0 && i < 16) {
                    odontogramaPacienteAdultoSuperior.add(odontograma);
                }
                if (i >= 16 && i < 32) {
                    odontogramaPacienteAdultoInferior.add(odontograma);
                }

                if (i >= 32 && i < 42) {
                    odontogramaPacienteInfantilSuperior.add(odontograma);
                }
                if (i >= 42 && i < 52) {
                    odontogramaPacienteInfantilInferior.add(odontograma);
                }

            }

        }

    }

    public boolean comprobarAlergias() {
        boolean conAlergia = false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        ActualizacionOdo actualizacionOdoAlergias = new ActualizacionOdo();

        actualizacionOdoAlergias = ejbActualizacionOdo.buscarPrimeraActualizacionPorPaciente(paciente.getId());

        AntecedenteOdo antecedenteOdo = ejbAntecedente.buscarPorActualizacionAlergia(actualizacionOdoAlergias);
        if (antecedenteOdo != null) {
            if (antecedenteOdo.getResultado().compareTo("No") == 0) {
                conAlergia = false;
            } else {
                ObsAntOdo obsAntOdo = ejbObservaiconAntecedentes.buscarPorActualizacionAlergia(actualizacionOdoAlergias);
                if (obsAntOdo != null) {
                    alergias = obsAntOdo.getObs();
                    FacesContext.getCurrentInstance().addMessage("msgAlergias", new FacesMessage(FacesMessage.SEVERITY_WARN, "Paciente con las siguientes alergias:", alergias));
                }
                conAlergia = true;

                requestContext.update("AlergiasForm");
            }
        }
        return conAlergia;
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

    private Date fechaActual() {
        GregorianCalendar c = new GregorianCalendar();
        return c.getTime();
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
    public void inicializarVariablesCancelar(CargarVistaController cargarVistaController) {
        cargarVistaController.cargarInicio();
        inicializarVariables();
    }

    public void inicializarVariables() {
        listaDiagnosticosTipo = new ArrayList<>();
        this.contadoresCariesDiente = new ArrayList<>(52);
        this.contadoresObturadosDiente = new ArrayList<>(52);
        this.contadoresPerdidosDiente = new ArrayList<>(52);
        this.actualizacionOdo = new ActualizacionOdo();
        this.obsOdontograma = new ObsOdontograma();
        this.obsOdontograma.setOclusion("Normal");
        this.obsOdontograma.setCaries("0");
        this.obsOdontograma.setObturados("0");
        this.obsOdontograma.setPerdidos("0");
        this.cuadroSintesis = new CuadroSintesis();
        this.diagnosticoDiente = "normal";
        this.pacienteSeleccionado = false;
        this.paciente = new Paciente();
        for (int i = 0; i < 52; i++) {
            contadoresCariesDiente.add(false);
            contadoresObturadosDiente.add(false);
            contadoresPerdidosDiente.add(false);
        }

        inicializarOdontograma();
        inicializarCuadroSintesis();
    }

}
