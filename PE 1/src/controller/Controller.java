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
	private int indi,NGen;
	private Boolean Elitismo;
	private double elitismoRango;
	private double precision;
	private List<AlgoritmoGenObserver> observers;
	
	public Controller(AlgoritmoGenetico Algon) {
		this.AlGen=Algon;
		this.indi=0;
		this.cruce=0;
		this.Seleccion=0;
		this.tamPoblacion=100;
		this.GenActual=0;
		this.maxGeneraciones=100;
		this.Elitismo=false;
		this.elitismoRango=0.03;
		this.probCruce=0.6;
		this.probMutacion=0.05;
		this.precision=0.001;
		NGen=2;
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
		if(Elitismo)AlGen.nextElisGen(Seleccion,cruce);
		else AlGen.nextGen(Seleccion,cruce);
		//if(AlGen.getMaximizar())AlGen.Mejor();
		//else AlGen.Peor();
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
		this.AlGen.limpiarPoblacion();
		load();
		for (AlgoritmoGenObserver o : observers) o.reset();
	}	
	
	public void load() {
		AlGen.setNGen(NGen);
		AlGen.setProbMut(probMutacion);
		AlGen.setProbCruc(probCruce);
		AlGen.setTamTor(tamTorneo);
		AlGen.setPrec(precision);
		AlGen.setEliteR(elitismoRango);
	}
	
	public boolean getElitism() {
		return Elitismo;
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
	public void setElitism(boolean b) {
		Elitismo=b;
	}
	public void setElitismRango(double value) {
		elitismoRango=value;
	}
	public void setPrecision(double value) {
		precision=value;
	}
	
	public void setNGenos(int value) {
		NGen=value;
	}
}
