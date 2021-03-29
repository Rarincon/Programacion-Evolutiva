package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividuoFuncion5  extends Individuo{

	private int N;
	
	public IndividuoFuncion5(int n, double p) {
		super(p);
		N=n;
		this.tamGenes = new int[n];
		this.min = new double[1];
		this.max = new double[1];
		this.min[0] = 0;
		this.max[0] = Math.PI;
		
		this.cromosoma = new Double[N];	
	}

	@Override
	public double evaluar() {
		double valor=0;
		double xi,x;
		for(int i=1;i<=N;i++) {
			xi= (double) cromosoma[i-1];
			valor+=Math.sin(xi) * Math.pow(Math.sin(((i+1) * (xi * xi)) / Math.PI), 20.0);
		}
		return valor * -1.0;
	}

	@Override
	public Individuo copia() {
		Individuo nuevo = new IndividuoFuncion5(N, precision);
		nuevo.setCromosoma(getCromosoma());
		nuevo.setFitness(this.getFitness());
		nuevo.setPunt(getPunt());
		nuevo.setPuntAcum(getPuntAcum());
		return nuevo;
	}

	@Override
	public List<Double> getFenotipos() {
		List<Double> results= new ArrayList<Double>();
		for(int i = 0; i < N; i++) results.add((Double) cromosoma[i]);
		return results;
	}

	@Override
	public void inicializa() { 
		//Random r= new Random();
		for(int i = 0; i < N; i++) {
			this.cromosoma[i] = Math.random()*max[0]; 
		}
	}
	
	@Override
	public void setCromosoma(Object[] crom) {
		for(int i=0; i<N;i++) {
			this.cromosoma[i]=(Double) crom[i];
		}
	}
}