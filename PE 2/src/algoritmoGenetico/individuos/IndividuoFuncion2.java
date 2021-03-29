package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class IndividuoFuncion2 extends Individuo{
	
	public IndividuoFuncion2(double p) {
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
	
	public double evaluar() {
		double sum1=0,sum2=0;
		double x1 = this.getFenotipo(0);
		double x2 = this.getFenotipo(1);
		for(int i=1;i<=5;i++) {
			sum1+=(i*Math.cos((i+1)*x1+i));
			sum2+=(i*Math.cos((i+1)*x2+i));
		}
		return sum1*sum2;
	}
	
	public Individuo copia() {
		Individuo nuevo = new IndividuoFuncion2(precision);
		nuevo.setCromosoma(getCromosoma());
		nuevo.setFitness(getFitness());
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