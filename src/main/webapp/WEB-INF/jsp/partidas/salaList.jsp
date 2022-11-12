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
                    <form: form modelAttribute="partidaOca"
                        class="form-horizontal">
                        <button class="btn btn-default" type="submit">Unirse</button>
                    </form>
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
                    <form: form modelAttribute="partidaParchis"
                        class="form-horizontal">
                        <button class="btn btn-default" type="submit">Unirse</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>