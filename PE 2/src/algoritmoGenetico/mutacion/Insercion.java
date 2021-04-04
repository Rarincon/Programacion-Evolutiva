package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Insercion  extends Mutacion{

	public Insercion(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> p) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		boolean mutado;
		double prob;
		int tam=p.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			mutado=false;
			crom=(Integer[]) nuevaPob.get(i).getCromosoma();
			for(int j=0;j<tam && !mutado;j++) {
				prob=Math.random();
				if(prob < probMutacion) {
					int pos;
					do {
						pos = (int) (Math.random()*tam);
					}while(pos==j);
					
					int aux;
					for(;pos<tam;pos++) {
						aux=crom[j];
						if(pos<j) {
							for(int x=j;x>pos;x--)
								crom[x]=crom[x-1];	
						}
						else {
							for(int x=j;x<pos;x++)
								crom[x]=crom[x+1];
						}
						crom[pos]=aux;
					}	
					mutado=true;
				}
			}
			if(mutado) nuevaPob.get(i).setCromosoma(crom);
		}
		
		return nuevaPob;
	}

}
