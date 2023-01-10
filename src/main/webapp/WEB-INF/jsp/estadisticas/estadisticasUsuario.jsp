<%@ page import="org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="partidas" title="Lista de partidas">
   
   
   
    <h2>Estadisticas <c:out value=" ${apodoUsuario}"></c:out></h2>
    
    <table id="partidasTable" class="table table-striped">
        <tr>
            <th>Estadisticas </th>
            <th> </th>
        </tr>
        <tr>
            <td>Partidas Parchis Jugadas</td>
            <td><c:out value="${estadisticas.parchisPartidasJugadas}"></c:out></td>
        </tr>
        <tr>
            <td>Partidas Parchis Ganadas</td>
            <td><c:out value="${estadisticas.parchisPartidasGanadas}"></c:out></td>
        </tr>
        <tr>
            <td>Fichas Comidas Parchis</td>
            <td><c:out value="${estadisticas.parchisFichasComidas}"></c:out></td>
        </tr>
        <tr>
            <td>Partidas Jugadas Oca</td>
            <td><c:out value="${estadisticas.ocaPartidasJugadas}"></c:out></td>
        </tr>
        <tr>
            <td>Partidas Oca Ganadas</td>
            <td><c:out value="${estadisticas.ocaPartidasGanadas}"></c:out></td>
        </tr>
        <tr>
            <td>Caidas en muerte</td>
            <td><c:out value="${estadisticas.ocaVecesCaidoEnMuerte}"></c:out></td>
        </tr>
        <tr>
            <td>Total Partida Jugadas</td>
            <td><c:out value="${estadisticas.ocaPartidasJugadas + estadisticas.parchisPartidasJugadas}"></c:out></td>
        </tr>
        <tr>
            <td>Total Partidas Ganadas</td>
            <td><c:out value="${estadisticas.ocaPartidasGanadas + estadisticas.parchisPartidasGanadas}"></c:out></td>
        </tr>


    </table>
   
</petclinic:layout>