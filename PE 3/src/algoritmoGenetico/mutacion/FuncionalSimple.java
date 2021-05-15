package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class FuncionalSimple extends Mutacion {
	
	public FuncionalSimple(double _probMutacion) {
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
				Arbol a = nuevaPob.get(i).getArbol().copia();
				a.getFunciones(a.getHijos(), nodos);
				if(nodos.size()>0) {
					
					int s = (int) (Math.random()*nodos.size());							
					Arbol b = nodos.get(s).copia();
					if(b.getValor().equals("PROGN2")) {
						b.setValor("SIC");
						a.insertFuncion(a.getHijos(), b, s, 0);					
						nuevaPob.get(i).setArbol(a);
					}
					else if(b.getValor().equals("SIC")) {
						b.setValor("PROGN2");
						a.insertFuncion(a.getHijos(), b, s, 0);					
						nuevaPob.get(i).setArbol(a);
					}					
					
				}
			}
		}
		return nuevaPob;
		
	}

}
