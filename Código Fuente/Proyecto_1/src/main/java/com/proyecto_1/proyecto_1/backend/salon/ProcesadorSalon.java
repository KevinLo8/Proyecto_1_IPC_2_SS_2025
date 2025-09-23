/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.salon;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.congreso.ProcesadorCongreso;
import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
public class ProcesadorSalon {

    private final ClaseDBSalon database;

    public ProcesadorSalon() {
        database = new ClaseDBSalon();
    }

    public ArrayList<Salon> crearListadoSalones(String correo, String numeroCongreso) throws DataBaseException, DataErrorException {

        ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
        Congreso congreso = procesadorCongreso.verificarCongreso(correo, numeroCongreso);

        ArrayList<Salon> salones = database.solicitarSalones(congreso.getNumero());
        return salones;
    }

    public void chequearYCrearSalon(String correo, String numeroCongreso, String nombre) throws DataErrorException, DataBaseException {

        ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
        Congreso congreso = procesadorCongreso.verificarCongreso(correo, numeroCongreso);

        String codigo = database.solicitarCodigo(congreso.getNumero());

        Salon salon = new Salon(codigo, nombre, congreso.getNumero());
        database.crearSalon(salon);
    }

    public void chequearYModificarSalon(String correo, String numeroCongreso, String codigo, String nombreViejo, String nombreNuevo) throws DataErrorException, DataBaseException {

        ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
        Congreso congreso = procesadorCongreso.verificarCongreso(correo, numeroCongreso);

        Salon salon = verificarSalon(codigo, congreso);

        database.modificarNombre(salon, nombreNuevo);
    }

    public void chequearYEliminarSalon(String correo, String numeroCongreso, String codigo) throws DataErrorException, DataBaseException {
        ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
        Congreso congreso = procesadorCongreso.verificarCongreso(correo, numeroCongreso);

        Salon salon = verificarSalon(codigo, congreso);

        database.eliminarSalon(salon);
    }

    public ArrayList<Salon> solicitarSalones(int numero) throws DataBaseException {
        return database.solicitarSalones(numero);
    }

    private Salon verificarSalon(String codigo, Congreso congreso) throws DataErrorException, DataBaseException {
        Salon salon = database.solicitarSalon(codigo);
        if (salon == null) {
            throw new DataErrorException("No existe un salon con el codigo enviado");
        } else if (salon.getNumeroCongreso() != congreso.getNumero()) {
            throw new DataErrorException("El salon a modificar no pertenece al congreso enviado");
        }
        return salon;
    }

}
