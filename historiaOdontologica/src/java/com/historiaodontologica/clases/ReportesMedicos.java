/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.clases;

import com.historiaodontologica.entidades.Paciente;
import java.util.Date;

/**
 *
 * @author Diana
 */
public class ReportesMedicos {

    private Date fechadesde = new Date();
    private Date fechahasta = new Date();
    private Paciente paciente;
    private int cantidad;
    private int contadorhombres;
    private int contadormujeres;

    public ReportesMedicos() {

        this.fechadesde = new Date();
        this.fechahasta = new Date();
    }

    public Date getFechadesde() {
        return fechadesde;
    }

    public void setFechadesde(Date fechadesde) {
        this.fechadesde = fechadesde;
    }

    public Date getFechahasta() {
        return fechahasta;
    }

    public void setFechahasta(Date fechahasta) {
        this.fechahasta = fechahasta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getContadorhombres() {
        return contadorhombres;
    }

    public void setContadorhombres(int contadorhombres) {
        this.contadorhombres = contadorhombres;
    }

    public int getContadormujeres() {
        return contadormujeres;
    }

    public void setContadormujeres(int contadormujeres) {
        this.contadormujeres = contadormujeres;
    }

}
