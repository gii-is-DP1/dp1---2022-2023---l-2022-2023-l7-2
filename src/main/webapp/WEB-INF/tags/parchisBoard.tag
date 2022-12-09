<%@ attribute name="tablero" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.ocachis.partida.PartidaParchis"
 description="parchisBoard to be rendered" %>

 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


 <div class="row">
    <div class="col-auto align-self-center">
        <canvas id="canvas" width="700" height="700"></canvas>
    </div>  
 </div>
    <img id="source" src="/resources/images/Parchis.jpg" style="display:none">
    <!--<c:forEach items="${tablero.jugadores}" var="jugador">
        <!--${jugador}-->
        <c:choose>
            <c:when test="${jugador.color eq 'ROJO'}">
                <c:forEach items="${jugador.fichasParchis}" var="fichaParchis">
                <img id="FichaRoja" src="/resources/images/FichaRoja.jpg" data-x=0 data-y=0 style="display:hidden">
                <!--rojo: ${fichaParchis.getCoordenadas().getX()} - ${fichaParchis.getCoordenadas().getY()}-->
                </c:forEach>
            </c:when>
            <c:when test="${jugador.color eq 'AMARILLO'}">
                <c:forEach items="${jugador.fichasParchis}" var="fichaParchis">
                <img id="FichaAmarilla" src="/resources/images/FichaAmarilla.jpg" data-x=0 data-y=0 style="display:hidden">
                <!--amarillo: ${fichaParchis.getCoordenadas().getX()} - ${fichaParchis.getCoordenadas().getY()}-->
                </c:forEach>
            </c:when>
            <c:when test="${jugador.color eq 'VERDE'}">
                <c:forEach items="${jugador.fichasParchis}" var="fichaParchis">
                <img id="FichaVerde" src="/resources/images/FichaVerde.jpg" data-x=0 data-y=0 style="display:hidden">
                <!--verde: ${fichaParchis.getCoordenadas().getX()} - ${fichaParchis.getCoordenadas().getY()}-->
                </c:forEach>
            </c:when>
           <c:when test="${jugador.color eq 'AZUL'}">
                <c:forEach items="${jugador.fichasParchis}" var="fichaParchis">
                <img id="FichaAzul" src="/resources/images/FichaAzul.jpg" data-x=0 data-y=0 style="display:hidden">
                <!--azul: ${fichaParchis.getCoordenadas().getX()} - ${fichaParchis.getCoordenadas().getY()}-->
                </c:forEach>
            </c:when>
        </c:choose>
   
    </c:forEach>-->
    


    <script>
        function drawBoard(){ 
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            
            var image = document.getElementById('source');
            ctx.drawImage(image, 0, 0, 700, 700);

         var fichaRoja = document.getElementById("FichaRoja");
            ctx.drawImage(fichaRoja,
                            fichaRoja.getAttribute("data-x"),
                            fichaRoja.getAttribute("data-y"),
                            24,24);

            var fichaAmarilla = document.getElementById("FichaAmarilla");
            ctx.drawImage(fichaAmarilla,
                            fichaAmarilla.getAttribute("data-x"),
                            fichaAmarilla.getAttribute("data-y"),
                            24,24);

            var fichaVerde = document.getElementById("FichaVerde");
            ctx.drawImage(fichaVerde,
                            fichaVerde.getAttribute("data-x"),
                            fichaVerde.getAttribute("data-y"),
                            24,24);

            var fichaAzul = document.getElementById("FichaAzul");
            ctx.drawImage(fichaAzul,
                            fichaAzul.getAttribute("data-x"),
                            fichaAzul.getAttribute("data-y"),
                            24,24);   
        }

            
        window.onload =drawBoard();
        </script> 