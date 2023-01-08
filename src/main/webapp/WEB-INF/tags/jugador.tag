
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ attribute name="jugador" required="true" rtexprvalue="true" type="org.springframework.samples.petclinic.ocachis.jugador.Jugador"%>

<c:if test="${jugador.color eq 'ROJO'}">
    <c:set var = "color"  value = "d9534f"/>
</c:if>

<c:if test="${jugador.color eq 'AMARILLO'}">
    <c:set var = "color"  value = "e0d500"/>
</c:if>

<c:if test="${jugador.color eq 'VERDE'}">
    <c:set var = "color"  value = "5cb85c"/>
</c:if>

<c:if test="${jugador.color eq 'AZUL'}">
    <c:set var = "color"  value = "5bc0de"/>
</c:if>


<c:if test="${not empty jugador.fichaOca}">
        <div class="alert" role="alert" style="background-color: #${color}; width: fit-content; padding:1.5em">
                <c:out value="${jugador.usuario.user.username}"> </c:out>
                <c:out value="Casilla: ${jugador.fichaOca.casillaActual.numero}"></c:out>
</c:if>

<c:if test="${empty jugador.fichaOca}">
        <div class="alert" role="alert" style="background-color: #${color}; width: fit-content;">
                <c:out value="${jugador.usuario.user.username}"> </c:out>
</c:if>
 
</div>