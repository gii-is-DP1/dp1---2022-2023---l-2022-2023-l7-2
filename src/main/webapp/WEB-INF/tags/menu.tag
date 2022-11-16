<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand"
				href="<spring:url value="/" htmlEscape="true" />"><span></span></a>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#main-navbar">
				<span class="sr-only"><os-p>Toggle navigation</os-p></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>
<%--
				<petclinic:menuItem active="${name eq 'owners'}" url="/owners/find"
					title="find owners">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Find owners</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'vets'}" url="/vets"
					title="veterinarians">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Veterinarians</span>
				</petclinic:menuItem>
--%>
				<sec:authorize access="hasAuthority('admin')">

					<petclinic:menuItem active="${name eq 'logro'}"
						url="/logro/listLogros" title="Logros">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Logros</span>
					</petclinic:menuItem>


					<petclinic:menuItem active="${name eq 'admin'}"
						url="/admin/listPartidas" title="Admin" dropdown="${true}">
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/admin/listPartidas/" />">Listado
									Partidas</a></li>
							<li class="divider"></li>
							<li><a href="<c:url value="/admin/listUsuarios" />">Listado
									Usuarios <span class="glyphicon glyphicon-certificate"
									aria-hidden="true"></span>
							</a></li>
						</ul>
					</petclinic:menuItem>


<%-- 
					<petclinic:menuItem active="${name eq 'error'}" url="/oups"
						title="trigger a RuntimeException to see how it is handled">
						<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
						<span>Error</span>
					</petclinic:menuItem>
--%>
				</sec:authorize>
			</ul>
			
			




			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Iniciar sesión</a></li>
					<li><a href="<c:url value="/usuario/nuevo" />">Registrarse</a></li>
				</sec:authorize>			
				
				<sec:authorize access="isAuthenticated()">						
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value="/" />">Editar usuario</a></li>
							<li class="divider"></li>
							<li><a href="<c:url value="/logout" />">Logout</a></li>
						</ul>
					
						</li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>
