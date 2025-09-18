/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.usuarios;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "CrearAdministradorCongresoServlet", urlPatterns = {"/usuario/crear-administrador-congreso-servlet"})
public class CrearAdministradorCongresoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";

        try {
            ProcesadorUsuario procesadorUsuario = new ProcesadorUsuario();
            procesadorUsuario.chequearYCrearAdminitradorCongreso(request.getParameter("correoUsuario"));
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha creado un adminitrador de congreso con el usuario de correo " + request.getParameter("correoUsuario"));
            disparcher = request.getRequestDispatcher("listado-de-usuarios-servlet");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("listado-usuarios-congresos-servlet");
        }
        disparcher.forward(request, response);
    }

}
