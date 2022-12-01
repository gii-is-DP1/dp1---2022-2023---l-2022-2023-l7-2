<%@ page session="false" trimDirectiveWhitespaces="true" %>

<%@ page import="org.springframework.samples.petclinic.model.Color"%>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>




<petclinic:layout pageName="home" title="Jugando a la Oca">
    <petclinic:ocaBoard tablero="${partidaOca}"></petclinic:ocaBoard>
    <span id="tirarDado" class="btn btn-default" onclick="tirarDado()">Tirar dado</span>
    <c:out value="${dado}"></c:out>
    </petclinic:layout>

<script>
    let res=null;
    function tirarDado(){
        //res = $.get("/session/rolldices").responseText;//
        $.ajax(
    {
        type: "GET",
        url: '/session/rolldices',
        dataType: "json",
        success: function(result) {
            res = result;
        },
        error: function(x, e) {

        }
    });
    if(res === null){
        alert("Vuelva a lanzar el dado");
    }else{
        alert(res);
    }
    //alert(res);
    //console.log(res);
    }
    
</script>