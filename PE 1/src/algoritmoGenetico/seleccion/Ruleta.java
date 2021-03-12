package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Ruleta extends Seleccion {
	private Integer[] sel_super;
	private double prob;
	private int pos_super;
	private Random r = new Random();
	//private List<Individuo<?>> nuevaPob;
	 
	public Ruleta(List<Individuo> _poblacion, int _tamPoblacion) {   //REPASAR, eliminar los valores negativos
		super(_poblacion, _tamPoblacion);
		
		sel_super = new Integer[this.tamPoblacion];
		
	}
	
	public List<Individuo> selecciona() {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		for(int i=0; i<this.tamPoblacion; i++) {
			prob=r.nextDouble()%1; //PROBABILIDAD COMPROBAR
			pos_super=0;
			while((this.prob > this.poblacion.get(pos_super).getPuntAcum()) && (pos_super < this.tamPoblacion)) {
				pos_super++;
			}
			sel_super[i]=pos_super; 
		}
		for(int i=0; i<this.tamPoblacion; i++) {
			nuevaPob.add(this.poblacion.get(sel_super[i])); //Comprobar si rompe encapsulacion o si se hace correctamente
		}
		return nuevaPob;  //Comprobar si rompe encapsulacion
		
	}
}
