package algoritmoGenetico.individuos;

import java.util.Map;
import java.util.Random;

public class Individuo {//implements Comparable<Individuo> {
	
	//public static Terminal terminales;
	//public static Funcion funciones;
	
	public static String terminales[];
	public static final String terminales6[] = { "AVANZA", "GIRA_DERECHA", "GIRA_IZQUIERDA"};
	public static final String funciones[] = { "SIC", "PROGN2", "PROGN3" };
	
	private Arbol arbol; // estrategia de rastreo
	private double aptitud;// función de evaluación
	private double puntuacion;//puntuacion relativa:adaptación/sumadaptacion
	private double punt_acu; // puntuacion acumulada
	
	private boolean elite; // elitismo
	
	private double fitness_bruto; //Aptitud antes de transformarla
	private String fenotipo;
	
	private int pasos,bocados;
	private Map mapa;
	private Hormiga hormiga;
	
	
	public Individuo(int profundidad, int tipoCreacion, int tipoMultiplexor) { //multiplexor sobra
		arbol = new Arbol(profundidad);
		switch(tipoCreacion){
			case 0:
				arbol.inicializacionCreciente(0,0);
				break;
			case 1:
				arbol.inicializacionCompleta(0,0);
				break;
			case 2:
				int ini = new Random().nextInt(2);
				if(ini == 0) arbol.inicializacionCreciente(0,0);
				else arbol.inicializacionCompleta(0,0);
				break;
		}
	}
	
	public Individuo() {
		
	}
	
	public void inicializa(int profundidad, int tipoCreacion) {
		arbol = new Arbol(profundidad);
		switch(tipoCreacion){
			case 0:
				arbol.inicializacionCompleta(0,0);
				break;
			case 1:
				arbol.inicializacionCreciente(0,0);
				break;
			case 2:
				int ini = new Random().nextInt(2);
				if(ini == 0) arbol.inicializacionCreciente(0,0);
				else arbol.inicializacionCompleta(0,0);
				break;
		}
	}
	
	
	public void fitness(int maxPasos) {
		//log.finest("Evaluando nuevo cromosoma");
		bocados = 0;
		pasos = 0;
		mapa = (Mapa) Controlador.getInstance().getMapa().clone();
		hormiga = new Hormiga();
		while (pasos < maxPasos && bocados<=mapa.getNumComida()) {
			//log.finest("Nueva ejecución del programa");
			try {
				ejecutarArbol(arbol,maxPasos);
			} catch (Exception e) {
				//this.log.severe("Cromosoma erróneo: \n"
					//	+ c.getCadena().toString());
				pasos++;
			}
		}

		setFitness(bocados);
		//log.finest("FIN Evaluando nuevo cromosoma. APTITUD=" + bocados);
	}

	protected void ejecutarArbol(Arbol arb, int maxPasos) { //
		//log.finest("Instrucción: " + nodo.getDato().toString());
		// mientras no se haya acabado el tiempo ni la comida
		if ((pasos < maxPasos)&&(aptitud<mapa.getNumComida())) {
			// si estamos encima de comida comemos
			if (mapa.getCasilla(hormiga.getX(), hormiga.getY())) {
				mapa.comer(hormiga.getX(), hormiga.getY());
				bocados++;
			}
			if (arb.getValor().equals(Tipo.PROGN3)) {
				ejecutarArbol(arb.at(0),maxPasos);
				ejecutarArbol(arb.at(1),maxPasos);
				ejecutarArbol(arb.at(2),maxPasos);
			} else if (arb.getValor().equals(Tipo.PROGN2)) {
				ejecutarArbol(arb.at(0),maxPasos);
				ejecutarArbol(arb.at(1),maxPasos);
			} else if (arb.getValor().equals(Tipo.SIC)) {
				int[] sigPos = hormiga.getSigPos();
				if (mapa.getCasilla(sigPos[0], sigPos[1])) {
					// Hay comida delante
					ejecutarArbol(arb.at(0),maxPasos);
				} else {
					// No hay comida delante
					ejecutarArbol(arb.at(1),maxPasos);
				}
			} else if (arb.getValor().equals(Tipo.AVANZA)) {
				hormiga.avanzar();
				pasos++;
			} else if (arb.getValor().equals(Tipo.GIRA_DERECHA)) {
				hormiga.girarDer();
				pasos++;
			} else if (arb.getValor().equals(Tipo.GIRA_IZQUIERDA)) {
				hormiga.girarIzq();
				pasos++;
			}
		}
	}

	
	public double evaluar() {
		return aptitud;
	}
	
	public Individuo copia() {
		Individuo n= new Individuo();
		n.setArbol(getArbol());
		n.setFitness(getFitness());
		n.setPunt(getPunt());
		n.setPuntAcu(getPuntAcu());
		return n;		
	}
	
	public void setArbol(Arbol a) {
		arbol= a;
	}
	
	public void setFitness(double f) {
		aptitud=f;
	}
	
	public void setPunt(double f) {
		puntuacion=f;
	}
	
	public void setPuntAcu(double f) {
		punt_acu=f;
	}
	
	public Arbol getArbol() {
		return arbol;
	}
	
	public double getPunt() {
		return puntuacion;
	}
	
	public double getPuntAcu() {
		return punt_acu;
	}
	
	public double getFitness() {
		return aptitud;
	}
	
	public int getTamCromosoma() {
		return 0;
	}

	public Integer[] getCromosoma() {
		return null;
	}
		
	public void setCromosoma(Integer[] crom) {
		
	}
	
	public void setPosCromosoma(int pos, int valor) {
		
	}
	
	/*public int compareTo(Individuo arg0) {
		if(this.getFitness() < arg0.getFitness()) return 1;
		else if(this.getFitness()==arg0.getFitness()) return 0;
		else return -1;
	}*/
}