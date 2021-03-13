package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.individuos.Individuo;

public class CruceMonopunto extends Cruce {
	
	private List<Integer> sel_cruce;
	public CruceMonopunto(List<Individuo> _poblacion, int _tamPoblacion, double _probCruce) {
		super(_poblacion, _tamPoblacion, _probCruce);
		sel_cruce=new ArrayList<Integer>();
	}

	public List<Individuo> selecCruzados() { //REVISAR YA QUE NO SE CRUZA NINGUN
		
		int tam=this.pobSeleccionada.get(0).getTamCromosoma(); //Coges el tama�o del primero
		int prob=(int) Math.random(); //Devuelve un numero entre 0 y 1 //NOSE QUENHACE LA VERDAD
		//this.probCruce=Math.random(); //Lo ponemos con el gui
		double p;//PROBABILIDAD
		this.puntoCruce=(int) (Math.random()*tam);
		for(int i=0; i<this.tamPoblacion; i++) {
			p=Math.random();
			if(p < this.probCruce) {
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
			nuevaPob.add(this.pobSeleccionada.get(i).copia());
		}
		for(int i=0; i<sel_cruce.size(); i+=2) {
			crom1=new Integer[nuevaPob.get(sel_cruce.get(i)).getTamCromosoma()];
			crom2=new Integer[nuevaPob.get(sel_cruce.get(i+1)).getTamCromosoma()];
			crom1=nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2=nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			/*System.out.print("Antiguos crom: ");
			for(int j=0; j<23; j++) {				
				System.out.print(crom1[j]);
			}
			System.out.print(" ");
			for(int j=0; j<23; j++) {
				System.out.print(crom2[j]);
			}
			System.out.print("\n");*/
			for(int j=0; j<this.puntoCruce; j++) {
				aux=crom1[j];
				crom1[j]=crom2[j];
				crom2[j]=aux;
			}
			/*
			System.out.print("Nuevosss crom: ");
			for(int j=0; j<23; j++) {
				
				System.out.print(crom1[j]);
			}
			System.out.print(" ");
			for(int j=0; j<23; j++) {
				System.out.print(crom2[j]);
			}
			System.out.print("\n");*/
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(crom1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(crom2);
		}
		return nuevaPob;
	}

}




