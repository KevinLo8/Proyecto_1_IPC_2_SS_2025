<%-- 
    Document   : modificar-institucion.jsp
    Created on : 8/09/2025, 6:47:25 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar nombre de institución</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Modificar nombre de institución</h2>
                <p class="lead">Llenar los datos que se piden abajo para poder modificar nombre de la institución.</p>
            </div>

            <div class="pb-5 offset-2 col-8">
                <div class="col-lg-12">
                    <h4 class="mb-3">Datos para modificar nombre de la institución</h4>
                    <form class="needs-validation" method="POST" action="${pageContext.servletContext.contextPath}/institucion/modificar-institucion-servlet">

                        <div class="row g-3 mb-4">

                            <div class="col-12">
                                <label for="username" class="form-label">Nombre de institucion a modificar</label>
                                <div class="input-group">
                                    <input type="text" class="form-control" value="${param.nombre}" disabled>
                                    <input type="hidden" value="${param.nombre}" name="nombre">
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="username" class="form-label">Nuevo nombre de institucion</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" value="${param.nombreNuevo}" placeholder="Nuevo nombre de institucion" name="nombreNuevo" maxlength="100" required>
                                    <div class="invalid-feedback">
                                        Se requiere un nuevo nombre de institución.
                                    </div>
                                </div>
                            </div>

                        </div>

                        <c:if test="${error != null}">
                            <div class="my-3 alert alert-warning" role="alert">
                                ${error}
                            </div>
                        </c:if>

                                                            <div class="d-grid gap-2 d-md-flex justify-content-center">
                            <a class="btn btn-primary btn-lg" type="button" href="${pageContext.servletContext.contextPath}/institucion/listado-de-instituciones-servlet">
                                Regresar
                            </a>
                        <button class="btn btn-primary btn-lg" method="POST">Modificar</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
