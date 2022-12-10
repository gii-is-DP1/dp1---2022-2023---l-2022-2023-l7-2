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

import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.casilla.Coordenadas;
import org.springframework.samples.petclinic.ocachis.jugador.Jugador;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PartidaParchis extends Partida{

	@OneToMany(cascade = CascadeType.ALL)
	private List<CasillaParchis> casillas;
	
	@OneToMany(mappedBy="partidaParchis", cascade = CascadeType.ALL)
	private Collection<Jugador> jugadores;


	@ElementCollection
	@CollectionTable(name="log")
	protected ArrayList<String> log = inicializarLog();

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
}
