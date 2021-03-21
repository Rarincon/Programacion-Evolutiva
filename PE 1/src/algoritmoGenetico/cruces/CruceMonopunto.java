package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto extends Cruce {
	
	public CruceMonopunto(double probCruce) {
		super(probCruce);
	}

	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Object[] crom1, crom2;
		
		double r=Math.random();
		double a=r*TamC;
		int puntoCruce=(int) (Math.random()*TamC);
		Object aux;
		
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Object[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Object[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=0; j<puntoCruce; j++) {
				aux=crom1[j];
				crom1[j]=crom2[j];
				crom2[j]=aux;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(crom1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(crom2);
		}
		return nuevaPob;
	}

}