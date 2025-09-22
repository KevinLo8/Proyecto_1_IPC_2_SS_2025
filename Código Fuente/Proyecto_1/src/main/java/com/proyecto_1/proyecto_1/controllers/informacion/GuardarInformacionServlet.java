/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.informacion;

import com.proyecto_1.proyecto_1.backend.exceptions.*;
import com.proyecto_1.proyecto_1.backend.foto.ProcesadorFoto;
import com.proyecto_1.proyecto_1.backend.informacion.ProcesadorInformacion;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Kevin
 */
@MultipartConfig
@WebServlet(name = "GuardarInformacionServlet", urlPatterns = {"/informacion/guardar-informacion-servlet"})
public class GuardarInformacionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part foto = request.getPart("foto");
        String error = "";

        try {
            ProcesadorInformacion procesadorInformacion = new ProcesadorInformacion();
            procesadorInformacion.chequearYGuardarInformacion(request.getParameter("identificacion"), request.getParameter("nombre"), request.getParameter("institucion"),
                    request.getParameter("telefono"), (String) request.getSession().getAttribute("correo"));

            if (!foto.getContentType().equals("application/octet-stream")) {
                ProcesadorFoto procesadorFoto = new ProcesadorFoto();
                procesadorFoto.chequearYGuardarFoto(request.getParameter("identificacion"), foto.getInputStream(), foto.getSubmittedFileName(), foto.getContentType(), foto.getSize());
            }
          

        } catch (DataErrorException | DataBaseException e) {
            error = e.getMessage();
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/informacion/cargar-informacion-servlet");
        if (StringUtils.isBlank(error)) {
            request.setAttribute("mensaje", "Se ha guardado la información exitosamente.");
        } else {
            request.setAttribute("error", error);
        }
        disparcher.forward(request, response);
    }

}
