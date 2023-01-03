<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<petclinic:layout pageName="usuarios" title="admin - Usuarios">
    <h2>Usuarios</h2>

    <table id="usuariosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Apodo</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 120px">Apellido</th>
            <th style="width: 50px"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${selections}" var="usuario">
            <tr>
                <td>
                    <spring:url value="/usuario/{usuarioId}" var="usuarioUrl">
                        <spring:param name="usuarioId" value="${usuario.id}"/>
                    </spring:url>
                    <!--<a href="${fn:escapeXml(usuarioUrl)}">--><c:out value="${usuario.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${usuario.nombre}"/>
                </td>
                <td>
                    <c:out value="${usuario.apellido}"/>
                </td>
                <td>
                    <spring:url value="listUsuarios/{usuarioId}/delete" var="usuarioDeleteUrl">
                        <spring:param name="usuarioId" value="${usuario.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(usuarioDeleteUrl)}">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                </a>
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