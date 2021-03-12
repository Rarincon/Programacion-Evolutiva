package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class Estocastico extends Seleccion {
	private Integer[] sel_super;
	//private double[] prob;
	private int pos_super;
	private Random r;
	//private List<Individuo<?>> nuevaPob;
	//private int sumTotal;
	
	public Estocastico(List<Individuo> _poblacion, int _tamPoblacion) {
		super(_poblacion, _tamPoblacion);
		
		r = new Random();
		sel_super = new Integer[this.tamPoblacion];
		//nuevaPob = new ArrayList<Individuo<?>>();
		/*prob= new double[_tamPoblacion];
		sumTotal=0;
		for(int i=0; i<this.tamPoblacion; i++) {
			sumTotal+=_poblacion.get(i).getFitness(); 
		}*/
	}
	
	public List<Individuo> selecciona() {
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		
		double dist=1/poblacion.size();
		double rand = 0 + ( 1 - 0 ) * r.nextDouble();
		pos_super=0;
		
		for(int i=0; i<this.tamPoblacion; i++) { //REVISAR PARA COMPRENDER
			while(pos_super < poblacion.size() && poblacion.get(pos_super).getPuntAcum()<rand)
				pos_super++;
			sel_super[i]=pos_super;
			rand+=dist;
			//prob[i]=poblacion[i].getFitness()/sumTotal; //Otro tipo de muestreo
		}
		
		for(int i=0; i<this.tamPoblacion; i++) {
			nuevaPob.add(poblacion.get(sel_super[i])); //Mirar lo de las copias
		}
		return nuevaPob;  //Comprobar si rompe encapsulacion
		
	}
}