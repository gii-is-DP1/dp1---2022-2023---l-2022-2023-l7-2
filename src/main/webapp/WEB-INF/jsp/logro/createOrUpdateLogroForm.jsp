<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="logros">
    <h2>
        <c:if test="${logro['new']}">New </c:if> Logro
    </h2>
    <form:form modelAttribute="logro" class="form-horizontal" id="add-logro-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Descripción" name="descripcion"/>
            <petclinic:inputField label="Partida Jugadas Parchís" name="estadisticasACumplir.parchisPartidasJugadas"/>
            <petclinic:inputField label="Partidas Ganadas Parchís" name="estadisticasACumplir.parchisPartidasGanadas"/>
            <petclinic:inputField label="Tiempo Jugando al Parchís" name="estadisticasACumplir.parchisDuracionTotal"/>
            <petclinic:inputField label="Fichas Comidas Parchís" name="estadisticasACumplir.parchisFichasComidas"/>
            <petclinic:inputField label="Partidas Jugadas Oca" name="estadisticasACumplir.ocaPartidasJugadas"/>
            <petclinic:inputField label="Partidas Ganadas Oca" name="estadisticasACumplir.ocaPartidasGanadas"/>
            <petclinic:inputField label="Tiempo Jugando a la Oca" name="estadisticasACumplir.ocaDuracionTotal"/>
            <petclinic:inputField label="Caidas en Muerte Oca" name="estadisticasACumplir.ocaVecesCaidoEnMuerte"/>
           
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${logro['new']}">
                        <button class="btn btn-default" type="submit">Crear Logro</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar Logro</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
