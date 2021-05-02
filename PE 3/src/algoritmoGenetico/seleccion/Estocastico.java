package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Estocastico implements Seleccion {
	
	public List<Individuo> selecciona(List<Individuo> p, int tam) {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		Integer[] sel_super= new Integer[p.size()];
		Random r= new Random();
		
		double dist=1/p.size();
		double rand = 0 + ( 1 - 0 ) * r.nextDouble();
		int pos_super=0;
		
		for(int i=0; i<p.size(); i++) {
			while(pos_super < p.size() && p.get(pos_super).getPuntAcu()<rand)
				pos_super++;
			sel_super[i]=pos_super;
			rand+=dist;
			
		}
		
		for(int i=0; i<p.size(); i++) {
			nuevaPob.add(p.get(sel_super[i]).copia());
		}
		return nuevaPob;
		
	}
}