<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ page import="org.springframework.samples.petclinic.model.Color" %>
        <%@ page contentType="text/html; charset=UTF-8" %>

            <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

                                    <petclinic:layout pageName="game" title="Jugando a la Oca">

                                        <h3>vista: ${modo}</h3>
                                        <h1>Es el turno del jugador ${partidaOca.colorJugadorActual}</h1>

                                        <c:if test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}">
                                            <div id="divAlerta10SecRestantes" class="alert alert-danger" role="alert" style="display:none;"></div>
                                        </c:if>
                                        
                                        <div style="display: flex; flex-wrap: wrap; width: 75vw;">
                                            <div style="width: 810px;">
                                                <petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>


                                            </div>
                                            <div style="width: 30%;">
                                                <h2>
                                                    <c:out value="JUGADORES"></c:out>
                                                </h2> <br>

                                                <c:forEach items="${partidaOca.jugadores}" var="jugador">
                                                    <petclinic:jugador jugador="${jugador}"></petclinic:jugador>
                                                    <br>
                                                </c:forEach>

                                                <br>
                                                <h3>Tiempo restante: <span id="countdown"></span></h3>

                                                <c:if
                                                    test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}">
                                                   

                                                    <form:form class="form-horizontal" id="tirar-dado-form"
                                                        method="post" action="/partida/oca/${partidaOca.id}/jugar">
                                                        <button class="btn btn-default"
                                                            onClick="this.form.submit(); this.disabled=true; this.innerText='Jugando...'; ">Tirar
                                                            dado</button>
                                                    </form:form>
                                                </c:if>

                                                <c:if
                                                    test="${not empty jugadorAutenticado && jugadorAutenticado.color != partidaOca.colorJugadorActual}">
                                                    <button id="tirarDado" class="btn btn-default" disabled>Tirar
                                                        dado</button>
                                                </c:if>



                                            </div>

                                        </div>




                                        <div id="fechaHoraUltimoMovimiento"
                                            data-fechaHoraUltimoMovimiento="${partidaOca.getFechaHoraUltimoMovimiento()}">
                                        </div>




                                        <table class="table table-striped">
                                            <tr>
                                                <td style="width: 50%;">
                                                    <h1>
                                                        <center>Resumen:</center>
                                                    </h1>
                                                    ${partidaOca.printLog()}
                                                </td>
                                                <td style="width: 50%;">
                                                    <h1>
                                                        <center>Chat:</center>
                                                    </h1>
                                                    <div style="width: 35em;">
                                                        <div style="height:100px; overflow:auto">
                                                            ${partidaOca.printChatOca()}
                                                        </div>
                                                        <spring:url value="/partida/oca/{partidaOcaId}/jugar"
                                                            var="chatOcaUrl">
                                                            <spring:param name="partidaOcaId"
                                                                value="${partidaOca.id}" />


                                                        </spring:url>
                                                        <form class="form-inline"
                                                            th:action="@{${fn:escapeXml(chatOcaUrl)}}">
                                                            <center><input type="text" name="mensaje" id="mensaje"
                                                                    th:value="${mensaje}"
                                                                    placeholder="Introduzca mensaje" required>
                                                                <input type="submit" class="btn btn-primary mb-2"
                                                                    value="Enviar">
                                                            </center>
                                                        </form>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>



                                        <c:if test="${jugadorAutenticado.color == partidaOca.colorJugadorActual}">
                                            <form id="pasarTurnoForm" method="post"
                                                action="/partida/oca/${partidaOca.id}/jugar">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                    value="${_csrf.token}" />
                                            </form>
                                        </c:if>

                                        <script>


                                            function pasarTurno() {
                                                let pasarTurnoFormDOM = document.getElementById("pasarTurnoForm");
                                                pasarTurnoFormDOM.submit();
                                            }


                                            let fechaHoraUltimoMovimiento = parseInt(document.getElementById("fechaHoraUltimoMovimiento").getAttribute("data-fechaHoraUltimoMovimiento"));
                                            console.log(fechaHoraUltimoMovimiento);

                                            let now = Date.now();

                                            console.log(now);
                                            let tiempoDelTurnoPasado = (now - fechaHoraUltimoMovimiento) / 1000 + 3600;
                                            let tiempoDelTurnoRestante = 30 - tiempoDelTurnoPasado;
                                            let tiempoDelTurnoRestanteMillis = tiempoDelTurnoRestante * 1000;

                                            if (tiempoDelTurnoRestanteMillis > 0) {
                                                setTimeout(pasarTurno, tiempoDelTurnoRestanteMillis);

                                                if (tiempoDelTurnoRestanteMillis > 10000) {
                                                    setTimeout(function () {
                                                        document.getElementById("divAlerta10SecRestantes").innerText = "Le quedan 10 segundos. Si no juega se pasará su turno";
                                                        document.getElementById("divAlerta10SecRestantes").style.display = "block";

                                                    }, tiempoDelTurnoRestanteMillis - 10000);
                                                } else {
                                                    document.getElementById("divAlerta10SecRestantes").innerText = "Le quedan menos de 10 segundos. Si no juega se pasará su turno";
                                                    document.getElementById("divAlerta10SecRestantes").style.display = "block";
                                                }
                                            }

                                            var totalTime = parseInt(tiempoDelTurnoRestante);
                                            updateClock()

                                            function updateClock() {
                                                document.getElementById('countdown').innerHTML = totalTime;
                                                if (totalTime == 0) {
                                                    console.log('Final');
                                                } else {
                                                    totalTime -= 1;
                                                    setTimeout("updateClock()", 1000);
                                                }
                                            }



                                        </script>
                                    </petclinic:layout>