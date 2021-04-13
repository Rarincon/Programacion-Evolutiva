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

	public CruceOXPP(double p) { //Bucle infinito en algun lado, no arranca, revisar
		super(p);
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
		
		boolean[] ocupado1,ocupado2;
		ocupado1= new boolean[TamC];
		ocupado2= new boolean[TamC];
		
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
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			int pos=0;
			
			for (Integer entry :  l) {
				copia1[entry]=crom2[entry];
				copia2[entry]=crom1[entry];
				ocupado1[crom1[entry]]=true;
				ocupado2[crom2[entry]]=true;
				pos=entry;
			}
			
			int x=(pos+1)%TamC;
			int y=(pos+1)%TamC;
			while(x!=pos) {
				while(ocupado1[crom1[y]])y=(y+1)%TamC;
				copia1[x]=crom1[y];				
				ocupado1[crom1[y]]=true;
				x=(x+1)%TamC;
			}
			
			x=(pos+1)%TamC;
			y=(pos+1)%TamC;
			while(x!=pos) {
				while(ocupado2[crom2[y]])y=(y+1)%TamC;
				copia2[x]=crom2[y];				
				ocupado2[crom2[y]]=true;
				x=(x+1)%TamC;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		return nuevaPob;
	}

}
