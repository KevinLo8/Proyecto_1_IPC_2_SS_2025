<%-- 
    Document   : agregar-institucion
    Created on : 7/09/2025, 10:44:29 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar nueva institución</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Agregar nueva institución</h2>
                <p class="lead">Llenar los datos que se piden abajo para poder agregar una nueva institución.</p>
            </div>

            <div class="pb-5 offset-2 col-8">
                <div class="col-lg-12">
                    <h4 class="mb-3">Datos para agregar una nueva instutición</h4>
                    <form class="needs-validation" method="POST" action="${pageContext.servletContext.contextPath}/institucion/agregar-institucion-servlet">

                        <div class="row g-3 mb-4">

                            <div class="col-12">
                                <label for="username" class="form-label">Nombre de institucion</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" value="${param.nombre}" placeholder="Nombre de institución" name="nombre" maxlength="100" required>
                                    <div class="invalid-feedback">
                                        Se requiere un nombre de institución.
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
                            <button class="btn btn-primary btn-lg" method="POST">Agregar</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
