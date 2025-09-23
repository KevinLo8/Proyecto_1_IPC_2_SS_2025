/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.salon.Salon;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBSalon {

    public Salon solicitarSalon(String codigo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM salon WHERE codigo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, codigo);
            ResultSet resultSet = preparedStatement.executeQuery();
            Salon salon = null;

            if (resultSet.next()) {
                salon = new Salon(resultSet);
            }

            return salon;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar el salon");
        }
    }

    public ArrayList<Salon> solicitarSalones(int numero) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM salon WHERE numeroCongreso = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Salon> salones = new ArrayList<>();

            while (resultSet.next()) {
                Salon salon = new Salon(resultSet);
                salones.add(salon);
            }

            return salones;

        } catch (SQLException e) {
            throw new DataBaseException("Error al solicitar los salones");
        }

    }

    public String solicitarCodigo(int numero) throws DataBaseException {

        String codigo = "";
        int i = 1;
        do {
            codigo = String.format("C%d-S%d", numero, i);
            i++;
        } while (solicitarSalon(codigo) != null);

        return codigo;
    }

    public void crearSalon(Salon salon) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO salon (codigo, nombre, numeroCongreso) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, salon.getCodigo());
            preparedStatement.setString(2, salon.getNombre());
            preparedStatement.setInt(3, salon.getNumeroCongreso());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el salon.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al crear el salon.");
        }
    }

    public void modificarNombre(Salon salon, String nombreNuevo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE salon SET nombre = ? WHERE codigo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombreNuevo);
            preparedStatement.setString(2, salon.getCodigo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al intentar modificar el nombre del salon.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al modificar el nombre del salon.");
        }

    }

    public void eliminarSalon(Salon salon) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "DELETE FROM salon WHERE codigo = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, salon.getCodigo());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al intentar eliminar el salon.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al eliminar el salon.");
        }

    }

}
