<%-- 
    Document   : index
    Created on : 5/09/2025, 9:46:39 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestor De Congresos</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/includes/header.jsp"/>
        <div class="px-4 py-5 my-5 text-center border-bottom">
            <h1 class="display-4 fw-bold text-body-emphasis">Bienvenido al gestor de congresos</h1>
            <div class="col-lg-6 mx-auto">
                <p class="lead mb-4">Aplicación Creada para poder participar en congresos y actividades, la cual posee administración por medio de usuarios y administración de congresos, salones y actividades.</p>
            </div>
            <div class="overflow-hidden" style="max-height: 40vh;">
                <div class="container px-5">
                    <img src="resources/image/usac_logo.png" class="img-fluid border rounded-3 shadow-lg mb-4" alt="Logo USAC" width="300" height="300" loading="lazy">
                </div>
            </div>
        </div>
        <jsp:include page="/includes/footer.jsp"/>
    </body>
</html>
