<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="crearPartida">
   
    <form:form modelAttribute="proceso" class="form-horizontal" id="add-procesarPartidaForm-form">
        <div class="form-group has-feedback">
           
            <label for="maxJugadores">Num Jugadores:</label>
            <select name="numJugador">
                <option selected value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
            </select>
            <label for ="tipo">Juego:</label>
            <select name="tipo">
                <option selected value="parchis">Parchis</option>
                <option value="oca">Oca</option>
            </select>
            
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Crear Partida</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>
