<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<petclinic:layout pageName="home" title="Jugando a la Oca">
    
    <h1>vista: ${modo}</h1>
    <h1>Es el turno del jugador ${partidaOca.colorJugadorActual}</h1>

<petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>
    
    <br>
    Jugador autenticado: ${jugadorAutenticado}

    Color jugador autenticado : ${jugadorAutenticado.color}
    <br>
    colorJugador actual : ${partidaOca.colorJugadorActual}

    <c:if test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}"> 
        
        <form:form class="form-horizontal" id="tirar-dado-form"
            method="post" action="/sala/${partidaOca.id}/playOca">
            <button class="btn btn-default">Tirar dado</button>
        </form:form>
            
    </c:if>
    <c:if test="${jugadorAutenticado.color != partidaOca.colorJugadorActual}"> 
        <button id="tirarDado" class="btn btn-default" disabled >Tirar dado</button>
    
    </c:if>
</petclinic:layout>