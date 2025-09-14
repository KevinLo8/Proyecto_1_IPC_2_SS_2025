/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

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
@WebServlet(name = "CambiarContraseñaServlet", urlPatterns = {"/inicio-sesion/cambiar-contrasena-servlet"})
public class CambiarContraseñaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorUsuario chequeadorUsuario = new ProcesadorUsuario();
            chequeadorUsuario.chequearCambioContraseña((String) request.getSession().getAttribute("correo"), request.getParameter("contraseñaVieja"),
                    request.getParameter("contraseñaNueva"));
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("/inicio-sesion/cambiar-contraseña-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/inicio-sesion/cambiar-contraseña.jsp");
        }
        disparcher.forward(request, response);
    }

}
