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

    public void chequearInicioSesion(Usuario usuario) throws DataErrorException, DatabaseException {

        ClaseDBUsuario database = new ClaseDBUsuario();
        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp == null) {
            throw new DataErrorException("correo electrónico o contraseña incorrecto.");
        } else if (!usuario.getContraseñaUsuario().equals(usuarioTemp.getContraseñaUsuario())) {
            throw new DataErrorException("correo electrónico o contraseña incorrecto.");
        }

    }

    public void chequearYCrearUsuario(Usuario usuario) throws DataErrorException, DatabaseException {

        ClaseDBUsuario database = new ClaseDBUsuario();
        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp != null) {
            throw new DataErrorException("Ya existe un usuario con ese correo electrónico.");
        }
        
        database.crearUsuario(usuario);

        ArrayList administradores = database.solicitarAdministradores();
        if (administradores.size() == 0) {
            database.crearAdminSistema(usuario);
        }
        
    }

}
