<%-- 
    Document   : cambiar-contraseña
    Created on : 13/09/2025, 7:00:07 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar contraseña</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Cambiar contraseña</h2>
            </div>


            <div class="pb-5 offset-2 col-8">
                <form method="POST" action="${pageContext.servletContext.contextPath}/inicio-sesion/cambiar-contrasena-servlet">

                    <div class="mb-3">
                        <label class="form-label">Ingrese la contraseña actual:</label>
                        <input type="password" class="form-control" name="contraseñaVieja" value="${param.contraseñaVieja}" minlength="1" maxlength="100" required>
                    </div>

                    <div class="mb-4">
                        <label class="form-label">Ingrese la nueva contraseña:</label>
                        <input type="password" class="form-control" name="contraseñaNueva" value="${param.contraseñaNueva}" minlength="1" maxlength="100" required>
                    </div>

                    <c:if test="${error != null}">
                        <div class="my-3 alert alert-warning" role="alert">
                            ${error}
                        </div>
                    </c:if>

                    <div class="d-md-flex justify-content-center">
                        <button style="width: 40%;margin: auto" method="POST" class="btn btn-primary">Cambiar Contraseña</button>
                    </div>

                </form>        
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
