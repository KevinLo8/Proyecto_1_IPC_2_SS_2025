<%-- 
    Document   : agregar-salon
    Created on : 22/09/2025, 8:14:22 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar nuevo salon</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Agregar nuevo salon</h2>
                <p class="lead">Llenar los datos que se piden abajo para poder agregar un nuevo salon.</p>
            </div>

            <div class="pb-5 offset-2 col-8">
                <div class="col-lg-12">
                    <h4 class="mb-3">Datos para agregar un nuevo salon</h4>
                    <form class="needs-validation" method="POST" action="agregar-salon-servlet">

                        <div class="mb-4">
                            <input type="hidden" name="numero" value="${param.numero}">
                        </div>

                        <div class="mb-4">
                            <label for="username" class="form-label">Nombre del salon</label>
                            <div class="input-group has-validation">
                                <input type="text" class="form-control" value="${param.nombre}" placeholder="Nombre del salon" name="nombre" maxlength="100" required>
                                <div class="invalid-feedback">
                                    Se requiere un nombre de salon.
                                </div>
                            </div>
                        </div>

                        <c:if test="${error != null}">
                            <div class="my-3 alert alert-warning" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <div class="d-flex justify-content-center">
                            <a class="btn btn-primary mx-2" type="button" href="listado-salones-servlet?numero=${param.numero}">
                                Regresar
                            </a>
                            <button class="btn btn-primary mx-2" method="POST">Agregar Salon</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
