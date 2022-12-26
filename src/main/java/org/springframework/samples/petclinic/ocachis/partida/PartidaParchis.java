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
import javax.persistence.OneToOne;

import org.springframework.samples.petclinic.model.Color;
import org.springframework.samples.petclinic.ocachis.casilla.CasillaParchis;
import org.springframework.samples.petclinic.ocachis.ficha.FichaParchis;
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


	private Integer dado = null;
	private Integer tirada = 0;
	private Integer vecesSacado6 = 0;

	@OneToOne
	private FichaParchis ultimaFichaMovida;

	@ElementCollection
	@CollectionTable(name="logParchis")
	protected List<String> log = inicializarLog();

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

	public CasillaParchis getCasillaConNumero(Integer numero){
		Optional<CasillaParchis> opt = this.getCasillas().stream().filter(c->c.getNumero().equals(numero)).findFirst();
		if(opt.isPresent()) return opt.get();
		return null;
	}

    public boolean sePuedeMover(FichaParchis ficha, int dado) {
		if(ficha.isEstaEnCasa() && dado != 5){
			return false;
		}
		if(ficha.isEstaEnLaMeta()){
			return false;
		}
		List<CasillaParchis> l = getCasillasPorLasQuePasa(ficha, dado);
		for(CasillaParchis c: l){
			if(c.getBloqueada()){
				return false;
			} 
		}
        return true;
    }

	public List<CasillaParchis> getCasillasPorLasQuePasa(FichaParchis ficha, Integer dado){
		ArrayList<CasillaParchis> result = new ArrayList<>();
		Integer numero = ficha.getCasillaActual().getNumero();
		int movimientos = dado;
		Boolean haRebotado = false;
		while(movimientos>0){
			numero = getSiguienteNumero(numero, ficha.getColor(), haRebotado);
			CasillaParchis casilla = this.getCasillaConNumero(numero);
			if(casilla.esMeta()) haRebotado = true;
			result.add(casilla);
			movimientos--;
		}
		return result;

	}

	public Integer getSiguienteNumero(Integer numeroInicial, Color color, Boolean rebote){
		Integer res = numeroInicial + 1;
		if(rebote) res = numeroInicial - 1;
		if(color==Color.ROJO && numeroInicial==34) res = 85;
		else if(color==Color.AMARILLO && numeroInicial==68) res = 69;
		else if(color==Color.VERDE && numeroInicial==51) res = 93;
		else if(color==Color.AZUL && numeroInicial==17) res = 77;
		if(numeroInicial == 68 && color != Color.AMARILLO) res = 1;
		if(numeroInicial == 101) res = 5;
		if(numeroInicial == 102) res = 22;
		if(numeroInicial == 103) res = 39;
		if(numeroInicial == 104) res = 56;
		return res;
	}

	public void pasarTurno(){
		switch(this.getColorJugadorActual()){
			case ROJO:
				this.setColorJugadorActual(Color.AMARILLO);
				break;
			case AMARILLO:
				if(this.getJugadores().size()==2) this.setColorJugadorActual(Color.ROJO);
				else this.setColorJugadorActual(Color.VERDE);
				break;
			case VERDE:
				if(this.getJugadores().size()==3) this.setColorJugadorActual(Color.ROJO);
				else this.setColorJugadorActual(Color.AZUL);
				break;
			case AZUL:
				this.setColorJugadorActual(Color.ROJO);
				break;
		}
		this.addLog("TURNO DEL JUGADOR " + this.getColorJugadorActual());
	}

	public List<CasillaParchis> METODORANDOM(FichaParchis ficha, Integer dado){
		Color color = ficha.getColor();
		ArrayList<CasillaParchis> result = new ArrayList<>();
		Integer numeroCasillaInicial = ficha.getCasillaActual().getNumero();
		Integer numeroCasillaInicialMasDado = numeroCasillaInicial + dado;
		Integer numeroCasillaFinal = null;
		
		// if(numeroCasillaInicialMasDado>68){
		// 	numeroCasillaFinal = numeroCasillaInicialMasDado%68;
		// }
		
		switch(color){
			case ROJO:
				if(numeroCasillaInicial<=34 && numeroCasillaInicialMasDado>34){
					Integer diferencia  = numeroCasillaInicialMasDado-34;
					numeroCasillaFinal = 85 + diferencia;
				}
				else if(numeroCasillaInicial<=68 && numeroCasillaInicialMasDado>68){
					numeroCasillaFinal = numeroCasillaInicialMasDado - 68;
				}
					break;
			case AMARILLO: //el pasillo empieza en el 69, por lo que no hay que moverla a ningun lado
				numeroCasillaFinal = numeroCasillaInicialMasDado;
				break;
			case VERDE:
				if(numeroCasillaInicial<=51 && numeroCasillaInicialMasDado>51){
					Integer diferencia  = numeroCasillaInicialMasDado - 51;
					numeroCasillaFinal = 93 + diferencia;
					break;
				}else if(numeroCasillaInicial<=68 && numeroCasillaInicialMasDado>68){
					numeroCasillaFinal = numeroCasillaInicialMasDado - 68;
				}

			case AZUL: 
				if(numeroCasillaInicial<=17 && numeroCasillaInicialMasDado>17){
					Integer diferencia  = numeroCasillaInicialMasDado - 34;
					numeroCasillaFinal = 77 + diferencia;
					break;
				}else if(numeroCasillaInicial<=68 && numeroCasillaInicialMasDado>68){
					numeroCasillaFinal = numeroCasillaInicialMasDado - 68;
				}
		}

		


		return null;
	}


    public CasillaParchis getCasillaFinal(FichaParchis ficha, Integer dado) {
		CasillaParchis result = null;
		
		if(ficha.getCasillaActual().esCasa() && dado==5){ //la ficha sale de casa
			Integer numeroFinal = getSiguienteNumero(ficha.getCasillaActual().getNumero(), ficha.getColor(), false);
			result = this.getCasillaConNumero(numeroFinal);
			return result;
		}

		else if(ficha.getCasillaActual().esMeta()){ //la ficha esta en la meta y no se mueve
			result = ficha.getCasillaActual();
		}else{//la ficha avanza el numero indicado en el dado
			Integer numero = ficha.getCasillaActual().getNumero();
			int movimientos = dado;
			Boolean haRebotado = false;
			while(movimientos>0){
				numero = getSiguienteNumero(numero, ficha.getColor(), haRebotado);
				result = this.getCasillaConNumero(numero);
				if(result.esMeta()) haRebotado = true;
				movimientos--;
			}
		}		
		return result;
    }
}
