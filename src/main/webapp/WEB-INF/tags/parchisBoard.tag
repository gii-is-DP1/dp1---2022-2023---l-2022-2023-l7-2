<%@ attribute name="tablero" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.ocachis.partida.PartidaParchis"
 description="parchisBoard to be rendered" %>

<%@ attribute name="fichasQueSePuedenMover" required="false" type="java.util.ArrayList" %>
<%@ attribute name="dado" required="false" %>

<%@ attribute name="jugadorAutenticado" required="false" type="org.springframework.samples.petclinic.ocachis.jugador.Jugador" %>

 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<style>
.zoom {
  transition: transform .2s; /* Animation */
}

.zoom:hover {
  transform: scale(1.5); /* (150% zoom - Note: if the zoom is too large, it will go outside of the viewport) */
}
</style>

 <div class="row">
    <div class="col-auto align-self-center">
        <canvas id="canvas" width="700" height="700" onclick="canvasClicked(this,event)"></canvas>
    </div>  
 </div>
    <img id="source" src="/resources/images/Parchis.jpg" style="display:none">

<c:if test="${not empty fichasQueSePuedenMover}"> 
    <c:if test="${not empty jugadorAutenticado}">
        <c:forEach items="${jugadorAutenticado.fichasParchis}" var="ficha" varStatus="loop">
            <c:if test="${fichasQueSePuedenMover.contains(ficha)}">
                <form id="formMoverFicha${loop.count}" modelAttribute="MoverFichaParchisForm"
                method="POST" action="/partida/parchis/${tablero.id}/jugar">
                    <input type="hidden" name="jugadorId" value="${jugadorAutenticado.id}">
                    <input type="hidden" name="fichaId" value="${ficha.id}">
                    <input type="hidden" name="dado" value="${dado}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </c:if>
        </c:forEach>
    </c:if>
</c:if>


    <c:forEach items="${tablero.jugadores}" var="jugador">
        <c:choose>
            <c:when test="${jugador.color eq 'ROJO'}">
                <img id="FichaRoja1" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:none">
                <img id="FichaRoja2" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:none">
                <img id="FichaRoja3" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:none">
                <img id="FichaRoja4" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:none">
            </c:when>
            <c:when test="${jugador.color eq 'AMARILLO'}">
                <img id="FichaAmarilla1" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAmarilla2" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAmarilla3" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAmarilla4" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:none">
            </c:when>
            <c:when test="${jugador.color eq 'VERDE'}">
                <img id="FichaVerde1" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:none">
                <img id="FichaVerde2" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:none">
                <img id="FichaVerde3" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:none">
                <img id="FichaVerde4" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:none">
            </c:when>
           <c:when test="${jugador.color eq 'AZUL'}">
                <img id="FichaAzul1" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAzul2" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAzul3" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:none">
                <img id="FichaAzul4" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:none">
            </c:when>
        </c:choose>
   
    </c:forEach>
    


<script>

        const arrayCentrosFichasConNombre = []
        function canvasClicked(canvas, event) {
                const rect = canvas.getBoundingClientRect()
                const x = event.clientX - rect.left
                const y = event.clientY - rect.top
                console.log("x: " + x + " y: " + y)
                for(ficha of arrayCentrosFichasConNombre){
                    var coordenadas = ficha[0];
                    if(Math.sqrt((coordenadas[0]-x)*(coordenadas[0]-x) + (coordenadas[1]-y)*(coordenadas[1]-y)) < 12){
                        const fichaDOM = document.getElementById(ficha[1]);
                        const formDOM = document.getElementById("formMoverFicha" + ficha[1].charAt(ficha[1].length - 1))
                        formDOM.submit()
                    }
                }
        }    

        function drawBoard(){ 
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            var image = document.getElementById('source');
            ctx.drawImage(image, 0, 0, 700, 700);
            const fichas = [];

            const fichasRojas = ['FichaRoja1', 'FichaRoja2', 'FichaRoja3', 'FichaRoja4'];
            const fichasAmarillas = ['FichaAmarilla1','FichaAmarilla2','FichaAmarilla3','FichaAmarilla4'];
            const fichasVerdes = ['FichaVerde1','FichaVerde2','FichaVerde3','FichaVerde4'];
            const fichasAzules = ['FichaAzul1','FichaAzul2','FichaAzul3','FichaAzul4'];

            if(document.getElementById('FichaRoja1')!==null) fichas.push(fichasRojas);
            if(document.getElementById('FichaAmarilla1')!==null) fichas.push(fichasAmarillas);
            if(document.getElementById('FichaVerde1')!==null) fichas.push(fichasVerdes);
            if(document.getElementById('FichaAzul1')!==null) fichas.push(fichasAzules);
            for(listFichas of fichas){
                for(id of listFichas){
                var ficha = document.getElementById(id);
                ctx.drawImage(ficha,
                        ficha.getAttribute("data-x"),
                        ficha.getAttribute("data-y"),
                        24,24);
                arrayCentrosFichasConNombre.push([[parseInt(ficha.getAttribute("data-x")) + 12, parseInt(ficha.getAttribute("data-y")) + 12], id])
                }
            }
        }      

        function main(){
            drawBoard()
        }
        window.onload = main();
        </script> 