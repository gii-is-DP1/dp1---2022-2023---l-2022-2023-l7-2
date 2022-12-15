<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<petclinic:layout pageName="game" title="Jugando al parchis">
    
    <h1>vista: ${modo}</h1>
    <h1>   El dado ha sacado el numero: ${dado}    </h1>
    <h1>Es el turno del jugador ${partidaParchis.colorJugadorActual}</h1>
   
<petclinic:parchisBoard tablero="${partidaParchis}"></petclinic:parchisBoard>
   
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
            method="post" action="/sala/${partidaParchis.id}/playParchis">
            <button class="btn btn-default">Tirar dado</button>

            <c:forEach  items="${jugadorAutenticado.fichasParchis}" var="fichaJugador">
                <c:if test="${fichasQueSePuedenMover.contains(fichaJugador)}">
                    <button class="btn btn-default">${fichaJugador.id}</button>
                </c:if>
            </c:forEach>
       
        </form:form>
            
    </c:if>
    


    <br>
    <br>
    dado: ${dado}
    fichasQueSePuedenMover: ${fichasQueSePuedenMover}

     

    
    <h1>Resumen:</h1>


     
    
</petclinic:layout>

<script>
 function tirarDado() {
    return Math.floor(Math.random() * 7);
}

var dado = tirarDado();
console.log(dado);

</script>

<script>

    function guardarIdFicha(){
        
    }
    
</script>