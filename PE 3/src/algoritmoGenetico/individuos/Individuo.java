package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.tablero.Hormiga;
import algoritmoGenetico.tablero.Tablero;
import controller.Controller;
import utils.Pair;

public class Individuo implements Comparable<Individuo> {
	
	public static final String terminales6[] = { "AVANZA", "GIRA_DERECHA", "GIRA_IZQUIERDA"};
	public static final String funciones[] = { "SIC", "PROGN2", "PROGN3" };
	
	private Arbol arbol; // estrategia de rastreo
	private double aptitud;// función de evaluación
	private double puntuacion;//puntuacion relativa
	private double punt_acu; // puntuacion acumulada
	
	private int pasos,bocados;
	private Tablero tab;
	private Hormiga hormiga;
	private List<Pair<Integer,Integer>>recorrido;
	private int Profundidad;

	public Individuo() {
		Profundidad=0;
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
				if(ini == 0) arbol.inicializacionCreciente(10,0);
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
		recorrido.add(new Pair<Integer,Integer>(0,0));
		while(pasos < maxPasos && bocados<tab.getNumComida()) {
			recorreArbol(arbol,maxPasos);
		}
		setFitness(bocados);		
		Profundidad= arbol.getAltura(arbol.getHijos(), 1);
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
				if (tab.getCasilla(sigPos.getFirst(), sigPos.getSecond())) {//HAY COMIDA
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
	
	public Individuo copia() {
		Individuo n= new Individuo();
		n.setArbol(getArbol().copia());
		n.setFitness(getFitness());
		n.setPunt(getPunt());
		n.setPuntAcu(getPuntAcu());
		n.setProfundidad(getProfundidad());
		n.setRecorrido(getRecorrido());
		n.setBocados(getBocados());
		return n;		
	}
	
	public void setBocados(int bocados2) {
		bocados=bocados2;		
	}

	private int getBocados() {
		return bocados;
	}

	public void setRecorrido(List<Pair<Integer, Integer>> recorrido2) {
		recorrido=new ArrayList<Pair<Integer, Integer>>();
		for(Pair<Integer,Integer> e : recorrido2)
			recorrido.add(new Pair<Integer,Integer>(e.getFirst(),e.getSecond()));		
	}

	public void setProfundidad(int profundidad2) {
		Profundidad=profundidad2;
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
	
	public int getProfundidad() {
		return Profundidad;
	}
	
	public List<Pair<Integer,Integer>> getRecorrido(){
		List<Pair<Integer,Integer>> a = new ArrayList<Pair<Integer,Integer>>();
		for(Pair<Integer,Integer> e : recorrido)
			a.add(new Pair<Integer,Integer>(e.getFirst(),e.getSecond()));
		return a;
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