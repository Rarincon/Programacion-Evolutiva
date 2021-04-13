package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class CruceOX extends Cruce{ //Necesario Repasar, haces unas copias mal y coge solo un elemento para todo

	public CruceOX(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		int puntoCruce1,puntoCruce2;
		
		//Map<Integer,Boolean> m1,m2;
		boolean[] ocupado1,ocupado2;
		ocupado1= new boolean[TamC];
		ocupado2= new boolean[TamC];
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			
			puntoCruce1=(int) (Math.random()*TamC);
			do{
				puntoCruce2=(int) (Math.random()*TamC);
			}while(puntoCruce1==puntoCruce2);
			
			if(puntoCruce1>puntoCruce2) {
				int a=puntoCruce1;
				puntoCruce1=puntoCruce2;
				puntoCruce2=a;
			}
			
			Arrays.fill(ocupado1, false);
			Arrays.fill(ocupado2, false);
			
			//m1= new HashMap<Integer,Boolean>();
			//m2= new HashMap<Integer,Boolean>();
			
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=puntoCruce1;j<=puntoCruce2;j++) {
				copia1[j]=crom2[j];
				copia2[j]=crom1[j];
				
				ocupado1[crom1[j]]=true;
				ocupado2[crom2[j]]=true;
				//m1.put((Integer) crom2[j], true);
				//m2.put((Integer) crom1[j], true);
			}
			
			int pos=(puntoCruce2+1)%TamC;
			int aux=(puntoCruce2+1)%TamC;
			
			while(pos!=puntoCruce1) {
				//while(m1.containsKey(crom1[aux]))aux=(aux+1)%TamC;
				while(ocupado1[crom1[aux]])aux=(aux+1)%TamC;
				copia1[pos]=crom1[aux];
				ocupado1[crom1[aux]]=true;
				//m1.put((Integer) crom1[aux], true);
				pos=(pos+1)%TamC;
				
				/*if(!m1.containsKey(crom1[aux])) {
					copia1[pos]=crom1[aux];
					m1.put((Integer) crom1[aux], true);
					pos=(pos+1)%TamC;
				}
				aux=(aux+1)%TamC;*/
			}
			
			pos=(puntoCruce2+1)%TamC;
			aux=(puntoCruce2+1)%TamC;
			
			while(pos!=puntoCruce1) {
				//while(m2.containsKey(crom2[aux]))aux=(aux+1)%TamC;
				while(ocupado2[crom2[aux]])aux=(aux+1)%TamC;
				copia1[pos]=crom2[aux];
				ocupado2[crom1[aux]]=true;
				//m2.put((Integer) crom2[aux], true);
				pos=(pos+1)%TamC;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		return nuevaPob;
	}

}
