package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.tablero.Hormiga;
import algoritmoGenetico.tablero.Tablero;
import controller.Controller;
import utils.Pair;

public class Individuo implements Comparable<Individuo> {
	
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
	private Tablero tab;
	private Hormiga hormiga;
	private List<Pair<Integer,Integer>>recorrido;

	public Individuo() {
		
	}
	
	public void inicializa(int profundidad, int tipoCreacion) {
		arbol = new Arbol(profundidad);
		//List<String> a;
		switch(tipoCreacion){
			case 0:
				arbol.inicializacionCompleta(0,1);
				//a=arbol.toArray();
				break;
			case 1:
				arbol.inicializacionCreciente(0,1);
				//a=arbol.toArray();
				break;
			case 2:
				int ini = new Random().nextInt(2);
				if(ini == 0) arbol.inicializacionCreciente(0,0);
				else arbol.inicializacionCompleta(0,0);
				break;
		}
	}
	
	
	public void evalua(int maxPasos) {
		bocados = 0;
		pasos = 0;
		tab = (Tablero) Controller.getTablero().clone();
		hormiga = new Hormiga();
		recorrido = new ArrayList<Pair<Integer,Integer>>();
		while(pasos < maxPasos && bocados<=tab.getNumComida()) {
			recorreArbol(arbol,maxPasos);
		}
		setFitness(bocados);
	}

	private void recorreArbol(Arbol arb, int maxPasos) { //
		if ((pasos < maxPasos)&&(bocados<tab.getNumComida())) {
			if (tab.getCasilla(hormiga.getX(), hormiga.getY())) {
				tab.comer(hormiga.getX(), hormiga.getY());
				bocados++;
			}
			if (arb.getValor().equals("PROGN3")) {
				recorreArbol(arb.getHijoAt(0),maxPasos);
				recorreArbol(arb.getHijoAt(1),maxPasos);
				recorreArbol(arb.getHijoAt(2),maxPasos);
			} else if (arb.getValor().equals("PROGN2")) {
				recorreArbol(arb.getHijoAt(0),maxPasos);
				recorreArbol(arb.getHijoAt(1),maxPasos);
			} else if (arb.getValor().equals("SIC")) {
				Pair<Integer,Integer> sigPos = hormiga.getSigPos();
				if (tab.getCasilla(sigPos.getFirst(), sigPos.getSecond())) {//hay comida
					recorreArbol(arb.getHijoAt(0),maxPasos);
				} else {
					recorreArbol(arb.getHijoAt(1),maxPasos);
				}
			} else if (arb.getValor().equals("AVANZA")) {
				hormiga.avanza();
				recorrido.add(hormiga.getPos());
				pasos++;
			} else if (arb.getValor().equals("GIRA_DERECHA")) {
				hormiga.giraDer();
				recorrido.add(hormiga.getPos());
				pasos++;
			} else if (arb.getValor().equals("GIRA_IZQUIERDA")) {
				hormiga.giraIzq();
				recorrido.add(hormiga.getPos());
				pasos++;
			}
		}
	}
	
	
	public double evaluar() {
		return aptitud;
	}
	
	public Individuo copia() {
		Individuo n= new Individuo();
		n.setArbol(getArbol().copia());
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
	
	public List<Pair<Integer,Integer>> getRecorrido(){
		return recorrido;
	}
	
	public String getArbolText() {
		return arbol.toString();
	}
	
	public static boolean esFuncion(String a) {
		return a.equals("PROGN3") || a.equals("PROGN2") || a.equals("SIC");
	}
	
	public static boolean esTerminal(String a) {
		return a.equals("GIRA_DERECHA") || a.equals("GIRA_IZQUIERDA") || a.equals("AVANZA");
	}
	
	public int compareTo(Individuo arg0) {
		if(this.getFitness() < arg0.getFitness()) return 1;
		else if(this.getFitness()==arg0.getFitness()) return 0;
		else return -1;
	}

}