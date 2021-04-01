package algoritmoGenetico.individuos;

import java.util.List;

public class IndividuoCifrado extends Individuo{

	public IndividuoCifrado(double p) {
		super(p);
	}

	@Override
	public double evaluar() {
		
		return aptitud;
	}

	@Override
	public Individuo copia() {
		
		return null;
	}

}
