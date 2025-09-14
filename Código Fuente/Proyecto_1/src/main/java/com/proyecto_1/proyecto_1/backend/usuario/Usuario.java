package com.proyecto_1.proyecto_1.backend.usuario;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;
import java.util.Base64;

/**
 *
 * @author Kevin
 */
public class Usuario {

    private final String correoElectronico;
    private final String contraseñaUsuario;
    private boolean adminSistema;
    private boolean adminCongreso;
    private boolean activacion;

    public Usuario(String correoElectronico, String contraseñaUsuario) throws DataErrorException {

        if (!correoElectronico.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new DataErrorException("Ingrese un correo electrónico valido");
        } else if (correoElectronico.length() > 100) {
            throw new DataErrorException("Tamaño de nombre de usuario erroneo.");
        } else if (contraseñaUsuario.length() > 100) {
            throw new DataErrorException("Tamaño de contraseña erroneo.");
        } else if (StringUtils.isBlank(correoElectronico) || StringUtils.isBlank(contraseñaUsuario)) {
            throw new DataErrorException("Llene todos los campos requeridos.");
        }
        
        this.correoElectronico = correoElectronico;
        this.contraseñaUsuario = Base64.getEncoder().encodeToString(contraseñaUsuario.getBytes());
    }

    public Usuario(ResultSet resultSet) throws SQLException {
        correoElectronico = resultSet.getString("correoElectronico");
        contraseñaUsuario = resultSet.getString("contraseña");
        adminSistema = resultSet.getInt("habilitacionAdministradorSistema") == 1;
        adminCongreso = resultSet.getInt("habilitacionAdministradorCongreso") == 1;
        activacion = resultSet.getInt("estadoActivacion") == 1;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public boolean isActivacion() {
        return activacion;
    }

    public boolean isAdminCongreso() {
        return adminCongreso;
    }

    public boolean isAdminSistema() {
        return adminSistema;
    }
}
