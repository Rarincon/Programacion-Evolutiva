package algoritmoGenetico.seleccion;

import java.util.List;

import algoritmoGenetico.individuos.Individuo;

public class Ranking implements Seleccion{ //REPASAR
	
	static private final double _beta = 1.5;

	@Override
	public List<Individuo> selecciona(List<Individuo> l, int tam) {
		
		rankingPunctuation(l);
		Ruleta roulette = new Ruleta();
		return roulette.selecciona(l, tam);
	}
	
	private void rankingPunctuation(List<Individuo> pop) {
		double accPunc = 0.0;
		for (int i = 0; i < pop.size(); ++i) {
			double probOfIth = (double)i/pop.size();
			probOfIth *= 2*(_beta-1);
			probOfIth = _beta - probOfIth;
			probOfIth = (double)probOfIth * ((double)1/pop.size());
			pop.get(i).setPuntAcum(accPunc);
			pop.get(i).setPunt(probOfIth);
			accPunc += probOfIth;
		}
	}
}
