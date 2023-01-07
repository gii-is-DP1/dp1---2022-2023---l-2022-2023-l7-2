<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>




<petclinic:layout pageName="game" title="Jugando a la Oca">
    
    <h1>vista: ${modo}</h1>
    <h1>Es el turno del jugador ${partidaOca.colorJugadorActual}</h1>
   


    <br>

    now: ${now}<br>
    fechaHoraUlti : ${partidaOca.getFechaHoraUltimoMovimiento()}<br>
    tiempo de turno : ${(now-partidaOca.getFechaHoraUltimoMovimiento())/1000}
    <div id="fechaHoraUltimoMovimiento" data-fechaHoraUltimoMovimiento="${partidaOca.getFechaHoraUltimoMovimiento()}"></div>



    <br>


<petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>
   
    <br>
    Jugador autenticado: ${jugadorAutenticado}

    <br>
    colorJugador actual : ${partidaOca.colorJugadorActual}

    <c:if test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}"> 
        <form:form class="form-horizontal" id="tirar-dado-form"
            method="post" action="/partida/oca/${partidaOca.id}/jugar">
            <button class="btn btn-default" onClick="this.form.submit(); this.disabled=true; this.innerText='Jugando...'; ">Tirar dado</button>
        </form:form>
    </c:if>

    <c:if test="${jugadorAutenticado.color != partidaOca.colorJugadorActual}"> 
        <button id="tirarDado" class="btn btn-default" disabled >Tirar dado</button>
    </c:if>


    <br>
    <br>
    <h1>Resumen:</h1>
    ${partidaOca.printLog()}
    <h2> <c:out value="CHAT"></c:out></h2>

    ${partidaOca.printChatOca()}
    <spring:url value="/partida/oca/{partidaOcaId}/jugar"
    var="chatOcaUrl">
    <spring:param name="partidaOcaId"
        value="${partidaOca.id}" />
       
   
</spring:url>
<form class="form-inline" th:action="@{${fn:escapeXml(chatOcaUrl)}}">
    <input type="text" name="mensaje" id="mensaje" th:value="${mensaje}" placeholder="Introduzca mensaje" required>
 <input type="submit" class="btn btn-primary mb-2" value="Enviar">
</form>

    <c:if test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}">
        <form id="pasarTurnoForm" method="post" action="/partida/oca/${partidaOca.id}/jugar">
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
        let tiempoDelTurnoRestante = 30 - tiempoDelTurnoPasado;
        console.log("tiempoRestante: " +tiempoDelTurnoRestante);
        
        setTimeout(pasarTurno, tiempoDelTurnoRestante*1000);
            
        
        
        

    </script>
</petclinic:layout>