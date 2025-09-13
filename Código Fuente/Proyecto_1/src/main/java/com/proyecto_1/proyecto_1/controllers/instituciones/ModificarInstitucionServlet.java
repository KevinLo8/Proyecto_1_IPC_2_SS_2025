/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.instituciones;

import com.proyecto_1.proyecto_1.backend.exceptions.DataErrorException;
import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.institucion.ProcesadorInstitucion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ModificarInstitucionServlet", urlPatterns = {"/institucion/modificar-institucion-servlet"})
public class ModificarInstitucionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorInstitucion procesadorInstitucion = new ProcesadorInstitucion();
            procesadorInstitucion.chequearYModificarInstitucion(request.getParameter("nombre"), request.getParameter("nombreNuevo"));
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha modificado la institucion de nombre " + request.getParameter("nombre")
                    + " al nuevo nombre " + request.getParameter("nombreNuevo"));
            disparcher = request.getRequestDispatcher("/institucion/listado-de-instituciones-servlet");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/institucion/modificar-institucion.jsp");
        }
        disparcher.forward(request, response);

    }

}
