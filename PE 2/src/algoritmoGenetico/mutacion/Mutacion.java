package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public abstract class Mutacion {
	
	protected static final int n=3;
	protected double probMutacion;
	
	public Mutacion(double _probMutacion) {
		this.probMutacion=_probMutacion;
	}
	
	public abstract List<Individuo> mutarInd(List<Individuo> p);
}

