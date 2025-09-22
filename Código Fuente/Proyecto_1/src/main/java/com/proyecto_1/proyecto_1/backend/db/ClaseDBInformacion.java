/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.informacion.Informacion;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class ClaseDBInformacion {

    public Informacion solicitarInformacionPorIdentificacion(String identificacion) throws DataBaseException {
        String query = "SELECT * FROM informacion WHERE numeroIdentificacion = ?";
        Informacion informacion = solicitarInformacion(query, identificacion);

        return informacion;
    }

    public Informacion solicitarInformacionPorUsuario(String usuario) throws DataBaseException {
        String query = "SELECT * FROM informacion WHERE nombre = ?";
        Informacion informacion = solicitarInformacion(query, usuario);

        return informacion;
    }

    public Informacion solicitarInformacionPorCorreo(String correo) throws DataBaseException {
        String query = "SELECT * FROM informacion WHERE correoUsuario = ?";
        Informacion informacion = solicitarInformacion(query, correo);

        return informacion;
    }

    public int solicitarInstitucionDeUsuario(String correo) throws DataBaseException {
        Informacion informacion = solicitarInformacionPorCorreo(correo);
        return informacion.getIdInstitución();
    }

    private Informacion solicitarInformacion(String query, String data) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, data);
            ResultSet resultSet = preparedStatement.executeQuery();

            Informacion informacion = null;
            if (resultSet.next()) {
                informacion = new Informacion(resultSet);
            }
            return informacion;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar la información");
        }

    }

    public void crearInformacion(Informacion informacion) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO informacion (numeroIdentificacion, nombre, idIntitucion, numeroTelefono, correoUsuario) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, informacion.getNumeroIdentificacion());
            preparedStatement.setString(2, informacion.getNombreUSuario());
            preparedStatement.setInt(3, informacion.getIdInstitución());
            preparedStatement.setString(4, informacion.getNumeroTelefono());
            preparedStatement.setString(5, informacion.getCorreoUsuario());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la información.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al crear la información.");
        }
    }

    public void actualizarInformacion(Informacion informacion) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE informacion SET nombre = ?, idIntitucion = ?, numeroTelefono = ? WHERE numeroIdentificacion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, informacion.getNombreUSuario());
            preparedStatement.setInt(2, informacion.getIdInstitución());
            preparedStatement.setString(3, informacion.getNumeroTelefono());
            preparedStatement.setString(4, informacion.getNumeroIdentificacion());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al modificar la información.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al actualizar la información.");
        }
    }

    public void modificarDinero(Informacion informacion, double cantidadDinero) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE informacion SET saldo = ? WHERE numeroIdentificacion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, cantidadDinero);
            preparedStatement.setString(2, informacion.getNumeroIdentificacion());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al cambiar el dinero.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al modificar el dinero.");
        }
    }

}
