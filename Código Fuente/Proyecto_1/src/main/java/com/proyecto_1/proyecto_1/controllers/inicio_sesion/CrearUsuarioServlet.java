package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "CrearUsuarioServlet", urlPatterns = {"/inicio-sesion/crear-usuario-servlet"})
public class CrearUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorUsuario chequeadorUsuario = new ProcesadorUsuario();
            Usuario usuario = new Usuario(request.getParameter("correo"), request.getParameter("contraseña"));
            chequeadorUsuario.chequearYCrearUsuario(usuario);
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = request.getRequestDispatcher("/inicio-sesion/crear-usuario-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/inicio-sesion/crear-usuario.jsp");
        }
        disparcher.forward(request, response);

    }
}
