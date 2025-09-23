<%-- 
    Document   : presentar-trabajo
    Created on : 20/09/2025, 6:54:19 p. m.
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Presentar trabajo</title>
        <jsp:include page="../includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="../includes/header.jsp"/>
        <div class="container">
            <div class="py-5 text-center">
                <h2>Presentar Trabajo</h2>
            </div>


            <div class="pb-5 offset-2 col-8">
                <form method="POST" action="presentar-trabajo-servlet">

                    <div>
                        <p></p>
                        <p><strong>seleccione el tipo de trabajo que quiere presentar al congreso:</strong></p>
                        <input type="hidden" name="numero" value="${param.numero}">
                    </div>

                    <div class="col-6 mt-3">
                        <label class="form-label">Trabajos</label>
                        <select class="form-select" name="trabajo" required>
                            <option value="">Escoger...</option>
                            <option value="Ponente">Ponencia</option>
                            <option value="Tallerista">Taller</option>
                        </select>
                        <div class="invalid-feedback">
                            Seleccione un tipo de trabajo.
                        </div>
                    </div>

                    <div class="mt-3 d-flex justify-content-center">
                        <button method="POST" class="btn btn-primary">Presentar Trabajo</button>
                    </div>
                    
                    <c:if test="${error != null}">
                        <div class="my-3 alert alert-warning" role="alert">
                            ${error}
                        </div>
                    </c:if>

                </form>        
            </div>
        </div>
        <jsp:include page="../includes/footer.jsp"/>
    </body>
</html>
