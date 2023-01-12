<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<petclinic:layout pageName="error" title="No tiene accesso">

    <spring:url value="/resources/images/pets.jpg" var="petsImage"/>
    <img src="${petsImage}"/>

    <h2>Access has been denied</h2>

    
</petclinic:layout>
