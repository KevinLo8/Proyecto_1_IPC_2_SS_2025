/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.usuario;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBUsuario;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ProcesadorUsuario {

    public Usuario chequearInicioSesion(Usuario usuario) throws DataErrorException, DatabaseException {

        ClaseDBUsuario database = new ClaseDBUsuario();
        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp == null) {
            throw new DataErrorException("correo electrónico o contraseña incorrecto.");
        } else if (!usuario.getContraseñaUsuario().equals(usuarioTemp.getContraseñaUsuario())) {
            throw new DataErrorException("correo electrónico o contraseña incorrecto.");
        }

        return usuarioTemp;
    }

    public void chequearYCrearUsuario(Usuario usuario) throws DataErrorException, DatabaseException {

        ClaseDBUsuario database = new ClaseDBUsuario();
        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp != null) {
            throw new DataErrorException("Ya existe un usuario con ese correo electrónico.");
        }

        database.crearUsuario(usuario);

        ArrayList administradores = database.solicitarAdministradores();
        if (administradores.isEmpty()) {
            database.crearAdminSistema(usuario);
        }

    }

    public void chequearCambioContraseña(String correo, String contraseñaVieja, String contraseñaNueva) throws DataErrorException, DatabaseException {

        ClaseDBUsuario database = new ClaseDBUsuario();
        Usuario usuarioTemp = database.solicitarUsuario(correo);
        Usuario usuario = new Usuario(correo, contraseñaVieja);

        if (usuarioTemp == null) {
            throw new DataErrorException("No existe un usuario con el correo " + correo + " .");
        } else if (!usuario.getContraseñaUsuario().equals(usuarioTemp.getContraseñaUsuario())) {
            throw new DataErrorException("A ingresado una contraseña actual incorrecta.");
        }
        
        usuario = new Usuario(correo, contraseñaNueva);
        
        database.cambiarContraseña(usuario);
    }

}
