<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas">
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
        <form:form modelAttribute="partida"
                   class="form-horizontal">
            <petclinic:selectField name="maxJugadores" label="Partida maxJugadores" names="${maxJugadores}" size="4"/>
            <button class="btn btn-default" type="submit">Crear Nueva Sala</button>
            
        </form:form>
    </jsp:body>
</petclinic:layout>