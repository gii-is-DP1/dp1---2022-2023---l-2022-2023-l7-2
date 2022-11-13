<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="espera">
    <h2>Estás en espera a que empiece la partida...</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Jugadores</th>
            <th>Color</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugador}" var="jugadores">
            <tr>
                <td>
                    <c:out value="${jugadores.jugador_id}"/>
                </td>
                <td>
                    <c:out value="${jugadores.color}"/>
                </td>
            </tr>
        </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>