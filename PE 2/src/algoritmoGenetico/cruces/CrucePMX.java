package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CrucePMX extends Cruce{

	public CrucePMX(double probCruce) {
		super(probCruce);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Object[] crom1;
		Object[] crom2;
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		int puntoCruce1=(int) (Math.random()*TamC);
		int puntoCruce2;
		do{
			puntoCruce2=(int) (Math.random()*TamC);
		}while(puntoCruce1==puntoCruce2);
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Object[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Object[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
		}
		return nuevaPob;
	}

}
