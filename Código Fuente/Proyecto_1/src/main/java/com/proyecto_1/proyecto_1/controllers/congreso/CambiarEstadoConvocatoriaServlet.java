/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.congreso;

import com.proyecto_1.proyecto_1.backend.congreso.ProcesadorCongreso;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CambiarEstadoConvocatoriaServlet", urlPatterns = {"/congreso/cambiar-estado-convocatoria-servlet"})
public class CambiarEstadoConvocatoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();

        try {
            procesadorCongreso.cambiarEstadoConvocarotia((String) request.getSession().getAttribute("correo"), request.getParameter("numero"));
            request.setAttribute("mensaje", "Se a cambiado el estado de la convocatoria exitosamente");
        } catch (DataBaseException | DataErrorException e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/congreso/congresos-creados-servlet");
        disparcher.forward(request, response);

    }

}
