<%@ page import="org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas" title="Lista de partidas">
    <h2>Partidas</h2>

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
        <c:forEach items="${partidaOca}" var="partidaOca">
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
                            <spring:url value="/sala/{partidaOcaId}/ocaJoin" var="ocaJoinUrl">
                            <spring:param name="partidaOcaId" value="${partidaOca.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(ocaJoinUrl)}" class="btn btn-default">Unirse</a>
                        </c:when>
                    </c:choose>
                </td>

            </tr>
        </c:forEach>
        <c:forEach items="${partidaParchis}" var="partidaParchis">
            <tr>
                <td>
                    <c:out value="Sala ${partidaParchis.codigoPartida}"></c:out>
                </td>

                <td>
                    Parchis
                </td>

                <td style="display:hidden"></td>

                <td>
                    <c:out value="${partidaParchis.jugadores.size()}/${partidaParchis.maxJugadores}"></c:out>
                </td>

                <td>
                    <c:out value="${partidaParchis.estado}"></c:out>
                </td>

                <td>
                    <c:out value="${partidaParchis.fechaCreacion}"></c:out>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${partidaParchis.estado==TipoEstadoPartida.CREADA}">
                            <spring:url value="/sala/{partidaParchisId}/parchisJoin" var="parchisJoinUrl">
                            <spring:param name="partidaParchisId" value="${partidaParchis.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(parchisJoinUrl)}" class="btn btn-default">Unirse</a>
                        </c:when>                   
                    </c:choose>             
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>