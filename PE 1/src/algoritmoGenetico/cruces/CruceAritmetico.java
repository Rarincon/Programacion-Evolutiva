package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce {
	
	private static final double landa=0.6;
	public CruceAritmetico(double probCruce) {
		super(probCruce);
	}

	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Object[] crom1;
		Object[] crom2;
		double aux1,aux2;
		
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Object[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Object[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=0; j<this.TamC; j++) {
				aux1=landa*(double)(crom1[j])+(1-landa)*(double)(crom2[j]);
				aux2=landa*(double)(crom2[j])+(1-landa)*(double)(crom1[j]);
				crom1[j]=aux1;
				crom2[j]=aux2;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(crom1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(crom2);
		}
		return nuevaPob;
	}
}