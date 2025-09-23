/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.actividad.Actividad;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class ClaseDBActividad {

    public Actividad solicitarActividad(String codigo) throws DataBaseException, DataErrorException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM actividad WHERE codigo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();

            Actividad actividad = null;
            if (resultSet.next()) {
                actividad = new Actividad(resultSet);
            }
            return actividad;

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el congreso");
        }
    }

    public void guardarActividad(Actividad actividad) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO actividad (codigo, nombre, descripcion, tipo, horaInicio, horaFin, correoEncargado, cupo, codigoSalon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, actividad.getCodigo());
            preparedStatement.setString(2, actividad.getNombre());
            preparedStatement.setString(3, actividad.getDescripcion());
            preparedStatement.setString(4, actividad.getTipoActividad().toString());
            preparedStatement.setTime(5, Time.valueOf(actividad.getHoraInicio()));
            preparedStatement.setTime(6, Time.valueOf(actividad.getHoraFin()));
            preparedStatement.setString(7, actividad.getCorreoEncargado());
            preparedStatement.setInt(8, actividad.getCupo());
            preparedStatement.setString(9, actividad.getCodigoSalon());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la actividad.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataBaseException("Error al crear la actividad.");
        }
    }

        public String solicitarCodigo(int numero) throws DataBaseException, DataErrorException {

        String codigo = "";
        int i = 1;
        do {
            codigo = String.format("C%d-A%d", numero, i);
            i++;
        } while (solicitarActividad(codigo) != null);

        return codigo;
    }

}
