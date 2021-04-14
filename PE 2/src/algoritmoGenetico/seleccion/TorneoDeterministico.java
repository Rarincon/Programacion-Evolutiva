package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.Sorted;

public class TorneoDeterministico implements Seleccion{

	private int tamTorneo;
	private boolean maximizar;
	
	public TorneoDeterministico(int tam,boolean m) {
		tamTorneo=tam;
		maximizar=m;
	}


	public List<Individuo> selecciona(List<Individuo> poblacion, int tam) {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		if (poblacion.size() < tamTorneo) return poblacion;
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			List<Individuo> rivales = new ArrayList<Individuo>();
			for (int j = 0; j < tamTorneo; j++)
				rivales.add(poblacion.get((int) (Math.random()*poblacion.size())).copia());
			
			//Collections.sort(rivales);
			rivales.sort(new Sorted(maximizar));
			nuevaPob.add(rivales.get(0));
		}
		return nuevaPob;
	}
}

