<%-- 
    Document   : agregar-actividad-2
    Created on : 23/09/2025, 11:30:00 a. m.
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
                <c:if test="${errordb == null}">
                    <p>Llene los siguientes datos para crear una actividad</p>
                    <form method="POST" action="crear-actividad-servlet">

                        <div>
                            <input type="hidden" name="nombre" value="${param.nombre}">
                            <input type="hidden" name="descripcion" value="${param.descripcion}">
                            <input type="hidden" name="horaInicio" value="${param.horaInicio}">
                            <input type="hidden" name="horaFin" value="${param.horaFin}">
                            <input type="hidden" name="numeroCongreso" value="${param.numeroCongreso}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Seleccione el congreso donde quiere crear la actividad</label>
                            <select class="form-select" name="codigoSalon" required>
                                <option value="">Escoger...</option>
                                <c:forEach items="${salones}" var="salon">
                                    <option value="${salon.codigo}">${salon.nombre}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Seleccione un salon.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Seleccione el congreso donde quiere crear la actividad</label>
                            <select class="form-select" name="tipoActividad" required>
                                <option value="">Escoger...</option>
                                <c:forEach items="${participacionesCongreso}" var="participacionCongreso">
                                    <option value="${participacionCongreso.tipoTrabajo.actividad}">${participacionCongreso.tipoTrabajo.actividad}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Seleccione un tipo de actividad.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Si la actividad es TALLER tiene que agregar cupo maximo</label>
                            <input type="number" class="form-control" name="cupo" min="1">
                        </div>

                        <c:if test="${error != null}">
                            <div class="alert alert-warning" role="alert">${error}</div>
                        </c:if>

                        <div class="d-grid gap-2 d-md-flex justify-content-center">
                            <a class="btn btn-primary" type="button" href="formulario-actividad-servlet">Inicio</a>
                            <button method="POST" class="btn btn-primary">Siguiente</button>
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
