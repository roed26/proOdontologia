package com.historiaodontologica.validadores;



import com.historiaodontologica.sessionbeans.PacienteFacade;
import com.historiaodontologica.sessionbeans.UsuariosSistemaFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


@FacesValidator(value="ValidarCampoNumeroIdentificacionPaciente")
public class ValidarCampoNumeroIdentificacionPaciente implements Validator
{
    @EJB
    private PacienteFacade pacienteEJB;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException 
    {
        String texto = String.valueOf(value);        
        Long numeroIdentificacion=Long.MIN_VALUE;
        try
        {
            numeroIdentificacion= Long.parseLong(texto);
        }catch(Exception e)
        {
            
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo solo puede contener números.","Campo solo puede contener números.");
            throw new ValidatorException(msg);
            
        }        
        if(texto.length()>15)
        {
            FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Campo no mas de 15 números.","Campo no mas de 15 números.");
            throw new ValidatorException(msg); 
        }
        else
        {
            String numeroDeIdentificacion=""+numeroIdentificacion ;
            if(pacienteEJB.buscarPacienteIdentificacionEjb(numeroDeIdentificacion))
            {
                FacesMessage msg= new FacesMessage(FacesMessage.SEVERITY_ERROR,"Esta identificacion ya fue regístrada.","Esta identificacion ya fue regístrada.");
                throw new ValidatorException(msg);  
            } 
        }        
        
    }
}