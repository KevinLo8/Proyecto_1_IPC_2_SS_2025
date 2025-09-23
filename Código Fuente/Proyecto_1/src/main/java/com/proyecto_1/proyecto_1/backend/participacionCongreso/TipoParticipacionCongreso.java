/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto_1.proyecto_1.backend.participacionCongreso;

/**
 *
 * @author Kevin
 */
public enum TipoParticipacionCongreso {
    ASISTENTE(""),
    PONENTE("PONENCIA"),
    TALLERISTA("TALLER"),
    INVITADO("PONENCIA");

    public final String actividad;

    private TipoParticipacionCongreso(String actividad) {
        this.actividad = actividad;
    }

    public String getActividad() {
        return actividad;
    }

}
