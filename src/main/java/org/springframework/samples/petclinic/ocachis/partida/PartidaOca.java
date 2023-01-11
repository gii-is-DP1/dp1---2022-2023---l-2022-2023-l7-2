package org.springframework.samples.petclinic.ocachis.partida;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaOca;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class PartidaOca extends Partida{

	
	@OneToMany(cascade = CascadeType.ALL)
	private List<CasillaOca> casillas;
	
	@OneToMany(mappedBy="partidaOca", cascade = CascadeType.ALL)
	private Collection<Jugador> jugadores;

	@ElementCollection
	@CollectionTable(name="chatOca")
	protected List<String> chatOca;

	@ElementCollection
	@CollectionTable(name="log")
	protected List<String> log = inicializarLog();

	public void addMensaje(String mensaje,Jugador jugador){
		String mensajeFinal ="";
		String username = jugador.getUsuario().getUser().getUsername();
		Color color = jugador.getColor();
		mensajeFinal = mensajeFinal + username +"("+color.toString()+"): " + mensaje;
		chatOca.add(mensajeFinal);
	}
	public String printChatOca(){
		String chat = "";
		List<String> aux = new ArrayList<>(chatOca);
		
		for(String s: aux){
			chat += s + "<br>";
		}  
		return chat;
	}
	

	private List<String> inicializarLog(){
		List<String> result = new ArrayList<>();
		result.add("TURNO DEL JUGADOR ROJO");
		return result;
	}

	public void addLog(String newLog){

		if(newLog.startsWith("TURNO DEL JUGADOR")){
			log.add(0, newLog);
		}
		else log.add(0, "&nbsp;&nbsp;&nbsp;&nbsp;" + newLog);
	}

	public String printLog(){
		String result = "";
		List<String> aux = new ArrayList<>(log);
		Collections.reverse(aux);
		for(String s: aux){
			result += s + "<br>";
		}  
		return result;
	}
	


	 public CasillaOca getCasillaConNumero(Integer numero){
		Optional<CasillaOca> opt = this.getCasillas().stream().filter(c->c.getNumero().equals(numero)).findFirst();
		if(opt.isPresent()) return opt.get();
		return null;
	}

	 
}
