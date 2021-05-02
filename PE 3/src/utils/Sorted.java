package utils;

import java.util.Comparator;

import algoritmoGenetico.individuos.Individuo;

public class Sorted implements Comparator<Individuo>{

	private boolean decreciente;
	private boolean fitness;
	
	public Sorted(boolean d, boolean f) {
		super();
		decreciente = d;
		fitness=f;
	}

	@Override
	public int compare(Individuo arg0, Individuo arg1) {
		double v1 = (fitness) ? arg0.getFitness() : arg0.getPuntAcu();
		double v2 = (fitness) ? arg1.getFitness() : arg1.getPuntAcu();
		if (v1> v2) return (decreciente) ? -1 : 1;
		else if (v1 < v2) return (decreciente) ? 1 : -1;
		else return 0;
	}
	
}
