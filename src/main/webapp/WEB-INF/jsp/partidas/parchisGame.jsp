<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<petclinic:layout pageName="game" title="Jugando al parchis">
    
    <h1>vista: ${modo}</h1>
    <h1>   El dado ha sacado el numero: ${dado}    </h1>
    <h1>Es el turno del jugador ${partidaParchis.colorJugadorActual}</h1>
   
<petclinic:parchisBoard tablero="${partidaParchis}"></petclinic:parchisBoard>
   
    <br>
    Jugador autenticado: ${jugadorAutenticado}
 
    Color jugador autenticado : ${jugadorAutenticado.color}
    <br>
    colorJugador actual : ${partidaParchis.colorJugadorActual}
    <br>
    ${partidaParchis.jugadores.get(0).fichasParchis}
    <br>
    ${partidaParchis.jugadores.get(1).fichasParchis}
    <br>
    ${partidaParchis.jugadores.get(2).fichasParchis}

  

    <c:if test="${jugadorAutenticado.color == partidaParchis.colorJugadorActual}"> 
        
        <form:form class="form-horizontal" id="tirar-dado-form"
            method="post" action="/sala/${partidaParchis.id}/playParchis">
            <button class="btn btn-default">Tirar dado</button>

            <c:forEach  items="${jugadorAutenticado.fichasParchis}" var="fichaJugador">
                <c:if test="${fichasQueSePuedenMover.contains(fichaJugador)}">
                    <form:hidden path="ficha" value="${fichaJugador}"></form:hidden>
                    <!--<input type="hidden" name="ficha" id="ficha" value="${fichaJugador}">-->
                    <button class="btn btn-default">${fichaJugador.id}</button>
                </c:if>
            </c:forEach>
       
        </form:form>
            
    </c:if>
    


    <br>
    <br>
    dado: ${dado}
    fichasQueSePuedenMover: ${fichasQueSePuedenMover}

     

    <h1>${ficha.id}</h1>
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