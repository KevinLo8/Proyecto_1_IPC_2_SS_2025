package com.proyecto_1.proyecto_1.controllers.inicio_sesion;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.participacionCongreso.ProcesadorParticipacionCongreso;
import com.proyecto_1.proyecto_1.backend.usuario.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/inicio-sesion/inicio-sesion-servlet"})
public class InicioSesionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error = "";
        Usuario usuario = null;
        boolean tieneTrabajos = false;

        try {
            ProcesadorUsuario chequeadorUsuario = new ProcesadorUsuario();
            usuario = new Usuario(request.getParameter("correo"), request.getParameter("contraseña"));
            usuario = chequeadorUsuario.chequearInicioSesion(usuario);
            ProcesadorParticipacionCongreso procesadorParticipacionCongreso = new ProcesadorParticipacionCongreso();
            tieneTrabajos = procesadorParticipacionCongreso.revisarTieneTrabajos(usuario.getCorreoElectronico());
        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = null;
        if (StringUtils.isBlank(error)) {
            request.getSession().setAttribute("correo", request.getParameter("correo"));
            request.getSession().setAttribute("adminSistema", usuario.isAdminSistema());
            request.getSession().setAttribute("adminCongreso", usuario.isAdminCongreso());
            request.getSession().setAttribute("tieneTrabajos", tieneTrabajos);
            disparcher = request.getRequestDispatcher("/inicio-sesion/inicio-sesion-completado.jsp");
        } else {
            request.setAttribute("error", error);
            disparcher = request.getRequestDispatcher("/inicio-sesion/inicio-sesion.jsp");
        }
        disparcher.forward(request, response);
    }

}
