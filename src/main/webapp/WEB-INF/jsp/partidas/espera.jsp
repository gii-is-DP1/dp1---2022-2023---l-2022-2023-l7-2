<%@ page import="org.springframework.samples.petclinic.ocachis.partida.TipoEstadoPartida" %>
    <%@ page session="false" trimDirectiveWhitespaces="true" %>
        <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
                        <%@ page contentType="text/html; charset=UTF-8" %>

                            <petclinic:layout pageName="espera" title="Sala de espera">

                                <c:choose>
                                    <c:when
                                        test="${partidaParchis != null &&  partidaParchis.estado==TipoEstadoPartida.CREADA}">
                                        <h2>Estas en espera a que empiece la partida...</h2>
                                        <h2>Codigo Partida: <c:out value="${partidaParchis.codigoPartida}"></c:out>
                                        </h2>
                                    </c:when>
                                    <c:when
                                        test="${partidaOca != null &&  partidaOca.estado==TipoEstadoPartida.CREADA}">
                                        <h2>Estas en espera a que empiece la partida...</h2>
                                        <h2>Codigo Partida: <c:out value="${partidaOca.codigoPartida}"></c:out>
                                        </h2>
                                    </c:when>
                                    <c:otherwise>
                                        <h2>La partida esta en curso</h2>
                                    </c:otherwise>
                                </c:choose>

                                <table id="jugadoresTable" class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Jugadores</th>
                                            <th>Color</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${jugadores}" var="jugadores">
                                            <tr>
                                                <td>
                                                    <c:out value="${jugadores.usuario.user.username}" />
                                                </td>
                                                <td>
                                                    <c:out value="${jugadores.color}" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                                
                                <c:choose>
                                    
                                    <c:when test="${partidaParchis != null}">                 
                                        <c:choose>
                                            <c:when test="${partidaParchis.estado==TipoEstadoPartida.CREADA}">
                                                <c:if
                                                    test="${partidaParchis.jugadores.size()>=2 && usuarioAutenticado.id == partidaParchis.jugadores.get(0).getUsuario().getId()}">
                                                    <spring:url value="/partida/parchis/{partidaParchisId}/empezar"
                                                        var="parchisStartUrl">
                                                        <spring:param name="partidaParchisId"
                                                            value="${partidaParchis.id}" />
                                                    </spring:url>
                                                    <a href="${fn:escapeXml(parchisStartUrl)}"
                                                        class="btn btn-default">Empezar Partida</a>
                                                </c:if>

                                                <spring:url value="/partida/parchis/{partidaParchisId}/abandonar"
                                                    var="parchisAbandonarUrl">
                                                    <spring:param name="partidaParchisId"
                                                        value="${partidaParchis.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(parchisAbandonarUrl)}"
                                                    class="btn btn-default">Abandonar Partida</a>
                                            </c:when>

                                            <c:when test="${partidaParchis.estado==TipoEstadoPartida.JUGANDO}">
                                                <spring:url value="/partida/parchis/{partidaParchisId}/jugar"
                                                    var="parchisJugarUrl">
                                                    <spring:param name="partidaParchisId"
                                                        value="${partidaParchis.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(parchisJugarUrl)}" class="btn btn-default">Ir a
                                                    la partida</a>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
    
                                    <c:when test="${partidaOca != null}">
                                        <c:choose>
                                            <c:when test="${partidaOca.estado==TipoEstadoPartida.CREADA}">
                                                <c:if
                                                    test="${partidaOca.jugadores.size()>=2 && usuarioAutenticado.id == partidaOca.jugadores.get(0).getUsuario().getId()}">
                                                    <spring:url value="/partida/oca/{partidaOcaId}/empezar" var="ocaStartUrl">
                                                        <spring:param name="partidaOcaId" value="${partidaOca.id}" />
                                                    </spring:url>
                                                    <a href="${fn:escapeXml(ocaStartUrl)}"
                                                        class="btn btn-default">Empezar
                                                        Partida</a>
                                                </c:if>

                                                <spring:url value="/partida/oca/{partidaOcaId}/abandonar"
                                                    var="ocaAbandonarUrl">
                                                    <spring:param name="partidaOcaId" value="${partidaOca.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(ocaAbandonarUrl)}"
                                                    class="btn btn-default">Abandonar Partida
                                                </a>
                                            </c:when>

                                  
                                            <c:when test="${partidaOca.estado == TipoEstadoPartida.JUGANDO}">
                                                <spring:url value="/partida/oca/{partidaOcaId}/jugar" var="ocaJugarUrl">
                                                    <spring:param name="partidaOcaId" value="${partidaOca.id}" />
                                                </spring:url>
                                                <a href="${fn:escapeXml(ocaJugarUrl)}" class="btn btn-default"> Ir a la
                                                    partida
                                                </a>
                                            </c:when>
                                        </c:choose>
                                    </c:when>
                              
                                </c:choose>
                             

                            </petclinic:layout>