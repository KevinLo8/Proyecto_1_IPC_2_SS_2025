/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.informacion;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.sql.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
public class Informacion {

    private final String numeroIdentificacion;
    private final String nombreUSuario;
    private final int idInstitución;
    private final String numeroTelefono;
    private final double dinero;
    private final String correoUsuario;

    public Informacion(String numeroIdentificacion, String nombreUSuario, int idInstitución,
            String numeroTelefono, String correoUsuario) throws DataErrorException {

        revisarInformacion(numeroIdentificacion, nombreUSuario, numeroTelefono, correoUsuario);

        this.numeroIdentificacion = numeroIdentificacion;
        this.nombreUSuario = nombreUSuario;
        this.idInstitución = idInstitución;
        this.numeroTelefono = numeroTelefono;
        this.dinero = 0;
        this.correoUsuario = correoUsuario;
    }

    public Informacion(ResultSet resultSet) throws SQLException {
        numeroIdentificacion = resultSet.getString("numeroIdentificacion");
        nombreUSuario = resultSet.getString("nombre");
        idInstitución = resultSet.getInt("idInstitucion");
        numeroTelefono = resultSet.getString("numeroTelefono");
        dinero = resultSet.getDouble("saldo");
        correoUsuario = resultSet.getString("correoUsuario");
    }

    private void revisarInformacion(String numeroIdentificacion, String nombreUSuario,
            String numeroTelefono, String correoUsuario) throws DataErrorException {

        if (!numeroTelefono.matches("\\d{7,15}")) {
            throw new DataErrorException("Ingrese un número telefonico valido");
        } else if (!correoUsuario.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new DataErrorException("Ingrese un correo electrónico valido.");
        } else if (numeroIdentificacion.length() > 25) {
            throw new DataErrorException("Tamaño de numero de identificación de usuario invalido.");
        } else if (nombreUSuario.length() > 100) {
            throw new DataErrorException("Tamaño de nombre de usuario invalido.");
        } else if (correoUsuario.length() > 100) {
            throw new DataErrorException("Tamaño de tamaño de correo electrónico invalido.");
        } else if (numeroTelefono.length() > 15) {
            throw new DataErrorException("Tamaño de numero telefonico invalido.");
        } else if (StringUtils.isBlank(numeroIdentificacion) || StringUtils.isBlank(nombreUSuario)
                || StringUtils.isBlank(numeroTelefono) || StringUtils.isBlank(correoUsuario)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }

    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getNombreUSuario() {
        return nombreUSuario;
    }

    public int getIdInstitución() {
        return idInstitución;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public double getDinero() {
        return dinero;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

}
