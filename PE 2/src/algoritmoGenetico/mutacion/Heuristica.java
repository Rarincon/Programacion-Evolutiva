package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.individuos.Individuo;

public class Heuristica extends Mutacion {
	
	public Heuristica(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> p) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		int pos=0;
		int tam=p.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		Map<Integer,Integer> mapa;
		
		for(int i=0;i<p.size();i++) {
			nuevaPob.add(p.get(i).copia());
			prob=Math.random();
			if(prob < probMutacion) {
				mapa= new HashMap<Integer,Integer>();
				crom= nuevaPob.get(i).getCromosoma();	
				while(!mapa.containsKey(pos))
				do {
					pos= (int) (Math.random()*tam);
					if(!mapa.containsKey(pos))mapa.put(pos, crom[pos]);
				} while(mapa.size()<n);
				
				List<Integer[]> permuta= new ArrayList<Integer[]>();
				permuta= perm(mapa,crom);
				
				pos=0;				
				double fitness= Individuo.apply(permuta.get(0));
				double fitnessAUX;
				for(int j=1;j<permuta.size();j++){
					fitnessAUX=Individuo.apply(permuta.get(j));
					if(fitnessAUX>fitness) { //MIRAR SI ES MAXIMIZACION O MINIMIZACION
						pos=j;
						fitness=fitnessAUX;
					}
				}
				
				nuevaPob.get(i).setCromosoma(permuta.get(pos));
			}
		}		
		return nuevaPob;
	}
	
	private List<Integer[]> perm(Map<Integer,Integer>map, Integer[] crom){
		List<Integer[]> permuta= new ArrayList<Integer[]>();
		Integer[] copia;
		List<int[]> l= new ArrayList<int[]>();
		int[]pos = new int[n];
		int i=0;
		int posAUX;
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			pos[i]=entry.getValue();
			i++;
		}
		permutaciones(l, pos, n);
		
		for(int j=0;j<l.size();j++) {
			copia= crom.clone(); //REVISAR SI SE MODIFICA EL ORIGINAL
			posAUX=0;
			for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
				copia[entry.getKey()]=l.get(j)[posAUX];
				posAUX++;
			}
			permuta.add(copia);
		}	
		return permuta;
	}
	
	private void permutaciones(List<int[]> l, int[] pos, int N) { 
	    if (N == 1) l.add(pos.clone()); 
	    else for (int i = 0; i < N; i++) { 
	    	permutaciones(l, pos, N-1); 
	    	int a;
	    	if(N % 2 == 1) a= 0;
	    	else a=i; 	
	        int b = pos[a];
	        pos[a] = pos[N-1];
	        pos[N-1] = b;
	    } 
	}

}
