package com.proyecto_1.proyecto_1.backend.db;

import java.sql.*;

import com.proyecto_1.proyecto_1.backend.Exception.DatabaseException;

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
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
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
