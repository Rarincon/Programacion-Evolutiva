package algoritmoGenetico.individuos;

import java.util.HashMap;
import java.util.List;
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
		double fitMon=0,fitBi=0,fitTri=0;
		String key;
		double a,b;
		/*
		for(Entry<String, Integer> entry : MonoFrec.entrySet()) {
			key= entry.getKey();
			if(M.containsKey(key)) {
				a = M.get(key);
				a *=100;
				b = total.get("Monogram");
				a = a/b;
				if(a>0.1) {
					double x= (Math.log(a)/Math.log(2)); 
					fitMon+= entry.getValue() * x;
				}
			}
			
		}
		for(Entry<String, Integer> entry : BiFrec.entrySet()) {
			key= entry.getKey();
			if(B.containsKey(key)) {
				a = B.get(key);
				a *=100;
				b = total.get("Bigram");
				a = a/b;
				if(a>0.1) {
					double x= (Math.log(a)/Math.log(2)); 
					fitBi+= entry.getValue() * x;
				}
			}			
		}
		*/
		double F=0;
		double valu=0;
		for(Entry<String, Integer> entry : MonoFrec.entrySet()) {
			key= entry.getKey(); //Frecuencia real de aparicion
			if(M.containsKey(key)) {
				a = M.get(key);  //Frecuencia esperada de aparicion
				a *=100;
				b = total.get("Bigram");
				a = a/b;
				valu=entry.getValue()*100;
				valu = valu / MonoFrec.get("total") ;
				F+=(Math.pow((valu-a), 2));
			}
		}
		//aptitud= fitMon+fitBi+fitTri;
		aptitud=F;
		
		//Para cada ngram en Textodescifrado
		/*{
		 if (frecuenciaEnIngles(ngram)) != 0)  
		         fitness += frecuenciaEnTextoDescifrado(ngram) * log 2 (frecuenciaEnIngles(ngram));
		      
		}*/
		
		return aptitud;
	}

	@Override
	public Individuo copia() {
		Individuo n=new IndividuoCifrado(cifrado);
		n.setCromosoma(getCromosoma());
		return n;
	}

}
