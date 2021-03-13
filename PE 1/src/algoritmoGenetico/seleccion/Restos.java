package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Restos extends Seleccion { //REVISAR PARA LAS COPIAS DE LOS NUEVOS INDIVIDUOS
	private List<Individuo> sel_super;
	private double TFitness;
	private int pos_super;
	private Random r = new Random();
	//private List<Individuo<?>> nuevaPob;
		
	public Restos(List<Individuo> _poblacion, int _tamPoblacion) { 
		super(_poblacion, _tamPoblacion);
		TFitness=0;
		for(Individuo v: _poblacion){
			TFitness+=v.getFitness();
		}
	}

	public List<Individuo> selecciona() {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		for(Individuo v: poblacion){
			if((v.getFitness()/TFitness)>1) //ESE VALOR PUEDE VARIAR, PUEDE SER 1 o 1'5
				sel_super.add(v);
		}
		List<Individuo> rule= new ArrayList<Individuo>();
		rule= new Ruleta(poblacion, poblacion.size()-sel_super.size()).selecciona();
		
		for(Individuo v: sel_super)
			nuevaPob.add(v);
		for(Individuo v: rule)
			nuevaPob.add(v);
		return nuevaPob;
	}
}