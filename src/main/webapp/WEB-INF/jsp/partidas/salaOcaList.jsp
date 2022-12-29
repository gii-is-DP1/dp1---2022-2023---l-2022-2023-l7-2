<%@ page import="org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas" title="Lista de partidas">
    <h2>Unirse por codigo</h2>
   
    <form class="form-inline" th:action="@{partida}">
        <input type="text" name="codigo" id="codigo" th:value="${codigo}" placeholder="Introduzca codigo" required>
     <input type="submit" class="btn btn-primary mb-2" value="Buscar">
    </form>
    <h2>Partidas de Oca</h2>
    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Salas</th>
            <th>Tipo<th>
            <th>Jugadores</th>
            <th>Estado</th>
            <th>Fecha Creacion</th>
            <th>Unirse</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidaOca.content}" var="partidaOca">
            <tr>
                <td>
                    <c:out value="Sala ${partidaOca.codigoPartida}"/>
                </td>

                <td>
                    <c:out value="Oca"></c:out>
                </td>

                <td style="display:hidden"></td>

                <td>
                    <c:out value="${partidaOca.jugadores.size()}/${partidaOca.maxJugadores}"></c:out>
                </td>

                <td>
                    <c:out value="${partidaOca.estado}"></c:out>
                </td>

                <td>
                    <c:out value="${partidaOca.fechaCreacion}"></c:out>
                </td>
                
                <td>
                    <c:choose>
                        <c:when test="${partidaOca.estado==TipoEstadoPartida.CREADA}">
                            <spring:url value="/partida/oca/{partidaOcaId}/entrar" var="ocaJoinUrl">
                            <spring:param name="partidaOcaId" value="${partidaOca.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(ocaJoinUrl)}" class="btn btn-default">Unirse</a>
                        </c:when>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${numPagina > 0}">
        <spring:url value="/partida/oca/listar/{numPagina}" var="enlaceAnterior">
            <spring:param name="numPagina" value="${numPagina-1}"/>
        </spring:url>
        <a href="${fn:escapeXml(enlaceAnterior)}" class="btn btn-default">Anterior</a>
    </c:if>
    <c:if test="${numTotalPaginas-1 > numPagina}">
        <spring:url value="/partida/oca/listar/{numPagina}" var="enlaceSiguiente">
            <spring:param name="numPagina" value="${numPagina+1}"/>
        </spring:url>
        <a href="${fn:escapeXml(enlaceSiguiente)}" class="btn btn-default">Siguiente</a>
    </c:if>
</petclinic:layout>