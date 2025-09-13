/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.foto;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
public class Foto {

    private final int id;
    private final String identificacionUsuario;
    private final InputStream dataFoto;
    private final String nombre;
    private final String tipo;
    private final long tamaño;

    public Foto(int id, String identificacionUsuario, InputStream dataFoto,
            String nombre, String tipo, long tamaño) throws DataErrorException {

        revisarInformacion(identificacionUsuario, nombre);

        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.dataFoto = dataFoto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tamaño = tamaño;
    }

    public Foto(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt("id");
        identificacionUsuario = resultSet.getString("identificacionUsuario");
        dataFoto = resultSet.getBlob("foto").getBinaryStream();
        nombre = resultSet.getString("nombre");
        tipo = resultSet.getString("tipo");
        tamaño = resultSet.getInt("tamaño");
    }

    private void revisarInformacion(String identificacionUsuario, String nombre) throws DataErrorException {

        if (identificacionUsuario.length() > 25) {
            throw new DataErrorException("Tamaño de numero de identificación de usuario invalido.");
        } else if (nombre.length() > 150) {
            throw new DataErrorException("Tamaño de nombre de archivo invalido.");
        } else if (StringUtils.isBlank(identificacionUsuario) || StringUtils.isBlank(nombre)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }

    }

    public int getId() {
        return id;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public InputStream getDataFoto() {
        return dataFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public long getTamaño() {
        return tamaño;
    }

}
