<%-- 
    Document   : inicio-sesion
    Created on : 6/09/2025, 7:55:57 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar sesión</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Iniciar sesión</h2>
            </div>


            <div class="pb-5 offset-2 col-8">
                <form method="POST" action="${pageContext.servletContext.contextPath}/inicio-sesion/inicio-sesion-servlet">

                    <div class="mb-3">
                        <label for=" sampleInpuUserName" class="form-label">Correo Electrónico</label>
                        <input type="text" class="form-control" inputmode="email" name="correo" value="${param.correo}" minlength="1" maxlength="150" required>
                    </div>

                    <div class="mb-3">
                        <label for="sampleInputPassword" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" name="contraseña" minlength="1" maxlength="100" required>
                    </div>

                    <button method="POST" class="btn btn-primary">Iniciar Sesión</button>

                    <c:if test="${error != null}">
                        <div class="my-3 alert alert-warning" role="alert">
                            ${error}
                        </div>
                    </c:if>

                    <div class="mb-3 mt-3">
                        <label for="sampleInputPassword" class="form-label">No tienes una cuenta?</label>
                        <a class="btn btn-link" role="button" href="crear-usuario.jsp">Registrarse</a>
                    </div>

                </form>        
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
