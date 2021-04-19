package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class MRaulRober extends Mutacion{

	public MRaulRober(double _probMutacion) {
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
				crom= nuevaPob.get(i).getCromosoma();
				pos1=(int) (Math.random()*tam);
				pos2=tam-pos1;
				
				while(pos1>0 || pos2<=tam) {
					aux=crom[pos1];
					crom[pos1]=crom[pos2];
					crom[pos2]=aux;
					pos1--;
					pos2++;
				}
				
				nuevaPob.get(i).setCromosoma(crom);
			}
		}		
		return nuevaPob;
	}

}
