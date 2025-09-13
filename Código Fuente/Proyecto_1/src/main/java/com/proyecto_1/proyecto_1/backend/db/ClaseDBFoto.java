/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.foto.Foto;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBFoto {

    public Foto solicitarFoto(String identificacion) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM foto WHERE identificacionUsuario = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, identificacion);
            resultSet = preparedStatement.executeQuery();

            Foto foto = null;
            if (resultSet.next()) {
                foto = new Foto(resultSet);
            }
            return foto;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error al solicitar la foto");
        }
    }

    public int solicitarIDFoto() throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM foto";
        ResultSet resultSet = null;

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(query);
            ArrayList<Foto> fotos = new ArrayList<>();

            while (resultSet.next()) {
                Foto foto = new Foto(resultSet);
                fotos.add(foto);
            }

            return fotos.size() + 1;

        } catch (SQLException e) {
            throw new DatabaseException("Error al solicitar el id de la foto");
        }
    }

    public void crearFoto(Foto foto) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO foto (id, identificacionUsuario, foto, nombre, tipo, tamaño) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, foto.getId());
            preparedStatement.setString(2, foto.getIdentificacionUsuario());
            preparedStatement.setBlob(3, foto.getDataFoto());
            preparedStatement.setString(4, foto.getNombre());
            preparedStatement.setString(5, foto.getTipo());
            preparedStatement.setLong(6, foto.getTamaño());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al registrar la foto.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear la foto.");
        }
    }

    public void actualizarFoto(Foto foto) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE foto SET foto = ?, nombre = ?, tipo = ?, tamaño = ? WHERE identificacionUsuario = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBlob(1, foto.getDataFoto());
            preparedStatement.setString(2, foto.getNombre());
            preparedStatement.setString(3, foto.getTipo());
            preparedStatement.setLong(4, foto.getTamaño());
            preparedStatement.setString(5, foto.getIdentificacionUsuario());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al modificar la foto.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al actualizar la foto.");
        }
    }

}
