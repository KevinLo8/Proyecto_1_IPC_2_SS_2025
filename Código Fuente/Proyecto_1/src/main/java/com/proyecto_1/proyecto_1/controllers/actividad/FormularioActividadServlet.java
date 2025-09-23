/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.actividad;

import com.proyecto_1.proyecto_1.backend.congreso.*;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.participacionCongreso.*;
import com.proyecto_1.proyecto_1.backend.salon.ProcesadorSalon;
import com.proyecto_1.proyecto_1.backend.salon.Salon;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "FormularioActividadServlet", urlPatterns = {"/actividad/formulario-actividad-servlet"})
public class FormularioActividadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";

        try {
            ProcesadorParticipacionCongreso procesadorParticipacionCongreso = new ProcesadorParticipacionCongreso();
            ArrayList<ParticipacionCongreso> trabajos = procesadorParticipacionCongreso.solicitarTrabajos((String) request.getSession().getAttribute("correo"));

            ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
            ArrayList<Congreso> congresos = procesadorCongreso.crearListaCongresos(trabajos);

            request.setAttribute("trabajos", trabajos);
            request.setAttribute("congresos", congresos);
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
            request.setAttribute("error", error);
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/actividad/agregar-actividad-1.jsp");
        disparcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";

        try {
            ProcesadorParticipacionCongreso procesadorParticipacionCongreso = new ProcesadorParticipacionCongreso();
            ArrayList<ParticipacionCongreso> trabajos = procesadorParticipacionCongreso.solicitarTrabajosPorCongreso((String) request.getSession().getAttribute("correo"),
                    request.getParameter("numeroCongreso"));
            
            ProcesadorSalon procesadorSalon = new ProcesadorSalon();
            ArrayList<Salon> salones = procesadorSalon.solicitarSalones(trabajos.get(0).getNumeroCongreso());

            request.setAttribute("participacionesCongreso", trabajos);
            request.setAttribute("salones", salones);
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
            request.setAttribute("errordb", error);
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/actividad/agregar-actividad-2.jsp");
        disparcher.forward(request, response);

    }

}
