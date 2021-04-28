package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceCX extends Cruce{

	boolean[] ocupado1,ocupado2;
	
	public CruceCX(double p) {
		super(p);

	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;
		ocupado1 = new boolean[TamC]; 
		ocupado2 = new boolean[TamC];
	
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		int pos;
		for(int i=0; i<sel_cruce.size(); i+=2) {
			Arrays.fill(ocupado1, false);
			Arrays.fill(ocupado2, false);
			
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			pos=0;
			while(!ocupado1[pos]) {
				copia1[pos]=crom1[pos];
				ocupado1[pos]=true;
				pos=getPos(crom1,crom2[pos]);
			}
			pos=0;
			while(!ocupado2[pos]) {
				copia2[pos]=crom2[pos];
				ocupado2[pos]=true;
				pos=getPos(crom2,crom1[pos]);
			}
			
			for(int j=0;j<TamC;j++) {
				if(!ocupado1[j]) copia1[j]=crom2[j];				
				if(!ocupado2[j]) copia2[j]=crom1[j];
			}
				
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
		}
		
		return nuevaPob;
	}

	private int getPos(Integer[] array, int e){
		int pos =-1;		
		for (int i = 0; i < array.length; i++) {
		    if (array[i] == e) 
		        pos = i;	    
		} 		
		return pos;
	}
}