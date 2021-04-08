package algoritmoGenetico.cruces;

import java.util.ArrayList;
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
				valor=(int) crom1[j];
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
			boolean cromoturno=false; //si es false empeizas por el crom1 y si es true empiezas por el true
			int eleccion; //para elegir por que cromosoma empezamos
			if(Math.random()*2 > 1) {
				eleccion=crom1[0];
			}
			else {
				eleccion=crom2[0];
				cromoturno=true;
			}
			int menorElegido=0;
			int menor=99999999;
			
			for(int k=0; k<matrizAdyacencia.size(); k++) {
				
				HashSet<Integer> li = new HashSet<Integer>();
				li=matrizAdyacencia.get(eleccion);
				
				for (Integer entry : li) {
					if(matrizAdyacencia.get(entry).size() < menor) {
						menor=matrizAdyacencia.get(entry).size();
						menorElegido=entry;
					}
				}
				copia1[k]=eleccion;
				eleccion=menorElegido;
				li.clear();
			}
			
			menor=999999999;
			menorElegido=0;
			if(cromoturno) eleccion=crom1[0];
			else eleccion=crom2[0];
			
			for(int n=0; n<matrizAdyacencia.size(); n++) {
				
				HashSet<Integer> li = new HashSet<Integer>();
				li=matrizAdyacencia.get(eleccion);
				
				for (Integer entry : li) {
					if(matrizAdyacencia.get(entry).size() < menor) {
						menor=matrizAdyacencia.get(entry).size();
						menorElegido=entry;
					}
				}
				copia2[n]=eleccion;
				eleccion=menorElegido;
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
