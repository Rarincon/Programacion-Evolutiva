package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class IndividuoFuncion1 extends Individuo{

	public IndividuoFuncion1(double p) {
		super(p);
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -3.0;
		this.min[1] = 4.1;
		this.max[0] = 12.1;
		this.max[1] = 5.8;
		this.tamGenes[0] = this.tamGen(this.precision, min[0], max[0]); //Tamaño de x1
		this.tamGenes[1] = this.tamGen(this.precision, min[1], max[1]); //Tamaño de x2
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
		
		//inicializa();
		//this.aptitud=evaluar();
	}
	
	public double evaluar() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return 21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2);
	}

	public Individuo copia() {
		Individuo nuevo = new IndividuoFuncion1(precision);
		nuevo.setCromosoma(getCromosoma());
		nuevo.setFitness(this.getFitness());
		nuevo.setPunt(getPunt());
		nuevo.setPuntAcum(getPuntAcum());
		return nuevo;
	}

	@Override
	public List<Double> getFenotipos() {
		List<Double> results= new ArrayList<Double>();
		results.add(getFenotipo(0));
		results.add(getFenotipo(1));
		return results;
	}
}