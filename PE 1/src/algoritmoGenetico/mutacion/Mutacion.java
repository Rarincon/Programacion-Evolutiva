package algoritmoGenetico.mutacion;

import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public abstract class Mutacion {
	
	protected List<Individuo> poblacion;
	protected int tamPoblacion;
	protected double probMutacion;
	
	public Mutacion(List<Individuo> _poblacion, int _tamPoblacion, double _probMutacion) {
		this.poblacion= new SortedArrayList<>();
		for(int i=0;i<_tamPoblacion;i++)
			poblacion.add(_poblacion.get(i)); //Revisar esta copia
		this.tamPoblacion=_tamPoblacion;
		this.probMutacion=_probMutacion;
	}
	
	public abstract List<Individuo> mutarInd();
}

