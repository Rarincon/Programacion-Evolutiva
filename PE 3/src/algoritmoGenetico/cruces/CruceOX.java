package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceOX extends Cruce{

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
		
		int pos,aux1,aux2;
		
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
						
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			Arrays.fill(copia1, -1);
			Arrays.fill(copia2, -1);
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=puntoCruce1;j<puntoCruce2;j++) {
				copia1[j]=crom2[j];
				copia2[j]=crom1[j];
			}
			
			pos=aux1=aux2=puntoCruce2;
			
			while(pos!=puntoCruce1) {
				while (esta(copia1, crom1[aux1])) aux1=(aux1+1)%TamC;
				while (esta(copia2, crom2[aux2])) aux2=(aux2+1)%TamC;
				copia1[pos]=crom1[aux1];
				copia2[pos]=crom2[aux2];
				pos=(pos+1)%TamC;
				aux1=(aux1+1)%TamC;
				aux2=(aux2+1)%TamC;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		return nuevaPob;
	}
	
	private boolean esta(Integer[] copia, int e) {
		for (int i = 0; i < copia.length; ++i) if(copia[i] == e) return true;
		return false;
	}

}
