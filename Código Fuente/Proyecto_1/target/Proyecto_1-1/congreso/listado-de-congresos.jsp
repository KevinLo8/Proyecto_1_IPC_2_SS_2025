<%-- 
    Document   : listado-de-congresos
    Created on : 18/09/2025, 9:16:45 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Congresos</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <h1 class="text-body-emphasis text-center">Listado de Congresos.</h1>

            <c:if test="${errordb == null}">
                <div class="container">
                    <c:if test="${error != null}">
                        <div class="alert alert-warning" role="alert">${error}</div>
                    </c:if>

                    <c:if test="${congresos.size() > 0}">
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
                                                <div class="container">

                                                </div>
                                                <div class="container">
                                                    <c:if test="${sessionScope.correo != null && sessionScope.correo != congreso.correoAdministrador}">
                                                        <c:if test="${congreso.estadoConvocatoriaTrabajos == true}">
                                                            <a type="button" class="btn btn-secondary" href="../participacion-congreso/presentar-trabajo.jsp?numero=${congreso.numero}">Presentar Trabajo</a>
                                                        </c:if>
                                                        <a type="button" class="btn btn-secondary" href="../participacion-congreso/agregar-asistencia-servlet?numero=${congreso.numero}">Participar En El Congreso</a>
                                                    </c:if>
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