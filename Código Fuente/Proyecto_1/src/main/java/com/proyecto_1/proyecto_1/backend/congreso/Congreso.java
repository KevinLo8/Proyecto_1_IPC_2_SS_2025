/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.congreso;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import com.proyecto_1.proyecto_1.backend.usuario.Usuario;
import java.sql.*;
import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class Congreso {

    private final int numero;
    private final LocalDate fecha;
    private final String ubicacion;
    private final double precio;
    private final int idInstitucion;
    private final boolean estadoConvocatoriaTrabajos;
    private ArrayList<Usuario> comite;

    public Congreso(int numero, String fecha, String ubicacion, String precio, int idInstitucion, ArrayList<Usuario> comite) throws DataErrorException {

        revisarData(fecha, ubicacion, precio);

        this.numero = numero;
        this.fecha = LocalDate.parse(fecha);
        this.ubicacion = ubicacion;
        this.precio = Double.parseDouble(precio);
        this.idInstitucion = idInstitucion;
        this.estadoConvocatoriaTrabajos = false;
        this.comite = comite;
    }

    public Congreso(ResultSet resultSet) throws SQLException {
        numero = resultSet.getInt("numero");
        fecha = resultSet.getDate("fecha").toLocalDate();
        ubicacion = resultSet.getString("ubicacion");
        precio = resultSet.getDouble("precio");
        idInstitucion = resultSet.getInt("idInstitucion");
        estadoConvocatoriaTrabajos = resultSet.getBoolean("estadoConvocatoriaTrabajos");
    }

    private void revisarData(String fecha, String ubicacion, String precio) throws DataErrorException {
        if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DataErrorException("Fecha ingresada invalida");
        } else if (ubicacion.length() > 100) {
            throw new DataErrorException("Ingrese una ubicación menor a 100 letras");
        }

        try {
            double dataPrecio = Double.parseDouble(precio);
            if (dataPrecio <= 0) {
                throw new DataErrorException("Ingrese un número de precio meyor a 0");
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Ingrese un número de precio valido");
        }

    }

    public int getNumero() {
        return numero;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public ArrayList<Usuario> getComite() {
        return comite;
    }

    public boolean isEstadoConvocatoriaTrabajos() {
        return estadoConvocatoriaTrabajos;
    }

    public void setComite(ArrayList<Usuario> comite) {
        this.comite = comite;
    }

    
}
