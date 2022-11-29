<%@ page import="org.springframework.samples.petclinic.model.Color"%>

<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>



<petclinic:layout pageName="Partidas" title="admin - Partidas">
    <h2>Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 80px;">Codigo de partida</th>
            <th style="width: 100px;">Creador (Jugador Rojo)</th>
            <th style="width: 200px">Jugadores</th>
            <th style="width: 100px">Tipo Partida</th>
            <th style="width: 100px">Estado</th>
            <th style="width: 50px"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${parchis}" var="parchis">
            <tr>
                <td>
                  <c:out value="${parchis.codigoPartida}"></c:out>
                </td>
                <td>
                    <c:forEach items="${parchis.jugadores}" var="jugador">
                        <c:if test="${jugador.color eq Color.ROJO}">
                            <c:out value="${jugador.usuario.user.username}"/>
                        </c:if>
                    </c:forEach>
                    

                </td>
                <td>
                    <c:forEach items="${parchis.jugadores}" var="jugador">
                        <c:if test="${jugador.color != Color.ROJO}">
                            <c:out value="${jugador.usuario.user.username} "/>
                        </c:if>
                    </c:forEach>
                    

                </td>
                <td>
                    <c:out value="Parchis"></c:out>
                </td>
                <td>
                    <c:out value="${parchis.estado}"></c:out>

                </td>
                <td>
                <spring:url value="/admin/listPartidas/partidaParchis/{partidaParchisId}/delete" var="partidaParchisDeleteUrl">
                        <spring:param name="partidaParchisId" value="${parchis.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(partidaParchisDeleteUrl)}">
                    <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                </td>
            </tr>
        </c:forEach>
        <c:forEach items="${oca}" var="oca">
            <tr>
                <td>
                  <c:out value="${oca.codigoPartida}"></c:out>
                </td>
                <td>
                    <c:forEach items="${oca.jugadores}" var="jugador">
                        <c:if test="${jugador.color eq Color.ROJO}">
                            <c:out value="${jugador.usuario.user.username}"/>
                        </c:if>
                    </c:forEach>
                    

                </td>
                <td>
                    <c:forEach items="${oca.jugadores}" var="jugador">
                        <c:if test="${jugador.color != Color.ROJO}">
                            <c:out value="${jugador.usuario.user.username} "/>
                        </c:if>
                    </c:forEach>
                    

                </td>
                <td>
                    <c:out value="Oca"></c:out>
                </td>
                <td>
                    <c:out value="${oca.estado}"></c:out>

                </td>
                <td>
                    <spring:url value="/admin/listPartidas/partidaOca/{partidaOcaId}/delete" var="partidaOcaDeleteUrl">
                            <spring:param name="partidaOcaId" value="${oca.id}"/>
                        </spring:url>
                        <a href="${fn:escapeXml(partidaOcaDeleteUrl)}">
                        <span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>
                    </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
