package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "CerrarSesionServlet", urlPatterns = {"/inicio-sesion/cerrar-sesion-servlet"})
public class CerrarSesionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("correo");
        request.getSession().removeAttribute("usuario");
        request.getRequestDispatcher("/inicio-sesion/cerrar-sesion-completado.jsp").forward(request, response);
    }

}
