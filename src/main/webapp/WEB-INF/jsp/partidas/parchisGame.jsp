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
    <h3>vista: <c:out value="${modo}"></c:out></h3>
    <c:if test="${not empty dado}"><h1> El dado ha sacado el numero: ${dado}</h1></c:if>
    <h1>Es el turno del jugador ${partidaParchis.colorJugadorActual}</h1>
   
    <c:if test="${jugadorAutenticado.color == partidaParchis.colorJugadorActual }"> 
        <div id="divAlerta10SecRestantes" class="alert alert-danger" role="alert" style="display:none;"></div>
    </c:if>
    
      <div style="display: flex; flex-wrap: wrap; width: 75vw;">
        <div style="width: 810px;">
            <petclinic:parchisBoard tablero="${partidaParchis}" fichasQueSePuedenMover="${fichasQueSePuedenMover}" dado="${dado}" jugadorAutenticado="${jugadorAutenticado}"></petclinic:parchisBoard>
    


        </div>
        <div style="width: 30%;">
            <c:forEach items="${partidaParchis.jugadores}" var="jugador">
                <petclinic:jugador jugador="${jugador}"></petclinic:jugador>
                <br>
            </c:forEach>
            
            <h3>Tiempo restante: <span id="countdown"></span></h3>
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

        </div>

    </div>
    
    


    

    
   
    
    

    
    

    <table class="table table-striped">
        <tr>
            <td style="width: 50%;">
                <h1>
                    <center>Resumen:</center>
                </h1>
                ${partidaParchis.printLog()}
            </td>
            <td style="width: 50%;">
                <h1>
                    <center><h1> <c:out value="Chat"></c:out></h1></center>
                </h1>
               
                
                <div style="width: 35em;">
                    <div style="height:100px; overflow:auto">
                        ${partidaParchis.printChatParchis()}
                        <spring:url value="/partida/parchis/{partidaParchisId}/jugar"
                         var="chatParchisUrl">
                        <spring:param name="partidaParchisId"
                         value="${partidaParchis.id}" />
                        </spring:url>
                    </div>
                    <c:if test="${not empty jugadorAutenticado}">
                        <form class="form-inline" th:action="@{${fn:escapeXml(chatParchisUrl)}}">
                            <input type="text" name="mensaje" id="mensaje" th:value="${mensaje}" placeholder="Introduzca mensaje" required>
                            <input type="submit" class="btn btn-primary mb-2" value="Enviar">
                        </form>
                    </c:if>                   
                   
                </div>
            </td>
        </tr>
    </table>


</petclinic:layout>

<div id="fechaHoraUltimoMovimiento" data-fechaHoraUltimoMovimiento="${partidaParchis.getFechaHoraUltimoMovimiento()}"></div>
    


<script>


  
    let fechaHoraUltimoMovimiento = parseInt(document.getElementById("fechaHoraUltimoMovimiento").getAttribute("data-fechaHoraUltimoMovimiento"));
    console.log(fechaHoraUltimoMovimiento);
    let now = Date.now();
    console.log(now);
    let tiempoDelTurnoPasado = (now-fechaHoraUltimoMovimiento)/1000 + 3600;
    let tiempoDelTurnoRestante = 30 - tiempoDelTurnoPasado;
    let tiempoDelTurnoRestanteMillis = tiempoDelTurnoRestante*1000;
    if(tiempoDelTurnoRestanteMillis>0){ 
            setTimeout(function(){location.reload();},tiempoDelTurnoRestanteMillis);
        if(tiempoDelTurnoRestanteMillis>10000){
            try {
                setTimeout(function(){
                    document.getElementById("divAlerta10SecRestantes").innerText="Le quedan 10 segundos. Si no juega se pasará su turno";
                    document.getElementById("divAlerta10SecRestantes").style.display="block";
                }, tiempoDelTurnoRestanteMillis - 10000);    
            } catch (error) {
                    console.error(error);
                }
        }else{
                try {
                    document.getElementById("divAlerta10SecRestantes").innerText="Le quedan menos de 10 segundos. Si no juega se pasará su turno";
                    document.getElementById("divAlerta10SecRestantes").style.display="block";
                } catch (error) {
                    console.error(error);
                }
        }
    }

    var totalTime = parseInt(tiempoDelTurnoRestante);
    console.log("totalTime: " + totalTime);
    updateClock()
    function updateClock() {
        document.getElementById('countdown').innerText = totalTime;
        if (totalTime == 0) {
        } else {
            totalTime -= 1;
            setTimeout("updateClock()", 1000);
        }
    }
     

</script> 