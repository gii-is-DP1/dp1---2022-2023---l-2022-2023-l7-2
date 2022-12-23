<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="amigos" title="solicitud - amigos">
    <h2>Amigos</h2>

    <table id="amigosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Apodo</th>
            <th style="width: 200px;">Nombre</th>
            <th style="width: 120px">Apellido</th>
           
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${amigos}" var="amigo">
            <tr>
                <td>
                    
                   <c:out value="${amigo.user.username}"/></a>
                </td>
                <td>
                    <c:out value="${amigo.nombre}"/>
                </td>
                <td>
                    <c:out value="${amigo.apellido}"/>
                </td>
                
      
<!--
                <td> 
                    <c:out value="${owner.user.username}"/> 
                </td>
                <td> 
                   <c:out value="${owner.user.password}"/> 
                </td> 
-->
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>