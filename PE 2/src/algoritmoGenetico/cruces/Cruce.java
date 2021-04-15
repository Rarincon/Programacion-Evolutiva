package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public abstract class Cruce {
	protected double probCruce;
	protected List<Integer> sel_cruce;
	protected int TamC;
	
	public Cruce(double p) {
		probCruce=p;
	}
	
	public List<Individuo> selecCruzados(List<Individuo> pob){
		sel_cruce= new ArrayList<Integer>();
		TamC=pob.get(0).getTamCromosoma();		
		double p;		
		for(int i=0; i<pob.size(); i++) {
			p=Math.random();
			if(p < this.probCruce) {
				sel_cruce.add(i);
			}
		}
		if((sel_cruce.size()%2)==1) {
			sel_cruce.remove(sel_cruce.size()-1); 
		}
		Collections.shuffle(sel_cruce);
		return cruce(pob);
	}
	public abstract List<Individuo> cruce(List<Individuo> l);
}

