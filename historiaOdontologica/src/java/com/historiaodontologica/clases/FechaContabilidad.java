/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.clases;

import com.historiaodontologica.entidades.Egreso;
import com.historiaodontologica.entidades.Ingreso;
import java.util.Date;

/**
 *
 * @author JUAN
 */
public class FechaContabilidad {
    private Date hasta = new Date();
    private Date desde = new Date();
    private Ingreso ingreso;
    private Egreso egreso;

    public FechaContabilidad() {
        this.hasta = new Date();
        this.desde = new Date();
        this.ingreso = new Ingreso();
        this.egreso = new Egreso();
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }

    public Egreso getEgreso() {
        return egreso;
    }

    public void setEgreso(Egreso egreso) {
        this.egreso = egreso;
    }
    
    
    
}
