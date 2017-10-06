/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.clases;

/**
 *
 * @author ROED26
 */
public class IndiceCOP {

    private String area;
    private int cariados;
    private int obturados;
    private int perdidos;

    public IndiceCOP() {
        this.cariados = 0;
        this.obturados = 0;
        this.perdidos = 0;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCariados() {
        return cariados;
    }

    public void setCariados(int cariados) {
        this.cariados = cariados;
    }

    public int getObturados() {
        return obturados;
    }

    public void setObturados(int obturados) {
        this.obturados = obturados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

}
