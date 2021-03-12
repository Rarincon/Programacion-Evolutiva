package algoritmoGenetico.individuos;

public class IndividuoFuncion3 extends Individuo {

	public IndividuoFuncion3() {
		this.tamGenes = new int[2];
		this.min = new double[2];
		this.max = new double[2];
		this.min[0] = -10;
		this.min[1] = -10;
		this.max[0] = 10;
		this.max[1] = 10;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]); //Tamaño de x
		this.tamGenes[1] = this.tamGen(this.valorError, min[1], max[1]); //Tamaño de y
		int tamTotal = tamGenes[0] + tamGenes[1];
		this.cromosoma = new Integer[tamTotal];
		
		for(int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = r.nextInt(1); //Se puede mejorar
		
		this.aptitud=getValor();
	}
	
	@Override
	public double getValor() {
		double x=getFenotipo(0),y=getFenotipo(1);
		double valor= -1.0 * Math.abs(Math.sin(x) * Math.cos(y) * Math.exp(Math.abs(1.0 - (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) / Math.PI))));
		return valor;
	}

}
