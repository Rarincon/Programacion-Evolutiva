package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class CruceERX extends Cruce{ 

	public CruceERX(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1,crom2, copia1, copia2, copia;

		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		Map<Integer, HashSet<Integer>> matrizAdyacencia;
		int anterior,anterior2,pos,valor;
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			copia=new Integer[TamC];
			
			matrizAdyacencia = new HashMap<Integer, HashSet<Integer>>();
			
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			
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
			
			Arrays.fill(copia1, -1);
			Arrays.fill(copia2, -1);
			copia1[0]=crom1[0];
			copia2[0]=crom2[0];
			
			List<Integer[]> solucion = new ArrayList<Integer[]>();
			descendiente(matrizAdyacencia, copia1, 1, solucion);
			copia= (solucion.size() == 1) ? solucion.get(0) : solucion.get(((Arrays.equals(crom1, solucion.get(0))) ? 1 : 0));
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia);
			
			solucion = new ArrayList<Integer[]>();
			descendiente(matrizAdyacencia, copia2, 1, solucion);
			copia= (solucion.size() == 1) ? solucion.get(0) : solucion.get(((Arrays.equals(crom2, solucion.get(0))) ? 1 : 0));		
			
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia);
			
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
	
	private void descendiente(Map<Integer, HashSet<Integer>> matriz, Integer[] solAct, int k, List<Integer[]> solucion) {
		if (solucion.size() < 2) {
			for (Integer[] sol : listaPosibles(matriz, solAct, k)) {
				if (valido(sol, k)) {
					if (k == solAct.length - 1) solucion.add(sol);
					else descendiente(matriz, sol, k + 1, solucion);
				}
			}
		}
	}

	private List<Integer[]> listaPosibles(Map<Integer, HashSet<Integer>> matriz, Integer[] g, int k) {
		List<Integer[]> posibles = new ArrayList<Integer[]>();
		for (int c : matriz.get(g[k-1])) {
			Integer[] sol = g.clone();
			sol[k] = c;
			posibles.add(sol);
		}
		posibles.sort(new Comparator<Integer[]>() { //ORDENA POR SIZE DE LA TABLA PARA LA POSTERIOR SELECCION
			@Override
			public int compare(Integer[] arg0, Integer[] arg1) {
				if (matriz.get(arg0[k]).size() > matriz.get(arg1[k]).size()) return 1;
				if (matriz.get(arg0[k]).size() < matriz.get(arg1[k]).size()) return -1;
				return 0;
			}
		});
		return posibles;
	}
	
	private boolean valido(Integer[] s, int k) {
		int c = s[k];
		s[k] = -1;
		boolean b = esta(s, c);
		s[k] = c;
		return !b;
	}
			
	private boolean esta(Integer[] l, int e) {
		for (int i = 0; i < l.length; ++i) if(l[i] == e) return true;
		return false;
	}
}
