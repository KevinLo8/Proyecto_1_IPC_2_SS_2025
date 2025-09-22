/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.institucion.Institucion;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBInstitucion {

    public Institucion solicitarInstitucion(String nombre) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM institucion WHERE nombre = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, nombre);
            resultSet = preparedStatement.executeQuery();

            Institucion institucion = null;
            if (resultSet.next()) {
                institucion = new Institucion(resultSet);
            }
            return institucion;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar la institución");
        }
    }

    public ArrayList<Institucion> solicitarInstituciones(int numero) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM institucion where id > ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numero);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Institucion> instituciones = new ArrayList<>();

            while (resultSet.next()) {
                Institucion institucion = new Institucion(resultSet);
                instituciones.add(institucion);
            }

            return instituciones;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar las instituciones");
        }
    }

    public int solicitarNumeroId() throws DataBaseException {
        ArrayList<Institucion> instituciones = solicitarInstituciones(0);
        return instituciones.size() + 1;
    }

    public void crearInstitucion(Institucion institucion) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO institucion (id, nombre) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, institucion.getNumero());
            preparedStatement.setString(2, institucion.getNombre());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar la institución.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al crear la institución.");
        }
    }

    public void modificarNombre(Institucion institucion, String nombreNuevo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE institucion SET nombre = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombreNuevo);
            preparedStatement.setInt(2, institucion.getNumero());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al intentar modificar el nombre de la institución.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al modificar el nombre de la institución.");
        }

    }

    public void modificarNumero(Institucion institucion, int numero) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE institucion SET id = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numero);
            preparedStatement.setInt(2, institucion.getNumero());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al intentar modificar el numero de la institución.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al modificar el numero de la institución.");
        }

    }

    public void eliminarInstitucion(Institucion institucion) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "DELETE FROM institucion WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, institucion.getNumero());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al intentar eliminar la institución.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al eliminar la institución.");
        }

    }

}
