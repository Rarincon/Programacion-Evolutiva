package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class Eggholder extends Individuo {

	public Eggholder(double p) {
		super(p);
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = min[1] = -512;
		this.max[0] = max[1]= 512;
		this.tamGenes[0] = tamGenes[1]= this.tamGen(this.precision, min[0], max[0]); //Tamaño de x1 y x2
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
	}

	@Override
	public double evaluar() {
		double x1 = getFenotipo(0);
		double x2 = getFenotipo(1);

		double term1 = -(x2+47) * Math.sin(Math.sqrt(Math.abs(x2+x1/2+47)));
		double term2 = -x1 * Math.sin(Math.sqrt(Math.abs(x1-(x2+47))));

		return term1 + term2;
	}

	@Override
	public Individuo copia() {
		Individuo nuevo = new Eggholder(precision);
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
