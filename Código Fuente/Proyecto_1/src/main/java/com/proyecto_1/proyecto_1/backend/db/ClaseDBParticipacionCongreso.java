/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.participacionCongreso.ParticipacionCongreso;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBParticipacionCongreso {

    public ParticipacionCongreso solicitarParticipacion(String correo, int numeroCongreso, String tipoTrabajo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM participacion_congreso WHERE correoUsuario = ? AND numeroCongreso = ? AND tipo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, correo);
            preparedStatement.setInt(2, numeroCongreso);
            preparedStatement.setString(3, tipoTrabajo);
            ResultSet resultSet = preparedStatement.executeQuery();

            ParticipacionCongreso trabajo = null;
            if (resultSet.next()) {
                trabajo = new ParticipacionCongreso(resultSet);
            }
            return trabajo;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar el trabajo");
        }
    }

    public void guardarParticipacion(ParticipacionCongreso trabajo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO participacion_congreso (id, tipo, correoUsuario, numeroCongreso) VALUES (?, ?, ?, ?)";
        int id = solicitarNumero();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, trabajo.getTipoTrabajo().toString());
            preparedStatement.setString(3, trabajo.getCorreoUsuario());
            preparedStatement.setInt(4, trabajo.getNumeroCongreso());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el trabajo.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al crear el trabajo.");
        }
    }

    public void cambiarEstadoRevision(ParticipacionCongreso trabajo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE participacion_congreso SET estadoRevision = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, !trabajo.isEstadoRevision());
            preparedStatement.setInt(2, trabajo.getNumero());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al modificar el estado de la revision.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al cambiar el estado de la revision.");
        }

    }

    private int solicitarNumero() throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM participacion_congreso";

        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(query);
            int numero = 1;

            while (resultSet.next()) {
                numero++;
            }

            return numero;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar el numero del trabajo");
        }
    }

}
