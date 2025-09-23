/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.salon;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.salon.ProcesadorSalon;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "EliminarSalonServlet", urlPatterns = {"/salon/eliminar-salon-servlet"})
public class EliminarSalonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorSalon procesadorSalon = new ProcesadorSalon();
            procesadorSalon.chequearYEliminarSalon((String) request.getSession().getAttribute("correo"), request.getParameter("numero"),
                    request.getParameter("codigo"));
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha eliminado el salon de nombre " + request.getParameter("nombre") + " exitosamente.");
            disparcher = request.getRequestDispatcher("listado-salones-servlet");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("eliminar-salon.jsp");
        }
        disparcher.forward(request, response);
    }

}
