<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="crearPartidas">
    <jsp:body>
            <c:if test="messages != null">
            <div class="messages">
                <c:forEach items="${messages}" var="message">
                    <div class="message">
                        <c:out value="${message}"/>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <form:form modelAttribute="partida" class="form-horizontal">
            <div>
            <label for="maxJugadores">Num Jugadores:</label>
            <select name="maxJugadoresSeleccionado">
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            
            </div>

            <div>
                <spring:url value="/sala/parchisCreate" var="parchisCreateUrl">
                        <!--<spring:param name="maxJugadoresSeleccionado" value="${maxJugadoresSeleccionado}"/> -->
                    </spring:url>
                    <a href="${fn:escapeXml(parchisCreateUrl)}" class="btn btn-default">Crear Partida Parchis</a>

            </div>
            
            <div>
                <spring:url value="/sala/ocaCreate" var="ocaCreateUrl">
                        <!--<spring:param name="maxJugadoresSeleccionado" value="${maxJugadoresSeleccionado}"/>-->
                    </spring:url>
                    <a href="${fn:escapeXml(ocaCreateUrl)}" class="btn btn-default">Crear Partida Oca</a>

            </div>

        </form:form>
    </jsp:body>
</petclinic:layout>