/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.participacionCongreso;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import com.proyecto_1.proyecto_1.backend.participacionCongreso.TipoParticipacionCongreso;
import java.sql.*;

/**
 *
 * @author Kevin
 */
public class ParticipacionCongreso {
    
    private final int numero;
    private final String correoUsuario;
    private final int numeroCongreso;
    private final TipoParticipacionCongreso tipoTrabajo;
    private final boolean estadoRevision;
    
    public ParticipacionCongreso(String correoUsuario, String numeroCongreso, String tipoTrabajo) throws DataErrorException {
        this.numero = 0;
        this.correoUsuario = correoUsuario;
        this.numeroCongreso = Integer.parseInt(numeroCongreso);
        this.tipoTrabajo = retornarTipoTrabajo(tipoTrabajo.toUpperCase());
        this.estadoRevision = false;
    }
    
    public ParticipacionCongreso(ResultSet resultSet) throws SQLException, DataErrorException {
        this.numero = resultSet.getInt("id");
        this.correoUsuario = resultSet.getString("correoUsuario");
        this.numeroCongreso = resultSet.getInt("numeroCongreso");
        this.tipoTrabajo = retornarTipoTrabajo(resultSet.getString("tipo"));
        this.estadoRevision = resultSet.getBoolean("estadoRevision");
    }
    
    private TipoParticipacionCongreso retornarTipoTrabajo(String tipoTrabajo) throws DataErrorException {
        TipoParticipacionCongreso tipo = null;
        switch (tipoTrabajo) {
            case "ASISTENTE" ->
                tipo = TipoParticipacionCongreso.ASISTENTE;
            case "PONENTE" ->
                tipo = TipoParticipacionCongreso.PONENTE;
            case "TALLERISTA" ->
                tipo = TipoParticipacionCongreso.TALLERISTA;
            case "INVITADO" ->
                tipo = TipoParticipacionCongreso.INVITADO;
            default ->
                throw new DataErrorException("El tipo de Participación selecionado es invalido");
        }
        return tipo;
    }
    
    public int getNumero() {
        return numero;
    }
    
    public String getCorreoUsuario() {
        return correoUsuario;
    }
    
    public int getNumeroCongreso() {
        return numeroCongreso;
    }
    
    public TipoParticipacionCongreso getTipoTrabajo() {
        return tipoTrabajo;
    }
    
    public boolean isEstadoRevision() {
        return estadoRevision;
    }
    
}
