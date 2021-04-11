package algoritmoGenetico.individuos;

import java.util.List;

public class IndividuoCifrado extends Individuo{

	public IndividuoCifrado(String p) {
		super(p);
	}

	@Override
	public double evaluar() {
		String descif=Descifrar();
		
		
		return aptitud;
	}

	@Override
	public Individuo copia() {
		
		return null;
	}

}
