package controller;


import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.AlgoritmoGenObserver;
import algoritmoGenetico.AlgoritmoGenetico;

public class Controller {

	AlgoritmoGenetico AlGen;
	private int tamPoblacion;
	private int maxGeneraciones;
	private int GenActual;
	private double probCruce;
	private double probMutacion;
	private int tamTorneo;
	private int Seleccion;
	private int cruce;
	private int indi;
	private Boolean elitismo;
	private List<AlgoritmoGenObserver> observers;
	
	public Controller(AlgoritmoGenetico Algon) {
		this.AlGen=Algon;
		this.indi=0;
		this.cruce=0;
		this.Seleccion=0;
		this.tamPoblacion=100;
		this.GenActual=0;
		this.maxGeneraciones=100;
		this.elitismo=false;
		observers = new ArrayList<AlgoritmoGenObserver>();
	}

	public void run() {
		reset();
		AlGen.iniciarPoblacion(indi, tamPoblacion); //Done
		AlGen.evaluar();
		while(this.GenActual < this.maxGeneraciones) {
			update();
		}
	}
	
	public void update() {
		if(elitismo)AlGen.nextElisGen(Seleccion,cruce);
		else AlGen.nextGen(Seleccion,cruce);
		//System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
		//System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
		GenActual++;
		for (AlgoritmoGenObserver o : observers) o.update(GenActual, AlGen.getResults());
	}
	
	public void addObserver(AlgoritmoGenObserver o) {
		observers.add(o);
	}
	
	public void reset(){
		GenActual = 0;
		initPopulation();
		for (AlgoritmoGenObserver o : observers) o.reset();
	}
	
	public void initPopulation() {
		this.AlGen.limpiarPoblacion();
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
	public void setIndi(int selectedIndex) {
		indi= selectedIndex;	
	}
	public void setElitism(boolean b) {//REPASAR GUI
		elitismo=b;
	}
}
