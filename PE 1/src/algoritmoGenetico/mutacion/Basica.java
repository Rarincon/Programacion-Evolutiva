package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Basica extends Mutacion {

	public Basica(double _probMutacion) {
		super(_probMutacion);
		
	}

	public List<Individuo> mutarInd(List<Individuo> pob) {
		
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		boolean mutado;
		double prob;
		int tam=pob.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<pob.size();i++) {
			nuevaPob.add(pob.get(i).copia());
			mutado=false;
			crom=(Integer[]) nuevaPob.get(i).getCromosoma(); //REVISAR ESTE CAST
			for(int j=0;j<tam;j++) {
				prob=Math.random();
				if(prob < probMutacion) {
					if(crom[j]==0)crom[j]=1;
					else crom[j]=0;
					mutado=true;
				}
			}
			if(mutado) nuevaPob.get(i).setCromosoma(crom);
		}	
		return nuevaPob;
	}
}