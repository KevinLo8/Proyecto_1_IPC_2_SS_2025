/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.usuario.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBCongreso {

    public Congreso solicitarCongreso(int numero) throws DatabaseException {
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
            throw new DatabaseException("Error al consultar el congreso");
        }
    }

    public Congreso solicitarCongresoPorUbicacionYFecha(LocalDate fecha, String ubicacion) throws DatabaseException {
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
            throw new DatabaseException("Error al consultar el congreso");
        }
    }

    public ArrayList<Congreso> solicitarCongresos() throws DatabaseException {
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
            throw new DatabaseException("Error al consultar los congresos");
        }
    }
    
    public int solicitarNumero() throws DatabaseException {
        ArrayList<Congreso> congresos = solicitarCongresos();
        return congresos.size() + 1;
    }
    
    public void guardarCongreso(Congreso congreso) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO congreso (numero, fecha, ubicacion, precio, idInstitucion) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, congreso.getNumero());
            preparedStatement.setDate(2, Date.valueOf(congreso.getFecha()));
            preparedStatement.setString(3, congreso.getUbicacion());
            preparedStatement.setDouble(4, congreso.getPrecio());
            preparedStatement.setInt(5, congreso.getIdInstitucion());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al registrar el congreso.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear el congreso.");
        }
    }

    private Congreso crearCongreso(ResultSet resultSet) throws SQLException, DatabaseException {
        Congreso congreso = new Congreso(resultSet);
        ClaseDBComite databaseComite = new ClaseDBComite();
        ArrayList<Usuario> comite = databaseComite.solicitarComite(congreso.getNumero());
        congreso.setComite(comite);
        return congreso;
    }

}
