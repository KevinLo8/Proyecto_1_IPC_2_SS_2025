/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.dinero;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBInformacion;
import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.informacion.Informacion;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CargarDineroServlet", urlPatterns = {"/dinero/cargar-dinero-servlet"})
public class CargarDineroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    private void procesarServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClaseDBInformacion databaseInformacion = new ClaseDBInformacion();

        try {

            Informacion informacion = databaseInformacion.solicitarInformacionPorCorreo((String) request.getSession().getAttribute("correo"));
            request.setAttribute("informacion", informacion);

        } catch (DataBaseException e) {
            request.setAttribute("errordb", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/dinero/dinero.jsp");
        disparcher.forward(request, response);
    }

}
