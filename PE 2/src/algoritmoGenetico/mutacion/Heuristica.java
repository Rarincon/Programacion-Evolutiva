package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Heuristica extends Mutacion {

	private static final int n = 3;
	
	public Heuristica(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> p) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		int pos1,pos2,aux;
		int tam=p.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			prob=Math.random();
			if(prob < probMutacion) {
				//FALTA POR HACER, complejito mirar video
			}
		}		
		return nuevaPob;
	}

}
