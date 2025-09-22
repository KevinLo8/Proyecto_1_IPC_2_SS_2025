/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.proyecto_1.proyecto_1.controllers.congreso;

import com.proyecto_1.proyecto_1.backend.congreso.Congreso;
import com.proyecto_1.proyecto_1.backend.db.ClaseDBCongreso;
import com.proyecto_1.proyecto_1.backend.exceptions.DataBaseException;
import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.util.ArrayList;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "ListadoCongresosServlet", urlPatterns = {"/congreso/listado-congresos-servlet"})
public class ListadoCongresosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        
        ClaseDBCongreso database = new ClaseDBCongreso();

        try {
            ArrayList<Congreso> congresos = database.solicitarCongresos();
            request.setAttribute("congresos", congresos);
        } catch (DataBaseException e) {
            request.setAttribute("errordb", e.getMessage());
        }

        RequestDispatcher disparcher = request.getRequestDispatcher("/congreso/listado-de-congresos.jsp");
        disparcher.forward(request, response);

    }

}
