<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<petclinic:layout pageName="Logros"title="Logros">
    <h2>Logros</h2>

    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 50px;">Nombre</th>
            <th style="width: 200px">Descripción</th>
            <sec:authorize access="hasAuthority('admin')">
            <th style="width: 50px"></th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${logros}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.nombre}"></c:out>
                </td>
                <td>
                    <c:out value="${logro.descripcion}"></c:out>

                </td>
                <sec:authorize access="hasAuthority('admin')">
                <td>
                <spring:url value="/logro/{logroId}/edit" var="logroUrl">
                        <spring:param name="logroId" value="${logro.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(logroUrl)}">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                </td>
                </sec:authorize>
            
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <sec:authorize access="hasAuthority('admin')">
    <spring:url value="/logro/new" var="logroCreateUrl">
    </spring:url>
    <a href="${fn:escapeXml(logroCreateUrl)}" class="btn btn-default">Crear un nuevo logro</a>
    </sec:authorize>
</petclinic:layout>