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
		double value0 = arg0.getFitness();
		double value1 = arg1.getFitness();
		if (value0 > value1) return (decreciente) ? -1 : 1;
		else if (value0 < value1) return (decreciente) ? 1 : -1;
		else return 0;
	}
	
}
