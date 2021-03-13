package algoritmoGenetico.mutacion;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Basica extends Mutacion {
	
	//private boolean mutado;
	//private double prob;
	//List<Individuo> nuevaPob;

	public Basica(List<Individuo> _poblacion, int _tamPoblacion, double _probMutacion) {
		super(_poblacion, _tamPoblacion, _probMutacion);
		
	}

	public List<Individuo> mutarInd() {
		
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		boolean mutado;
		double prob;
		int tam=poblacion.get(0).getTamCromosoma();
		Integer[] crom= new Integer[tam];
		for(int i=0;i<tamPoblacion;i++) {
			nuevaPob.add(poblacion.get(i).copia());
			mutado=false;
			crom=nuevaPob.get(i).getCromosoma();
			for(int j=0;j<tam;j++) {
				prob=Math.random();
				if(prob < probMutacion) {
					if(crom[j]==0)crom[j]=1;
					else crom[j]=0;
					mutado=true;
				}
			}
			if(mutado) nuevaPob.get(i).setCromosoma(crom);
		}
		
		return nuevaPob;
	}
		//List<Individuo> nuevaPob= new ArrayList<Individuo>();
		/*
		for(int i=0; i<this.tamPoblacion; i++) {
			nuevaPob.add(this.poblacion.get(i));
		}
		
		for(int i=0; i<this.tamPoblacion; i++) {
			mutado=false;
			
			for(int j=0; i<this.nuevaPob.get(i).getTamCromosoma(); i++) {
				prob= Math.random()*2;
				
				if(prob < this.probMutacion) {
					int jMutado=this.nuevaPob.get(i).getCromosoma()[j];
					if(jMutado==0) jMutado=1;
					else if(jMutado==1) jMutado=0;
					
					this.nuevaPob.get(i).setPosCromosoma(j, jMutado);
					mutado=true;
				}
				
				if(mutado) {
					this.nuevaPob.get(i).setFitness(this.nuevaPob.get(i).evaluar());
				}
			}
		}
		return nuevaPob;
	}*/

}
