/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.congreso;

import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ProcesadorCongreso {

    private final ClaseDBCongreso database;

    public ProcesadorCongreso() {
        database = new ClaseDBCongreso();
    }

    public void chequearYCrearCongreso(String correo, String descripcion, String fecha, String ubicacion, String nombre, String precio, String[] correos) throws DataBaseException, DataErrorException {

        if (correos == null || correos.length > 3) {
            throw new DataErrorException("Tiene que seleccionar de 1 a 3 usuarios para el comité");
        }
        ProcesadorUsuario procesadorUsuario = new ProcesadorUsuario();
        ArrayList<Usuario> comite = procesadorUsuario.crearArrreglo(correos);

        ClaseDBInformacion databaseInformacion = new ClaseDBInformacion();
        int idInstitucion = databaseInformacion.solicitarInstitucionDeUsuario(correo);

        int numero = database.solicitarNumero();
        Congreso congreso = new Congreso(numero, descripcion, fecha, ubicacion, nombre, precio, idInstitucion, correo, comite);

        Congreso congresoTemp = database.solicitarCongresoPorUbicacionYFecha(congreso.getFecha(), congreso.getUbicacion());
        if (congresoTemp != null) {
            throw new DataErrorException("Ya existe un congreso en esa ubicación y en esa fecha");
        }

        database.guardarCongreso(congreso);
        ClaseDBComite databaseComite = new ClaseDBComite();
        databaseComite.guardarComite(congreso);

    }

    public void cambiarEstadoConvocarotia(String numeroString) throws DataErrorException, DataBaseException {

        int numero = 0;
        try {
            numero = Integer.parseInt(numeroString);
        } catch (NumberFormatException e) {
            throw new DataErrorException("numero de congreso invalido");
        }
        Congreso congreso = database.solicitarCongreso(numero);

        if (congreso == null) {
            throw new DataErrorException("No existe un congreso con el numero seleccionado");
        }

        database.cambiarEstadoConvocatoria(congreso);

    }

}
