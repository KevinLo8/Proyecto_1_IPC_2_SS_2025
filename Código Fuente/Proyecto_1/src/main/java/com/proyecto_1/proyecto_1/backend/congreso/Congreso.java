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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
public class Congreso {

    private final int numero;
    private final String descripcion;
    private final LocalDate fecha;
    private final String ubicacion;
    private final String nombre;
    private final double precio;
    private final int idInstitucion;
    private final String correoAdministrador;
    private final boolean estadoConvocatoriaTrabajos;
    private ArrayList<Usuario> comite;
    //private ArrayList<Actividad> actividades;

    public Congreso(int numero, String descripcion, String fecha, String ubicacion, String nombre, String precio, int idInstitucion, String correo,
            ArrayList<Usuario> comite/*, ArrayList<Actividad> actividades*/) throws DataErrorException {

        revisarData(fecha, descripcion, ubicacion, nombre, precio);

        this.numero = numero;
        this.descripcion = descripcion;
        this.fecha = LocalDate.parse(fecha);
        this.ubicacion = ubicacion;
        this.nombre = nombre;
        this.precio = Double.parseDouble(precio);
        this.idInstitucion = idInstitucion;
        this.correoAdministrador = correo;
        this.estadoConvocatoriaTrabajos = false;
        this.comite = comite;
        //this.actividades = actividades;
    }

    public Congreso(ResultSet resultSet) throws SQLException {
        numero = resultSet.getInt("numero");
        descripcion = resultSet.getString("descripcion");
        fecha = resultSet.getDate("fecha").toLocalDate();
        ubicacion = resultSet.getString("ubicacion");
        nombre = resultSet.getString("nombre");
        precio = resultSet.getDouble("precio");
        idInstitucion = resultSet.getInt("idInstitucion");
        correoAdministrador = resultSet.getString("correoAdministrador");
        estadoConvocatoriaTrabajos = resultSet.getBoolean("estadoConvocatoriaTrabajos");
    }

    private void revisarData(String fecha, String descripcion, String ubicacion, String nombre, String precio) throws DataErrorException {
        if (!fecha.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DataErrorException("Fecha ingresada invalida");
        } else if (descripcion.length() > 200) {
            throw new DataErrorException("Ingrese una ubicación menor a 100 letras");
        } else if (ubicacion.length() > 100) {
            throw new DataErrorException("Ingrese una ubicación menor a 100 letras");
        } else if (nombre.length() > 150) {
            throw new DataErrorException("Ingrese una nombre menor a 150 letras");
        }

        try {
            double dataPrecio = Double.parseDouble(precio);
            if (dataPrecio <= 0) {
                throw new DataErrorException("Ingrese un número de precio meyor a 0");
            }
        } catch (NumberFormatException | NullPointerException e) {
            throw new DataErrorException("Ingrese un número de precio valido");
        }

        if (StringUtils.isBlank(descripcion) || StringUtils.isBlank(ubicacion) || StringUtils.isBlank(nombre)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }

    }

    public int getNumero() {
        return numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public String getCorreoAdministrador() {
        return correoAdministrador;
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

    /*
    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }
     */
    
}
