package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceCO extends Cruce {

	public CruceCO(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		
		Integer[] crom1,crom2, copia1, copia2;
	
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			copia1=getCOcrom(crom1);
			copia2=getCOcrom(crom2);
			
			int puntoCruce=(int) (Math.random()*TamC);
			Integer aux;
			for(int j=0; j<puntoCruce; j++) {
				aux=copia1[j];
				copia1[j]=copia2[j];
				copia2[j]=aux;
			}
			
			copia1=decodeCO(copia1);
			copia2=decodeCO(copia2);
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
		}
		
		return nuevaPob;
	}
	
	private Integer[] decodeCO(Integer[] crom) {
		List<Integer> listaCO= new ArrayList<Integer>();
		Integer[] result = new Integer[TamC];

		for(int i=0; i<TamC; i++) {
			listaCO.add(i);
		}
		
		for(int i=0; i<TamC; i++) {
			result[i]=listaCO.get(crom[i]);
			listaCO.remove(result[i]);
		}
		
		return result;
	}
	
	private Integer[] getCOcrom(Integer[] crom) {
		List<Integer> listaCO= new ArrayList<Integer>();
		Integer[] result = new Integer[TamC];
		int pos=0;

		for(int i=0; i<TamC; i++) {
			listaCO.add(i);
		}
		
		for(int i=0; i<TamC; i++) {
			pos=getPos(listaCO, crom[i]);
			result[i]=pos;
			listaCO.remove(crom[i]);
		}
		
		return result;
	}
	
	private int getPos(List<Integer> array, int e){
		int pos =-1;		
		for (int i = 0; i < array.size(); i++) {
		    if (array.get(i) == e) 
		        pos = i;	    
		} 		
		return pos;
	}

}
