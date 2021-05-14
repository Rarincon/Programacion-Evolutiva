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
				List<String> l=a.toArray();
				int s = (int) (Math.random()*3);
				if(s==0)s++;
				int prof= (int) (Math.random()*3);
				Arbol b=new Arbol(prof);
				b.inicializacionCompleta(0,1);
				
				//Aqui hay que borrar ese trozo del arbol antes de insertar b
				
				a.insertFuncion(a.getHijos(), b, s, 0);
				//a.insert(b,s);
				
				nuevaPob.get(i).setArbol(a);
			}
		}
		return nuevaPob;
		
	}

}
