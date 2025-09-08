package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ControladorCerrarSesion", urlPatterns = {"/inicio-sesion/cerrar-sesion-servlet"})
public class ControladorCerrarSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("correo");
        req.getRequestDispatcher("/inicio-sesion/cerrar-sesion-completado.jsp").forward(req, resp);
    }

}
