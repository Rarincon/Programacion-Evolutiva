package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class CruceCX extends Cruce{ //REPASAR POR SI ACASO

	public CruceCX(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Object[] crom1,crom2, copia1, copia2;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		/*
		int puntoCruce1=(int) (Math.random()*TamC);
		int puntoCruce2;
		
		do{
			puntoCruce2=(int) (Math.random()*TamC);
		}while(puntoCruce1==puntoCruce2);
		
		if(puntoCruce1>puntoCruce2) {
			int a=puntoCruce1;
			puntoCruce1=puntoCruce2;
			puntoCruce2=a;
		}
		*/
		Map<Integer,Boolean> m1,m2;
		List<Integer> x1,x2;
			
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			int pos1, pos2;
			Object elem1;
			Object elem2;
			
			for(int j=0; j<sel_cruce.size(); j++) {
				crom1=nuevaPob.get(sel_cruce.get(j)).getCromosoma();
				crom2=nuevaPob.get(sel_cruce.get(j+1)).getCromosoma();
				
				int w=0,cont1=0;
				
				copia1[w]=crom1[w];
				
				while(cont1<crom1.length) {
					int k=0;
					elem1=crom2[w];
					
					while(elem1 != crom1[k]) {
						w++;
						w=k;
					}
					cont1++;
					k=0;
					copia1[w]=elem1;
					elem2=crom2[w];
					
					while(elem2 != crom1[k]) {
						w++;
						w=k;
					}
					
					copia1[w]=elem2;
					
					cont1++;
				}
				
				nuevaPob.get(sel_cruce.get(j)).setCromosoma(copia1);
				nuevaPob.get(sel_cruce.get(j+1)).setCromosoma(copia2);
			}
		return nuevaPob;
	}

}
