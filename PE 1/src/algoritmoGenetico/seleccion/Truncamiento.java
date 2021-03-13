package algoritmoGenetico.seleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

import algoritmoGenetico.individuos.Individuo;
import utils.SortedArrayList;;

public class Truncamiento extends Seleccion {
	
	private double trunc;


	public Truncamiento(List<Individuo> _poblacion, int _tamPoblacion) { //DEL 50% el de 10% no lo hacemos aun
		super(_poblacion, _tamPoblacion);
		trunc=0.5; //Este valor puede cambiar con la GUI, habra opciones;
	}

	
	public List<Individuo> selecciona() { //Quizas haya que ordenar la poblacion por fitness
		List<Individuo> nuevaPob;
		nuevaPob = new ArrayList<Individuo>();
		Collections.sort(poblacion); //REVISAR PARA LA ordenacion
		
		int p = (int) (1/trunc);
		int Nselccionados = poblacion.size()/p;
		
		for (int i =0; i < Nselccionados; i++){
			for (int j=0; j<p;j++){
				nuevaPob.add(poblacion.get(i).copia());
			}
		}
		return nuevaPob;
	}

}
