package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControladorCrearUsuario", urlPatterns = {"/inicio-sesion/crear-usuario-servlet"})
public class ControladorCrearUsuario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error = "";

        try {
            ProcesadorUsuario chequeadorUsuario = new ProcesadorUsuario();
            Usuario usuario = new Usuario(req.getParameter("correo"), req.getParameter("contraseña"));
            chequeadorUsuario.chequearYCrearUsuario(usuario);
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            disparcher = req.getRequestDispatcher("/inicio-sesion/crear-usuario-completado.jsp");
        } else {
            req.setAttribute("error", error);
            disparcher = req.getRequestDispatcher("/inicio-sesion/crear-usuario.jsp");
        }
        disparcher.forward(req, resp);

    }
}
