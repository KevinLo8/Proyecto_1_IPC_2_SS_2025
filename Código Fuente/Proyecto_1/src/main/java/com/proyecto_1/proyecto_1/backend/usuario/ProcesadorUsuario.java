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

    private ClaseDBUsuario database;

    public ProcesadorUsuario() {
        database = new ClaseDBUsuario();
    }

    public Usuario chequearInicioSesion(Usuario usuario) throws DataErrorException, DatabaseException {

        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp == null) {
            throw new DataErrorException("Correo electrónico o contraseña incorrecto.");
        } else if (!usuario.getContraseñaUsuario().equals(usuarioTemp.getContraseñaUsuario())) {
            throw new DataErrorException("Correo electrónico o contraseña incorrecto.");
        } else if (!usuarioTemp.isActivacion()) {
            throw new DataErrorException("Su usuario se encuetra desactivado.");
        }

        return usuarioTemp;
    }

    public void chequearYCrearUsuario(Usuario usuario) throws DataErrorException, DatabaseException {

        Usuario usuarioTemp = database.solicitarUsuario(usuario.getCorreoElectronico());

        if (usuarioTemp != null) {
            throw new DataErrorException("Ya existe un usuario con ese correo electrónico.");
        }

        database.crearUsuario(usuario);

        ArrayList administradores = database.solicitarAdministradoresSistema();
        if (administradores.isEmpty()) {
            database.crearAdminSistema(usuario);
        }

    }

    public void chequearCambioContraseña(String correo, String contraseñaVieja, String contraseñaNueva) throws DataErrorException, DatabaseException {

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

    public void chequearYCrearAdminitradorCongreso(String correo) throws DataErrorException, DatabaseException {

        Usuario usuario = database.solicitarUsuario(correo);

        if (usuario == null) {
            throw new DataErrorException("No existe un usuario con el correo " + correo + " .");
        } else if (usuario.isAdminCongreso()) {
            throw new DataErrorException("El usuario con el correo " + correo + " ya es un administrador de congreso.");
        } else if (!usuario.isActivacion()) {
            throw new DataErrorException("El usuario con el correo " + correo + " esta desactivado.");
        }

        database.crearAdminCongreso(usuario);
    }

    public ArrayList<Usuario> crearArrreglo(String[] correos) throws DatabaseException {
        ArrayList<Usuario> comite = new ArrayList<>();
        
        for (String correo : correos) {
            Usuario usuario = database.solicitarUsuario(correo);
            comite.add(usuario);
        }
        
        return comite;
    }

}
