package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Uniforme extends Mutacion {

	public Uniforme(double probMutacion) {
		super(probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> p) {
		//Random r= new Random();
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		boolean mutado;
		double prob;
		int tam=p.get(0).getTamCromosoma();
		Double[] crom= new Double[tam];
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			mutado=false;
			crom=(Double[]) nuevaPob.get(i).getCromosoma();
			for(int j=0;j<tam;j++) {
				prob=Math.random();
				if(prob < probMutacion) {
					crom[j]=Math.random()*Math.PI;
					//(Math.abs(r.nextInt() % (1/0.001)) / (1/0.001))*Math.PI;
					mutado=true;
				}
			}
			if(mutado) nuevaPob.get(i).setCromosoma(crom);
		}
		
		return nuevaPob;
	}
}
