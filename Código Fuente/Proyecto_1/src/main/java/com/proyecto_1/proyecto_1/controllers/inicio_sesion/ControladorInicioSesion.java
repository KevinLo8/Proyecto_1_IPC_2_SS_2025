package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "ControladorInicioSesion", urlPatterns = {"/inicio-sesion/inicio-sesion-servlet"})
public class ControladorInicioSesion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String error = "";
        Usuario usuario = null;

        try {
            ProcesadorUsuario chequeadorUsuario = new ProcesadorUsuario();
            usuario = new Usuario(req.getParameter("correo"), req.getParameter("contraseña"));
            usuario = chequeadorUsuario.chequearInicioSesion(usuario);
        } catch (DataErrorException | DatabaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            req.getSession().setAttribute("correo", req.getParameter("correo"));
            req.getSession().setAttribute("usuario", usuario);
            disparcher = req.getRequestDispatcher("/inicio-sesion/inicio-sesion-completado.jsp");
        } else {
            req.setAttribute("error", error);
            disparcher = req.getRequestDispatcher("/inicio-sesion/inicio-sesion.jsp");
        }
        disparcher.forward(req, resp);
    }

}
