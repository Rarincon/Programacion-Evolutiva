package algoritmoGenetico.individuos;

import java.util.List;
import java.util.Random;


public abstract class Individuo implements Comparable<Individuo> {
	protected Object[] cromosoma;
	protected int[] tamGenes;
	protected double[] min;
	protected double[] max;
	//protected int valorError;
	protected Random r = new Random();
	
	protected double aptitud;
	protected double puntuacion;
	protected double punt_acum;
	
	protected double precision;
	
	public Individuo(double p) {
		precision=p;
	}
	
	public int tamGen(double valorError, double min, double max) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}
	
	public double getFenotipo(int x) {
		if(x==0) {
			 return min[0]+ bin2dec(0,tamGenes[0]-1) *((max[0]-min[0])/(Math.pow(2,tamGenes[0])-1));		 
		}
		else {
			 return min[1]+ bin2dec(tamGenes[0], cromosoma.length-1)*((max[1]-min[1])/(Math.pow(2,tamGenes[1])-1));		
		}
	}
	
	public long bin2dec(int ini, int fin) {
		long valor=0;
		int pos =0;
		for(int i = fin; i >= ini; i--){
			if(valido((int) cromosoma[i])){
				double num = (float)Math.pow(2, pos);
				valor += num;
			}
			pos++;
		}
		return valor;
	}
	
	public void inicializa() {
		int tamTotal=0;
		for(Integer e: tamGenes)
			tamTotal+=e;
		for(int i = 0; i < tamTotal; i++) {
			this.cromosoma[i] = (int) (Math.random()*2);
		}
	}
	
	private Boolean valido(int t) {
		if(t==1)return true;
		else return false;
	}
	
	public void setFitness(double f) {
		aptitud=f;
	}
	
	public void setPunt(double f) {
		puntuacion=f;
	}
	
	public void setPuntAcum(double f) {
		punt_acum=f;
	}
	
	public double getPunt() {
		return puntuacion;
	}
	
	public double getPuntAcum() {
		return punt_acum;
	}
	
	public double getFitness() {
		return aptitud;
	}
	
	public int getTamCromosoma() {
		return cromosoma.length;
	}

	public Object[] getCromosoma() {
		return cromosoma;
	}
		
	public void setCromosoma(Object[] crom) {
		for(int i=0; i<cromosoma.length;i++) {
			this.cromosoma[i]=(Integer) crom[i];
		}
	}
	
	public void setPosCromosoma(int pos, int valor) {
		this.cromosoma[pos]=valor;
	}
	
	public abstract double evaluar();
	public abstract Individuo copia();
	public abstract List<Double> getFenotipos();
	
	public int compareTo(Individuo arg0) {
		if(this.getFitness() < arg0.getFitness()) return 1;
		else if(this.getFitness()==arg0.getFitness()) return 0;
		else return -1;
	}
}