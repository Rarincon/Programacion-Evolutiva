package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class FuncionSimple extends Mutacion{

	public FuncionSimple(double _probMutacion) {
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
				Arbol a = nuevaPob.get(i).getArbol().copia();
				a.getFunciones(a.getHijos(), nodos);
				int func = rnd.nextInt(Individuo.funciones.length);
				
				int s = (int) (Math.random()*nodos.size());			
				
				Arbol b = nodos.get(s).copia();
				b.setValor(Individuo.funciones[func]);
				a.insertFuncion(a.getHijos(), b, s, 0);
				
				nuevaPob.get(i).setArbol(a);
			}
		}
		return nuevaPob;
		
	}

}
