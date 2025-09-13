<%-- 
    Document   : dinero
    Created on : 12/09/2025, 9:42:43 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dinero</title>
        <jsp:include page="../includes/resources.jsp"/>
        <script src="${pageContext.servletContext.contextPath}/resources/js/dinero.js"></script>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="section">

            <div class="container offset-4 col-4 text-center">
                <form method="POST" action="${pageContext.servletContext.contextPath}/dinero/agregar-dinero-servlet">
                    <h1 class="h2">Agregar Dinero.</h1>
                    <div class="form-group text-start mt-3">
                        <label for="nombreRevista">correo del usuario</label>
                        <input type="text" class="form-control" value="${correo}" disabled/>
                    </div>
                    <div class="form-group text-start mt-3">
                        <label for="precio">Dinero del usuario</label>
                        <div class="input-group">
                            <span class="input-group-text">Q </span>
                            <input type="number" min="0.01" class="form-control" value="${informacion.dinero}" disabled/>
                        </div>
                    </div>

                    <div class="form-group text-start mt-3">
                        <label for="precio">Cuanto dinero quiere agregar</label>
                        <div class="input-group">
                            <span class="input-group-text">Q </span>
                            <input id="dinero" name="dinero" type="number" onblur="ajustarDinero()" min="0.01" step="0.01" class="form-control" value="0.00" required/>
                        </div>
                    </div>

                    <c:if test="${error != null}">
                        <div class="row g-5 justify-content-center">
                            <div class="alert alert-warning col-10" role="alert">${error}</div>
                        </div>
                    </c:if>

                    <button class="btn btn-success mt-3">Agregar dinero</button>
                </form>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>