package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algoritmoGenetico.individuos.Individuo;

public class CruceERX extends Cruce{ //REPASAR POR SI ACASO

	public CruceERX(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		Map<Integer, HashSet<Integer>> matrizAdyacencia = new HashMap<Integer, HashSet<Integer>>();
		int anterior,anterior2,pos,valor;
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			crom1=(Integer[]) nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=(Integer[]) nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			
			for(int j=0; j<TamC; j++) {
				HashSet<Integer> l = new HashSet<Integer>();
				valor= crom1[j];
				l.add(crom1[(j+1)%TamC]);
				anterior=j-1;
				if(anterior==-1) anterior=TamC-1;
				l.add(crom1[anterior]);
				pos=getPos(crom2, valor);
				l.add(crom2[(pos+1)%TamC]);
				anterior2=pos-1;
				if(anterior2==-1) anterior2=TamC-1;
				l.add(crom2[anterior2]);
				matrizAdyacencia.put(valor, l);
			}
			
			if(Math.random()*2 > 1) {
				copia1=descendiente(matrizAdyacencia, crom1[0]);
				copia2=descendiente(matrizAdyacencia, crom2[0]);
			}
			else {
				copia2=descendiente(matrizAdyacencia, crom2[0]);
				copia1=descendiente(matrizAdyacencia, crom1[0]);			
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
	
	private Integer[] descendiente(Map<Integer, HashSet<Integer>> matriz, int select) {	
		
		int menorElegido=0;
		int menor;
		
		Integer[] copia =new Integer[TamC];
		boolean[] ocupado = new boolean[TamC];
		
		Arrays.fill(ocupado, false);
		
		for(int k=0; k<matriz.size(); k++) {
			menor=Integer.MAX_VALUE;
			HashSet<Integer> li = new HashSet<Integer>();
			li=matriz.get(select);
			
			for (Integer entry : li) {
				if(matriz.get(entry).size() < menor && !ocupado[entry]) {
					menor=matriz.get(entry).size();
					menorElegido=entry;
				}
			}
			copia[k]=select;
			ocupado[select]=true;
			select=menorElegido;
			li.clear();
		}
		
		return copia;
	}

}
