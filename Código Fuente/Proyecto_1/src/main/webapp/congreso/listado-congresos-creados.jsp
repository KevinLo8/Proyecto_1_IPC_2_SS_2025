<%-- 
    Document   : listado-congresos-creados
    Created on : 19/09/2025, 8:55:42 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congresos Creados</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <h1 class="text-body-emphasis text-center">Listado de Congresos Creados.</h1>

            <c:if test="${errordb == null}">
                <div class="container">
                    <c:if test="${congresos.size() > 0}">
                        <c:if test="${mensaje != null}">
                            <div class="my-3 alert alert-success" role="alert">
                                ${mensaje}
                            </div>
                        </c:if>
                        <c:if test="${error != null}">
                            <div class="my-3 alert alert-warning" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <div class="accordion accordion-flush">

                            <c:forEach items="${congresos}" var="congreso">
                                <div class="accordion m-4">
                                    <div class="accordion-item">
                                        <h2 class="accordion-header">
                                            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${congreso.numero}" aria-expanded="false" aria-controls="collapse${congreso.numero}">
                                                <h4><strong>${congreso.nombre}</strong></h4> 
                                            </button>
                                        </h2>
                                        <div id="collapse${congreso.numero}" class="accordion-collapse collapse">
                                            <div class="accordion-body">
                                                <p><strong>Ubicación:</strong>&emsp;${congreso.ubicacion}</p>
                                                <p><strong>Fecha:</strong>&emsp;${congreso.fecha}</p>
                                                <p class=" mb-0"><strong>Descripción:</strong></p>
                                                <p>${congreso.descripcion}</p>
                                                <p><strong>Precio:</strong>&emsp;${congreso.precio}</p>
                                                <c:if test="${congreso.estadoConvocatoriaTrabajos == true}">
                                                    <p><strong>Convocatoria de trabajos:</strong>&emsp;Abierta</p>
                                                </c:if>
                                                <c:if test="${congreso.estadoConvocatoriaTrabajos == false}">
                                                    <p><strong>Convocatoria de trabajos:</strong>&emsp;Cerrada</p>
                                                </c:if>
                                                <div class="container">

                                                </div>
                                                <div class="d-grid gap-2 d-md-flex justify-content-end">
                                                    <div class="btn-group dropstart">
                                                        <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                                            Opciones
                                                        </button>
                                                        <ul class="dropdown-menu">
                                                            <c:if test="${congreso.estadoConvocatoriaTrabajos == true}">
                                                                <a type="button" class="dropdown-item" href="cambiar-estado-convocatoria-servlet?numero=${congreso.numero}">
                                                                    Cerrar Convocatoria De Trabajos
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${congreso.estadoConvocatoriaTrabajos == false}">
                                                                <a type="button" class="dropdown-item" href="cambiar-estado-convocatoria-servlet?numero=${congreso.numero}">
                                                                    Abrir Convocatoria De Trabajos
                                                                </a>
                                                            </c:if>
                                                            <a type="button" class="dropdown-item" href="../salon/listado-salones-servlet?numero=${congreso.numero}">Administrar Salones</a>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${congresos.size() == 0}">
                        <h5 class="py-3 text-body-emphasis text-center">No se ha creado ningun congreso</h5>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${errordb != null}">
                <div class="alert alert-warning" role="alert">${errordb}</div>
            </c:if>

        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>