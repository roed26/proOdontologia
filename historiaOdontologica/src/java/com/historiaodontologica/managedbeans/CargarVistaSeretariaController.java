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


@Named(value = "cargarVistaSecretariaController")
@SessionScoped
public class CargarVistaSeretariaController implements Serializable {
   
   private String ruta;
   public String getRuta() {
      return ruta;
   }
   
   public CargarVistaSeretariaController() {
      
   }
   
   public void cargarGestionarUsuarios(){
        this.ruta = "/usuariodelsistema/secretaria/usuariosSistema/List.xhtml";
   }
   public void cargarPerfilUsuario() {
        this.ruta = "/usuariodelsistema/perfilUsuario.xhtml";
    }
}
