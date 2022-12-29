<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="error" title="No tiene accesso">

    <spring:url value="/resources/images/pets.jpg" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Acceso denegado</h2>

</petclinic:layout>
