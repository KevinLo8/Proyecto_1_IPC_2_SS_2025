<%-- 
    Document   : cerrar-sesion-completado
    Created on : 6/09/2025, 11:42:49 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cierre de Sesión completo</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <div class="position-relative p-5 text-center text-muted bg-body border border-dashed rounded-5">
                <h1 class="text-body-emphasis">Cierre de Sesión Completado</h1>
                <p class="col-lg-6 mx-auto mb-4">
                    Se a cerrado la sesión correctamente.
                </p>
                <a class="btn btn-primary px-5 mb-5" type="button" href="../index.jsp">Inicio</a>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
