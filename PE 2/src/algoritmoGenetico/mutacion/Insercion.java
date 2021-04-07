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
		int pos1,pos2;
		int tam=p.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			
			prob=Math.random();
			if(prob < probMutacion) {
				crom=(Integer[]) nuevaPob.get(i).getCromosoma();
				pos1=(int) (Math.random()*tam);
				do {
					pos2 = (int) (Math.random()*tam);
				}while(pos1==pos2);
				
				int aux;
				aux=crom[pos1];
				if(pos2<pos1) {
					for(int x=pos1;x>pos2;x--)
						crom[x]=crom[x-1];	
				}
				else {
					for(int x=pos1;x<pos2;x++)
						crom[x]=crom[x+1];
				}
				crom[pos2]=aux;
				
				nuevaPob.get(i).setCromosoma(crom);
			}
				
		}
		return nuevaPob;
	}
}
