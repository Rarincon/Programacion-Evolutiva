package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;

public abstract class Cruce {
	protected int tipoSeleccion;
	protected int contSelecionados;
	protected int puntoCruce;
	protected double probCruce;
	protected List<Individuo> pobSeleccionada;
	protected int tamPoblacion;
	
	public Cruce(List<Individuo> _poblacion, int _tamPoblacion, double _probCruc) {
		this.pobSeleccionada= new ArrayList<>();
		this.tamPoblacion=_tamPoblacion;
		pobSeleccionada=_poblacion;
		probCruce=_probCruc;
		/*for(int i=0;i<_tamPoblacion;i++)
			pobSeleccionada.add(_poblacion.get(i)); //Revisar esta copia*/
	}
	
	public abstract List<Individuo> selecCruzados();
	public abstract List<Individuo> cruce();
}
