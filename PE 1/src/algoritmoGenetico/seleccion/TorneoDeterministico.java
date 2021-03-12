package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class TorneoDeterministico extends Seleccion{

	private int tamTorneo;

	public TorneoDeterministico(List<Individuo> _poblacion, int _tamPoblacion, int tam) {
		super(_poblacion, _tamPoblacion);
		tamTorneo=tam;
	}

	@Override
	public List<Individuo> selecciona() {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		if (poblacion.size() < tamTorneo) return poblacion;
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			List<Individuo> rivales = new ArrayList<Individuo>();
			for (int j = 0; j < tamTorneo; j++)
				rivales.add(poblacion.get((int) (Math.random()*poblacion.size()))); //Mirar si se cogen todos o falta el ultimo
			
			Collections.sort(rivales);
			nuevaPob.add(rivales.get(0)); //Mirar si es el mejor o el peor		
		}
		return nuevaPob;
	}
}
