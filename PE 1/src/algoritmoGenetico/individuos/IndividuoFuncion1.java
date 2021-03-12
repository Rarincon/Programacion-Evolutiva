package algoritmoGenetico.individuos;

public class IndividuoFuncion1 extends Individuo{

	public IndividuoFuncion1() {
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -3.0;
		this.min[1] = 12.1;
		this.max[0] = 4.1;
		this.max[1] = 5.8;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]); //Tamaño de x1
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]); //Tamaño de x2
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
		
		for(int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = r.nextInt()%1; //Se puede mejorar //Ay que cambiar el metodo
		
		this.aptitud=getValor(); //Esto puede sobrar
	}
	
	public double getValor() {
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
	}

	/*
	public double evaluar() {
		int x1 = getFenotipo(0); 
		int x2 = getFenotipo(1);
		double fitness_actual= 21.5 + x1 * Math.sin(4*Math.PI*x1) +x2 *Math.sin(20* Math.PI *x2);
		return fitness_actual;
	}*/
}
