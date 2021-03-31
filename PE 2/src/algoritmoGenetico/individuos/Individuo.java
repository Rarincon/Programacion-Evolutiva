package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public abstract class Individuo implements Comparable<Individuo> {
	
	static private final int TAM=26;
	static private final String[] abecedario={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	protected Object[] cromosoma;
	//protected int valorError;
	protected Random r = new Random();
	
	protected double aptitud;
	protected double puntuacion;
	protected double punt_acum;
	
	protected double precision;
	
	public Individuo(double p) {
		precision=p;
		cromosoma=  new Integer[TAM];
	}
	
	public void inicializa() {
		List<Integer> shuffle= new ArrayList<Integer>();
		for(int i=0;i<TAM;i++)shuffle.add(i);
		Collections.shuffle(shuffle);
		for(int i=0;i<TAM;i++)cromosoma[i]=shuffle.get(i);
		
		/*int caracteres = (int)(Math.random()*20)+2; 
		for (int i=0; i<caracteres; i++){ 
			int codigoAscii = (int)Math.floor(Math.random()*(122 -97)+97);
			cromosoma[i]=codigoAscii;
	   }*/
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
	
	public int compareTo(Individuo arg0) {
		if(this.getFitness() < arg0.getFitness()) return 1;
		else if(this.getFitness()==arg0.getFitness()) return 0;
		else return -1;
	}
}