<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usuarios">
    <h2>
        <c:if test="${usuario['new']}">Nuevo </c:if> Usuario
    </h2>
   
   
   
   <c:catch var="exception">
   		<c:forEach items="${errors}" var="error">
	    	<petclinic:formError error="${error}"></petclinic:formError>
	    </c:forEach>
   </c:catch>
<c:if test="${not empty exception}">No errors</c:if>


 

    
    <form:form modelAttribute="usuario" class="form-horizontal" id="add-usuario-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellido" name="apellido"/>
            <petclinic:inputField label="Username" name="user.username"/>
            <petclinic:inputField label="Contraseña" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${usuario['new']}">
                        <button class="btn btn-default" type="submit">Crear usuario</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar usuario</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>
