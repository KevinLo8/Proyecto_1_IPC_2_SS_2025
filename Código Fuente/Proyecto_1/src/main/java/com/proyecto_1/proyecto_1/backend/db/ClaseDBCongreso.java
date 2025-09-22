/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.usuario.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBCongreso {

    public Congreso solicitarCongreso(int numero) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM congreso WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();

            Congreso congreso = null;
            if (resultSet.next()) {
                congreso = crearCongreso(resultSet);
            }
            return congreso;

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el congreso");
        }
    }

    public ArrayList<Congreso> solicitarCongresos() throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM congreso";

        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(query);
            ArrayList<Congreso> congresos = new ArrayList<>();

            while (resultSet.next()) {
                Congreso congreso = crearCongreso(resultSet);
                congresos.add(congreso);
            }

            return congresos;

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los congresos");
        }
    }

    public ArrayList<Congreso> solicitarCongresosPorCorreo(String correo) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM congreso WHERE correoAdministrador = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, correo);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Congreso> congresos = new ArrayList<>();

            while (resultSet.next()) {
                Congreso congreso = crearCongreso(resultSet);
                congresos.add(congreso);
            }

            return congresos;

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar los congresos");
        }
    }

    public Congreso solicitarCongresoPorUbicacionYFecha(LocalDate fecha, String ubicacion) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM congreso WHERE fecha = ? AND ubicacion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setDate(1, Date.valueOf(fecha));
            preparedStatement.setString(2, ubicacion);
            ResultSet resultSet = preparedStatement.executeQuery();

            Congreso congreso = null;
            if (resultSet.next()) {
                congreso = crearCongreso(resultSet);
            }
            return congreso;

        } catch (SQLException e) {
            throw new DataBaseException("Error al consultar el congreso");
        }
    }

    public int solicitarNumero() throws DataBaseException {
        ArrayList<Congreso> congresos = solicitarCongresos();
        return congresos.size() + 1;
    }

    public void guardarCongreso(Congreso congreso) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO congreso (numero, nombre, ubicacion, fecha, descripcion, precio, idInstitucion, correoAdministrador) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, congreso.getNumero());
            preparedStatement.setString(2, congreso.getNombre());
            preparedStatement.setString(3, congreso.getUbicacion());
            preparedStatement.setDate(4, Date.valueOf(congreso.getFecha()));
            preparedStatement.setString(5, congreso.getDescripcion());
            preparedStatement.setDouble(6, congreso.getPrecio());
            preparedStatement.setInt(7, congreso.getIdInstitucion());
            preparedStatement.setString(8, congreso.getCorreoAdministrador());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al registrar el congreso.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al crear el congreso.");
        }
    }

    private Congreso crearCongreso(ResultSet resultSet) throws SQLException, DataBaseException {
        Congreso congreso = new Congreso(resultSet);
        ClaseDBComite databaseComite = new ClaseDBComite();
        ArrayList<Usuario> comite = databaseComite.solicitarComite(congreso.getNumero());
        congreso.setComite(comite);
        return congreso;
    }

    public void cambiarEstadoConvocatoria(Congreso congreso) throws DataBaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "UPDATE congreso SET estadoConvocatoriaTrabajos = ? WHERE numero = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, !congreso.isEstadoConvocatoriaTrabajos());
            preparedStatement.setInt(2, congreso.getNumero());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DataBaseException("Error al modificar el estado de la convocatoria.");
            }
        } catch (SQLException e) {
            throw new DataBaseException("Error al cambiar el estado de la convocatoria.");
        }
    }

}
