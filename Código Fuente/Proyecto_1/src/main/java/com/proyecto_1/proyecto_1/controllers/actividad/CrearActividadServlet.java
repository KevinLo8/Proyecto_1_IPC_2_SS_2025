/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.actividad;

import com.proyecto_1.proyecto_1.backend.actividad.ProcesadorActividad;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CrearActividadServlet", urlPatterns = {"/actividad/crear-actividad-servlet"})
public class CrearActividadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";
        boolean tieneTrabajos = false;

        try {
            ProcesadorActividad procesadorActividad = new ProcesadorActividad();
            procesadorActividad.chequearYCrearActividad((String) request.getSession().getAttribute("correo"), request.getParameter("numeroCongreso"),
                    request.getParameter("nombre"), request.getParameter("descripcion"), request.getParameter("horaInicio"), request.getParameter("horaFin"),
                    request.getParameter("codigoSalon"), request.getParameter("tipoActividad"), request.getParameter("cupo"));
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("crear-actividad-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("formulario-actividad-servlet");
        }
        disparcher.forward(request, response);

    }

}
