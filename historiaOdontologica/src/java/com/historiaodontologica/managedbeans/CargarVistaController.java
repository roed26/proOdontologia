/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.context.RequestContext;

@Named(value = "cargarVistaController")
@SessionScoped
public class CargarVistaController implements Serializable {

    private String ruta;

    public String getRuta() {
        return ruta;
    }

    public CargarVistaController() {
        this.ruta = "/usuariodelsistema/odontologo/paginaPrincipal.xhtml";
    }

    public void cargarInicio() {
        this.ruta = "/usuariodelsistema/odontologo/paginaPrincipal.xhtml";
    }

    public void cargarRegistrarPaciente() {
        this.ruta = "/usuariodelsistema/odontologo/paciente/CrearPaciente.xhtml";
    }

    public void cargarEditarPaciente() {
        this.ruta = "/usuariodelsistema/odontologo/paciente/EditarPaciente.xhtml";
    }

    public void cargarVerPaciente() {
        this.ruta = "/usuariodelsistema/odontologo/paciente/VerPaciente.xhtml";
    }

    public void cargarAperturaHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/odontologo/historiaOdontologica/aperturaHistoriaOdontologica.xhtml";
    }

    public void cargarEvolucionHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/odontologo/historiaOdontologica/evolucionHistoriaOdontologica.xhtml";
    }

    public void cargarGestionHistoriaOdontologica() {
        this.ruta = "/usuariodelsistema/odontologo/historiaOdontologica/listaHistoriaOdontologicaPorPaciente.xhtml";
    }

    public void cargarPerfilUsuario() {
        this.ruta = "/usuariodelsistema/perfilUsuario.xhtml";
    }

    //estadisticas
    public void cargarEstadisticaFacultadDiagnostico() {
        this.ruta = "/usuariodelsistema/odontologo/estadisticas/diagnosticosFacultades.xhtml";
    }

    public void cargarIndiceCOP() {
        this.ruta = "/usuariodelsistema/odontologo/estadisticas/indiceCOP.xhtml";
    }

    public void cargarRegistrarPagos() {
        this.ruta = "/usuariodelsistema/odontologo/presupuesto/ListaPresupuesto.xhtml";
    }

    public void cargarRegistrarIngresos() {
        this.ruta = "/usuariodelsistema/odontologo/ingreso/ListaIngreso.xhtml";
    }

    public void cargarRegistrarEgresos() {
        this.ruta = "/usuariodelsistema/odontologo/egreso/ListaEgreso.xhtml";
    }

    public void cargarRegistrarResultados() {
        this.ruta = "/usuariodelsistema/odontologo/contabilidad/Contabilidad.xhtml";
    }

    public void cargarRegistrarAbonos() {
        this.ruta = "/usuariodelsistema/odontologo/abonoOdo/ListarAbono.xhtml";
    }

    public void cargarGestionServicios() {
        this.ruta = "/usuariodelsistema/odontologo/serviciosOdo/ListaServicio.xhtml";
    }
}
