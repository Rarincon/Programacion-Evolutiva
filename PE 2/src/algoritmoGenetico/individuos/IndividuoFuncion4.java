package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;

public class IndividuoFuncion4 extends Individuo {

	private int N;
	
	public IndividuoFuncion4(int n, double p) {
		super(p);
		N=n;
		this.tamGenes = new int[n];
		this.min = new double[1];
		this.max = new double[1];
		this.min[0] = 0;
		this.max[0] = Math.PI;
		this.tamGenes[0] = this.tamGen(this.precision, min[0], max[0]);
		int tamTotal=0;
		for(int i = 0; i < n; i++) {
			tamGenes[i]=tamGenes[0];
			tamTotal+=tamGenes[i];
		}
		this.cromosoma = new Integer[tamTotal];		
		
		//inicializa();
		//this.aptitud=evaluar();
	}
	
	@Override
	public double evaluar() {
		double valor=0;
		double xi; 
		for(int i=1;i<=N;i++) {
			xi= this.getFenotipo(i-1);
			valor+=Math.sin(xi) * Math.pow(Math.sin(((i+1) * (xi * xi)) / Math.PI), 20.0);
		}
		return valor * -1.0;
	}
	
	public double getFenotipo(int x) {
		 return min[0]+ bin2dec(tamGenes[0]*x,tamGenes[0]*(x+1)-1) *((max[0]-min[0])/(Math.pow(2,tamGenes[0])-1));
	}
	
	public Individuo copia() {
		Individuo nuevo = new IndividuoFuncion4(N, precision);
		nuevo.setCromosoma(getCromosoma());
		nuevo.setFitness(this.getFitness());
		nuevo.setPunt(getPunt());
		nuevo.setPuntAcum(getPuntAcum());
		return nuevo;
	}
	
	public List<Double> getFenotipos() {
		List<Double> results= new ArrayList<Double>();
		double x;
		for(int i=1;i<=N;i++) {
			x= this.getFenotipo(i-1);
			results.add(x);
		}
		return results;
	}
}

