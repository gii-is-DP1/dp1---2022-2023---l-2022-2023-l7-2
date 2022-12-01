<%@ page session="false" trimDirectiveWhitespaces="true" %>

<%@ page import="org.springframework.samples.petclinic.model.Color"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>




<petclinic:layout pageName="home" title="Jugando a la Oca">
    <c:out value="${now}"></c:out>
    <petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>
    <span id="tirarDado" class="btn btn-default" onclick="tirarDado()">Tirar dado</span>
    <c:out value="${dado}"></c:out>


    
    ${jugadorAutenticado}
    
    
    partidaOca: ${partidaId}

    user:
    ${uId}
    
    
    <c:if test="jugadorAutenticado.color==partidaOca.jugadorActual"> 
            <form:form method="post" action="/sala/${partidaOca.id}/jugarOca" >
                <button >tirar dado</button>
            </form:form>
            
    </c:if>



    
    </petclinic:layout>
