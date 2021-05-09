package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
				Random rnd = new Random();
				ArrayList<Arbol> nodos= new ArrayList<Arbol>();
				ArrayList<Arbol> nodosPerm= new ArrayList<Arbol>();
				Arbol a = nuevaPob.get(i).getArbol().copia();
				a.getFunciones(a.getHijos(), nodos);
				for(int j=nodos.size()-1; j>0; j--) {
					nodosPerm.add(nodos.get(j));
				}
				
				int s = (int) (Math.random()*nodosPerm.size());			
				Arbol b = nodosPerm.get(s).copia();
				a.insertFuncion(a.getHijos(), b, s, 0);
				
				nuevaPob.get(i).setArbol(a);
			}
		}
		return nuevaPob;
		
	}

}
