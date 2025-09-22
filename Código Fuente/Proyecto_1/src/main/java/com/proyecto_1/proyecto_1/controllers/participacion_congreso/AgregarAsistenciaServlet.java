/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.participacion_congreso;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
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
@WebServlet(name = "AgregarAsistenciaServlet", urlPatterns = {"/participacion-congreso/agregar-asistencia-servlet"})
public class AgregarAsistenciaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorParticipacionCongreso procesadorInstitucion = new ProcesadorParticipacionCongreso();
            Congreso congreso = procesadorInstitucion.chequearYAgregarAsistencia((String) request.getSession().getAttribute("correo"), request.getParameter("numero"));
            request.setAttribute("congreso", congreso);
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("/participacion-congreso/asistencia-agregada-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("../congreso/listado-congresos-servlet");
        }
        disparcher.forward(request, response);

    }

}
