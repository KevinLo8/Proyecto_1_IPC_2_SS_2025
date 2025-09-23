/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.salon;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.salon.*;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ListadoSalonesServlet", urlPatterns = {"/salon/listado-salones-servlet"})
public class ListadoSalonesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    private void procesarServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ProcesadorSalon procesadorSalon = new ProcesadorSalon();
            ArrayList<Salon> salones = procesadorSalon.crearListadoSalones((String) request.getSession().getAttribute("correo"), request.getParameter("numero"));
            request.setAttribute("salones", salones);
        } catch (DataBaseException | DataErrorException e) {
            request.setAttribute("error", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/salon/listado-salones.jsp");
        disparcher.forward(request, response);

    }

}
