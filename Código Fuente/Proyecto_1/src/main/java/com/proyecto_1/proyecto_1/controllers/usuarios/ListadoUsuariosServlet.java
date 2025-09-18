/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.usuarios;

import com.proyecto_1.proyecto_1.backend.db.*;
import com.proyecto_1.proyecto_1.backend.exceptions.DatabaseException;
import com.proyecto_1.proyecto_1.backend.usuario.Usuario;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ListadoUsuariosServlet", urlPatterns = {"/usuario/listado-de-usuarios-servlet"})
public class ListadoUsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesarServlet(request, response);
    }

    private void procesarServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ClaseDBUsuario databaseUsuario = new ClaseDBUsuario();

        try {
            ArrayList<Usuario> usuarios = databaseUsuario.solicitarUsuariosEInformacion();
            request.setAttribute("usuarios", usuarios);
        } catch (DatabaseException e) {
            request.setAttribute("errordb", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/usuario/listado-de-usuarios.jsp");
        disparcher.forward(request, response);

    }

}
