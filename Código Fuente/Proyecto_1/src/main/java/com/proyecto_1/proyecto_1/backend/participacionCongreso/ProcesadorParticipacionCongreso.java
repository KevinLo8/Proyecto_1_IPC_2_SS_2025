/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.participacionCongreso;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.informacion.Informacion;

/**
 *
 * @author Kevin
 */
public class ProcesadorParticipacionCongreso {

    private final ClaseDBParticipacionCongreso database;

    public ProcesadorParticipacionCongreso() {
        database = new ClaseDBParticipacionCongreso();
    }

    public void chequearYCrearTrabajo(String correo, String numero, String tipo) throws DataErrorException, DataBaseException {

        ParticipacionCongreso participacion = new ParticipacionCongreso(correo, numero, tipo);

        ClaseDBCongreso databaseCongreso = new ClaseDBCongreso();
        Congreso congreso = databaseCongreso.solicitarCongreso(participacion.getNumeroCongreso());
        if (congreso == null) {
            throw new DataErrorException("No existe un congreso con el número enviado");
        }

        ParticipacionCongreso participacionTemp = database.solicitarParticipacion(correo, participacion.getNumeroCongreso(), participacion.getTipoTrabajo().toString());
        if (participacionTemp != null) {
            throw new DataErrorException("Ya ha presentado este tipo de trabajo en el congreso enviado");
        }

        crearParticipacion(participacion);
    }

    public Congreso chequearYAgregarAsistencia(String correo, String numero) throws DataErrorException, DataBaseException {
        ClaseDBInformacion databaseInformacion = new ClaseDBInformacion();
        ClaseDBCongreso databaseCongreso = new ClaseDBCongreso();
        ParticipacionCongreso participacion = new ParticipacionCongreso(correo, numero, "Asistente");

        Informacion informacion = databaseInformacion.solicitarInformacionPorCorreo(correo);
        if (informacion == null) {
            throw new DataErrorException("El usuario no ha agregado información de perfil, llenar antes de participar en un congreso.");
        }

        Congreso congreso = databaseCongreso.solicitarCongreso(participacion.getNumeroCongreso());
        if (congreso == null) {
            throw new DataErrorException("No existe un congreso con el número enviado.");
        }

        if (congreso.getPrecio() > informacion.getDinero()) {
            throw new DataErrorException("El usuario no posee el dinero suficiente para participar en el congreso.");
        }

        ParticipacionCongreso participacionTemp = database.solicitarParticipacion(correo, participacion.getNumeroCongreso(), "Asistente");
        if (participacionTemp != null) {
            throw new DataErrorException("El usuario esta participando en el congreso enviado.");
        }

        crearParticipacion(participacion);
        databaseInformacion.modificarDinero(informacion, informacion.getDinero() - congreso.getPrecio());

        return congreso;
    }

    private void crearParticipacion(ParticipacionCongreso participacion) throws DataBaseException {
        database.guardarParticipacion(participacion);
        participacion = database.solicitarParticipacion(participacion.getCorreoUsuario(), participacion.getNumeroCongreso(), participacion.getTipoTrabajo().toString());
        database.cambiarEstadoRevision(participacion);
    }
}
