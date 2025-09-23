/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.actividad;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBActividad;
import com.proyecto_1.proyecto_1.backend.exceptions.*;

/**
 *
 * @author Kevin
 */
public class ProcesadorActividad {
    
    private final ClaseDBActividad database;
    
    public ProcesadorActividad() {
        this.database = new ClaseDBActividad();
    }
    
    public void chequearYCrearActividad(String correo, String numeroCongreso, String nombre, String descripcion, String horaaInicio, String horaFin, String codigoSalon,
            String tipoActividad, String cupo) throws DataBaseException, DataErrorException {
        
        String codigo = database.solicitarCodigo(Integer.parseInt(numeroCongreso));
        Actividad actividad = new Actividad(codigo, correo, nombre, descripcion, horaaInicio, horaFin, codigoSalon, tipoActividad, cupo);
        
        database.guardarActividad(actividad);
    }
    
}
