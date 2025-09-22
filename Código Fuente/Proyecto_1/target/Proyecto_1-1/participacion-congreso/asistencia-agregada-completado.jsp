<%-- 
    Document   : asistencia-agregada-completado.jsp
    Created on : 21/09/2025, 8:09:46 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asistencia agregada completo</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container my-5">
            <div class="position-relative p-5 text-center text-muted bg-body border border-dashed rounded-5">
                <h1 class="text-body-emphasis">Agregación de asistencia a congreso completado</h1>
                <p class="col-lg-6 mx-auto mb-4">
                    Se a agregado la asistencia al congreso ${congreso.nombre} correctamente.
                </p>
                <a class="btn btn-primary px-5 mb-5" type="button" href="../congreso/listado-congresos-servlet">Regresar</a>
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
