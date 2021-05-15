package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class Hoist extends Mutacion {

	public Hoist(double _probMutacion) {
		super(_probMutacion);
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
			
			prob=Math.random();
			if(prob < probMutacion) {
				Arbol a = nuevaPob.get(i).getArbol().copia();
				ArrayList<Arbol> nodos= new ArrayList<Arbol>();
				a.getFunciones(a.getHijos(), nodos);				
				if(nodos.size()>0) {
					int s = (int) (Math.random()*nodos.size());
					Arbol b = new Arbol(a.getMax_prof());
					b.setEsRaiz(true);
					b.setEsHoja(false);
					b.setProfundidad(0);
					b.setNumHijos(nodos.get(s).getNumHijos());
					b.setValor(nodos.get(s).getValor());
					b.setHijos(nodos.get(s).getHijos());
					b.profundidad(b.getHijos(),1,0);
					int nod = b.obtieneNodos(b, 0);
					b.setNumNodos(nod);
					
					nuevaPob.get(i).setArbol(b);
				}
			}
		}
		return nuevaPob;
	}

}
