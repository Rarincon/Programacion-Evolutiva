package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class Expansion extends Mutacion{

	public Expansion(double _probMutacion) {
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
				ArrayList<Arbol> nodos= new ArrayList<Arbol>();
				Arbol a = nuevaPob.get(i).getArbol().copia();
				a.getTerminales(a.getHijos(), nodos);
				
				int s = (int) (Math.random()*nodos.size());	
				int prof=a.getMax_prof();
				if(prof>1)prof--;
				Arbol b= new Arbol(prof);
				b.inicializacionCompleta(0, 0);				
				
				a.insertTerminal(a.getHijos(), b, s, 0);
				
				a.profundidad(b.getHijos(),1,0);
				int nod = a.obtieneNodos(a, 0);
				a.setNumNodos(nod);
				nuevaPob.get(i).setArbol(a);			
			}
		}
		return nuevaPob;
	}

}
