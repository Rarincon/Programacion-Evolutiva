package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class CrucePMX extends Cruce{

	public CrucePMX(double probCruce) {
		super(probCruce);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
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
		
		Map<Integer,Boolean> m1,m2;
		List<Integer> x1,x2;
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			m1= new HashMap<Integer,Boolean>();
			m2= new HashMap<Integer,Boolean>();
			
			x1=new ArrayList<Integer>();
			x2=new ArrayList<Integer>();
			
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=puntoCruce1;j<=puntoCruce2;j++) {
				copia1[j]=crom2[j];
				copia2[j]=crom1[j];
				m1.put((Integer) crom2[j], true);
				m2.put((Integer) crom1[j], true);
			}
			
			int x=(puntoCruce2+1)%TamC;
			while(x!=puntoCruce1) {
				if(!m1.containsKey(crom1[x])) {
					copia1[x]=crom1[x];
					m1.put((Integer) crom1[x], true);
				}
				else x1.add(x);
				if(!m2.containsKey(crom2[x])) {
					copia2[x]=crom2[x];
					m2.put((Integer) crom2[x], true);
				}
				else x2.add(x);
				x++;
				x=x%TamC;
			}
			
			x=puntoCruce1;
			for(int j=0;j<x1.size();j++) {			
				while(m1.containsKey(crom1[x]))x++;
				copia1[x1.get(j)]=crom1[x];
				m1.put((Integer) crom1[x], true);
			}
			x=puntoCruce1;
			for(int j=0;j<x2.size();j++) {
				while(m2.containsKey(crom2[x]))x++;
				copia2[x2.get(j)]=crom2[x];	
				m2.put((Integer) crom2[x], true);
			}
					
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		return nuevaPob;
	}

}
