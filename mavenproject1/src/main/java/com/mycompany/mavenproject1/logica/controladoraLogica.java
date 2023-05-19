
package com.mycompany.mavenproject1.logica;

import com.mycompany.mavenproject1.persistencia.RolJpaController;
import com.mycompany.mavenproject1.persistencia.controladoraPersistencia;
import java.util.List;


public class controladoraLogica {
    
    controladoraPersistencia controlPersis;
    RolJpaController rolJpa = new  RolJpaController();
    
    public controladoraLogica(){
        controlPersis = new controladoraPersistencia();
   }
   Usuario usr=null;
    public Usuario validarUsuario(String usuario, String contrasenia) {
       
        //String mensaje="";
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        
        for(Usuario usu: listaUsuarios){
            if(usu.getNombreUsuario().equals(usuario)){
               if(usu.getContrasenia().equals(contrasenia)){
                   //mensaje = "Usuario y contraseña correctos. Bienvenido/a";
                   usr=usu;
                   return usr;
               }
               else{
                   //mensaje = "Contraseña incorrecta!";
                   usr=null;
                   return usr;
               }
            }
            else{
                usr=null;
                //return usr;
                //mensaje = "Usuario no encontrado";
            }
        }
        
         //return mensaje;
         return usr;
        }

    public List<Usuario> traerUsuarios() {
       return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
       return controlPersis.traerRoles();
    }
  //METODO PARA CREAR USUARIO
    public void crearUsuario(String usuario, String contra, String rolRecibido) {
     
        Usuario usu = new Usuario();//CREAMOS UN OBJ VACIO PARA QUE SE COMPLETEN CON LOS DATOS QUE FALTAN,
                                  //LOS OTROS DATOS SE AUTOCOMPLETAN EN LA BD(ID)
        
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contra);
       
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        if(rolEncontrado != null){
         usu.setUnRol(rolEncontrado);
        }
        
        //METODO PARA OBTENER EL ULTIMO ID DESDE LA BASE DE DATOS
        int id = this.buscarUltimaIdUsuarios();
        usu.setId(id+1);
        controlPersis.crearUsuario(usu);
                                  
    }                              
    //METODO PARA TRAER UN ROL DE LA BD
    private Rol traerRol(String rolRecibido) {
       List<Rol> listaRoles = controlPersis.traerRoles();
       
       for(Rol rol: listaRoles){
           if(rol.getNombreRol().equals(rolRecibido)){
               return rol;
           }
       }
       return null;      
}

    private int buscarUltimaIdUsuarios() {
        List<Usuario> listaUsuarios = this.traerUsuarios();
        
        Usuario usu = listaUsuarios.get(listaUsuarios.size()-1);//OBTENGO EL ULTIMO USUARIO
        return usu.getId();//OBTENGO EL ID DEL ULTIMO USUARIO
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }
    
    //METODO PARA TRAER UN SOLO USUARIO
    public Usuario traerUsuario(int id_usuario) {
        return controlPersis.traerUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido) {
        
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contra);
        
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.traerRol(rolRecibido);
        if(rolEncontrado != null){
         usu.setUnRol(rolEncontrado);
        }
        
        controlPersis.editarUsuario(usu);
        
        
    }



}