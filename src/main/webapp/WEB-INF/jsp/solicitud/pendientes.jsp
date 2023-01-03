<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="solicitudes pendientes" title="solicitud - pendientes">
    <h2>Solicitudes Pendientes</h2>

    <table id="amigosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Apodo</th>
            <th style="width: 200px;">Estado</th>
            <th style="width: 120px">Aceptar</th>
            <th style="width: 120px">Rechazar</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pendientes}" var="pendiente">
            <tr>
                <td>
                    
                   <c:out value="${pendiente.usuarioSolicitud.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${pendiente.tipoEstado}"/>
                </td>
                <td>
                    <spring:url value="/solicitud/pendientes/{solicitudId}/aceptar" var="aceptarSolicitudUrl">
                        <spring:param name="solicitudId" value="${pendiente.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(aceptarSolicitudUrl)}" class="btn btn-default">Aceptar</a>
                </td>
                <td>
                    <spring:url value="/solicitud/pendientes/{solicitudId}/rechazar" var="rechazarSolicitudUrl">
                        <spring:param name="solicitudId" value="${pendiente.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(rechazarSolicitudUrl)}" class="btn btn-default">Rechazar</a>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>