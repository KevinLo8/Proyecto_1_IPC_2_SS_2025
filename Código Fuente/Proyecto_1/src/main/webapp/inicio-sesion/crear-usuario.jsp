<%-- 
    Document   : crear-usuario
    Created on : 6/09/2025, 8:14:16 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Usuario</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Crear nuevo usuario</h2>
                <p class="lead">Llenar los datos que se piden abajo para poder crear un nuevo usuario.</p>
            </div>

            <div class="pb-5 offset-2 col-8">
                <div class="col-lg-12">
                    <h4 class="mb-3">Datos para registrase</h4>
                    <form class="needs-validation" method="POST" action="${pageContext.servletContext.contextPath}/inicio-sesion/crear-usuario-servlet">

                        <div class="row g-3 mb-4">

                            <div class="col-12">
                                <label for="username" class="form-label">Correo electrónico del usuario</label>
                                <div class="input-group has-validation">
                                    <input type="text" class="form-control" value="${param.correo}" placeholder="Correo Electrónico" name="correo" minlength="1" maxlength="150" required>
                                    <div class="invalid-feedback">
                                        se requiere un correo electrónico.
                                    </div>
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" placeholder="Contraseña" name="contraseña" minlength="1" maxlength="100" required>
                                <div class="invalid-feedback">
                                    se requiere una contraseña.
                                </div>
                            </div>

                        </div>

                        <c:if test="${error != null}">
                            <div class="my-3 alert alert-warning" role="alert">
                                ${error}
                            </div>
                        </c:if>

                        <button class="w-100 btn btn-primary btn-lg" method="POST">Registrarse</button>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>      
    </body>
</html>
