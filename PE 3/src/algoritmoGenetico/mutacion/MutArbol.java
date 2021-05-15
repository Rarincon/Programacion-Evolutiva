package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class MutArbol extends Mutacion{

	public MutArbol(double _probMutacion) {
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
				Arbol a = nuevaPob.get(i).getArbol().copia();
				
				int total=a.getNumNodos();
				
				//List<String> l=a.toArray();
				int s = (int) (Math.random()*total);
				if(s==0)s++;
				int prof= (int) (Math.random()*a.getMax_prof());
				prof++;
				Arbol b=new Arbol(prof);
				
				b.inicializacionCreciente(0,1);
				
				//Aqui hay que borrar ese trozo del arbol antes de insertar b
				
				a.insert(a.getHijos(), b, s, 0);
				//a.insert(b,s);
				int nodos = a.obtieneNodos(a, 0);
				a.setNumNodos(nodos);
				
				nuevaPob.get(i).setArbol(a);
			}
		}
		return nuevaPob;
		
	}

}
