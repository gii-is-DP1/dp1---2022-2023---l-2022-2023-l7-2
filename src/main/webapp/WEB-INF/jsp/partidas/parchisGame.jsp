<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<petclinic:layout pageName="game" title="Jugando al parchis">
    
    <h1>vista: ${modo}</h1>
    <h1>   El dado ha sacado el numero: ${numDado}    </h1>
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
            method="post" action="/sala/${partidaParchis.id}/playOca">
            <button class="btn btn-default">Tirar dado</button>
        </form:form>
            
    </c:if>
    


    <br>
    <br>
    
    <h1>Resumen:</h1>
    
</petclinic:layout>

<script>
 function tirarDado() {
    return Math.floor(Math.random() * 7);
}

var dado = tirarDado();
console.log(dado);

</script>