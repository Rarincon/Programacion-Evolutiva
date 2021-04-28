package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public class CruceOXOP extends Cruce{
	
	static private final int n=5;

	public CruceOXOP(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;
		List<Integer>selec,pos1,pos2;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		for(int i=0; i<sel_cruce.size(); i+=2) {

			pos1=new ArrayList<Integer>();
			pos2=new ArrayList<Integer>();
			selec = new SortedArrayList<Integer>();
			while(selec.size() < n) {
				int valor=(int) (Math.random()*TamC);
				if(!selec.contains(valor)) {
					selec.add(valor);
				}
			}
			
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			pos1=posiciones(selec, crom1, crom2);
			pos2=posiciones(selec, crom2, crom1);
			
			for(int j=0;j<TamC;j++) {
				if(!pos1.contains(j))copia1[j]=crom2[j];
				if(!pos2.contains(j))copia2[j]=crom1[j];
			}
			for(int j=0;j<pos1.size();j++) {
				copia1[pos1.get(j)]=crom1[selec.get(j)];
				copia2[pos2.get(j)]=crom2[selec.get(j)];	
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
			
		}
		
		return nuevaPob;
	}
	
	private List<Integer> posiciones(List<Integer> pos,Integer[] crom1, Integer[] crom2){
		List<Integer> posiciones= new SortedArrayList<Integer>();
		for(int i=0;i<pos.size();i++) {
			posiciones.add(getPos(crom2,crom1[pos.get(i)]));		
		}
		return posiciones;
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
