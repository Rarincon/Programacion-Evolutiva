package algoritmoGenetico.individuos;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class IndividuoCifrado extends Individuo{

	public IndividuoCifrado(String p) {
		super(p);
	}

	@Override
	public double evaluar() {
		descifrado=Descifrar();
		NGram(descifrado);
		
		double fitMon=0,fitBi=0,fitTri=0;
		
		for(Entry<String, Integer> entry : MonoFrec.entrySet()) {
			
			
		}
		
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
