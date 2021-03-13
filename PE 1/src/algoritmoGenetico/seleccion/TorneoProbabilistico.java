package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public class TorneoProbabilistico extends Seleccion{
	
	private int tamTorneo;

	public TorneoProbabilistico(List<Individuo> _poblacion, int _tamPoblacion, int tam) {
		super(_poblacion, _tamPoblacion);
		tamTorneo=tam;
	}

	@Override
	public List<Individuo> selecciona() { //REVISAR
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		if (poblacion.size() < tamTorneo) return poblacion;
		
		for (int i = 0; i < poblacion.size(); i++) {
			
			List<Individuo> rivales = new SortedArrayList<Individuo>();
			for (int j = 0; j < tamTorneo; j++)
				rivales.add(poblacion.get((int) (Math.random()*poblacion.size())).copia()); //Mirar si se cogen todos o falta el ultimo
			
			Collections.sort(rivales);
			
			//nuevaPob.add(rivales.get((Math.random() > 0.5) ? 0 : tamTorneo - 1)); LO MISMO
			if(Math.random() > 0.5)
				nuevaPob.add(rivales.get(0));
			else
				nuevaPob.add(rivales.get(tamTorneo - 1));
		}
		
		return nuevaPob;
	}

}
