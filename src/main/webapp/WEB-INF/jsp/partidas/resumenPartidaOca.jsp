<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ page import="org.springframework.samples.petclinic.model.Color"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<petclinic:layout pageName="resumen" title="Final Partida Oca">
    
    <h2>EL GANADOR HA SIDO: ${partidaOca.ganador.user.username}</h2>
    <h1> La partida ha durado: <c:out value="${partidaOca.duracion}"></c:out> minutos.</h1>
    
    <h1>
        Historia:
    </h1>
    ${partidaOca.printLog()}
</petclinic:layout>