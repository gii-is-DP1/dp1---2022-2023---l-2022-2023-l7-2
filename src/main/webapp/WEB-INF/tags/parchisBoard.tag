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
                 
                <img id="FichaRoja1" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaRoja2" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaRoja3" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaRoja4" src="/resources/images/FichaRoja.jpg"          data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:hidden">
                
                <!--
                <img id="FichaRoja1" src="/resources/images/FichaRoja.jpg"          data-x="0" data-y="0"   style="display:hidden">
                <img id="FichaRoja2" src="/resources/images/FichaRoja.jpg"          data-x="0" data-y="100" style="display:hidden">
                <img id="FichaRoja3" src="/resources/images/FichaRoja.jpg"          data-x="0" data-y="150" style="display:hidden">
                <img id="FichaRoja4" src="/resources/images/FichaRoja.jpg"          data-x="0" data-y="200" style="display:hidden">
                --!>
            </c:when>
            <c:when test="${jugador.color eq 'AMARILLO'}">
                <img id="FichaAmarilla1" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAmarilla2" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAmarilla3" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAmarilla4" src="/resources/images/FichaAmarilla2.jpg" data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:hidden">
            </c:when>
            <c:when test="${jugador.color eq 'VERDE'}">
                <img id="FichaVerde1" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaVerde2" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaVerde3" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaVerde4" src="/resources/images/FichaVerde.jpg"        data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:hidden">
            </c:when>
           <c:when test="${jugador.color eq 'AZUL'}">
                <img id="FichaAzul1" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(0).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(0).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAzul2" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(1).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(1).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAzul3" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(2).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(2).getCoordenadas().getY()}" style="display:hidden">
                <img id="FichaAzul4" src="/resources/images/FichaAzul.jpg"          data-x="${jugador.getFichasParchis().get(3).getCoordenadas().getX()}" data-y="${jugador.getFichasParchis().get(3).getCoordenadas().getY()}" style="display:hidden">
            </c:when>
        </c:choose>
   
    </c:forEach>-->
    


    <script>
        function drawBoard(){ 
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            
            var image = document.getElementById('source');
            ctx.drawImage(image, 0, 0, 700, 700);

        const fichasRojas = ['FichaRoja1', 'FichaRoja2', 'FichaRoja3', 'FichaRoja4'];
        const fichasAmarillas = ['FichaAmarilla1','FichaAmarilla2','FichaAmarilla3','FichaAmarilla4'];
        const fichasVerdes = ['FichaVerde1','FichaVerde2','FichaVerde3','FichaVerde4'];
        const fichasAzules = ['FichaAzul1','FichaAzul2','FichaAzul3','FichaAzul4'];

        for(id of fichasRojas){
            var ficha = document.getElementById(id);
            ctx.drawImage(ficha,
                    ficha.getAttribute("data-x"),
                    ficha.getAttribute("data-y"),
                    24,24);
            }

        for(id of fichasAmarillas){
            var ficha = document.getElementById(id);
            ctx.drawImage(ficha,
                    ficha.getAttribute("data-x"),
                    ficha.getAttribute("data-y"),
                    24,24);
            }
        for(id of fichasVerdes){
            var ficha = document.getElementById(id);
            ctx.drawImage(ficha,
                    ficha.getAttribute("data-x"),
                    ficha.getAttribute("data-y"),
                    24,24);
            }
            for(id of fichasAzules){
            var ficha = document.getElementById(id);
            ctx.drawImage(ficha,
                    ficha.getAttribute("data-x"),
                    ficha.getAttribute("data-y"),
                    24,24);
            }
        }      
        window.onload =drawBoard();


        
        </script> 