package algoritmoGenetico.cruces;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class CruceRaulRober extends Cruce{
	private static final int n=7;
	
	public CruceRaulRober(double p) {
		super(p);
	}

	@Override
	public List<Individuo> cruce(List<Individuo> pob) {
		List<Individuo> nuevaPob= new ArrayList<Individuo>();
		int pos1, pos2,x,y;
		Integer[] crom1,crom2, copia1, copia2;
		List<Integer> l1, l2;
	
		for(int i=0; i<pob.size(); i++) {
			nuevaPob.add(pob.get(i).copia());
		}
		
		for(int i=0; i<sel_cruce.size(); i+=2) {
			copia1=new Integer[TamC];
			copia2=new Integer[TamC];
			
			l1= new ArrayList<Integer>();
			l2= new ArrayList<Integer>();
			crom1= nuevaPob.get(sel_cruce.get(i)).getCromosoma();
			crom2= nuevaPob.get(sel_cruce.get(i+1)).getCromosoma();
			
			copia1=crom1.clone();
			copia2=crom2.clone();
			
			//Metodo Inventado///
			pos1=(int) (Math.random() *(TamC-n));
			pos2=(int) (Math.random() *(TamC-n));
			
			x=pos1;
			y=pos2;
			
			for(int j=0; j<n; j++) {
				l1.add(copia1[x]);
				copia1[x]=-1;
				l2.add(copia2[y]);
				copia2[y]=-1;
				x++;
				y++;			
			}
			
			x=pos1;
			y=pos2;
			
			for(int j=0; j < l1.size(); j++) {
				
				if(!esta(copia1, l2.get(j))) {
					copia1[x]=l2.get(j);
					x++;
				}
				if(!esta(copia2, l1.get(j))) {
					copia2[y]=l1.get(j);
					y++;
				}
			}
			
			int d=0;
			while(x < (pos1+n)) {
				while(esta(copia1, l1.get(d))) d++;
				copia1[x] =l1.get(d);
				x++;
			}
			
			d=0;
			while(y < (pos2+n)) {
				while(esta(copia2, l2.get(d))) d++;
				copia2[y] =l2.get(d);
				y++;
			}
			
			/////////////////////
			
			nuevaPob.get(sel_cruce.get(i)).setCromosoma(copia1);
			nuevaPob.get(sel_cruce.get(i+1)).setCromosoma(copia2);
		}
		
		return nuevaPob;
	}
	
	private boolean esta(Integer[] l, int e) {
		for (int i = 0; i < l.length; ++i) if(l[i] == e) return true;
		return false;
	}

}
