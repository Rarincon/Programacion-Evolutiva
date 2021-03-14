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
import algoritmoGenetico.individuos.IndividuoFuncion2;
import algoritmoGenetico.individuos.IndividuoFuncion3;
import algoritmoGenetico.individuos.IndividuoFuncion4;
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
	private Individuo elMejor,elPeor;
	private int pos_mejor,pos_peor;
	//private double aptitudMejor;
	
	
	
	public AlgoritmoGenetico() {
		tamPoblacion=100;//tamPob;
		maxGeneraciones=100;//maxGen;
		probCruce=0.6;
		probMutacion=0.05;
		tamTorneo=5;
		//GenActual=0;
		//elMejor = new IndividuoFuncion1();
	}
	
	public void iniciarPoblacion(int indi) { //ESTO SERA PRIVADO
		poblacion= new ArrayList<Individuo>(); //Camnio a list, no determino el tamaño
		fitness= new double[tamPoblacion];
		
		if(indi==0){
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion1()); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else if(indi==1) {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion2()); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else if(indi==2) {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion3()); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion4(3)); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		
		pos_mejor=pos_peor=0;
		elMejor= poblacion.get(0);
		elPeor=poblacion.get(0);
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
			return new CruceMonopunto(poblacion,tamPoblacion,probCruce).selecCruzados();			
		}else if(tipo == 1){
			return new CruceBLX(poblacion,tamPoblacion, probCruce).selecCruzados();
		}else if(tipo == 2){
			return new CruceAritmetico(poblacion,tamPoblacion, probCruce).selecCruzados(); //La probabilidad de cruce varia con la gui
		}else if(tipo == 3){
			return new CruceSBX(poblacion,tamPoblacion, probCruce).selecCruzados();
		}else
			return new CruceUniforme(poblacion,tamPoblacion, probCruce).selecCruzados();
	}

	
	//VOLVER A HACER ENTERA
	
	public void evaluar() {  //REVISAR TODAS las funciones, ya que lo mismo hay que separar los For en 2 funciones distintas
		double punt_acu=0,MejorFitness=0,PeorFitness=100,TotalFitness=0;
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
			if(c.getFitness()<PeorFitness) {
				pos_peor=a;
				PeorFitness=c.getFitness();
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
			elMejor=poblacion.get(pos_mejor); 
		}
		if(PeorFitness < elPeor.getFitness()) {
			elPeor=poblacion.get(pos_peor);
		}
	}
	
	public void run() {
		//iniciarPoblacion(); //Done
		evaluar();
		List<Individuo> nuevaPob;
		//nuevaPob= new ArrayList<Individuo>();
		while(this.GenActual < this.maxGeneraciones) {
			//Seleccion
			nuevaPob=seleccion(0); //pasar el tipo
			//Cruce
			nuevaPob = new CruceMonopunto(nuevaPob,tamPoblacion, probCruce).selecCruzados();
			//Mutacion
			nuevaPob= new Basica(nuevaPob, tamPoblacion, probMutacion).mutarInd();
			poblacion= nuevaPob;
			evaluar();//Por funciones GetFitness
			//generaGrafica();
			//Siguiente generacion
			
			System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
			System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
			GenActual++;
		}
		//System.out.print("EL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1) );
	}
	
	public void avanza(int S, int C) {
		List<Individuo> nuevaPob;
		//Seleccion
		nuevaPob=seleccion(S); //pasar el tipo
		//Cruce
		nuevaPob = cruce(C);//new CruceMonopunto(nuevaPob,tamPoblacion, probCruce).selecCruzados();
		//Mutacion
		nuevaPob= new Basica(nuevaPob, tamPoblacion, probMutacion).mutarInd();
		poblacion= nuevaPob;
		evaluar();//Por funciones GetFitness
		//generaGrafica();
		
		System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
		System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
		
	}
	
}
