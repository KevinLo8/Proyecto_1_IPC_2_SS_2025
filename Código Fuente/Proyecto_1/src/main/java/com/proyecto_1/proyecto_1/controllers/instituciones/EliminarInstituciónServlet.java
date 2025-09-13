/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.instituciones;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.institucion.ProcesadorInstitucion;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "EliminarInstituciónServlet", urlPatterns = {"/institucion/eliminar-institucion-servlet"})
public class EliminarInstituciónServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorInstitucion procesadorInstitucion = new ProcesadorInstitucion();
            procesadorInstitucion.chequearYEliminiarInstitucion(request.getParameter("nombre"));
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha eliminado la institucion de nombre " + request.getParameter("nombre") + " exitosamente.");
            disparcher = request.getRequestDispatcher("/institucion/listado-de-instituciones-servlet");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/institucion/eliminar-institucion.jsp");
        }
        disparcher.forward(request, response);

    }

}
