package utils;

import java.util.Comparator;

import algoritmoGenetico.individuos.Individuo;

public class Sorted implements Comparator<Individuo>{

	private boolean decreciente;
	
	public Sorted(boolean d) {
		super();
		decreciente = d;
	}

	@Override
	public int compare(Individuo arg0, Individuo arg1) {
		double v1 = arg0.getFitness();
		double v2 = arg1.getFitness();
		if (v1> v2) return (decreciente) ? -1 : 1;
		else if (v1 < v2) return (decreciente) ? 1 : -1;
		else return 0;
	}
	
}
