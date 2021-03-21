package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class IndividuoFuncion3 extends Individuo {

	public IndividuoFuncion3(double p) {
		super(p);
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -10;
		this.min[1] = -10;
		this.max[0] = 10;
		this.max[1] = 10;
		this.tamGenes[0] = this.tamGen(this.precision, min[0], max[0]); 
		this.tamGenes[1] = this.tamGen(this.precision, min[1], max[1]);
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
		
		//inicializa();
		//this.aptitud=evaluar();
	}
	
	@Override
	public double evaluar() {
		double x=getFenotipo(0),y=getFenotipo(1);
		double valor= -1.0 * Math.abs(Math.sin(x) * Math.cos(y) * Math.exp(Math.abs(1.0 - (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / Math.PI))));
		return valor;
	}
	
	public Individuo copia() {
		Individuo nuevo = new IndividuoFuncion3(precision);
		nuevo.setCromosoma(getCromosoma());
		nuevo.setFitness(this.getFitness());
		nuevo.setPunt(getPunt());
		nuevo.setPuntAcum(getPuntAcum());
		return nuevo;
	}
	
	public List<Double> getFenotipos() {
		List<Double> results= new ArrayList<Double>();
		results.add(getFenotipo(0));
		results.add(getFenotipo(1));
		return results;
	}

}