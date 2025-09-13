/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.dinero;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBInformacion;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.informacion.Informacion;

/**
 *
 * @author Kevin
 */
public class ProcesadorDinero {
    
    public void AgregarDinero(String dinero, String correo) throws DataErrorException, DatabaseException {
        ClaseDBInformacion database = new ClaseDBInformacion();
        
        Informacion informacion = database.solicitarInformacionPorCorreo(correo);
        double dineroAAgregar = Double.parseDouble(dinero);
        
        if (dineroAAgregar <= 0) {
            throw new DataErrorException("Ingrese una cantidad de dinero mayor a 0.");
        }
        
        double cantidadDinero = informacion.getDinero() + dineroAAgregar;
        
        database.modificarDinero(informacion, cantidadDinero);
    }
}
