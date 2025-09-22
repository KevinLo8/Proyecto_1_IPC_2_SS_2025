/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.participacion_congreso;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.participacionCongreso.ProcesadorParticipacionCongreso;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "PresentarTrabajoServlet", urlPatterns = {"/participacion-congreso/presentar-trabajo-servlet"})
public class PresentarTrabajoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorParticipacionCongreso procesadorParticipacionCongreso = new ProcesadorParticipacionCongreso();
            procesadorParticipacionCongreso.chequearYCrearTrabajo((String) request.getSession().getAttribute("correo"), request.getParameter("numero"),
                    request.getParameter("trabajo"));
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("/trabajo/presentar-trabajo-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/trabajo/presentar-trabajo.jsp");
        }
        disparcher.forward(request, response);

    }

}
