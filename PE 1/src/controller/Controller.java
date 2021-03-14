package controller;

import java.util.List;

import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.mutacion.Basica;

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
	
	
	
	
	public Controller(AlgoritmoGenetico Algon) {
		AlGen=Algon;
		indi=0;
		cruce=0;
		Seleccion=0;
	}

	public void run() {
		//Algen.reset();
		AlGen.iniciarPoblacion(indi); //Done
		AlGen.evaluar();
		while(this.GenActual < this.maxGeneraciones) {
			
			AlGen.avanza(Seleccion,cruce);
			//System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
			//System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
			GenActual++;
		}
		AlGen.run();
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
	
	public void reset(){
		GenActual = 0;
		//initPopulation();
		//for (GeneticAlgorithmObserver o : _observers) o.onReset();
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

}
