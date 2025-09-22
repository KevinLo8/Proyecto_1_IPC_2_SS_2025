/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.foto;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBFoto;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.io.InputStream;

/**
 *
 * @author Kevin
 */
public class ProcesadorFoto {

    public void chequearYGuardarFoto(String identificacion, InputStream data, String nombre, String tipo, long tamaño) throws DataErrorException, DataBaseException {

        ClaseDBFoto database = new ClaseDBFoto();
      
        int id = database.solicitarIDFoto();
        Foto foto = new Foto(id, identificacion, data, nombre, tipo, tamaño);

        
        if (database.solicitarFoto(identificacion) == null) {
            database.crearFoto(foto);
        } else {
            database.actualizarFoto(foto);
        }
    }

}
