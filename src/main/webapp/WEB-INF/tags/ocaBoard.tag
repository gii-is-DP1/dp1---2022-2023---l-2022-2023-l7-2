
<%@ attribute name="tablero" required="false" rtexprvalue="true" type="org.springframework.samples.petclinic.ocachis.partida.PartidaOca"
 description="ocaBoard to be rendered" %>
    <canvas id="canvas" width="800" height="800"></canvas>
    <img id="source" src="${tablero.tableroURL}" style="display:none">
    

    <script>
        function drawBoard(){ 
            var canvas = document.getElementById("canvas");
            var ctx = canvas.getContext("2d");
            var image = document.getElementById('source');
            ctx.drawImage(image, 0, 0, 800, 800);     
        }
        window.onload =drawBoard();
        </script> 
