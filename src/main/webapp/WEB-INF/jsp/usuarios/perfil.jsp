<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.petclinic.ocachis.usuario.Usuario"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="usuarios" title="Mi perfil - ${usuario.getUser().getUsername()}">
       <h1><center>${usuario.getUser().getUsername()}</center></h1>
    <table id="partidasTable" class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td><c:out value="${usuario.getNombre()}"></c:out></td>
        </tr>
        <tr>
            <th>Apellido</th>
            <td><c:out value="${usuario.getApellido()}"></c:out></td>
        </tr>
    </table>

    <h2><center>Estadisticas</center></h2>
    <table id="partidasTable" class="table table-striped">
        <thead>
            <tr>
                <th></th>
                <th>OCA</th>
                <th>PARCHIS</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>Partidas Ganadas</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaPartidasGanadas()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisPartidasGanadas()}"></c:out></td>
            </tr>
            <tr>
                <th>Partidas Jugadas</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaPartidasJugadas()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisPartidasJugadas()}"></c:out></td>
            </tr>
            <tr>
                <th>Duracion total</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaDuracionTotal()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisDuracionTotal()}"></c:out></td>
            </tr>
            <tr>
                <th>Duracion maxima</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaDuracionMaxima()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisDuracionMaxima()}"></c:out></td>
            </tr>
            <tr>
                <th>Duracion minima</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaDuracionMinima()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisDuracionMinima()}"></c:out></td>
            </tr>
            <tr>
                <th>Duracion media</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaDuracionMedia()}"></c:out></td>
                <td><c:out value="${usuario.getEstadisticas().getParchisDuracionMedia()}"></c:out></td>
            </tr>
            <tr>
                <th>Caido en Muerte</th>
                <td><c:out value="${usuario.getEstadisticas().getOcaVecesCaidoEnMuerte()}"></c:out></td>
                <td> - </td>
            </tr>
            <tr>
                <th>Fichas comidas</th>
                <td> - </td>
                <td><c:out value="${usuario.getEstadisticas().getParchisFichasComidas()}"></c:out></td>
            </tr>


        </tbody>
    </table>


    <br>
    <br>

    <h3>Mis logros</h3>

    <table id="logrosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 50px;">Nombre</th>
            <th style="width: 200px">Descripción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usuario.logros}" var="logro">
            <tr>
                <td>
                    <c:out value="${logro.nombre}"></c:out>
                </td>
                <td>
                    <c:out value="${logro.descripcion}"></c:out>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>