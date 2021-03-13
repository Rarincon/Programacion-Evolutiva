package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceSBX extends Cruce {
	
	private List<Integer> sel_cruce;
	public CruceSBX(List<Individuo> _poblacion, int _tamPoblacion, double _probCruce) {
		super(_poblacion, _tamPoblacion, _probCruce);
		sel_cruce=new ArrayList<Integer>();
	}

	public List<Individuo> selecCruzados() {
		int tam=this.pobSeleccionada.get(0).getTamCromosoma(); //Coges el tama�o del primero
		int prob=(int) Math.random(); //Devuelve un numero entre 0 y 1
		//this.probCruce=Math.random(); //Lo ponemos con el gui
		this.puntoCruce=(int) (Math.random()*tam);
		for(int i=0; i<this.tamPoblacion; i++) {
			if(prob < this.probCruce) {
				sel_cruce.add(i);
			}
		}
		if((sel_cruce.size()%2)==1) {
			sel_cruce.remove(sel_cruce.size()-1); 
		}
		
		return cruce();
	}

	public List<Individuo> cruce() {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		Integer[] crom1, crom2;
		int aux;
		
		for(int i=0; i<this.tamPoblacion; i++) {
			nuevaPob.add(this.pobSeleccionada.get(i));
		}
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Integer[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Integer[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			for(int j=0; j<this.puntoCruce; j++) {
				aux=crom1[j];
				crom1[j]=crom2[j];
				crom2[j]=aux;
			}
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(crom1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(crom2);
		}
		return nuevaPob;
	}

}
