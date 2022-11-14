<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>Salas</th>
            <th>Maximo Jugadores</th>
            <th>Estado</th>
            <th>Fecha Creacion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidaOca}" var="partidaOca">
            <tr>
                <td>
                    <c:out value="Sala ${partidaOca.codigoPartida}"/>
                </td>
                <td>
                    <c:out value="${partidaOca.maxJugadores}"/>
                </td>
                <td>
                    <c:out value="${partidaOca.estado}"/>
                </td>
                <td>
                    <c:out value="${partidaOca.fechaCreacion}"/>
                </td>
                <td>
                    <spring:url value="/sala/{partidaOcaId}/ocaJoin" var="ocaJoinUrl">
                        <spring:param name="partidaOcaId" value="${partidaOca.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ocaJoinUrl)}" class="btn btn-default">Detalles</a>
                </td>
            </tr>
        </c:forEach>
        <c:forEach items="${partidaParchis}" var="partidaParchis">
            <tr>
                <td>
                    <c:out value="Sala ${partidaParchis.codigoPartida}"/>
                </td>
                <td>
                    <c:out value="${partidaParchis.maxJugadores}"/>
                </td>
                <td>
                    <c:out value="${partidaParchis.estado}"/>
                </td>
                <td>
                    <c:out value="${partidaParchis.fechaCreacion}"/>
                </td>
                <td>
                    <spring:url value="/sala/{partidaParchisId}/parchisJoin" var="parchisJoinUrl">
                        <spring:param name="partidaParchisId" value="${partidaParchis.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(parchisJoinUrl)}" class="btn btn-default">Detalles</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>