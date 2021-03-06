package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Ruleta implements Seleccion {

	public List<Individuo> selecciona(List<Individuo> p, int tam) { 
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		double prob;
		int pos_super;
		Random r = new Random();
		for(int i=0; i<tam; i++) {
			prob=r.nextDouble()%1; 
			pos_super=0;
			while((prob >= p.get(pos_super).getPuntAcum()) && (pos_super < tam)) {
				pos_super++;
			}
			nuevaPob.add(p.get(pos_super).copia());
		}

		return nuevaPob; 		
	}
}
