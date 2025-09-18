<%-- 
    Document   : crear-congreso
    Created on : 15/09/2025, 6:40:32 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Congreso</title>
        <jsp:include page="../includes/resources.jsp"/>
        <script src="${pageContext.servletContext.contextPath}/resources/js/dinero.js"></script>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h1>Crear congreso</h1>
            </div>


            <div class="pb-5 offset-2 col-8">
                <c:if test="${errordb == null}">
                    <p>Llene los siguientes datos para crear un congreso</p>
                    <form method="POST" action="${pageContext.servletContext.contextPath}/congreso/crear-congreso-servlet">

                        <div class="mb-3">
                            <label class="form-label">Fecha del congreso</label>
                            <input type="date" class="form-control" name="fecha" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Ubicación de congreso</label>
                            <input type="text" class="form-control" name="ubicacion" minlength="1" maxlength="100" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Precio del congreso</label>
                            <div class="input-group">
                                <span class="input-group-text">Q </span>
                                <input id="dinero" name="precio" type="number" onblur="ajustarDinero()" min="0.01" step="0.01" class="form-control" value="0.00" required/>
                            </div>
                        </div>

                        <div class="bd-example mb-5 border-0">
                            <label class="form-label">Seleccione los estudiantes para el comité cientifico (Max. 3 estudiantes)</label>
                            <table class="table table-striped border">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Nombre de usuario</th>
                                        <th scope="col">Correo de usuario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${usuarios.size() > 0}">
                                        <c:forEach items="${usuarios}" var="usuario">
                                            <c:if test="${usuario.correoElectronico != sessionScope.correo}">
                                                <tr>
                                                    <th scope="row"><input id="usuario" name="correoUsuario" type="checkbox" class="form-check-input" value="${usuario.correoElectronico}"></th>
                                                    <td>${usuario.informacion.nombreUSuario}</td>
                                                    <td>${usuario.correoElectronico}</td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>

                            <c:if test="${error != null}">
                                <div class="my-3 alert alert-warning" role="alert">
                                    ${error}
                                </div>
                            </c:if>

                            <div class="d-grid gap-2 d-md-flex justify-content-center">
                                <a class="btn btn-primary" type="button" href="../index.jsp">Inicio</a>
                                <button method="POST" class="btn btn-primary">Crear Congreso</button>
                            </div>

                        </div>
                    </form>   
                </c:if>
                <c:if test="${errordb != null}">
                    <div class="alert alert-warning" role="alert">${errordb}</div>
                </c:if>

            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
