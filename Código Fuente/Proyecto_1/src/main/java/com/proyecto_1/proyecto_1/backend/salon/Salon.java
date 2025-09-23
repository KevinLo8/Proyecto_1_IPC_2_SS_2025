/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.salon;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kevin
 */
public class Salon {

    private final String codigo;
    private final String nombre;
    private final int numeroCongreso;
    private final int cantidadActividades;

    public Salon(String codigo, String nombre, int numeroCongreso) throws DataErrorException {

        revisarData(nombre);

        this.codigo = codigo;
        this.nombre = nombre;
        this.numeroCongreso = numeroCongreso;
        this.cantidadActividades = 0;
    }

    public Salon(ResultSet resultSet) throws SQLException {
        this.codigo = resultSet.getString("codigo");
        this.nombre = resultSet.getString("nombre");
        this.numeroCongreso = resultSet.getInt("numeroCongreso");
        this.cantidadActividades = resultSet.getInt("cantidadActividades");
    }

    private void revisarData(String nombre) throws DataErrorException {
        if (nombre.length() > 100) {
            throw new DataErrorException("El tamaño del nombre del salon tiene que ser menor a 100 letras.");
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroCongreso() {
        return numeroCongreso;
    }

    public int getCantidadActividades() {
        return cantidadActividades;
    }
}
