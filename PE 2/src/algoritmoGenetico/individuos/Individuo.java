package algoritmoGenetico.individuos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public abstract class Individuo implements Comparable<Individuo> {
	
	static private final int TAM=26;
	static private final String[] abecedario={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	protected Integer[] cromosoma;
	//protected int valorError;
	protected Random r = new Random();
	protected Map<String,Integer> abec;
	
	protected double aptitud;
	protected double puntuacion;
	protected double punt_acum;
	protected Map<String,Long> Mono2Frec,Bi2Frec;
	protected Map<String,Integer> MonoFrec,BiFrec,TriFrec;
	
	//protected double precision;
	protected String cifrado,descifrado;
	
	public Individuo(String s) {
		cifrado=s;
		//Gram();
		carga();
		cromosoma =  new Integer[TAM];		
	}
	
	private void carga() {
		abec = new HashMap<String,Integer>();
		for(int i=0;i<TAM;i++) {
			abec.put(abecedario[i], i);
		}
	}
	
	public void inicializa() {
		List<Integer> shuffle= new ArrayList<Integer>();
		for(int i=0;i<TAM;i++)shuffle.add(i);
		Collections.shuffle(shuffle);
		for(int i=0;i<TAM;i++)cromosoma[i]=shuffle.get(i);
		
		/*int caracteres = (int)(Math.random()*20)+2; 
		for (int i=0; i<caracteres; i++){ 
			int codigoAscii = (int)Math.floor(Math.random()*(122 -97)+97);
			cromosoma[i]=codigoAscii;
	   }*/
	}
	
	protected String Descifrar() {
		String a="";
		String p="";
		for(int i=0;i<cifrado.length();i++) {
			if(Character.isLetter(cifrado.charAt(i))){
				p+=cifrado.charAt(i);
				a+=abecedario[(int) cromosoma[abec.get(p)]];
				p="";
			}
			else a+=cifrado.charAt(i);
		}
		return a;		
	}

	/*private void Gram() {
		Stream<String> o= Arrays.stream(cifrado.replaceAll("(?<!^| ).(?! |$)", "$0").split(" |(?<=\\G.)"));
		Stream<String> p= Arrays.stream(cifrado.replaceAll("(?<!^| ).(?! |$)", "$0$0").split(" |(?<=\\G..)"));
		Stream<String> m= Arrays.stream(cifrado.replaceAll("(?<!^| ).(?!| ).(?! |$)", "$0$0$0").split(" |(?<=\\G...)"));
		Mono2Frec=Arrays.stream(cifrado.replaceAll("(?<!^| ).(?! |$)", "$0").split(" |(?<=\\G.)")).filter(s -> s.length() == 1).collect(Collectors.groupingBy(s -> s,Collectors.counting()));
		Bi2Frec = Arrays.stream(cifrado.replaceAll("(?<!^| ).(?! |$)", "$0$0").split(" |(?<=\\G..)")).filter(s -> s.length() > 1).collect(Collectors.groupingBy(s -> s,Collectors.counting()));
		//TriFrec = Arrays.stream(cifrado.replaceAll("(?<!^| ).(?! |$)", "$0$0$0").split(" |(?<=\\G...)")).filter(s -> s.length() > 2).collect(Collectors.groupingBy(s -> s,Collectors.counting()));
		NGram(cifrado);
	}*/
	
	protected void NGram(String text) {
		String trio="",mono="",bi="";
		
		MonoFrec= new HashMap<String,Integer>();
		BiFrec= new HashMap<String,Integer>();
		TriFrec= new HashMap<String,Integer>();
		
		for(int i=0;i<text.length();i++) {
			if(!Character.isWhitespace(text.charAt(i))) {
				mono+=text.charAt(i);
				bi+=text.charAt(i);
				trio+=text.charAt(i);
				if(mono.length()==1) {
					if(!MonoFrec.containsKey(mono)) {
						MonoFrec.put(mono, 1);
					}
					else {
						MonoFrec.put(mono, MonoFrec.get(mono)+1);
					}
					mono="";
				}
				if(bi.length()>1) {
					if(!BiFrec.containsKey(bi)) {
						BiFrec.put(bi, 1);
					}
					else {
						BiFrec.put(bi, BiFrec.get(bi)+1);
					}
					bi = bi.substring(1, bi.length());
				}
				if(trio.length()>2) {
					if(!TriFrec.containsKey(trio)) {
						TriFrec.put(trio, 1);
					}
					else {
						TriFrec.put(trio, TriFrec.get(trio)+1);
					}
					trio = trio.substring(1, trio.length());
				}
			}
			else {
				mono="";
				bi="";
				trio="";
			}
		}
		int total=0;
		for(Entry<String, Integer> entry : MonoFrec.entrySet()) {
			total+= entry.getValue();
		}
		MonoFrec.put("total", total);
		total=0;
		for(Entry<String, Integer> entry : BiFrec.entrySet()) {
			total+= entry.getValue();
		}
		BiFrec.put("total", total);
		total=0;
		for(Entry<String, Integer> entry : TriFrec.entrySet()) {
			total+= entry.getValue();
		}
		TriFrec.put("total", total);
	}
	
	public void setFitness(double f) {
		aptitud=f;
	}
	
	public void setPunt(double f) {
		puntuacion=f;
	}
	
	public void setPuntAcum(double f) {
		punt_acum=f;
	}
	
	public double getPunt() {
		return puntuacion;
	}
	
	public double getPuntAcum() {
		return punt_acum;
	}
	
	public double getFitness() {
		return aptitud;
	}
	
	public int getTamCromosoma() {
		return cromosoma.length;
	}

	public Integer[] getCromosoma() {
		return cromosoma;
	}
	
	public String getDescifrado() {
		return descifrado;
	}
	
	public String getConversion() {
		String s="";
		for(int i=0;i<TAM;i++)
			s+= abecedario[cromosoma[i]]+" ";
		return s;
	}
		
	public void setCromosoma(Integer[] crom) {
		for(int i=0; i<cromosoma.length;i++) {
			this.cromosoma[i]=(Integer) crom[i];
		}
	}
	
	public void setPosCromosoma(int pos, int valor) {
		this.cromosoma[pos]=valor;
	}
	
	public abstract double evaluar(Map<String,Map<Object,Integer>> map, Map<String, Long> total);
	public abstract Individuo copia();
	
	public int compareTo(Individuo arg0) {
		if(this.getFitness() < arg0.getFitness()) return 1;
		else if(this.getFitness()==arg0.getFitness()) return 0;
		else return -1;
	}

	public static double apply(Integer[] integers) {
		// TODO Auto-generated method stub
		return 0;
	}
}