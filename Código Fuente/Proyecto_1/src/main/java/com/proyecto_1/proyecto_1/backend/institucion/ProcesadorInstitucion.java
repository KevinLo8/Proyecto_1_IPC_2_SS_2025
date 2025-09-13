/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.institucion;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBInstitucion;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ProcesadorInstitucion {

    public void crearYGuardarInstitucion(String nombre) throws DatabaseException, DataErrorException {
        ClaseDBInstitucion database = new ClaseDBInstitucion();

        Institucion institucionTemp = database.solicitarInstitucion(nombre);

        if (institucionTemp != null) {
            throw new DataErrorException("Ya existe una institución con el nombre: " + nombre);
        }

        int numero = database.solicitarNumeroId();
        Institucion institucion = new Institucion(numero, nombre);
        database.crearInstitucion(institucion);
    }

    public void chequearYModificarInstitucion(String nombreViejo, String nombreNuevo) throws DatabaseException, DataErrorException {
        ClaseDBInstitucion database = new ClaseDBInstitucion();

        Institucion institucionTemp = database.solicitarInstitucion(nombreNuevo);

        if (institucionTemp != null) {
            throw new DataErrorException("Ya existe una institución con el nombre: " + nombreNuevo);
        }

        Institucion institucion = database.solicitarInstitucion(nombreViejo);
        database.modificarNombre(institucion, nombreNuevo);
    }

    public void chequearYEliminiarInstitucion(String nombre) throws DatabaseException, DataErrorException {
        ClaseDBInstitucion database = new ClaseDBInstitucion();

        Institucion institucionTemp = database.solicitarInstitucion(nombre);

        if (institucionTemp == null) {
            throw new DataErrorException("No existe una institución con el nombre: " + nombre);
        }

        database.eliminarInstitucion(institucionTemp);
        
        ArrayList<Institucion> Instituciones = database.solicitarInstituciones(institucionTemp.getNumero());
        
        for (Institucion Institucion : Instituciones) {
            database.modificarNumero(Institucion, Institucion.getNumero() - 1);
        }
        
    }

}
