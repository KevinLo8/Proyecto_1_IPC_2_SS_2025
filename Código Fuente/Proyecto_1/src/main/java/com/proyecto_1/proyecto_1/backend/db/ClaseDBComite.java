/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ClaseDBComite {

    public ArrayList<Usuario> solicitarComite(int numero) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM comite_cientifico WHERE numeroCongreso = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, numero);
            ResultSet resultSet = preparedStatement.executeQuery();
            String[] arreglo = new String[0];

            if (resultSet.next()) {
                do {

                    String data = resultSet.getString("correoMiembro");
                    String[] partTemp = new String[arreglo.length + 1];

                    int i = 0;
                    while (i < arreglo.length) {
                        partTemp[i] = arreglo[i];
                        i++;
                    }

                    partTemp[arreglo.length] = data;
                    arreglo = partTemp;

                } while (resultSet.next());
            }

            ProcesadorUsuario procesadorUsuario = new ProcesadorUsuario();
            ArrayList<Usuario> comite = procesadorUsuario.crearArrreglo(arreglo);
            
            return comite;

        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar el comite");
        }
    }

    public void guardarComite(Congreso congreso) throws DatabaseException {
        ArrayList<Usuario> comite = congreso.getComite();
        int numero = solicitarNumero();
        
        for (Usuario usuario : comite) {
            guardarMiembro(numero, usuario.getCorreoElectronico(), congreso.getNumero());
            numero++;
        }
    }
    
    private int solicitarNumero() throws DatabaseException {
                Connection connection = ConexionDB.getInstance().getConnection();
        String query = "SELECT * FROM comite_cientifico";

        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(query);
            int numero = 1;

            if (resultSet.next()) {
                numero++;
            }

            return numero;

        } catch (SQLException e) {
            throw new DatabaseException("Error al consultar el numero del comite");
        }
    }
    
    private void guardarMiembro(int numero, String correoElectronico, int numeroCongreso) throws DatabaseException {
        Connection connection = ConexionDB.getInstance().getConnection();
        String query = "INSERT INTO comite_cientifico (id, correoMiembro, numeroCongreso) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, numero);
            preparedStatement.setString(2, correoElectronico);
            preparedStatement.setInt(3, numeroCongreso);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DatabaseException("Error al registrar el miembro del comité.");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al crear el miembro del comité.");
        }
    }

}
