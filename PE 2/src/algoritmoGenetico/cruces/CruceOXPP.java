package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public class CruceOXPP extends Cruce {
	
	static private final int n=5;

	public CruceOXPP(double p) { //Bucle infinito en algun lado, no arranca, revisar
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;
		List<Integer>selec ;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		int x,pos,aux1,aux2;
		
		for(int i=0; i<sel_cruce.size(); i+=2) {

			selec = new SortedArrayList<Integer>();
			while(selec.size() < n) {
				int valor=(int) (Math.random()*TamC);
				if(!selec.contains(valor)) {
					selec.add(valor);
				}
			}
	
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			Arrays.fill(copia1, -1);
			Arrays.fill(copia2, -1);
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int p=0;p<n;p++) {
				copia1[selec.get(p)]=crom2[selec.get(p)];
				copia2[selec.get(p)]=crom1[selec.get(p)];
			}
			
			pos=selec.get(n-1);
			x=aux1=aux2=(selec.get(n-1)+1)%TamC;
			
			while(x!=pos) {
				if(copia1[x]==-1) {
					while (esta(copia1, crom1[aux1]))aux1=(aux1+1)%TamC;
					while (esta(copia2, crom2[aux2])) aux2=(aux2+1)%TamC;
					copia1[x]=crom1[aux1];
					copia2[x]=crom2[aux2];
					x=(x+1)%TamC;
					aux1=(aux1+1)%TamC;
					aux2=(aux2+1)%TamC;
				}
				else x=(x+1)%TamC;
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
