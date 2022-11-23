<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<petclinic:layout pageName="home" title="Jugando a la Oca">
    <petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>
</petclinic:layout>