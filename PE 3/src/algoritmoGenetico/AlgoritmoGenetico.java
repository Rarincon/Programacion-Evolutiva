package algoritmoGenetico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceArbol;
import algoritmoGenetico.cruces.CruceCO;
import algoritmoGenetico.cruces.CruceCX;
import algoritmoGenetico.cruces.CruceERX;
import algoritmoGenetico.cruces.CruceOX;
import algoritmoGenetico.cruces.CruceOXOP;
import algoritmoGenetico.cruces.CruceOXPP;
import algoritmoGenetico.cruces.CrucePMX;
import algoritmoGenetico.cruces.CruceRaulRober;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.mutacion.Contraccion;
import algoritmoGenetico.mutacion.Expansion;
import algoritmoGenetico.mutacion.FuncionSimple;
import algoritmoGenetico.mutacion.Hoist;
import algoritmoGenetico.mutacion.MutArbol;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.Permutacion;
import algoritmoGenetico.mutacion.TerminalSimple;
import algoritmoGenetico.seleccion.Estocastico;
import algoritmoGenetico.seleccion.Ranking;
import algoritmoGenetico.seleccion.Restos;
import algoritmoGenetico.seleccion.Ruleta;
import algoritmoGenetico.seleccion.Seleccion;
import algoritmoGenetico.seleccion.TorneoDeterministico;
import algoritmoGenetico.seleccion.TorneoProbabilistico;
import algoritmoGenetico.seleccion.Truncamiento;
import utils.Sorted;

public class AlgoritmoGenetico {
	
	static private final int defaultPop = 100;
	static private final double defaultProbCruce = 0.6;
	static private final double defaultProbMut = 0.05;
	static private final double defaultEliteRate = 0.03;
	static private final int defaultTamTorn = 5;
	static private final int maxreinicio = 7;	
	static private final int defaultprof = 4;	
	
	private List<Individuo> poblacion;
	private int TamPob;
	private Seleccion selMod;
	private Cruce crucMod;
	private Mutacion mutMod;
	private double probCruce;
	private double probMut;
	private int tamTorneo;
	private double eliteRango;
	private boolean maximizar;
	private int profundidad;
	
	private double PeorF;
	private double PeorAF;
	private double Media;
	
	private int reinicio;
	
	private int NumCruces, NumMutac;
	
	public AlgoritmoGenetico() {
		
		poblacion= new ArrayList<Individuo>();
		
		TamPob=defaultPop;
		probCruce=defaultProbCruce;
		probMut=defaultProbMut;
		eliteRango=defaultEliteRate;
		tamTorneo=defaultTamTorn;
		maximizar=true;
		profundidad=defaultprof;
		
		reset();
	}


	public void init(int opcionI,int opcionS, int opcionC, int opcionM) { //ESTO SERA PRIVADO
		maximizar=false;
		
		for(int i=0;i<TamPob;i++) poblacion.add(new Individuo()); 
		
		if(opcionI==0) for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa(profundidad,0);
		else if(opcionI==1)for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa(profundidad,1); 
		else {
			int c=profundidad-1;
			int N= TamPob/c;
			int x=0;
			int tipo=0;
			while(x<c) {
				tipo=0;
				for(int i=0;i<N;i++) {
					poblacion.get(i).inicializa(profundidad-x,tipo);
					tipo=(tipo+1)%2;
				}
				x++;
			}
		}
		
		if(opcionS==0) selMod= new Ruleta();
		else if(opcionS==1) selMod= new Estocastico();
		else if(opcionS==2 )selMod= new TorneoProbabilistico(tamTorneo);
		else if(opcionS==3) selMod= new TorneoDeterministico(tamTorneo);
		else if(opcionS==4) selMod= new Truncamiento(maximizar);
		else if(opcionS==5) selMod= new Restos();
		else selMod= new Ranking();
		
		
		crucMod= new CruceArbol(probCruce);
		/*if(opcionC==0) crucMod= new CrucePMX(probCruce);
		else if(opcionC==1)crucMod= new CruceOX(probCruce);
		else if(opcionC==2)crucMod= new CruceOXPP(probCruce);
		else if(opcionC==3)crucMod= new CruceOXOP(probCruce);
		else if(opcionC==4)crucMod= new CruceCX(probCruce);
		else if(opcionC==5)crucMod= new CruceERX(probCruce);
		else if(opcionC==6)crucMod= new CruceCO(probCruce);
		else crucMod= new CruceRaulRober(probCruce);*/
		
		if(opcionM==0)mutMod= new TerminalSimple(probMut);
		else if(opcionM==1)mutMod= new MutArbol(probMut);
		else if(opcionM==2)mutMod= new Permutacion(probMut);
		else if(opcionM==3) mutMod= new FuncionSimple(probMut);
		else if(opcionM==4)mutMod= new Contraccion(probMut);
		else if(opcionM==5)mutMod= new Expansion(probMut);
		else mutMod= new Hoist(probMut);
		
		PeorF = Double.POSITIVE_INFINITY;
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
		reinicio=0;
		
		NumCruces=0;
		NumMutac=0;
	}
	
	public void conteo() {
		//NumCruces+=crucMod.NumCruces();
		NumMutac+=mutMod.NumMutaciones();
	}
	
	public void resetAct() {
		PeorAF = Double.POSITIVE_INFINITY;
	}
	
	public void evaluar() {
		resetAct();
		double punt_acu=0,TotalFitness=0;		

		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setFitness(poblacion.get(i).evaluar());
			
			TotalFitness+=poblacion.get(i).getFitness();
			
			if(poblacion.get(i).getFitness()<PeorAF) {
				PeorAF=poblacion.get(i).getFitness();
				//descifrado=poblacion.get(i).getDescifrado();
				//Conversion=poblacion.get(i).getConversion();
			}
		}
		
		Media=TotalFitness/poblacion.size();
		
		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/TotalFitness);
			poblacion.get(i).setPuntAcu(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(PeorAF < PeorF) {
			PeorF=PeorAF;
			reinicio=0;
		}
		else
			reinicio++;
			
	}
	
	public void nextElisGen() {
		if(reinicio>=maxreinicio) reinicializar();
		List<Individuo> nuevaPob;
		List<Individuo> fijos;
		fijos=escogerElite(poblacion);
		
		//Seleccion
		nuevaPob=seleccion();
		//Cruce
		nuevaPob = cruce(nuevaPob);
		//Mutacion
		nuevaPob= mutacion(nuevaPob);
		
		conteo();
		
		nuevaPob=insertartElite(nuevaPob, fijos);
		poblacion.clear();
		poblacion= nuevaPob;
		evaluar();
	}
	
	private void reinicializar() {
		reinicio=0;
		List<Individuo>fijos=EliteReset(poblacion);
		//for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa();
		insertartElite(poblacion, fijos);
		evaluar();
	}
	
	private List<Individuo> escogerElite(List<Individuo> pob) {
		pob.sort(new Sorted(false,true));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * eliteRango);
		for (int i = 0; i < tam; i++) {
			elite.add(pob.get(i).copia());
		}
		return elite;
	}
	
	private List<Individuo> EliteReset(List<Individuo> pob) {
		pob.sort(new Sorted(false,true));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * 0.10);
		for (int i = 0; i < tam; i++) {
			elite.add(pob.get(i).copia());
		}
		return elite;
	}
	
	private List<Individuo> insertartElite(List<Individuo> pob, List<Individuo> elite){
		pob.sort(new Sorted(false,true));
		for (int i = 0; i < elite.size(); ++i) {
			pob.remove(pob.size() - 1 - i);
			pob.add(elite.get(i).copia());
		}
		return pob;
	}

	public Map<String, Object> getResults() { 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Media", Media);
		//map.put("Descifrado", descifradoM);
		//map.put("Conversion", ConversionM);		
		map.put("fitness", PeorF);	
		map.put("Mejor Actual", PeorAF);
		map.put("Num Cruces", NumCruces);
		map.put("Num Mutaciones", NumMutac);
		return map;
	}
	
	public boolean getMaximizar() {return maximizar;}

	public void setProbMut(double p)	{		probMut=p;	}
	public void setProbCruc(double p)	{		probCruce=p;	}
	public void setTamTor(int t)		{		tamTorneo=t;	}
	public void setEliteR(double e) 	{		eliteRango=e;	}
	public void setTamPob(int value) 	{ 		TamPob=value;	}
	
}
