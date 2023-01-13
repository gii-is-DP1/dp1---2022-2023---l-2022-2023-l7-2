<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%-- Esta vista requiere de los siguientes parametros:
	-usuario, de tipo Usuario: es el usuario que se edita. Si se va a crear un usuario hay que pasarlo vacio como new Usuario()
--%>


<c:if test="${usuario['new']}"><c:set var = "title"  value = "Nuevo Usuario"/></c:if>
<c:if test="${!usuario['new']}"><c:set var = "title"  value = "Editar Usuario"/></c:if>

<petclinic:layout pageName="usuarios" title="${title}">
    <h2> 
        <c:if test="${usuario['new']}">Nuevo </c:if> Usuario
    </h2>
   
    
    <form:form modelAttribute="usuario" class="form-horizontal" id="add-usuario-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Apellido" name="apellido"/>
           <c:if test="${usuario['new']}">
           <petclinic:inputField label="Username" name="user.username"/>


           <spring:bind path="user.password">
                <c:set var="cssGroup" value="form-group ${status.error ? 'has-error' : '' }"/>
                <c:set var="valid" value="${not status.error and not empty status.actualValue}"/>
                <div class="${cssGroup}">
                    <label class="col-sm-2 control-label">Contraseña</label>
                    <div class="col-sm-10">
                        <form:password class="form-control" path="user.password"/>
                        <c:if test="${valid}">
                            <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
                        </c:if>
                        <c:if test="${status.error}">
                            <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
                            <span class="help-inline">${status.errorMessage}</span>
                        </c:if>
                    </div>
                </div>
            </spring:bind>
           </c:if>            
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
    
    <c:if test="${usuario['new']}">
	    <div class="row">
	    	<div class="col-auto align-self-center">Ya tienes una cuenta?</div>
	   	</div>
	   	<div class="row">
	    	<div class="col-auto align-self-center"><a href="<c:url value="/login" />">Iniciar sesión</a></div>
	   	</div>
   	</c:if>
</petclinic:layout>
