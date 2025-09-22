<%-- 
    Document   : header
    Created on : 5/09/2025, 9:40:59 p. m.
    Author     : Kevin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container"> 
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom"> 
        <div class="col-md-3 mb-2 mb-md-0">  
            <a href="${pageContext.servletContext.contextPath}/index.jsp" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-body-emphasis text-decoration-none"> 
                <i class="bi bi-bootstrap" style="font-size: 2rem;"></i>
                <span class="mx-3 fs-4">Gestión de congresos</span> 
            </a>
        </div> 
        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0"> 
            <li><a href="${pageContext.servletContext.contextPath}/congreso/listado-congresos-servlet" class="nav-link px-2">Congresos</a></li> 
            <li><a href="#" class="nav-link px-2">Actividades</a></li> 
            <li><a href="#" class="nav-link px-2">Acerca De</a></li>
        </ul> 
        <div class="col-md-3 text-end"> 
            <c:if test="${correo == null}">

                <a type="button" href="${pageContext.servletContext.contextPath}/inicio-sesion/inicio-sesion.jsp" class="btn btn-outline-primary me-2">Iniciar Sesión</a> 
                <a type="button" href="${pageContext.servletContext.contextPath}/inicio-sesion/crear-usuario.jsp" class="btn btn-primary">Crear Usuario</a>

            </c:if>
            <c:if test="${correo != null}">

                <div class="btn-group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">${correo}</button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/informacion/cargar-informacion-servlet">Información</a></li>

                        <c:if test="${usuario.adminSistema == true}">

                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/institucion/listado-de-instituciones-servlet">Administrar Instituciones</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/usuario/listado-de-usuarios-servlet">Administrar Usuarios</a></li>

                        </c:if>

                        <c:if test="${usuario.adminCongreso == true}">

                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/congreso/formulario-congreso-servlet">Crear Congreso</a></li>
                            <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/congreso/congresos-creados-servlet">Administrar Congresos creados</a></li>

                        </c:if>

                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/inicio-sesion/cambiar-contraseña.jsp">Cambiar Contraseña</a></li>
                        <li><a class="dropdown-item" href="${pageContext.servletContext.contextPath}/inicio-sesion/cerrar-sesion-servlet">Cerrar Sesión</a></li>
                    </ul>
                </div>

            </c:if>
        </div>
    </header>
</div>
