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
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${partidas.partidaList}" var="partida">
            <tr>
                <td>
                    <c:out value="${partida.id}"/>
                </td>
                <td>
                    <c:out value="${partida.codigoPartida}"/>
                </td>
                <td>
                    <c:out value="${partida.maxJugadores}"/>
                </td>
                <form: form modelAttribute="partida"
                class="form-horizontal">
                    <button class="btn btn-default" type="submit">Unirse a Sala</button>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>