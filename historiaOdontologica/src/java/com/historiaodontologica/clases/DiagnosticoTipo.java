/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.clases;

import com.historiaodontologica.entidades.Diagnosticocie10Odo;
import com.historiaodontologica.entidades.TipoDiagnostico;

/**
 *
 * @author ROED26
 */
public class DiagnosticoTipo {
    private Diagnosticocie10Odo diagnosticoCie10;
    private TipoDiagnostico diagnostico;

    public Diagnosticocie10Odo getDiagnosticoCie10() {
        return diagnosticoCie10;
    }

    public void setDiagnosticoCie10(Diagnosticocie10Odo diagnosticoCie10) {
        this.diagnosticoCie10 = diagnosticoCie10;
    }

    public TipoDiagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(TipoDiagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
}
