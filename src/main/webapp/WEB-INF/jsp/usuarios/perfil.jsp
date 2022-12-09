<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page import="org.springframework.samples.petclinic.ocachis.usuario.Usuario"%>

<petclinic:layout pageName="usuarios" title="Mi perfil - ${usuario.getUser().getUsername()}">
       <h1><center>${usuario.getUser().getUsername()}</center></h1>
    <table id="partidasTable" class="table table-striped">
        <tr>
            <th>Nombre</th>
            <td>${usuario.getNombre()}</td>
        </tr>
        <tr>
            <th>Apellido</th>
            <td>${usuario.getApellido()}</td>
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
                <td>${usuario.getEstadisticas().getOcaPartidasGanadas()}</td>
                <td>${usuario.getEstadisticas().getParchisPartidasGanadas()}</td>
            </tr>
            <tr>
                <th>Partidas Jugadas</th>
                <td>${usuario.getEstadisticas().getOcaPartidasJugadas()}</td>
                <td>${usuario.getEstadisticas().getParchisPartidasJugadas()}</td>
            </tr>
            <tr>
                <th>Duracion total</th>
                <td>${usuario.getEstadisticas().getOcaDuracionTotal()}</td>
                <td>${usuario.getEstadisticas().getParchisDuracionTotal()}</td>
            </tr>
            <tr>
                <th>Duracion maxima</th>
                <td>${usuario.getEstadisticas().getOcaDuracionMaxima()}</td>
                <td>${usuario.getEstadisticas().getParchisDuracionMaxima()}</td>
            </tr>
            <tr>
                <th>Duracion minima</th>
                <td>${usuario.getEstadisticas().getOcaDuracionMinima()}</td>
                <td>${usuario.getEstadisticas().getParchisDuracionMinima()}</td>
            </tr>
            <tr>
                <th>Duracion media</th>
                <td>${usuario.getEstadisticas().getOcaDuracionMedia()}</td>
                <td>${usuario.getEstadisticas().getParchisDuracionMedia()}</td>
            </tr>
            <tr>
                <th>Caido en Muerte</th>
                <td>${usuario.getEstadisticas().getOcaVecesCaidoEnMuerte()}</td>
                <td> - </td>
            </tr>
            <tr>
                <th>Fichas comidas</th>
                <td> - </td>
                <td>${usuario.getEstadisticas().getParchisFichasComidas()}</td>
            </tr>


        </tbody>
    </table>
   
    
</petclinic:layout>
