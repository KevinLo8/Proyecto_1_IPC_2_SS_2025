<%-- 
    Document   : listado-salones
    Created on : 21/09/2025, 9:37:52 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado De Salones</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <h1 class="text-body-emphasis text-center">Listado de Salones.</h1>

            <c:if test="${mensaje != null}">
                <div class="alert alert-success" role="alert">${mensaje}</div>
            </c:if>
            <c:if test="${error != null}">
                <div class="alert alert-warning" role="alert">${error}</div>
            </c:if>

            <div class="bd-example m-0 border-0">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Codigo de salon</th>
                            <th scope="col">Nombre de salon</th>
                            <th scope="col">Cantidad de actividades</th>
                            <th scope="col"> </th>
                            <th scope="col"> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${salones.size() > 0}">
                            <%int i = 1;%>
                            <c:forEach items="${salones}" var="salon">
                                <tr>
                                    <th scope="row"><%=i%></th>
                                    <th scope="row">${salon.codigo}</th>
                                    <td>${salon.nombre}</td>
                                    <td>${salon.cantidadActividades}</td>
                                    <td><a href="modificar-salon.jsp?numero=${param.numero}&codigo=${salon.codigo}&nombre=${salon.nombre}">modificar</a></td>
                                    <c:if test="${salon.cantidadActividades == 0}">
                                        <td><a href="eliminar-salon.jsp?numero=${param.numero}&codigo=${salon.codigo}&nombre=${salon.nombre}">eliminar</a></td>
                                    </c:if>
                                    <c:if test="${salon.cantidadActividades > 0}">
                                        <td><a href="">eliminar</a></td>
                                    </c:if>
                                </tr>
                                <%i++;%>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <c:if test="${salones.size() == 0}">
                <h5 class="py-3 text-body-emphasis text-center">El congreso no tiene ningun salon creado.</h5>
            </c:if>

            <div class="d-grid gap-2 col-6 mx-auto pt-5">
                <a type="button" class="btn btn-primary" href="agregar-salon.jsp?numero=${param.numero}">Agregar Salon</a>
            </div>

        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
