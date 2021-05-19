package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Arbol;
import algoritmoGenetico.individuos.Individuo;

public class MutArbol extends Mutacion{
	
	private int opcion, prof;

	public MutArbol(double _probMutacion, int opc, int profundidad) {
		super(_probMutacion);
		opcion= opc;
		prof= profundidad;
	}

	@Override
	public List<Individuo> mutarInd(List<Individuo> pob) { //Mirar si lo hace bn
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		double prob;
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
			
			prob=Math.random();
			if(prob < probMutacion) {
				Arbol a= new Arbol(prof);
				if(opcion==0) a.inicializacionCompleta(0, 0);
				else if(opcion==1) a.inicializacionCompleta(0, 0);
				else {
					int ini = new Random().nextInt(2);
					if(ini == 0) a.inicializacionCreciente(0,0);
					else a.inicializacionCompleta(0,0);
				}
				/*Arbol a = nuevaPob.get(i).getArbol().copia();
				int total=a.getNumNodos();
				
				int s = (int) (Math.random()*total);
				if(s==0)s++;
				int prof= (int) (Math.random()*a.getMax_prof());
				prof++;
				Arbol b=new Arbol(prof);
				
				b.inicializacionCreciente(0,1);
				
				a.insert(a.getHijos(), b, s, 0);
				//a.insert(b,s);
				int nodos = a.obtieneNodos(a, 0);
				a.setNumNodos(nodos);
				
				a.profundidad(a.getHijos(),1,0);*/
				nuevaPob.get(i).setArbol(a);
			}
		}
		return nuevaPob;
		
	}

}
