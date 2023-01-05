<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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


    <c:if test="${jugadorAutenticado.color == partidaParchis.colorJugadorActual }"> 
        <c:if test="${empty dado}">
        <form:form class="form-horizontal" id="tirar-dado-form"
            method="post" action="/partida/parchis/${partidaParchis.id}/jugar">
            <button class="btn btn-default">Tirar dado</button>     
        </form:form>
        </c:if>
        
        <br>
        <c:choose>
            <c:when test="${fichasQueSePuedenMover.size() == 0}">
                <form:form modelAttribute="MoverFichaParchisForm"> 
                    <form:input type="hidden" path="jugadorId" name="jugadorId" value="${jugadorAutenticado.id}"></form:input>
                    <form:input type="hidden" path="fichaId" name="fichaId" value="-1"></form:input>
                    <form:input type="hidden" path="dado" name="dado" value="${dado}"></form:input>
                    <button class="btn btn-default">Pasar turno</button>
                </form:form>
            </c:when> 
        <c:otherwise>
            <c:forEach  items="${fichasQueSePuedenMover}" var="fichaMovible">
            
                <form:form modelAttribute="MoverFichaParchisForm"> 
                    <form:input type="hidden" path="jugadorId" name="jugadorId" value="${jugadorAutenticado.id}"></form:input>
                    <form:input type="hidden" path="fichaId" name="fichaId" value="${fichaMovible.id}"></form:input>
                    <form:input type="hidden" path="dado" name="dado" value="${dado}"></form:input>
                    
                    <button class="btn btn-default">Mover ficha en casilla ${fichaMovible.casillaActual.numero}</button>
                </form:form>

            </c:forEach>

        </c:otherwise>

        </c:choose>

        
        
            
    </c:if>
    

    


    <br>
    <br>
    fichasQueSePuedenMover: ${fichasQueSePuedenMover}

    <h1>Resumen:</h1>
    ${partidaParchis.printLog()}
</petclinic:layout>

<div id="fechaHoraUltimoMovimiento" data-fechaHoraUltimoMovimiento="${partidaParchis.getFechaHoraUltimoMovimiento()}"></div>
    
<c:if test="${jugadorAutenticado.color == partidaParchis.colorJugadorActual}">
        <form id="pasarTurnoForm" modelAttribute="MoverFichaParchisForm"
                method="POST" action="/partida/parchis/${partidaParchis.id}/jugar">
            <input type="hidden" name="jugadorId" value="${jugadorAutenticado.id}">
            <input type="hidden" name="fichaId" value="-1">
            <input type="hidden" name="dado" value="1">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>


</c:if>

<script>


    function pasarTurno(){
        let pasarTurnoFormDOM = document.getElementById("pasarTurnoForm");
        pasarTurnoFormDOM.submit();
    }


    let fechaHoraUltimoMovimiento = parseInt(document.getElementById("fechaHoraUltimoMovimiento").getAttribute("data-fechaHoraUltimoMovimiento"));
    console.log(fechaHoraUltimoMovimiento);

    let now = Date.now();
   
    console.log(now);
    let tiempoDelTurnoPasado = (now-fechaHoraUltimoMovimiento)/1000 + 3600;
    let tiempoDelTurnoRestante = 10 - tiempoDelTurnoPasado;
    console.log("tiempoRestante: " +tiempoDelTurnoRestante);
    let tiempoDelTurnoRestanteMillis = tiempoDelTurnoRestante*1000;
    console.log("tiempoDelTurnoRestanteMillis: " + tiempoDelTurnoRestanteMillis);
     if(tiempoDelTurnoRestanteMillis>0){
        //setTimeout(pasarTurno, tiempoDelTurnoRestanteMillis);    
     }

</script>