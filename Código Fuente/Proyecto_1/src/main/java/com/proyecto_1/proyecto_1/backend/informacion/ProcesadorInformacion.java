/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.informacion;

import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.institucion.Institucion;
import com.proyecto_1.proyecto_1.backend.usuario.Usuario;

/**
 *
 * @author Kevin
 */
public class ProcesadorInformacion {

    public void chequearYGuardarInformacion(String identificacion, String nombre, String nombreinstitucion, String telefono, String correo) throws DataErrorException, DatabaseException {
        ClaseDBUsuario databaseUsuario = new ClaseDBUsuario();
        ClaseDBInstitucion databaseInstitucion = new ClaseDBInstitucion();
        ClaseDBInformacion databaseInformacion = new ClaseDBInformacion();

        Usuario usuario = databaseUsuario.solicitarUsuario(correo);
        if (usuario == null) {
            throw new DataErrorException("No existe un usuario con el correo: " + correo);
        }

        Institucion institucion = databaseInstitucion.solicitarInstitucion(nombreinstitucion);
        if (institucion == null) {
            throw new DataErrorException("No existe una institución con el nombre: " + nombreinstitucion);
        }

        Informacion informacion = databaseInformacion.solicitarInformacionPorUsuario(nombre);
        if (informacion != null && !informacion.getCorreoUsuario().equals(usuario.getCorreoElectronico())) {
            throw new DataErrorException("El nombre de usuario ya esta en uso");
        }
        
        informacion = new Informacion(identificacion, nombre, institucion.getNumero(), telefono, correo);
        if (databaseInformacion.solicitarInformacionPorIdentificacion(identificacion) == null) {
            databaseInformacion.crearInformacion(informacion);
        } else {
            databaseInformacion.actualizarInformacion(informacion);
        }
        
    }
}
