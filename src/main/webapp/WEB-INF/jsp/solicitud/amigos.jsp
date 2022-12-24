<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="amigos" title="solicitud - amigos">
<h2>Agregar Amigos</h2>
<form class="form-inline" th:action="@{solicitud/amigos}">
    <input type="text" name="apodoFiltro" id="apodoFiltro" th:value="${apodoFiltro}" placeholder="Introduzca apodo" required>
 <input type="submit" class="btn btn-primary mb-2" value="Buscar">
 <c:set var = "filtrados"  value = "${filtrados}"/>
     <c:if test="${filtrados!= null}">
       
    <table id="agregarTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Apodo</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 120px">Apellido</th>
            <th></th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${filtrados}" var="filtrado">
            <tr>
                <td>
                    
                   <c:out value="${filtrado.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${filtrado.nombre}"/>
                </td>
                <td>
                    <c:out value="${filtrado.apellido}"/>
                </td>
                <td>
                    <spring:url value="/solicitud/amigos/{usuarioId}/agregar" var="agregarAmigoUrl">
                        <spring:param name="usuarioId" value="${filtrado.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(agregarAmigoUrl)}" class="btn btn-default">Agregar</a>
                </td>
                    
            </tr>
        </c:forEach>
        </tbody>
    </table>



     </c:if>
    <h2>Amigos</h2>

    <table id="amigosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Apodo</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 120px">Apellido</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${amigos}" var="amigo">
            <tr>
                <td>
                    
                   <c:out value="${amigo.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${amigo.nombre}"/>
                </td>
                <td>
                    <c:out value="${amigo.apellido}"/>
                </td>
                    
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>