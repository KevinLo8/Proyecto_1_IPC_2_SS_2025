package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import java.sql.*;

import com.proyecto_1.proyecto_1.backend.usuario.Usuario;
import java.util.ArrayList;

public class ClaseDBUsuario {

    public Usuario solicitarUsuario(String correo) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM usuario WHERE correoElectronico = ?";
        ResultSet resultSet = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, correo);
            resultSet = preparedStatement.executeQuery();

            Usuario usuario = null;
            if (resultSet.next()) {
                usuario = new Usuario(resultSet);
            }
            return usuario;

        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar el usuario");
        }
    }

    public ArrayList solicitarAdministradores() throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM usuario WHERE habilitacionAdministradorSistema = 1";
        ResultSet resultSet = null;

        try (Statement Statement = connection.createStatement()) {
            resultSet = Statement.executeQuery(query);

            ArrayList<Usuario> Administradores = new ArrayList<>();

            while (resultSet.next()) {
                Usuario usuario = new Usuario(resultSet);
                Administradores.add(usuario);
            }

            return Administradores;

        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar el usuario");
        }
    }

    public void crearUsuario(Usuario usuario) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO usuario (correoElectronico, contraseña) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getCorreoElectronico());
            preparedStatement.setString(2, usuario.getContraseñaUsuario());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al registrar el usuario.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear el usuario.");
        }
    }

    public void crearAdminSistema(Usuario usuario) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE usuario SET habilitacionAdministradorSistema = 1 WHERE correoElectronico = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getCorreoElectronico());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al registrar el administrador de sistema.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear el administrador de sistema.");
        }
    }

    public void cambiarContraseña(Usuario usuario) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE usuario SET contraseña = ? WHERE correoElectronico = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, usuario.getContraseñaUsuario());
            preparedStatement.setString(2, usuario.getCorreoElectronico());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al modificar la contraseña del usuario..");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al cambiar la contraseña del usuario.");
        }

    }

}
