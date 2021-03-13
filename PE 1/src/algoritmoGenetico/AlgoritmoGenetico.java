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
import algoritmoGenetico.mutacion.Basica;
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
		tamPoblacion=10;//tamPob;
		maxGeneraciones=10;//maxGen;
		probCruce=probCruc;
		probMutacion=probMut;
		tamTorneo=tamTor;
		GenActual=0;
		//elMejor = new IndividuoFuncion1();
	}
	
	private void iniciarPoblacion() {
		poblacion= new ArrayList<Individuo>(); //Camnio a list, no determino el tamaño
		fitness= new double[tamPoblacion];
		
		for(int i=0;i<tamPoblacion;i++) {
			poblacion.add(new IndividuoFuncion1()); //Habra que seleccionar que funcione quiere
			poblacion.get(i).inicializa();
			//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
		}
		
		pos_mejor=0;
		elMejor= poblacion.get(0);
		//aptitudMejor=poblacion[0].getFitness();
		
	}
	
	public List<Individuo> seleccion(int tipo) {
		if(tipo == 0){
			return new Ruleta(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 1){
			return new Estocastico(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 2){
			return new TorneoProbabilistico(poblacion,tamPoblacion,3).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 3){
			return new TorneoDeterministico(poblacion,tamPoblacion,3).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 4){
			return new Truncamiento(poblacion,tamPoblacion).selecciona();	
		}else{
			return new Restos(poblacion,tamPoblacion).selecciona();
		}
	}
	
	public List<Individuo> cruce(int tipo) {
		if(tipo == 0){
			return new CruceAritmetico(poblacion,tamPoblacion).selecCruzados();
		}else if(tipo == 1){
			return new CruceBLX(poblacion,tamPoblacion).selecCruzados();
		}else if(tipo == 2){
			return new CruceMonopunto(poblacion,tamPoblacion).selecCruzados(); 
		}else if(tipo == 3){
			return new CruceSBX(poblacion,tamPoblacion).selecCruzados();
		}else
			return new CruceUniforme(poblacion,tamPoblacion).selecCruzados();
	}

	
	//VOLVER A HACER ENTERA
	
	public void evaluar() {  //REVISAR TODAS las funciones, ya que lo mismo hay que separar los For en 2 funciones distintas
		double punt_acu=0,MejorFitness=0,TotalFitness=0;
		//aptitud_mejor=poblacion[0].getFitness();
		//double sumaptitud=0;
		int a=0;
		for(Individuo c: poblacion) {
			c.setFitness(c.evaluar());
			TotalFitness+=c.getFitness();
			if(c.getFitness()>MejorFitness) {
				pos_mejor=a;
				MejorFitness=c.getFitness();
				//System.out.print("EL mejor es: "+poblacion.get(pos_mejor).getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
			}
			a++;
		}
		
		//double FitnessPromedio=TotalFitness/tamPoblacion;
		//TotalFitness=0;
		
		/*for(int i=0;i<tamPoblacion;i++) {
			sumaptitud+=poblacion.get(i).getFitness();
			if(poblacion.get(i).getFitness()>aptitud_mejor) {
				//setMejor(i);
				pos_mejor=i;
				aptitud_mejor=poblacion.get(i).getFitness();
			}
		}*/
		for(int i=0;i<tamPoblacion;i++) {
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/TotalFitness);
			poblacion.get(i).setPuntAcum(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(MejorFitness > elMejor.getFitness()) {
			elMejor=poblacion.get(pos_mejor); //Esto puede romper encapsulacion
		}
	}
	
	public void run() {
		iniciarPoblacion(); //Done
		evaluar();
		List<Individuo> nuevaPob;
		//nuevaPob= new ArrayList<Individuo>();
		while(this.GenActual < this.maxGeneraciones) {
			//Seleccion
			nuevaPob=seleccion(0); //pasar el tipo
			//Cruce
			nuevaPob = new CruceMonopunto(nuevaPob,tamPoblacion).selecCruzados();
			//Mutacion
			//nuevaPob= new Basica(nuevaPob, tamPoblacion, probMutacion).mutarInd();
			poblacion= nuevaPob;
			evaluar();//Por funciones GetFitness
			//generaGrafica();
			//Siguiente generacion
			
			System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
			GenActual++;
		}
		//System.out.print("EL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1) );
	}
	
}
