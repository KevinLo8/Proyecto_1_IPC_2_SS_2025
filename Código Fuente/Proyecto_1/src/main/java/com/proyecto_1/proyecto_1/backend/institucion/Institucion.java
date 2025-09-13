/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.institucion;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.sql.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
public class Institucion {

    private final int numero;
    private final String nombre;

    public Institucion(int numero, String nombre) throws DataErrorException {

        if (nombre.length() > 100) {
            throw new DataErrorException("Tamaño de nombre de institución invalido.");
        } else if (StringUtils.isBlank(nombre)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }

        this.numero = numero;
        this.nombre = nombre;
    }

    public Institucion(ResultSet resultSet) throws SQLException {
        numero = resultSet.getInt("id");
        nombre = resultSet.getString("nombre");
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }
}
