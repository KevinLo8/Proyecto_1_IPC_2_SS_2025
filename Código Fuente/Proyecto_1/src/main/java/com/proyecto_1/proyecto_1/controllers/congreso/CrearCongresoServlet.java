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
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CrearCongresoServlet", urlPatterns = {"/congreso/crear-congreso-servlet"})
public class CrearCongresoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";

        try {
            ProcesadorCongreso procesadorCongreso = new ProcesadorCongreso();
            procesadorCongreso.chequearYCrearCongreso((String) request.getSession().getAttribute("correo"), request.getParameter("descripcion"), request.getParameter("fecha"), request.getParameter("ubicacion"),
                    request.getParameter("nombre"), request.getParameter("precio"), request.getParameterValues("correoUsuario"));
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("congreso-creado-completo.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("formulario-congreso-servlet");
        }
        disparcher.forward(request, response);

    }
    
}
