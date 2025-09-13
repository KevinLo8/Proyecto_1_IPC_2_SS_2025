<%-- 
    Document   : listado-de-instituciones
    Created on : 7/09/2025, 7:37:55 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado De Intituciones</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <h1 class="text-body-emphasis text-center">Listado de Instituciones.</h1>

            <c:if test="${mensaje != null}">
                <div class="alert alert-success" role="alert">${mensaje}</div>
            </c:if>

            <c:if test="${errordb == null}">
                <div class="bd-example m-0 border-0">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Nombre de institucion</th>
                                <th scope="col"> </th>
                                <th scope="col"> </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${instituciones.size() > 0}">
                                <c:forEach items="${instituciones}" var="institucion">
                                    <tr>
                                        <th scope="row">${institucion.numero}</th>
                                        <td>${institucion.nombre}</td>
                                        <td><a href="modificar-institucion.jsp?&nombre=${institucion.nombre}">modificar</a></td>
                                        <td><a href="eliminar-institucion.jsp?&nombre=${institucion.nombre}">eliminar</a></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
                <c:if test="${instituciones.size() == 0}">
                    <h5 class="py-3 text-body-emphasis text-center">No se ha registrado ninguna institución</h5>
                </c:if>
            </c:if>
                    
            <c:if test="${errordb != null}">
                <div class="alert alert-warning" role="alert">${errordb}</div>
            </c:if>

            <div class="d-grid gap-2 col-6 mx-auto pt-5">
                <a type="button" class="btn btn-primary" href="agregar-institucion.jsp">Agregar Institución</a>
            </div>

        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
