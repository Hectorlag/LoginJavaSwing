
package com.mycompany.mavenproject1.persistencia;

import com.mycompany.mavenproject1.logica.Rol;
import com.mycompany.mavenproject1.logica.Usuario;
import com.mycompany.mavenproject1.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class controladoraPersistencia {
    
    UsuarioJpaController usuJpa = new  UsuarioJpaController();
     RolJpaController rolJpa = new  RolJpaController();

    public List<Usuario> traerUsuarios() { //GUARDA LOS DATOS EN UNA LISTA
        return usuJpa.findUsuarioEntities();//ES IGUAL A SELECT * FROM USUARIOS
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();//SELECT * FROM ROLES
    }

    public void crearUsuario(Usuario usu) {
        usuJpa.create(usu);
    }

    public void borrarUsuario(int id_usuario) {
        try {
            usuJpa.destroy(id_usuario);//INTENTA BORRAR POR EL NUM DE ID, SINO ARROJA UN EXCEPCION
        } catch (NonexistentEntityException ex) {   //PARA NO PROVIOCAR UN ERROR
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   //METODO PARA TRAER UN SOLO USUARIO
    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}


