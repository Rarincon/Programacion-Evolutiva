package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruces.CruceAritmetico;
import algoritmoGenetico.cruces.CruceBLX;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.cruces.CruceSBX;
import algoritmoGenetico.cruces.CruceUniforme;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoFuncion1;
import algoritmoGenetico.seleccion.Estocastico;
import algoritmoGenetico.seleccion.Restos;
import algoritmoGenetico.seleccion.Ruleta;
import algoritmoGenetico.seleccion.TorneoDeterministico;
import algoritmoGenetico.seleccion.TorneoProbabilistico;
import algoritmoGenetico.seleccion.Truncamiento;

public class AlgoritmoGenetico {
	
	private int tamPoblacion;
	private List<Individuo> poblacion;
	private double[] fitness;
	private int maxGeneraciones;
	private int GenActual;
	private double probCruce;
	private double probMutacion;
	private int tamTorneo;
	private Individuo elMejor;
	private int pos_mejor;
	//private double aptitudMejor;
	
	
	
	public AlgoritmoGenetico(int tamPob, int maxGen, double probCruc, double probMut, int tamTor) {
		tamPoblacion=tamPob;
		maxGeneraciones=maxGen;
		probCruce=probCruc;
		probMutacion=probMut;
		tamTorneo=tamTor;
		GenActual=0;
		elMejor = new IndividuoFuncion1();
	}
	
	private void iniciarPoblacion() {
		poblacion= new ArrayList<Individuo>(); //Camnio a list, no determino el tamaño
		fitness= new double[tamPoblacion];
		
		for(int i=0;i<tamPoblacion;i++) {
			poblacion.add(new IndividuoFuncion1()); //Habra que seleccionar que funcione quiere
			fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
		}
		
		pos_mejor=0;
		//aptitudMejor=poblacion[0].getFitness();
		
	}
	
	public void seleccion(int tipo) {
		if(tipo == 0){
			poblacion = new Ruleta(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 1){
			poblacion = new Estocastico(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 2){
			poblacion = new TorneoProbabilistico(poblacion,tamPoblacion,3).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 3){
			poblacion = new TorneoDeterministico(poblacion,tamPoblacion,3).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 4){
			poblacion = new Truncamiento(poblacion,tamPoblacion).selecciona();	
		}else{
			poblacion = new Restos(poblacion,tamPoblacion).selecciona();
		}
	}
	
	public void cruce(int tipo) {
		if(tipo == 0){
			poblacion = new CruceAritmetico(poblacion,tamPoblacion).selecCruzados();
		}else if(tipo == 1){
			poblacion = new CruceBLX(poblacion,tamPoblacion).selecCruzados();
		}else if(tipo == 2){
			poblacion = new CruceMonopunto(poblacion,tamPoblacion).selecCruzados(); 
		}else if(tipo == 3){
			poblacion = new CruceSBX(poblacion,tamPoblacion).selecCruzados();
		}else
			poblacion = new CruceUniforme(poblacion,tamPoblacion).selecCruzados();
	}
	
	/*public void setMejor(int pos) {
		pos_mejor=pos;
	}*/
	
	public void evaluar() {  //REVISAR TODAS las funciones, ya que lo mismo hay que separar los For en 2 funciones distintas
		double punt_acu=0,aptitud_mejor=0,sumaptitud=0;
		//aptitud_mejor=poblacion[0].getFitness();
		
		for(int i=0;i<tamPoblacion;i++) {
			sumaptitud+=poblacion.get(i).getFitness();
			if(poblacion.get(i).getFitness()>aptitud_mejor) {
				//setMejor(i);
				pos_mejor=i;
				aptitud_mejor=poblacion.get(i).getFitness();
			}
		}
		for(int i=0;i<tamPoblacion;i++) {
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/sumaptitud);
			poblacion.get(i).setPuntAcum(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(aptitud_mejor > elMejor.getFitness()) {
			elMejor=poblacion.get(pos_mejor); //Esto puede romper encapsulacion
		}
	}
	
	public void run() {
		iniciarPoblacion(); //Done
		evaluar();
		while(this.GenActual < this.maxGeneraciones) {
			//Seleccion
			seleccion(0); //pasar el tipo
			//Cruce
			poblacion = new CruceMonopunto(poblacion,tamPoblacion).selecCruzados();
			//Mutacion
			evaluar();//Por funciones GetFitness
			//generaGrafica();
			//Siguiente generacion
			GenActual++;
		}
		System.out.print("EL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1) );
	}
	
}
