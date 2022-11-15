<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="espera">
	<form:form modelAttribute="jugadores" class="form-horizontal" id="add-jugadores-form">
		<h2>Estas en espera a que empiece la partida...</h2>
		
	
       	<table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th>Jugadores</th>
            <th>Color</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugadores"> 
            <tr>
                <td>
                    <c:out value="${jugadores.usuario.nombre}"/>
                </td>
                <td>
                    <c:out value="${jugadores.color}"/>
                </td>
            </tr>
        </c:forEach>
        <form>
            <div>
                <div class="form">
                    <div>
                        <button class="btn btn-default" type="submit">Unirse a Partida </button>
                    </div>
                </div>
            </div>
        </form>
        </tbody>
    </form:form>
    </table>
</petclinic:layout>