<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
    <h2>Partidas</h2>

    <table id="partidasTable" class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Codigo Partida</th>
            <th>Maximo Jugadores</th>
            <th>Estado</th>
            <th>Fecha Creacion</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidaOca}" var="partidaOca">
            <tr>
                <td>
                    <c:out value="${partidaOca.id}"/>
                </td>
                <td>
                    <c:out value="${partidaOca.codigoPartida}"/>
                </td>
                <td>
                    <c:out value="${partidaOca.maxJugadores}"/>
                </td>
                <td>
                    <c:out value="${patidaOca.estado}"/>
                </td>
                <td>
                    <c:out value="${patidaOca.fechaCreacion}"/>
                </td>
                <form: form modelAttribute="partidaOca"
                class="form-horizontal">
                    <button class="btn btn-default" type="submit">Unirse a Sala de Oca</button>
                </form>
            </tr>
        </c:forEach>
        <c:forEach items="${partidaParchis}" var="partidaParchis">
            <tr>
                <td>
                    <c:out value="${partidaParchis.id}"/>
                </td>
                <td>
                    <c:out value="${partidaParchis.codigoPartida}"/>
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
                <form: form modelAttribute="partidaParchis"
                class="form-horizontal">
                    <button class="btn btn-default" type="submit">Unirse a Sala de Parchis</button>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>