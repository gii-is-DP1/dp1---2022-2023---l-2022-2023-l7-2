<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<petclinic:layout pageName="error">

    <spring:url value="/resources/images/pets.jpg" var="petsImage"/>
    <img src="${petsImage}"/>
    <H1>ERROR 404</H1>
    <h2>La página que busca no existe</h2>
</petclinic:layout>
