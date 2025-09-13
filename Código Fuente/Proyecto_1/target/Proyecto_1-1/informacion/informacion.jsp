<%-- 
    Document   : informacion
    Created on : 9/09/2025, 10:09:03 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <div class="container">
                <main>
                    <div class="py-3 text-center">
                        <h1 class="h2">Información de usuario</h1>
                    </div>

                    <c:if test="${mensaje != null}">
                        <div style="width: 1125px; margin: auto;" class="alert alert-success mb-2" role="alert">${mensaje}</div>
                    </c:if>

                    <c:if test="${errordb == null}">

                        <form class="needs-validation" method="POST" action="${pageContext.servletContext.contextPath}/informacion/guardar-informacion-servlet" enctype="multipart/form-data">
                            <div style="width: 1125px; margin: auto;" class="row">

                                <div class="col-12 mt-3">
                                    <label class="form-label">Correo electrónico</label>
                                    <input type="text" class="form-control" value="${correo}" disabled>
                                </div>
                                <div class="col-12 mt-3">
                                    <label class="form-label mt-3">Nombre de usuario</label>
                                    <input type="text" class="form-control" name="nombre" placeholder="Nombre de usuario" value="${informacion.nombreUSuario}" maxlength="100" required>
                                    <div class="invalid-feedback">
                                        Se requiere un nombre de usuario.
                                    </div>
                                </div>
                                <div class="col-12 mt-3">
                                    <label class="form-label mt-3">Foto del usuario</label>
                                    <input type="file" s class="form-control" name="foto" value="${foto}" accept="image/*" readonly>
                                </div>
                                <div class="col-12 mt-3">
                                    <label class="form-label">Identificación del usuario</label>
                                    <div class="input-group has-validation">
                                        <input type="text" class="form-control" name="identificacion" value="${informacion.numeroIdentificacion}" placeholder="Identificación de usuario" maxlength="25" required>
                                        <div class="invalid-feedback">
                                            Se requiere un número de identificación.
                                        </div>
                                    </div>
                                </div>
                                <div class="col-6 mt-3">
                                    <label class="form-label">Número telefónico</label>
                                    <input type="text" class="form-control" name="telefono" value="${informacion.numeroTelefono}" placeholder="Numero telefónico" maxlength="15" required>
                                    <div class="invalid-feedback">
                                        Se requiere un número telefónico.
                                    </div>
                                </div>
                                <div class="col-6 mt-3">
                                    <label class="form-label">Institución</label>
                                    <select class="form-select" name="institucion" required>
                                        <option value="">Escoger...</option>
                                        <c:forEach items="${instituciones}" var="institucion">
                                            <c:if test="${institucion.numero == informacion.idInstitución}">
                                                <option value="${institucion.nombre}" selected>${institucion.nombre}</option>
                                            </c:if>
                                            <c:if test="${institucion.numero != informacion.idInstitución}">
                                                <option value="${institucion.nombre}">${institucion.nombre}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <div class="invalid-feedback">
                                        Seleccione una institución.
                                    </div>
                                </div>

                                <c:if test="${informacion == null}">
                                    <div class="col-9 mt-3">
                                        <label class="form-label">Saldo</label>
                                        <input type="text" class="form-control" value="0.00" readonly>
                                    </div>
                                    <div class="col-3 mt-5">
                                        <a type="button" class="w-100 btn btn-secondary" disabled>Agregar saldo</a>
                                    </div>
                                </c:if>
                                <c:if test="${informacion != null}">
                                    <div class="col-9 mt-3">
                                        <label class="form-label">Saldo</label>
                                        <input type="text" class="form-control" value="${informacion.dinero}" readonly>
                                    </div>
                                    <div class="col-3 mt-5">
                                        <a type="button" class="w-100 btn btn-secondary" href="${pageContext.servletContext.contextPath}/dinero/cargar-dinero-servlet">Agregar saldo</a>
                                    </div>
                                </c:if>

                                <c:if test="${error != null}">
                                    <div class="row g-5 justify-content-center">
                                        <div class="alert alert-warning col-10" role="alert">${error}</div>
                                    </div>
                                </c:if>

                                <div class="col-12 mt-4">
                                    <button class="w-100 btn btn-primary my-3" method="POST">Guardar Información</button>
                                </div>
                            </div>
                        </form>
                    </c:if>

                    <c:if test="${errordb != null}">
                        <div class="alert alert-warning" role="alert">${errordb}</div>
                    </c:if>

                </main>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>