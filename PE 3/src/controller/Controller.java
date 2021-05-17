package controller;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.AlgoritmoGenObserver;
import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.tablero.Tablero;

public class Controller {

	AlgoritmoGenetico AlGen;
	private int tamPoblacion;
	private int maxGeneraciones;
	private int GenActual;
	private double probCruce;
	private double probMutacion;
	private int tamTorneo;
	private int Seleccion;
	private int Inicializacion;
	private int cruce;
	private int mutacion;
	private double elitismoRango;
	private boolean apocal;
	
	private static Tablero tab;
	
	private List<AlgoritmoGenObserver> observers;
	
	public Controller() {
		this.AlGen=new AlgoritmoGenetico();
		this.mutacion=0;
		this.cruce=0;
		this.Seleccion=0;
		this.Inicializacion=0;
		this.tamPoblacion=100;
		this.GenActual=0;
		this.maxGeneraciones=100;
		this.elitismoRango=0.03;
		this.probCruce=0.6;
		this.probMutacion=0.05;
		this.apocal=false;
		
		tab= new Tablero();
		tab.cargarMapa("resources/SantaFe.txt");
		observers = new ArrayList<AlgoritmoGenObserver>();
	}

	public void run() {
		reset();
		AlGen.init(Inicializacion, Seleccion, cruce,mutacion); 
		AlGen.evaluar();
	}
	
	public void run_sim() {
		update();
	}
	
	public void update() {
		AlGen.nextElisGen();
		GenActual++;
		for (AlgoritmoGenObserver o : observers) o.update(GenActual, AlGen.getResults());
	}
	
	public void addObserver(AlgoritmoGenObserver o) {
		observers.add(o);
	}
	
	public void reset(){
		
		GenActual = 0;
		AlGen.reset();
		load();
		for (AlgoritmoGenObserver o : observers) o.reset();
	}	
	
	public void load() {
		AlGen.setProbMut(probMutacion);
		AlGen.setProbCruc(probCruce);
		AlGen.setTamTor(tamTorneo);
		AlGen.setEliteR(elitismoRango);
		AlGen.setTamPob(tamPoblacion);
		AlGen.setApoc(apocal);
	}

	
	public void setPob(int i) {
		tamPoblacion=i;
	}	
	public void setMaxGen(int i) {
		maxGeneraciones=i;		
	}	
	public void setProbCruce(double i) {
		probCruce=i;
	}	
	public void setProbMut(double i) {
		probMutacion=i;
	}
	public void setTamTorneo(int i) {
		tamTorneo=i;
	}	
	public void setSelection(int selectedIndex) {
		Seleccion=selectedIndex;
	}
	public void setCruce(int selectedIndex) {
		cruce= selectedIndex;	
	}
	public void setMutac(int selectedIndex) {
		mutacion= selectedIndex;	
	}
	public void setInic(int selectedIndex) {
		Inicializacion=selectedIndex;
	}
	public void setElitismRango(double value) {
		elitismoRango=value;
	}
	public void setApocal(boolean selected) {
		apocal=selected;	
	}
	
	public int getGenAct() {
		return GenActual;
	}
	public static Tablero getTablero() {
		return tab;
	}


	
}
