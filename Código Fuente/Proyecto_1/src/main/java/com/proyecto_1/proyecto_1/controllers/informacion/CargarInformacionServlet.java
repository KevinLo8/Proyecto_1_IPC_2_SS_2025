/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.informacion;

import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import com.proyecto_1.proyecto_1.backend.foto.Foto;
import com.proyecto_1.proyecto_1.backend.informacion.Informacion;
import com.proyecto_1.proyecto_1.backend.institucion.Institucion;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CargarInformacionServlet", urlPatterns = {"/informacion/cargar-informacion-servlet"})
public class CargarInformacionServlet extends HttpServlet {

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
        ClaseDBFoto databaseFoto = new ClaseDBFoto();
        ClaseDBInstitucion databaseInstitucion = new ClaseDBInstitucion();
        ArrayList<Institucion> instituciones = null;

        try {

            Informacion informacion = databaseInformacion.solicitarInformacionPorCorreo((String) request.getSession().getAttribute("correo"));
            request.setAttribute("informacion", informacion);

            if (informacion != null) {
                Foto foto = databaseFoto.solicitarFoto(informacion.getNumeroIdentificacion());
                request.setAttribute("informacion", informacion);
            }

            instituciones = databaseInstitucion.solicitarInstituciones(0);
            request.setAttribute("instituciones", instituciones);

        } catch (DataBaseException e) {
            request.setAttribute("errordb", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/informacion/informacion.jsp");
        disparcher.forward(request, response);
    }

}
