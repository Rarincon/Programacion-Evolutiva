package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce {
	
	//private List<Integer> sel_cruce;
	//private int TamC;
	public CruceAritmetico(List<Individuo> _poblacion, int _tamPoblacion, double probCruce) {
		super(probCruce);
	}

	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		/*Integer[] crom1, crom2;
		int aux;
		
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Integer[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Integer[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=0; j<this.puntoCruce; j++) {
				aux=crom1[j];
				crom1[j]=crom2[j];
				crom2[j]=aux;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(crom1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(crom2);
		}*/
		return nuevaPob;
	}
}