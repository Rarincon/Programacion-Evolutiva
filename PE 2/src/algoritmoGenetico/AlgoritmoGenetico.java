package algoritmoGenetico;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import algoritmoGenetico.cruces.Cruce;
import algoritmoGenetico.cruces.CruceCO;
import algoritmoGenetico.cruces.CruceCX;
import algoritmoGenetico.cruces.CruceERX;
import algoritmoGenetico.cruces.CruceOX;
import algoritmoGenetico.cruces.CruceOXPP;
import algoritmoGenetico.cruces.CrucePMX;
import algoritmoGenetico.individuos.Individuo;
import algoritmoGenetico.individuos.IndividuoCifrado;
import algoritmoGenetico.mutacion.Basica;
import algoritmoGenetico.mutacion.Heuristica;
import algoritmoGenetico.mutacion.Insercion;
import algoritmoGenetico.mutacion.Intercambio;
import algoritmoGenetico.mutacion.Inversion;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Estocastico;
import algoritmoGenetico.seleccion.Ranking;
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
	//static private final double defaultPrec = 0.001;
	static private final int defaultTamTorn = 5;
	static private final int maxreinicio = 7;
	//static private final int defaultNGen = 5;
	
	
	private List<Individuo> poblacion;
	private int TamPob;
	private Seleccion selMod;
	private Cruce crucMod;
	private Mutacion mutMod;
	private double probCruce;
	private double probMut;
	private int tamTorneo;
	//private double precision;
	private double eliteRango;
	private boolean maximizar;
	//private int NGen;
	
	
	//private double MejorF;
	private double PeorF;
	//private double MejorAF;
	private double PeorAF;
	private double Media;
	//private Object MejorVF;
	private Object PeorVF;
	
	private int GenActual;
	private int reinicio;
	
	private String descifrado,descifradoM,ConversionM,Conversion;
	private static String cifrado;
	//private static Map<Object,Integer>conteo;
	
	private static final String _default= "eqa ycwe aqqt aqcit v aqqtwecwb wecwb zn v aqqtwecwb wqcit wecwb aqqt?  zr aqcit wecwb vii rep aqqt revr v aqqtwecwb wqcit, zn v aqqtwecwb wqcit wecwb aqqt.";
	private static final String[] NGRAMAS= {"Bigram","Monogram","Trigram"};
	private static Map<String,Map<Object,Integer>>gramas;
	private static Map<String, Long> total;
	
	public AlgoritmoGenetico() {
		
		poblacion= new ArrayList<Individuo>();
		
		TamPob=defaultPop;
		probCruce=defaultProbCruce;
		probMut=defaultProbMut;
		//precision=defaultPrec;
		eliteRango=defaultEliteRate;
		tamTorneo=defaultTamTorn;
		//NGen=defaultNGen;
		maximizar=true;
		
		//conteo= new HashMap<Object, Integer>();
		cifrado=_default;
		descifradoM=descifrado="";
		gramas= new HashMap<String,Map<Object,Integer>>();
		total= new HashMap<String,Long>();
		reset();
		//loadMapa();
	}


	public void init(int opcionS, int opcionC, int opcionM) { //ESTO SERA PRIVADO
		//if(opcionI==0)maximizar=true;
		maximizar=false;
		
		for(int i=0;i<TamPob;i++) poblacion.add(new IndividuoCifrado(cifrado)); 
		
		for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa();
		
		if(opcionS==2 || opcionS==3) {
			JSpinner N = new JSpinner(new SpinnerNumberModel(5, 2, 10, 1));
			int n = JOptionPane.showConfirmDialog(null, N, "Tamaño del Torneo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			tamTorneo =  (n == JOptionPane.OK_OPTION) ? (int) N.getValue() : 5;
		}
		
		if(opcionS==0) selMod= new Ruleta();
		else if(opcionS==1) selMod= new Estocastico();
		else if(opcionS==2 )selMod= new TorneoProbabilistico(tamTorneo);
		else if(opcionS==3) selMod= new TorneoDeterministico(tamTorneo);
		else if(opcionS==4) selMod= new Truncamiento(maximizar);
		else if(opcionS==5) selMod= new Restos();
		else selMod= new Ranking();
		
		if(opcionC==0) crucMod= new CrucePMX(probCruce);
		else if(opcionC==1)crucMod= new CruceOX(probCruce);
		else if(opcionC==2)crucMod= new CruceOXPP(probCruce);
		else if(opcionC==3)crucMod= new CruceCX(probCruce);
		else if(opcionC==4)crucMod= new CruceERX(probCruce);
		else crucMod= new CruceCO(probCruce);
		
		if(opcionM==0)mutMod= new Inversion(probMut);
		else if(opcionM==1)mutMod= new Intercambio(probMut);
		else if(opcionM==1)mutMod= new Insercion(probMut);
		else mutMod= new Heuristica(probMut);
		
		/*if(opcionC==2 && opcionI == 4) crucMod= new CruceAritmetico(probCruce);
		else if(opcionC==0) crucMod= new CruceMonopunto(probCruce);
		else crucMod= new CruceUniforme(probCruce);
		
		if(opcionI==4)mutMod= new Uniforme(probMut);
		else mutMod= new Basica(probMut);*/
		
		//MejorF= Double.NEGATIVE_INFINITY;
		PeorF = Double.POSITIVE_INFINITY;
		//MejorVF=PeorVF=null;
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
		reinicio=0;
	}
	
	public void resetAct() {
		//MejorAF= Double.NEGATIVE_INFINITY;
		PeorAF = Double.POSITIVE_INFINITY;
	}
	
	public void evaluar() {
		resetAct();
		double punt_acu=0,TotalFitness=0;		

		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setFitness(poblacion.get(i).evaluar(gramas,total));
			
			TotalFitness+=poblacion.get(i).getFitness();
			
			/*if(poblacion.get(i).getFitness()>MejorAF) {
				MejorAF=poblacion.get(i).getFitness();
				descifrado=poblacion.get(i).getDescifrado();
				Conversion=poblacion.get(i).getConversion();
				//MejorVAF=poblacion.get(i).getFenotipos();
			}*/
			if(poblacion.get(i).getFitness()<PeorAF) {
				PeorAF=poblacion.get(i).getFitness();
				descifrado=poblacion.get(i).getDescifrado();
				Conversion=poblacion.get(i).getConversion();
				//PeorVAF=poblacion.get(i).getFenotipos();
			}
		}
		
		Media=TotalFitness/poblacion.size();
		
		for(int i=0;i<poblacion.size();i++) {
			poblacion.get(i).setPunt(poblacion.get(i).getFitness()/TotalFitness);
			poblacion.get(i).setPuntAcum(poblacion.get(i).getPunt()+ punt_acu);
			punt_acu+=poblacion.get(i).getPunt();
		}
		
		if(PeorAF < PeorF) {
			PeorF=PeorAF;
			descifradoM=descifrado;
			ConversionM=Conversion;
			reinicio=0;
		}
		else
			reinicio++;
			
	}
	
	/*public void nextGen(int S, int C) {
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
	}*/
	
	public void nextElisGen() {
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
		if(reinicio>=maxreinicio) reinicializar();
		evaluar();
		GenActual++;
	}
	
	private void reinicializar() {
		reinicio=0;
		List<Individuo>fijos=EliteReset(poblacion);
		for(int i=0;i<TamPob;i++) poblacion.get(i).inicializa();
		insertartElite(poblacion, fijos);
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
	
	private List<Individuo> EliteReset(List<Individuo> pob) {
		pob.sort(new Sorted(false));
		List<Individuo> elite = new ArrayList<Individuo>();
		int tam= (int) Math.ceil(pob.size() * 0.15);
		for (int i = 0; i < tam; i++) {
			elite.add(pob.get(i).copia());
		}
		Individuo x= elite.get(0);
		List<Individuo> mutado =mutacion(elite);
		mutado.remove(mutado.size()-1);
		mutado.add(x);
		return mutado;
	}
	
	private List<Individuo> insertartElite(List<Individuo> pob, List<Individuo> elite){
		pob.sort(new Sorted(false));
		for (int i = 0; i < elite.size(); ++i) {
			pob.remove(pob.size() - 1 - i);
			pob.add(elite.get(i).copia());
		}
		return pob;
	}

	public Map<String, Object> getResults() { //CAMBIAR ATRIBUTOS DEL MAP, el OBJECT
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Media", Media);
		map.put("Descifrado", descifradoM);
		map.put("Conversion", ConversionM);		
		map.put("fitness", PeorF);	
		map.put("Mejor Actual", PeorAF);
		return map;
	}
	
	public boolean getMaximizar() {return maximizar;}
	
	/*public void Mejor() {
		System.out.print("Generacion: " + GenActual+ " eL mejor es: "+MejorF+" con Genotipos "+ MejorVF.toString()+"\n");
	}*/
	
	public void Peor() {
		System.out.print("Generacion: " + GenActual+ " eL peor es: "+PeorF+" con Genotipos "+ PeorVF.toString()+"\n");
	}

	//public void setNGen(int value) {		NGen=value;		}
	public void setProbMut(double p)	{		probMut=p;		}
	public void setProbCruc(double p)	{		probCruce=p;	}
	public void setTamTor(int t)		{		tamTorneo=t;	}
	//public void setPrec(double p) 		{		precision=p;	}
	public void setEliteR(double e) 	{		eliteRango=e;	}
	public void setTamPob(int value) 	{ 		TamPob=value;	}
	
	public static void loadDataFile(Object[] objects) {
		try {
			String a;
			int b;
			long tot;
			Map<Object,Integer> map;
			for(int j=0;j<objects.length;j++) {		
				Scanner s = new Scanner(new File(objects[j].toString()));
				map = new HashMap<Object,Integer>();
				tot=0;
				while(s.hasNext()) {
					a=s.next().toLowerCase();
					b=s.nextInt();
					tot+=b;
					map.put(a,b);				
				}
				total.put(NGRAMAS[j], tot);
				gramas.put(NGRAMAS[j],map);
				
				/*if(gramas.containsKey(NGRAMAS[j])) {
					map=gramas.get(NGRAMAS[j]);
					if(map.containsKey(NGRAMAS[j]+"total"))
						System.out.print("TODO OK");
				}*/
				
				/*conteo.clear();
				for(int i=0;i<cifrado.length();i++) {				
					if(Character.isLetter(cifrado.charAt(i))) {
						if(!conteo.containsKey(cifrado.charAt(i)))
							conteo.put(cifrado.charAt(i),1);
						else
							conteo.put(cifrado.charAt(i), conteo.get(cifrado.charAt(i))+1);
					}
				}*/
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public void setText(String text) {
		if(!text.isEmpty())
			cifrado=text;
	}
	
}
