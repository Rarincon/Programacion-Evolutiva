package algoritmoGenetico.individuos;

public class IndividuoFuncion4 extends Individuo {

	private int N;
	
	public IndividuoFuncion4(int n) {
		N=n;
		this.tamGenes = new int[n];
		this.min = new double[1];
		this.max = new double[1];
		this.min[0] = 0;
		this.max[0] = Math.PI;
		this.tamGenes[0] = this.tamGen(this.valorError, min[0], max[0]); //Tamaño de xi
		int tamTotal=0;
		for(int i = 0; i < n; i++)
			tamTotal+=tamGenes[0];
		this.cromosoma = new Integer[tamTotal];		
		for(int i = 0; i < tamTotal; i++)
			this.cromosoma[i] = r.nextInt(1); //Se puede mejorar
		
		this.aptitud=getValor();
	}
	
	@Override
	public double getValor() {
		double valor=0;
		double xi= this.getFenotipo(0); //REVISAR, ya que no se si todos tienen el mismo, Esta mal el fenotipo
		for(int i=1;i<=N;i++) 
			valor+=Math.sin(xi) * Math.pow(Math.sin(((i+1) * (xi * xi)) / Math.PI), 20.0);
		return valor * -1.0;
	}

}
