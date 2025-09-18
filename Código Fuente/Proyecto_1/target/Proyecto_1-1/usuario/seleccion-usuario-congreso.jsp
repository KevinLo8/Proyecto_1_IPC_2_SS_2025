<%-- 
    Document   : seleccion-usuario-congreso
    Created on : 13/09/2025, 10:55:44 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado De Usuarios</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <h1 class="text-body-emphasis text-center">Listado de Usuarios.</h1>

            <c:if test="${mensaje != null}">
                <div class="alert alert-success" role="alert">${mensaje}</div>
            </c:if>

            <c:if test="${errordb == null}">

                <form class="needs-validation" method="POST" action="crear-administrador-congreso-servlet">

                    <div class="bd-example my-5 border-0">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Correo de usuario</th>
                                    <th scope="col">Nombre de usuario</th>
                                    <th scope="col">Es administrasdor de sistema</th>
                                    <th scope="col">Es administrasdor de congreso</th>
                                    <th scope="col">Estado activacion</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${usuarios.size() > 0}">
                                    <c:forEach items="${usuarios}" var="usuario">
                                        <tr>
                                            <th scope="row"><input id="usuario" name="correoUsuario" type="radio" class="form-check-input" value="${usuario.correoElectronico}" required></th>
                                            <td>${usuario.correoElectronico}</td>
                                            <td>${usuario.informacion.nombreUSuario}</td>
                                            <c:if test="${usuario.adminSistema == true}">
                                                <td>SI</td>
                                            </c:if>
                                            <c:if test="${usuario.adminSistema == false}">
                                                <td>NO</td>
                                            </c:if>
                                            <c:if test="${usuario.adminCongreso == true}">
                                                <td>SI</td>
                                            </c:if>
                                            <c:if test="${usuario.adminCongreso == false}">
                                                <td>NO</td>
                                            </c:if>
                                            <c:if test="${usuario.activacion == true}">
                                                <td>Activado</td>
                                            </c:if>
                                            <c:if test="${usuario.activacion == false}">
                                                <td>Desactivado</td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>

                            <c:if test="${error != null}">
                                <div class="my-3 alert alert-warning" role="alert">
                                    ${error}
                                </div>
                            </c:if>

                            <div class="d-grid gap-2 d-md-flex justify-content-center">
                                <a class="btn btn-primary btn-lg" type="button" href="listado-de-usuarios-servlet">Regresar</a>
                                <button class="btn btn-primary btn-lg" method="POST">Crear Administrador</button>
                            </div>
                        </div>
                    </form>

                </c:if>
                <c:if test="${usuarios.size() == 0}">
                    <h5 class="py-3 text-body-emphasis text-center">No se ha encontrado ningun usuario con el que se pueda crear un adminitrador de congresos.</h5>
                    <div class="d-grid gap-2 d-md-flex justify-content-center">
                        <a class="btn btn-primary btn-lg" type="button" href="listado-de-usuarios-servlet">Regresar</a>
                    </div>
                </c:if>
            </c:if>

            <c:if test="${errordb != null}">
                <div class="alert alert-warning" role="alert">${errordb}</div>
            </c:if>

        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
