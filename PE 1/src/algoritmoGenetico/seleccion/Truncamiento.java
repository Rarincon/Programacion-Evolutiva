package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import algoritmoGenetico.individuos.Individuo;
import utils.Sorted;
import utils.SortedArrayList;;

public class Truncamiento implements Seleccion {
	
	private static final double trunc=0.5;
	
	public List<Individuo> selecciona(List<Individuo> pob, int tam) { 

		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		pob.sort(new Sorted(true));
		
		int p = (int) (1/trunc);
		int Nselccionados = pob.size()/p;
		
		for (int i =0; i < Nselccionados; i++){
			for (int j=0; j<p;j++){
				nuevaPob.add(pob.get(i).copia());
			}
		}
		return nuevaPob;
	}

}
