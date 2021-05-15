package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class Contraccion extends Mutacion{

	public Contraccion(double _probMutacion) {
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
				a.getFunciones(a.getHijos(), nodos);				
				if(nodos.size()>0) {
					int s = (int) (Math.random()*nodos.size());	
					Arbol b= new Arbol(0);
					b.inicializacionCompleta(0, 0);				
					
					a.insertFuncion(a.getHijos(), b, s, 0);
					
					a.profundidad(b.getHijos(),1,0);
					int nod = a.obtieneNodos(a, 0);
					a.setNumNodos(nod);
					nuevaPob.get(i).setArbol(a);	
				}					
			}
		}
		return nuevaPob;
	}

}
