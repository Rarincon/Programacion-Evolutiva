package algoritmoGenetico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceAritmetico;
import algoritmoGenetico.cruces.CruceMonopunto;
import algoritmoGenetico.cruces.CruceUniforme;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoCifrado;
import algoritmoGenetico.mutacion.Basica;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.Uniforme;
import algoritmoGenetico.seleccion.Estocastico;
import algoritmoGenetico.seleccion.Restos;
import algoritmoGenetico.seleccion.Ruleta;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.TorneoDeterministico;
import algoritmoGenetico.seleccion.TorneoProbabilistico;
import algoritmoGenetico.seleccion.Truncamiento;
import utils.Sorted;
import utils.SortedArrayList;

public class AlgoritmoGenetico {
	
	static private final int defaultPop = 100;
	static private final double defaultProbCruce = 0.6;
	static private final double defaultProbMut = 0.05;
	static private final double defaultEliteRate = 0.03;
	static private final double defaultPrec = 0.001;
	static private final int defaultTamTorn = 5;
	static private final int defaultNGen = 5;
	
	private List<Individuo> poblacion;
	private int TamPob;
	private Seleccion selMod;
	private Cruce crucMod;
	private Mutacion mutMod;
	private double probCruce;
	private double probMut;
	private int tamTorneo;
	private double precision;
	private double eliteRango;
	private boolean maximizar;
	private int NGen;
	
	private double MejorF;
	private double PeorF;
	private double MejorAF;
	private double PeorAF;
	private double Media;
	private Object MejorVF;
	private Object PeorVF;
	private Object MejorVAF;
	private Object PeorVAF;
	
	private int GenActual;

	
	public AlgoritmoGenetico() {
		
		poblacion= new ArrayList<Individuo>();
		
		TamPob=defaultPop;
		probCruce=defaultProbCruce;
		probMut=defaultProbMut;
		precision=defaultPrec;
		eliteRango=defaultEliteRate;
		tamTorneo=defaultTamTorn;
		NGen=defaultNGen;
		maximizar=true;
		
		reset();
	}
	
	public void init(int opcionI,int opcionS, int opcionC, int opcionM) { //ESTO SERA PRIVADO
		if(opcionI==0)maximizar=true;
		else maximizar=false;
		
		for(int i=0;i<TamPob;i++) poblacion.add(new IndividuoCifrado(precision)); 
		
		for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa();
		
		if(opcionS==0) selMod= (Seleccion) new Ruleta();
		else if(opcionS==1) selMod= (Seleccion) new Estocastico();
		else if(opcionS==2) selMod= (Seleccion) new TorneoProbabilistico(tamTorneo);
		else if(opcionS==3) selMod= (Seleccion) new TorneoDeterministico(tamTorneo);
		else if(opcionS==4) selMod= (Seleccion) new Truncamiento(maximizar);
		else selMod= (Seleccion) new Restos();
		
		if(opcionC==2 && opcionI == 4) crucMod= new CruceAritmetico(probCruce);
		else if(opcionC==0) crucMod= new CruceMonopunto(probCruce);
		else crucMod= new CruceUniforme(probCruce);
		
		if(opcionI==4)mutMod= new Uniforme(probMut);
		else mutMod= new Basica(probMut);
		
		MejorF= Double.NEGATIVE_INFINITY;
		PeorF = Double.POSITIVE_INFINITY;
		MejorVF=PeorVF=null;
	}
	
	public List<Individuo> seleccion() {
		 return selMod.selecciona(poblacion,poblacion.size());
	}
	
	public List<Individuo> cruce(List<Individuo> pob) {
		return crucMod.selecCruzados(pob);
	}
	
	public List<Individuo> mutacion(List<Individuo> pob){
		return mutMod.mutarInd(pob);
	}
	
	public void reset() {
		poblacion.clear();
		Media=0;
		resetAct();
		GenActual=0;
	}
	
	public void resetAct() {
		MejorAF= Double.NEGATIVE_INFINITY;
		PeorAF = Double.POSITIVE_INFINITY;
		PeorVAF=MejorVAF=null;
	}
	
	public void evaluar() {
		resetAct();
		double punt_acu=0,TotalFitness=0;		
		
		int pos_mejor=0,pos_peor=0;

		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setFitness(poblacion.get(i).evaluar());
			
			TotalFitness+=poblacion.get(i).getFitness();
			
			if(poblacion.get(i).getFitness()>MejorAF) {
				pos_mejor=i;
				MejorAF=poblacion.get(i).getFitness();
				//MejorVAF=poblacion.get(i).getFenotipos();
			}
			if(poblacion.get(i).getFitness()<PeorAF) {
				pos_peor=i;
				PeorAF=poblacion.get(i).getFitness();
				//PeorVAF=poblacion.get(i).getFenotipos();
			}
		}
		
		Media=TotalFitness/poblacion.size();
		
		//Desplazamiento para la Ruleta (No funciona muy bn creo)
		/*TotalFitness=0; 
		poblacion.sort(new Sorted(maximizar));
		
		for (Individuo p : poblacion) {
			double f = ((maximizar) ? p.getFitness() + Math.abs(poblacion.get(0).getFitness()) : poblacion.get(0).getFitness() - p.getFitness());
			p.setFitness(f);
			TotalFitness += f;
		} */
		
		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/TotalFitness);
			poblacion.get(i).setPuntAcum(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(MejorAF > MejorF) {//Faltan los valores del rango
			MejorF=MejorAF;
			//MejorVF=poblacion.get(pos_mejor).getFenotipos();
		}
		if(PeorAF < PeorF) {
			PeorF=PeorAF;
			//PeorVF=poblacion.get(pos_peor).getFenotipos();
		}
	}
	
	public void nextGen(int S, int C) {
		List<Individuo> nuevaPob;
		//Seleccion
		nuevaPob=seleccion();
		//Cruce
		nuevaPob = cruce(nuevaPob);
		//Mutacion
		nuevaPob= mutacion(nuevaPob);
		poblacion.clear();
		poblacion= nuevaPob;
		evaluar();
		
		GenActual++;
	}
	
	public void nextElisGen(int S, int C) {
		List<Individuo> nuevaPob;
		List<Individuo> fijos;
		fijos=escogerElite(poblacion);
		//Seleccion
		nuevaPob=seleccion();
		//Cruce
		nuevaPob = cruce(nuevaPob);
		//Mutacion
		nuevaPob= mutacion(nuevaPob);
		
		nuevaPob=insertartElite(nuevaPob, fijos);
		poblacion.clear();
		poblacion= nuevaPob;
		evaluar();
		GenActual++;
	}
	
	private List<Individuo> escogerElite(List<Individuo> pob) {
		pob.sort(new Sorted(maximizar));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * eliteRango);
		for (int i = 0; i < tam; i++) {
			elite.add(pob.get(i).copia());
		}
		return elite;
	}
	
	private List<Individuo> insertartElite(List<Individuo> pob, List<Individuo> elite){
		pob.sort(new Sorted(maximizar));
		for (int i = 0; i < elite.size(); ++i) {
			pob.remove(pob.size() - 1 - i);
			pob.add(elite.get(i).copia());
		}
		return pob;
	}

	public Map<String, Object> getResults() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Media", Media);
		if(maximizar) {
			map.put("Objetivo", MejorF);
			map.put("Objetivo Genotipos",MejorVF);
			map.put("Mejor Actual", MejorAF);
			map.put("Mejor Actual Genotipos",MejorVAF);
		}
		else {
			map.put("Objetivo", PeorF);
			map.put("Objetivo Genotipos",PeorVF);
			map.put("Mejor Actual", PeorAF);
			map.put("Mejor Actual Genotipos",PeorVAF);
		}
		return map;
	}
	
	public boolean getMaximizar() {return maximizar;}
	
	public void Mejor() {
		System.out.print("Generacion: " + GenActual+ " eL mejor es: "+MejorF+" con Genotipos "+ MejorVF.toString()+"\n");
	}
	
	public void Peor() {
		System.out.print("Generacion: " + GenActual+ " eL peor es: "+PeorF+" con Genotipos "+ PeorVF.toString()+"\n");
	}

	//public void setNGen(int value) {		NGen=value;		}
	public void setProbMut(double p) {		probMut=p;	}
	public void setProbCruc(double p) {		probCruce=p;	}
	public void setTamTor(int t) {		tamTorneo=t;			}
	public void setPrec(double p) {		precision=p;		}
	public void setEliteR(double e) {		eliteRango=e;			}
	public void setTamPob(int value) { TamPob=value; }
	
}
