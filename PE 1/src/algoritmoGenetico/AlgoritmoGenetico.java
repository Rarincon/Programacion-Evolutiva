package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.cruces.CruceAritmetico;
import algoritmoGenetico.cruces.CruceBLX;
import algoritmoGenetico.cruces.CruceMonopunto;
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
import utils.SortedArrayList;

public class AlgoritmoGenetico {
	
	//private static final double _eliteRate = 0.001; //CAMBIA CON la GUI
	private int tamPoblacion;
	private List<Individuo> poblacion;
	private double[] fitness;
	//private int maxGeneraciones;
	private int GenActual;
	private double probCruce;
	private double probMutacion;
	private int tamTorneo;
	private double precision, eliteRango;
	private Individuo elMejor,elPeor,mejorAct, peorAct;
	private int pos_mejor,pos_peor;
	private boolean maximizar;
	private double media;
	private int NGen;
	//private double aptitudMejor;
	
	
	
	public AlgoritmoGenetico() {
		//tamPoblacion=100;//tamPob;
		//maxGeneraciones=100;//maxGen;
		probCruce=0.6;
		probMutacion=0.05;
		precision=0.001;
		eliteRango=0.03;
		tamTorneo=5;
		NGen=2;
		maximizar=true;
		poblacion= new ArrayList<Individuo>();
		
		//Borrar
		GenActual=0;
		
		//elMejor = new IndividuoFuncion1();
	}
	
	public void iniciarPoblacion(int indi, int tam) { //ESTO SERA PRIVADO
		tamPoblacion=tam;
		poblacion= new ArrayList<Individuo>();
		fitness= new double[tamPoblacion];
		
		if(indi==0){
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion1(precision)); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				maximizar=true;
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else if(indi==1) {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion2(precision)); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				maximizar=false;
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else if(indi==2) {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion3(precision)); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				maximizar=false;
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		else if(indi==3) {
			for(int i=0;i<tamPoblacion;i++) {
				poblacion.add(new IndividuoFuncion4(NGen,precision)); //Habra que seleccionar que funcione quiere
				poblacion.get(i).inicializa();
				maximizar=false;
				//fitness[i]=poblacion.get(i).getFitness(); //Se podra quitar
			}
		}
		
		pos_mejor=pos_peor=0;
		elMejor= elPeor=mejorAct=peorAct=poblacion.get(0);
		
		//GenActual=0;
		//aptitudMejor=poblacion[0].getFitness();
		
	}
	
	public List<Individuo> seleccion(int tipo) {
		if(tipo == 0){
			return new Ruleta(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 1){
			return new Estocastico(poblacion,tamPoblacion).selecciona();
		}else if(tipo == 2){
			return new TorneoProbabilistico(poblacion,tamPoblacion,tamTorneo).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 3){
			return new TorneoDeterministico(poblacion,tamPoblacion,tamTorneo).selecciona(); //Seleccionar el tamaño
		}else if(tipo == 4){
			return new Truncamiento(poblacion,tamPoblacion).selecciona();	
		}else{
			return new Restos(poblacion,tamPoblacion).selecciona();
		}
	}
	
	public List<Individuo> cruce(List<Individuo> nuevaPob, int tipo) {
		if(tipo == 0){
			return new CruceMonopunto(nuevaPob,tamPoblacion,probCruce).selecCruzados();			
		}else if(tipo == 1){
			return new CruceUniforme(nuevaPob,tamPoblacion, probCruce).selecCruzados();
		}else if(tipo == 2){
			return new CruceAritmetico(nuevaPob,tamPoblacion, probCruce).selecCruzados(); //La probabilidad de cruce varia con la gui
		}else 
			return new CruceBLX(nuevaPob,tamPoblacion, probCruce).selecCruzados();
	}

	
	//VOLVER A HACER ENTERA
	
	public void evaluar() {  //REVISAR TODAS las funciones, ya que lo mismo hay que separar los For en 2 funciones distintas
		double punt_acu=0,TotalFitness=0;
		double MejorFitness=poblacion.get(0).getFitness();
		double PeorFitness=poblacion.get(0).getFitness();
		//aptitud_mejor=poblacion[0].getFitness();
		//double sumaptitud=0;
		int a=0;
		for(Individuo c: poblacion) {
			c.setFitness(c.evaluar());
			TotalFitness+=c.getFitness();
			if(c.getFitness()>MejorFitness) {
				pos_mejor=a;
				mejorAct=c;
				MejorFitness=c.getFitness();
				//System.out.print("EL mejor es: "+poblacion.get(pos_mejor).getFitness()+" con x1: "+elMejor.getFenotipo(0)+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
			}
			if(c.getFitness()<PeorFitness) {
				pos_peor=a;
				peorAct=c;
				PeorFitness=c.getFitness();
			}
			a++;
		}
		
		media=TotalFitness/tamPoblacion;
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
	
	/*public void run() {
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
	}*/
	
	public void nextGen(int S, int C) {
		List<Individuo> nuevaPob;
		//Seleccion
		nuevaPob=seleccion(S);
		//Cruce
		nuevaPob = cruce(nuevaPob,C);//new CruceMonopunto(nuevaPob,tamPoblacion, probCruce).selecCruzados();
		//Mutacion
		nuevaPob= new Basica(nuevaPob, tamPoblacion, probMutacion).mutarInd();
		poblacion= nuevaPob;
		evaluar();//Por funciones GetFitness
		//generaGrafica();
		
		//System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+"\n");//+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
		//System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+"\n");//+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
		GenActual++;
	}
	
	public void nextElisGen(int S, int C) {
		List<Individuo> nuevaPob;
		List<Individuo> fijos;
		fijos=escogerElite(poblacion);
		//Seleccion
		nuevaPob=seleccion(S);
		//Cruce
		nuevaPob = cruce(nuevaPob,C);//new CruceMonopunto(nuevaPob,tamPoblacion, probCruce).selecCruzados();
		//Mutacion
		nuevaPob= new Basica(nuevaPob, tamPoblacion, probMutacion).mutarInd();
		nuevaPob=insertartElite(nuevaPob, fijos);
		poblacion= nuevaPob;
		evaluar();
		GenActual++;
	}
	
	private List<Individuo> escogerElite(List<Individuo> pob) {
		List<Individuo> nuevaPob = new SortedArrayList<Individuo>();
		nuevaPob=pob;
		Collections.sort(nuevaPob);
		List<Individuo> elite = new ArrayList<Individuo>();
		for (int i = 0; i < (int) Math.ceil(nuevaPob.size() * eliteRango); i++) {
			if(maximizar)  elite.add(nuevaPob.get(i).copia());
			else  elite.add(nuevaPob.get(nuevaPob.size()-i-1).copia());
		}
		return elite;
	}
	
	private List<Individuo> insertartElite(List<Individuo> pob, List<Individuo> elite){
		List<Individuo> nuevaPob = new SortedArrayList<Individuo>();
		nuevaPob=pob;
		Collections.sort(nuevaPob);
		for (int i = 0; i < elite.size(); ++i) {
			nuevaPob.remove(nuevaPob.size() - 1 - i);
			nuevaPob.add(elite.get(i).copia());
		}
		return nuevaPob;
	}

	public Map<String, Object> getResults() {
		Map<String, Object> map = new HashMap<String, Object>();
		//stats.put("Current Best Value", _currentBestValue);
		//stats.put("Current Best Fitness", _currentBestFitness);
		//stats.put("Current Worst Value", _currentWorstValue);
		//stats.put("Current Worst Fitness", _currentWorstFitness);
		
		//stats.put("Absolute Best Value", _absoluteBestValue);
		map.put("Media", media);
		Object OV,AV;
		if(maximizar) {
			map.put("Objetivo", elMejor.getFitness());
			OV=elMejor.getFenotipos();
			map.put("Objetivo Valores",OV );
			map.put("Mejor Actual", mejorAct.getFitness());
			AV=  mejorAct.getFenotipos();
			map.put("Mejor Actual Valores", AV);
		}
		else {
			map.put("Objetivo", elPeor.getFitness());
			OV=elPeor.getFenotipos();
			map.put("Objetivo Valores", OV );
			map.put("Mejor Actual", peorAct.getFitness());
			AV=peorAct.getFenotipos();
			map.put("Mejor Actual Valores",AV);
		}
		//stats.put("Absolute Worst Value", _absoluteWorstValue);
		//stats.put("Average Fitness", _averageFitness);
		//stats.put("Selective Pressure", _selectivePressure);
		return map;
	}
	
	public void limpiarPoblacion() {
		this.poblacion.clear();
	}
	
	//public boolean getMaximizar() {return maximizar;}
	
	public void Mejor() {
		System.out.print("Generacion: " + GenActual+ " eL mejor es: "+elMejor.getFitness()+" con x1: "+elMejor.getFenotipo(0)+"\n");//+ " y x2: "+ elMejor.getFenotipo(1)+"\n" );
	}
	
	public void Peor() { System.out.print("Generacion: " + GenActual+ " eL peor es: "+elPeor.getFitness()+" con x1: "+elPeor.getFenotipo(0)+"\n");//+ " y x2: "+ elPeor.getFenotipo(1)+"\n" );
	}

	public void setNGen(int value) {		NGen=value;		}
	public void setProbMut(double p) {		probMutacion=p;	}
	public void setProbCruc(double p) {		probCruce=p;	}
	public void setTamTor(int t) {		tamTorneo=t;			}
	public void setPrec(double p) {		precision=p;		}
	public void setEliteR(double e) {		eliteRango=e;			}
	
}
