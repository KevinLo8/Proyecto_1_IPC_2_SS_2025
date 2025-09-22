/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.dinero;

import com.proyecto_1.proyecto_1.backend.dinero.ProcesadorDinero;
import com.proyecto_1.proyecto_1.backend.exceptions.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "AgregarDineroServlet", urlPatterns = {"/dinero/agregar-dinero-servlet"})
public class AgregarDineroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorDinero procesadorDinero = new ProcesadorDinero();
            procesadorDinero.AgregarDinero(request.getParameter("dinero"), (String) request.getSession().getAttribute("correo"));

        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher;
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha agregado Q" + request.getParameter("dinero") + " de dinero exitosamente.");
            disparcher = request.getRequestDispatcher("/informacion/cargar-informacion-servlet");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/dinero/cargar-dinero-servlet");
        }
        disparcher.forward(request, response);

    }

}
