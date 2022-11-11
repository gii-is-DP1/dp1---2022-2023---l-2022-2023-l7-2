<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
        <form:form modelAttribute="partida" class="form-vertical">
            
            <select name="tipo">
                <option value="0">Selecciona un tipo</option>
                <option value="parchis">Parchis</option>
                <option value="oca">Oca</option>
            </select>

            <select name="maxJugadores">
                <option value="0">Selecciona un num</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>


            <button class="btn btn-default" type="submit">Crear Nueva Sala</button>
        </form:form>
    </jsp:body>
</petclinic:layout>