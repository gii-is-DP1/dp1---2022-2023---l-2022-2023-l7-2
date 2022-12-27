<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<petclinic:layout pageName="game" title="Jugando al parchis">
    <h5>${partidaParchis.estado}</h5>
    ${debug}
    <h1>vista: ${modo}</h1>
    <h1>   El dado ha sacado el numero: ${dado}    </h1>
    <h1>Es el turno del jugador ${partidaParchis.colorJugadorActual}</h1>
   
<petclinic:parchisBoard tablero="${partidaParchis}" fichasQueSePuedenMover="${fichasQueSePuedenMover}" dado="${dado}" jugadorAutenticado="${jugadorAutenticado}"></petclinic:parchisBoard>
   
    <br>
    Jugador autenticado: ${jugadorAutenticado}
    <br>
    colorJugador actual : ${partidaParchis.colorJugadorActual}
    <br>
    <br>
    
     JUGADORES:<br>
    <c:forEach items="${partidaParchis.jugadores}" var="jugador">
        ${jugador.color}<br>
        <c:forEach items="${jugador.fichasParchis}" var="ficha">
            &nbsp;&nbsp;&nbsp;&nbsp;-${ficha}<br>
        </c:forEach>
        <br>
    </c:forEach>


    <c:if test="${jugadorAutenticado.color == partidaParchis.colorJugadorActual}"> 
        
        <form:form class="form-horizontal" id="tirar-dado-form"
            method="post" action="/partida/parchis/${partidaParchis.id}/jugar">
            <button class="btn btn-default">Tirar dado</button>     
        </form:form>
        
        <br>

        <c:if test="${fichasQueSePuedenMover.size() == 1 && fichasQueSePuedenMover.get(0).getId() == -1}">
            <form:form modelAttribute="MoverFichaParchisForm"> 
                <form:input type="hidden" path="jugadorId" name="jugadorId" value="${jugadorAutenticado.id}"></form:input>
                <form:input type="hidden" path="fichaId" name="fichaId" value="${fichasQueSePuedenMover.get(0).id}"></form:input>
                <form:input type="hidden" path="dado" name="dado" value="${dado}"></form:input>
                <button class="btn btn-default">Pasar turno</button>
            </form:form>
        </c:if>
        <c:forEach  items="${fichasQueSePuedenMover}" var="fichaMovible">
            
            <form:form modelAttribute="MoverFichaParchisForm"> 
                <form:input type="hidden" path="jugadorId" name="jugadorId" value="${jugadorAutenticado.id}"></form:input>
                <form:input type="hidden" path="fichaId" name="fichaId" value="${fichaMovible.id}"></form:input>
                <form:input type="hidden" path="dado" name="dado" value="${dado}"></form:input>
                
                <button class="btn btn-default">Mover ficha en casilla ${fichaMovible.casillaActual.numero}</button>
            </form:form>

        </c:forEach>
            
    </c:if>
    


    <br>
    <br>
    fichasQueSePuedenMover: ${fichasQueSePuedenMover}

    <h1>Resumen:</h1>

</petclinic:layout>