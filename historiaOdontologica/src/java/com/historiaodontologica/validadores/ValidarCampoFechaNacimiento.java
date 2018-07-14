/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.historiaodontologica.validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author geovanny
 */
@FacesValidator(value = "ValidarCampoFechaNacimiento")
public class ValidarCampoFechaNacimiento implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date fecha = (Date) value;
        int edadMinima = 18;
        if (!validarFechaNacimiento(fecha)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Campo fecha de nacimiento no puede ser mayor a la fecha actual.", "Campo fecha de nacimiento no puede ser mayor a la fecha actual.");
            throw new ValidatorException(msg);
        }
        if (validarEdad(fecha) < edadMinima) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Verificar fecha! El paciente es muy joven aún.", "¡Verificar fecha! El paciente es muy joven aún.");
            throw new ValidatorException(msg);
        }
    }

    public boolean validarFechaNacimiento(Date fecha) {

        Date fechaActual = new Date();
        return fecha.compareTo(fechaActual) <= 0;

    }

    //Calcula la diferencia en meses para validar que no se registren niños menores a año y medio
    public int validarEdad(Date fechaNacimiento) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaActual = new Date();
        Date fecha = new Date();
        fecha.setTime(fechaActual.getTime() - fechaNacimiento.getTime());
        int edad = fecha.getMonth() + (12 * (fecha.getYear() - 70));
       
        return edad;
    }
}
