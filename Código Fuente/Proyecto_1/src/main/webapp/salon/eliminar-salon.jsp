<%-- 
    Document   : eliminar-salon
    Created on : 22/09/2025, 10:43:49 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar salon</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Eliminar salon</h2>
            </div>

            <div class="pb-5 offset-2 col-8">
                <div class="col-lg-12">
                    <h4 class="mb-3">Información sobre del salon a eliminar</h4>
                    <form class="needs-validation" method="POST" action="eliminar-salon-servlet">

                        <div class="my-3">
                            <input type="hidden" name="numero" value="${param.numero}">
                        </div>

                        <div class="my-3">
                            <label for="username" class="form-label">Codigo del salon a modificar</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${param.codigo}" disabled>
                                <input type="hidden" value="${param.codigo}" name="codigo">
                            </div>
                        </div>

                        <div class="my-3">
                            <label for="username" class="form-label">Nombre del salon a modificar</label>
                            <div class="input-group">
                                <input type="text" class="form-control" value="${param.nombre}" disabled>
                                <input type="hidden" value="${param.nombre}" name="nombre">
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
                            <button class="btn btn-primary mx-2" method="POST">Eliminar</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
