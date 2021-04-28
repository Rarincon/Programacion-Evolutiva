package algoritmoGenetico.seleccion;

import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public interface Seleccion {	
	public abstract List<Individuo> selecciona(List<Individuo> l, int tam);
}

