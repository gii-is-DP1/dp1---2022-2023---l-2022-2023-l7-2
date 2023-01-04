<%@ page import="org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
        <%@ page session="false" trimDirectiveWhitespaces="true" %>
            <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                            <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                                <petclinic:layout pageName="estadisticasGlobales"
                                    title="Estadisticas y Ranking de jugadores">

                                    <h2>
                                        <center>Estadisticas</center>
                                    </h2>

                                    <table id="estadisticasTable" class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>OCA</th>
                                                <th>PARCHIS</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th>Partidas Jugadas</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaPartidasJugadas}">
                                                    </c:out>
                                                </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisPartidasJugadas}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Duracion total</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaDuracionTotal}">
                                                    </c:out>
                                                </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisDuracionTotal}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Duracion maxima</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaDuracionMaxima}">
                                                    </c:out>
                                                </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisDuracionMaxima}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Duracion minima</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaDuracionMinima}">
                                                    </c:out>
                                                </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisDuracionMinima}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Duracion media</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaDuracionMedia}">
                                                    </c:out>
                                                </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisDuracionMedia}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                            <tr>
                                                <th>Caido en Muerte</th>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.ocaVecesCaidoEnMuerte}">
                                                    </c:out>
                                                </td>
                                                <td> - </td>
                                            </tr>
                                            <tr>
                                                <th>Fichas comidas</th>
                                                <td> - </td>
                                                <td>
                                                    <c:out
                                                        value="${estadisticaGlobal.estadisticasGlobales.parchisFichasComidas}">
                                                    </c:out>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>


                                    <div style="display: block;">
                                        <h2>Ranking Partidas Ganadas</h2>
                                        <table class="table table-striped">
                                            <thead>
                                                <th>OCA</th>
                                                <th>PARCHIS</th>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <table>
                                                            <tbody>
                                                                <c:forEach
                                                                    items="${estadisticaGlobal.ocaRankingJugadores}"
                                                                    var="usuario">
                                                                    <tr>
                                                                        <td>
                                                                            <c:out value="${usuario.user.username}">
                                                                            </c:out>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <c:out
                                                                                value="${usuario.estadisticas.ocaPartidasGanadas}">
                                                                            </c:out>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </td>

                                                    <td>
                                                        <table>
                                                            <tbody>
                                                                <c:forEach
                                                                    items="${estadisticaGlobal.parchisRankingJugadores}"
                                                                    var="usuario">
                                                                    <tr>
                                                                        <td>
                                                                            <c:out value="${usuario.user.username}">
                                                                            </c:out>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <c:out
                                                                                value="${usuario.estadisticas.parchisPartidasGanadas}">
                                                                            </c:out>
                                                                        </td>
                                                                    </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </td>

                                                </tr>
                                            </tbody>
                                        </table>

                                        <br>
                                        <br>

                                        <h2>Ranking Fichas Comidas y Muertes</h2>

                                        <table id="rankingParchis" class="table table-striped">
                                            <thead>
                                                <tr>

                                                    <th>Fichas Comidas</th>
                                                    <th>Veces Caido en Muerte</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <c:forEach items="${estadisticaGlobal.parchisFichasComidas}"
                                                            var="usuario">
                                                            <table>
                                                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <c:out value="${usuario.user.username}"></c:out>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <c:out
                                                                            value="${usuario.estadisticas.parchisFichasComidas}">
                                                                        </c:out>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                            </table>
                                                        </c:forEach>
                                                    </td>

                                                    <td>
                                                        <c:forEach items="${estadisticaGlobal.ocaVecesCaidoEnMuerte}"
                                                            var="usuario">

                                                            <table>
                                                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <c:out value="${usuario.user.username}"></c:out>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        <c:out
                                                                            value="${usuario.estadisticas.ocaVecesCaidoEnMuerte}">
                                                                        </c:out>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                            </table>
                                                        </c:forEach>

                                                    </td>
                                                </tr>


                                            </tbody>
                                        </table>
                                    </div>

                                </petclinic:layout>