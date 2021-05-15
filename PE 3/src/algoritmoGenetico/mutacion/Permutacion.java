package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class Permutacion extends Mutacion{

	public Permutacion(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> pob) { //Mirar si lo hace bn
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
			
			prob=Math.random();
			if(prob < probMutacion) {
				ArrayList<Arbol> nodos= new ArrayList<Arbol>();
				ArrayList<Arbol> nodosPerm= new ArrayList<Arbol>();
				ArrayList<Arbol> hijos = new ArrayList<Arbol>();
				Arbol a = nuevaPob.get(i).getArbol().copia();
				
				a.getFunciones(a.getHijos(), nodos);
				if(nodos.size()==0) {
					a.getTerminales(a.getHijos(), nodos);
					
					for(int j=nodos.size()-1; j>=0; j--) {
						nodosPerm.add(nodos.get(j));
					}
					a.setHijos(nodosPerm);					
				}
				else {
					int s = (int) (Math.random()*nodos.size());					
					hijos = nodos.get(s).getHijos();
					
					for(int j=hijos.size()-1; j>=0; j--) {
						nodosPerm.add(hijos.get(j));
					}
					
					nodos.get(s).setHijos(nodosPerm);
					a.insertFuncion(a.getHijos(), nodos.get(s), s, 0);							
				}			
				nuevaPob.get(i).setArbol(a);		
			}
		}
		return nuevaPob;
		
	}

}
