package algoritmoGenetico.individuos;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class IndividuoCifrado extends Individuo{

	public IndividuoCifrado(String p) {
		super(p);
	}

	@Override
	public double evaluar(Map<String,Map<Object,Integer>> map,Map<String, Long> total) {
		descifrado=Descifrar();
		NGram(descifrado);
		HashMap<Object,Integer>M=(HashMap<Object, Integer>) map.get("Monogram");
		HashMap<Object,Integer>B=(HashMap<Object, Integer>) map.get("Bigram");
		HashMap<Object,Integer>T=(HashMap<Object, Integer>) map.get("Trigram");
		double fitMon=0,fitBi=0,fitTri=0;
		String key;
		double a,b,t,x;
	
		double valu;
		b = total.get("Monogram");
		t = MonoFrec.get("total") ;
		for(Entry<String, Integer> entry : MonoFrec.entrySet()) {
			key= entry.getKey(); //Frecuencia real de aparicion
			if(M.containsKey(key)) {
				a = M.get(key);  //Frecuencia esperada de aparicion			
				a = a/b;
				valu=entry.getValue();		
				valu = valu / t;
				x=Math.abs(valu*Math.log(a)/Math.log(2));
				fitMon+=x;
			}
		}
		fitMon*=0.05;
		b = total.get("Bigram");
		t = BiFrec.get("total") ;
		for(Entry<String, Integer> entry : BiFrec.entrySet()) {
			key= entry.getKey(); //Frecuencia real de aparicion
			if(B.containsKey(key)) {
				a = B.get(key);  //Frecuencia esperada de aparicion				
				a = a/b;
				valu=entry.getValue();				
				valu = valu / t;
				x=Math.abs(valu*Math.log(a)/Math.log(2));
				fitBi+=x;
			}
		}
		b = total.get("Trigram");
		t = TriFrec.get("total") ;
		fitBi*=0.3;
		for(Entry<String, Integer> entry : TriFrec.entrySet()) {
			key= entry.getKey(); //Frecuencia real de aparicion
			if(T.containsKey(key)) {
				a = T.get(key);  //Frecuencia esperada de aparicion				
				a = a/b;
				valu=entry.getValue();			
				valu = valu / t;
				x=Math.abs(valu*Math.log(a)/Math.log(2));
				fitTri+=x;
			}
		}
		fitTri*=0.7;
		return fitMon+fitBi+fitTri;
	}

	@Override
	public Individuo copia() {
		Individuo n=new IndividuoCifrado(cifrado);
		n.setCromosoma(getCromosoma());
		n.setFitness(getFitness());
		n.setPunt(getPunt());
		n.setPuntAcum(getPuntAcum());
		return n;
	}

}
