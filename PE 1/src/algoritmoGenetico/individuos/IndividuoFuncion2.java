package algoritmoGenetico.individuos;

public class IndividuoFuncion2 extends Individuo{
	
	public IndividuoFuncion2() {
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -10;
		this.min[1] = -10;
		this.max[0] = 10;
		this.max[1] = 10;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]); //Tamaño de x1
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]); //Tamaño de x2
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
		
		for(int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = r.nextInt(1); //Se puede mejorar
		
		//this.aptitud=getValor();
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
		Individuo nuevo = new IndividuoFuncion2();
		nuevo.setCromosoma(getCromosoma());
		return nuevo;
	}
}
