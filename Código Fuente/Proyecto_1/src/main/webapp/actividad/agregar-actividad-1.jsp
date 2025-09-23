<%-- 
    Document   : agregar-actividad-1
    Created on : 23/09/2025, 10:35:35 a. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Actividad</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h1>Crear Actividad</h1>
            </div>


            <div class="pb-5 offset-2 col-8">
                <c:if test="${error == null}">
                    <p>Llene los siguientes datos y continue al siguiente paso</p>
                    <form method="POST" action="formulario-actividad-servlet">

                        <div class="mb-3">
                            <label class="form-label">Nombre de la actividad</label>
                            <input type="text" class="form-control" name="nombre" minlength="1" maxlength="100" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Descripcion de la actividad</label>
                            <textarea  type="text" rows="4"  class="form-control" name="descripcion" minlength="1" maxlength="250" required></textarea>
                        </div>

                        <div class="row">
                            <div class="mb-3 col-6">
                                <label class="form-label">Hora de inicio de la actividad</label>
                                <input type="time" class="form-control" name="horaInicio" required>
                            </div>

                            <div class="mb-3 col-6">
                                <label class="form-label">Hora de finalización de la actividad</label>
                                <input type="time" class="form-control" name="horaFin" required>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Seleccione el congreso donde quiere crear la actividad</label>
                            <select class="form-select" name="numeroCongreso" required>
                                <option value="">Escoger...</option>
                                <c:forEach items="${congresos}" var="congreso">
                                    <c:if test="${congreso.numero == param.congreso}">
                                        <option value="${congreso.numero}" selected>${congreso.nombre}</option>
                                    </c:if>
                                    <c:if test="${congreso.numero != param.congreso}">
                                        <option value="${congreso.numero}">${congreso.nombre}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Seleccione un congreso.
                            </div>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-center">
                            <a class="btn btn-primary" type="button" href="../index.jsp">Inicio</a>
                            <button method="POST" class="btn btn-primary">Siguiente</button>
                        </div>

                    </form>   
                </c:if>
                <c:if test="${error != null}">
                    <div class="alert alert-warning" role="alert">${error}</div>
                </c:if>

            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
