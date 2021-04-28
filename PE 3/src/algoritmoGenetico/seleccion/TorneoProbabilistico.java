package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;


import algoritmoGenetico.individuos.Individuo;
import utils.Sorted;
import utils.SortedArrayList;

public class TorneoProbabilistico implements Seleccion{
	
	private int tamTorneo;

	public TorneoProbabilistico(int tam) {
		tamTorneo=tam;
	}

	public List<Individuo> selecciona(List<Individuo> pob, int tam) {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		if (pob.size() < tamTorneo) return pob;
		
		for (int i = 0; i < pob.size(); i++) {
			
			List<Individuo> rivales = new SortedArrayList<Individuo>();
			for (int j = 0; j < tamTorneo; j++)
				rivales.add(pob.get((int) (Math.random()*pob.size())).copia());
			
			rivales.sort(new Sorted(false,true));
			
			if(Math.random() > 0.5)
				nuevaPob.add(rivales.get(0));
			else
				nuevaPob.add(rivales.get(tamTorneo - 1));
		}
		
		return nuevaPob;
	}

}