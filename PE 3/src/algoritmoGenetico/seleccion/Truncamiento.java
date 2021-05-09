package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.Sorted;

public class Truncamiento implements Seleccion {
	
	boolean maximizar;
	private static final double trunc=0.5;
	public Truncamiento(boolean b) {
		maximizar=b;
	}
	
	public List<Individuo> selecciona(List<Individuo> pob, int tam) { 

		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		pob.sort(new Sorted(true,true));
		
		int p = (int) (1/trunc);
		int Nselccionados = tam/p;
		
		for (int i =0; i < Nselccionados; i++){
			for (int j=0; j<p;j++){
				nuevaPob.add(pob.get(i).copia());
			}
		}
		if(tam%2==1)nuevaPob.add(pob.get(Nselccionados).copia());
		return nuevaPob;
	}

}
