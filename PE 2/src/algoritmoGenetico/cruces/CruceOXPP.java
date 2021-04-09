package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class CruceOXPP extends Cruce {
	
	static private final int n=5;

	public CruceOXPP(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Object[] crom1,crom2, copia1, copia2;

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
		
		boolean[] ocupado1,ocupado2;
		List<Integer> x1,x2;
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			HashSet<Integer> l= new HashSet<Integer>();
			
			while(l.size() < n) {
				int valor=(int) (Math.random()*TamC);
				l.add(valor); 
			}
			
			Arrays.fill(ocupado1, false);
			Arrays.fill(ocupado2, false);
	
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			int pos;
			
			for (Integer entry :  l) {
				copia1[entry]=crom2[entry];
				copia2[entry]=crom1[entry];
				ocupado1[entry]=true;
				ocupado2[entry]=true;
				pos=(entry+1)%TamC;
			}
			/*
			for(int i=pos; ) {
				
			}*/
			
			int pos=(puntoCruce2+1)%TamC;
			int aux=(puntoCruce2+1)%TamC;
			
			while(pos!=puntoCruce1) {
				while(m1.containsKey(crom1[aux]))aux=(aux+1)%TamC;
				copia1[pos]=crom1[aux];
				m1.put((Integer) crom1[aux], true);
				pos=(pos+1)%TamC;
			}
			
			pos=(puntoCruce2+1)%TamC;
			aux=(puntoCruce2+1)%TamC;
			
			while(pos!=puntoCruce1) {
				while(m2.containsKey(crom2[aux]))aux=(aux+1)%TamC;
				copia1[pos]=crom2[aux];
				m2.put((Integer) crom2[aux], true);
				pos=(pos+1)%TamC;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		return nuevaPob;
	}

}
