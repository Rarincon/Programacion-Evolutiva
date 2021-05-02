package algoritmoGenetico.individuos;

public class Terminal {
	private Tipo[] terminales = {Tipo.AVANZA, Tipo.GIRA_DERECHA, Tipo.GIRA_IZQUIERDA};
	
	public static boolean isTerminal(Tipo tipoNodo){
		return tipoNodo == Tipo.AVANZA || tipoNodo == Tipo.GIRA_DERECHA || tipoNodo == Tipo.GIRA_IZQUIERDA;
	}
}
