/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.instituciones;

import com.proyecto_1.proyecto_1.backend.db.ClaseDBInstitucion;
import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.institucion.Institucion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ListadoDeInstitucionesServlet", urlPatterns = {"/institucion/listado-de-instituciones-servlet"})
public class ListadoDeInstitucionesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    private void procesarServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClaseDBInstitucion database = new ClaseDBInstitucion();
        ArrayList<Institucion> instituciones = null;

        try {
            instituciones = database.solicitarInstituciones(0);
            request.setAttribute("instituciones", instituciones);
        } catch (DatabaseException e) {
            request.setAttribute("errordb", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/institucion/listado-de-instituciones.jsp");
        disparcher.forward(request, response);

    }

}
