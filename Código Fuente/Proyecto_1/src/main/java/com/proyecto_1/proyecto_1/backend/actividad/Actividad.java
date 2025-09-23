/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.actividad;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
public class Actividad {

    private final String codigo;
    private final String correoEncargado;
    private final String nombre;
    private final String descripcion;
    private final LocalTime horaInicio;
    private final LocalTime horaFin;
    private final String codigoSalon;
    private final TipoActividad tipoActividad;
    private final int cupo;

    public Actividad(String codigo, String correoEncargado, String nombre, String descripcion, String horaInicio, String horaFin, String codigoSalon,
            String tipoActividad, String cupo) throws DataErrorException {

        revisarData(nombre, descripcion, horaInicio, horaFin, tipoActividad, cupo);

        this.codigo = codigo;
        this.correoEncargado = correoEncargado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horaInicio = LocalTime.parse(horaInicio);
        this.horaFin = LocalTime.parse(horaFin);
        this.codigoSalon = codigoSalon;
        this.tipoActividad = retornarActividad(tipoActividad.toUpperCase());
        if (cupo == "") {
            this.cupo = 0;
        } else {
            this.cupo = Integer.parseInt(cupo);
        }
    }

    public Actividad(ResultSet resultSet) throws SQLException, DataErrorException {
        this.codigo = resultSet.getString("codigo");
        this.nombre = resultSet.getString("nombre");
        this.correoEncargado = resultSet.getString("correoEncargado");
        this.descripcion = resultSet.getString("descripcion");
        this.horaInicio = resultSet.getTime("horaInicio").toLocalTime();
        this.horaFin = resultSet.getTime("horaFin").toLocalTime();
        this.codigoSalon = resultSet.getString("codigo");
        this.tipoActividad = retornarActividad(resultSet.getString("tipoActividad"));
        if (resultSet.getString("cupo") == null) {
            this.cupo = 0;
        } else {
            this.cupo = resultSet.getInt("cupo");
        }
    }

    private void revisarData(String nombre, String descripcion, String horaInicio, String horaFin, String tipoActividad, String cupo) throws DataErrorException {
        if (nombre.length() > 100) {
            throw new DataErrorException("Ingrese un nombre menor a 100 letras");
        } else if (descripcion.length() > 250) {
            throw new DataErrorException("Ingrese una descripcion menor a 250 letras");
        } else if (StringUtils.isBlank(nombre) || StringUtils.isBlank(descripcion)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }

        try {
            int numeroCupo = Integer.parseInt(cupo);
            if (numeroCupo <= 0) {
                throw new DataErrorException("Ingrese un número de cupo mayot a 0");
            }
        } catch (NumberFormatException | NullPointerException e) {
            if (tipoActividad.toUpperCase().equals("TALLER")) {
                throw new DataErrorException("Ingrese un número de cupo valido");
            }
        }

        try {
            LocalTime inicio = LocalTime.parse(horaInicio);
            LocalTime fin = LocalTime.parse(horaFin);

            if (inicio.isAfter(fin)) {
                throw new DataErrorException("Ingrese una hora de inicio menor a la hora de finalización");
            }
        } catch (DateTimeParseException e) {
            throw new DataErrorException("Ingrese una hora de inicio o fin valida");
        }
    }

    private TipoActividad retornarActividad(String tipoActividad) throws DataErrorException {
        TipoActividad tipo = null;
        switch (tipoActividad) {
            case "PONENCIA" ->
                tipo = TipoActividad.PONENCIA;
            case "TALLER" ->
                tipo = TipoActividad.TALLER;
            default ->
                throw new DataErrorException("El tipo de actividad selecionado es invalido");
        }
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCorreoEncargado() {
        return correoEncargado;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public String getCodigoSalon() {
        return codigoSalon;
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }

    public int getCupo() {
        return cupo;
    }

}
