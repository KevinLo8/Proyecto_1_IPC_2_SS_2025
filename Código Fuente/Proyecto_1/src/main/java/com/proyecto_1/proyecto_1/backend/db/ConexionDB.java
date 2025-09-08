package com.proyecto_1.proyecto_1.backend.db;

import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import java.sql.*;

public class ConexionDB {

    private static final String IP = "localhost";
    private static final int PUERTO = 3306;
    private static final String SCHEMA = "CONGRESODB";
    private static final String USER_NAME = "admindba";
    private static final String PASSWORD = "12345";
    private static final String URL = "jdbc:mysql://" + IP + ":" + PUERTO + "/" + SCHEMA;

    private static ConexionDB instance;

    private Connection connection;

    private ConexionDB() throws DatabaseException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseException("Ha ocurido un error al conectarse a la Base de Datos.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConexionDB getInstance() throws DatabaseException {
        if (instance == null) {
            instance = new ConexionDB();
        }
        return instance;
    }
}
