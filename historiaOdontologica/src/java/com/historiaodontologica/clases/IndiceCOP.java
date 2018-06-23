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
    private int contHombres;
    private int contMujeres;
    

    public IndiceCOP() {
        this.cariados = 0;
        this.obturados = 0;
        this.perdidos = 0;
        this.contHombres =0;
        this.contMujeres = 0;
    }

    public int getContHombres() {
        return contHombres;
    }

    public void setContHombres(int contHombres) {
        this.contHombres = contHombres;
    }
    public void aumentarContHombres() {
        this.contHombres++;
    }

    public int getContMujeres() {
        return contMujeres;
    }

    public void setContMujeres(int contMujeres) {
        this.contMujeres = contMujeres;
    }
    public void aumentarContMujeres() {
        this.contMujeres++;
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
