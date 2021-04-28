package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Restos implements Seleccion { 

	public List<Individuo> selecciona(List<Individuo> p, int tam) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		List<Individuo>sel_super;
		sel_super=new ArrayList<Individuo>();
		
		double TFitness=0;
		for(Individuo v: p){
			TFitness+=v.getFitness();
		}
		
		for(Individuo v: p){
			if(((v.getFitness()/TFitness)*p.size())>1) //ESE VALOR PUEDE VARIAR, PUEDE SER 1 o 1'5
				sel_super.add(v);
		}
		List<Individuo> rule= new ArrayList<Individuo>();
				
		rule= new Ruleta().selecciona(p, p.size()-sel_super.size());
		
		for(Individuo v: sel_super)
			nuevaPob.add(v.copia());
		for(Individuo v: rule)
			nuevaPob.add(v.copia());
		return nuevaPob;
	}
}