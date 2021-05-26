package algoritmoGenetico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceArbol;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.mutacion.Contraccion;
import algoritmoGenetico.mutacion.Expansion;
import algoritmoGenetico.mutacion.FuncionalSimple;
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
import utils.Pair;
import utils.Sorted;

public class AlgoritmoGenetico {
	
	static private final int defaultPop = 100;
	static private final double defaultProbCruce = 0.6;
	static private final double defaultProbMut = 0.05;
	static private final double defaultEliteRate = 0.03;
	static private final int defaultTamTorn = 5;
	static private final int maxreinicio = 10;	
	static private final int maxaltura = 5;
	static private final int defaultprof = 2; //la altura es siempre una mas, ya que empieza en 0	
	static private final int defaultpasos = 300;
	static private final double very_low_fitness = 0.01;
	
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
	private boolean apocalipsis;
	
	private double MejorF;
	private double MejorAF;
	private double Media;
	private List<Pair<Integer,Integer>>recorrido;
	private String arbol;
	private int OpcionI;
	private int pasos;
	
	private int reinicio;
	
	
	public AlgoritmoGenetico() {
		
		poblacion= new ArrayList<Individuo>();
		
		TamPob=defaultPop;
		probCruce=defaultProbCruce;
		probMut=defaultProbMut;
		eliteRango=defaultEliteRate;
		tamTorneo=defaultTamTorn;
		maximizar=true;
		profundidad=defaultprof;
		recorrido = new ArrayList<Pair<Integer,Integer>>();
		apocalipsis=false;
		pasos=defaultpasos;
		reset();
	}


	private void inicializa() {
		if(OpcionI==0) for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa(profundidad,0);
		else if(OpcionI==1)for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa(profundidad,1); 
		else {
			int c=profundidad-1;
			int N= TamPob/c;
			int x=0;
			int pos=0;
			int tipo=0;
			while(x<c) {
				tipo=0;
				for(int i=0;i<N;i++) {
					poblacion.get(pos).inicializa(profundidad-x,tipo);
					tipo=(tipo+1)%2;
					pos++;
				}
				x++;
			}
			while(pos<poblacion.size()) {
				poblacion.get(pos).inicializa(profundidad,tipo);
				tipo=(tipo+1)%2;
				pos++;
			}
		}
	}
	
	public void init(int opcionI,int opcionS, int opcionC, int opcionM) {
		maximizar=true;
		OpcionI=opcionI;
		
		for(int i=0;i<TamPob;i++) poblacion.add(new Individuo()); 
		
		inicializa();
		
		if(opcionS==0) selMod= new Ruleta();
		else if(opcionS==1) selMod= new Estocastico();
		else if(opcionS==2 )selMod= new TorneoProbabilistico(tamTorneo);
		else if(opcionS==3) selMod= new TorneoDeterministico(tamTorneo);
		else if(opcionS==4) selMod= new Truncamiento(maximizar);
		else if(opcionS==5) selMod= new Restos();
		else selMod= new Ranking();
				
		crucMod= new CruceArbol(probCruce);
		
		if(opcionM==0)mutMod= new TerminalSimple(probMut);
		else if(opcionM==1)mutMod= new MutArbol(probMut,opcionI,profundidad);
		else if(opcionM==2)mutMod= new Permutacion(probMut);
		else if(opcionM==3) mutMod= new FuncionalSimple(probMut);
		else if(opcionM==4)mutMod= new Contraccion(probMut);
		else if(opcionM==5)mutMod= new Expansion(probMut);
		else mutMod= new Hoist(probMut);
		
		MejorF = Double.NEGATIVE_INFINITY;
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
		arbol="";
	}
	
	public void resetAct() {
		MejorAF = Double.NEGATIVE_INFINITY;
	}
	
	private double ProfundidadMedia(){
		double averagePopSize = 0.0;
		for (Individuo c : poblacion) {
			averagePopSize += c.getProfundidad();
		}
		return averagePopSize/poblacion.size();
	}
	
	/*private double covarianza(){
		double covariance = 0.0;
		double p = ProfundidadMedia();
		for (Individuo c : poblacion){
			covariance += (c.getProfundidad()-p)* (c.getFitness()-Media);
		}
		
		return covariance/poblacion.size();
	}
	private double varianza(){
		double variance = 0.0;
		double p = ProfundidadMedia();
		for (Individuo c : poblacion){
			variance += Math.pow(c.getProfundidad()-p, 2);
		}
		
		return variance/poblacion.size();
	}
	
	private void Bloating() {
		double var=varianza();
		if(var!=0) {
			double coeficiente = covarianza()/var;
			for (Individuo c : poblacion)
				c.setFitness(c.getFitness()+(coeficiente*(c.getArbol().getNumNodos()+1)));
		}
	}*/
	
	private void Bloating() {
		List<Individuo> nuevaPob = new ArrayList<Individuo>();
		double p;
		double a=ProfundidadMedia();
		for(int i=0;i<poblacion.size();i++) {
			nuevaPob.add(poblacion.get(i).copia());
			p = Math.random();
			if(nuevaPob.get(i).getProfundidad()> maxaltura  /*&& p < 0.5 && nuevaPob.get(i).getFitness()!=MejorF*/) //Conflicto con elitismo
				nuevaPob.get(i).setFitness(very_low_fitness);
		}
		
		poblacion.clear();
		poblacion=nuevaPob;
		
	}
	
	public void evaluar() {
		resetAct();
		String arb="";
		List<Pair<Integer,Integer>> rec = new ArrayList<Pair<Integer,Integer>>();
		double punt_acu=0,TotalFitness=0;		

		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).evalua(pasos);
			TotalFitness+=poblacion.get(i).getFitness();	
		}
		
		TotalFitness=0;
		Bloating();
		for(int i=0;i<poblacion.size();i++) {
			TotalFitness+=poblacion.get(i).getFitness();
			if(poblacion.get(i).getFitness()>MejorAF) {
				MejorAF=poblacion.get(i).getFitness();
				rec=poblacion.get(i).getRecorrido();
				arb=poblacion.get(i).getArbolText();
			}
		}
		
		Media=TotalFitness/poblacion.size();
		
		for(int i=0;i<poblacion.size();i++) {		
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/TotalFitness);
			poblacion.get(i).setPuntAcu(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(MejorAF > MejorF) {
			MejorF=MejorAF;
			reinicio=0;
			recorrido = rec; 
			arbol = arb;
		}
		else
			reinicio++;
			
	}
	
	public void nextElisGen() {
		try {
			if(apocalipsis && reinicio>=maxreinicio) reinicializar();
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
			poblacion= nuevaPob;
			
			evaluar();

		}catch(IndexOutOfBoundsException e){
			System.out.print("Hubo un fallo con un arbol");
		}
		
	}
	
	private void reinicializar() {
		reinicio=0;
		List<Individuo>fijos=EliteReset(poblacion);
		inicializa();
		insertartElite(poblacion, fijos);
		evaluar();
	}
	
	private List<Individuo> escogerElite(List<Individuo> pob) {
		pob.sort(new Sorted(true,true));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * eliteRango);
		for (int i = 0; i < tam; i++) {		
			elite.add(pob.get(i).copia());
		}
		return elite;
	}
	
	private List<Individuo> EliteReset(List<Individuo> pob) {
		pob.sort(new Sorted(true,true));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * 0.10);
		for (int i = 0; i < tam; i++) {
			elite.add(pob.get(i).copia());
		}
		return elite;
	}
	
	private List<Individuo> insertartElite(List<Individuo> pob, List<Individuo> elite){
		pob.sort(new Sorted(true,true));
		for (int i = 0; i < elite.size(); ++i) {
			pob.remove(pob.size() - 1 - i);
			pob.add(elite.get(i).copia());
		}
		return pob;
	}

	public Map<String, Object> getResults() { 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Media", Media);		
		map.put("fitness", MejorF);	
		map.put("Mejor Actual", MejorAF);
		map.put("Recorrido", recorrido);
		map.put("Arbol", arbol);
		return map;
	}
	
	public boolean getMaximizar() {return maximizar;}

	public void setProbMut(double p)	{		probMut=p;	}
	public void setProbCruc(double p)	{		probCruce=p;	}
	public void setTamTor(int t)		{		tamTorneo=t;	}
	public void setEliteR(double e) 	{		eliteRango=e;	}
	public void setTamPob(int value) 	{ 		TamPob=value;	}
	public void setApoc(boolean value) 	{ 		apocalipsis=value;}
	public void setPasos(int value) 	{ 		pasos=value;	}
	
}
