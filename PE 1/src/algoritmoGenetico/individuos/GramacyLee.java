package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class GramacyLee extends Individuo{
	
	public GramacyLee(double p) {
		super(p);
		this.tamGenes = new int[1];
		this.min = new double[1];
		this.max = new double[1];
		this.min[0] = 0.5;
		this.max[0] = 2.5;
		this.tamGenes[0] = this.tamGen(this.precision, min[0], max[0]); //Tamaño de x
		int tamTotal = tamGenes[0];
		this.cromosoma = new Integer[tamTotal];
	}

	@Override
	public double evaluar() {
		double x= getFenotipo(0);
		double term1 = Math.sin(10*Math.PI*x)/(2*x);
		double term2 = Math.pow(x-1,4);

		return term1 + term2;
	}
	
	@Override
	public Individuo copia() {
		Individuo nuevo = new GramacyLee(precision);
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
		return results;
	}
}