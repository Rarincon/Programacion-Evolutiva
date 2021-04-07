package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Heuristica extends Mutacion {
	
	public Heuristica(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> p) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		int pos1,pos2,pos3,aux;
		int tam=p.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			prob=Math.random();
			if(prob < probMutacion) {
				do {
					pos1=(int) (Math.random()*tam);
					pos2=(int) (Math.random()*tam);
					pos3=(int) (Math.random()*tam);
					
				} while(pos1==pos2 || pos1==pos3 || pos3==pos2);
				
				for(int j=0; j<n*n; j++) {
					
				}
			}
		}		
		return nuevaPob;
	}
	
	private List<Integer[]> permutaciones(int []a, List<Integer[]> conjunto) {
		List<Integer[]> permus = new ArrayList<Integer[]>(); 
	    for(int i=0; i<n; i++) {
	    	for(int j=0; j<n; j++) {
	    		 
	    	}
	    }
		return permus;
    }

}
