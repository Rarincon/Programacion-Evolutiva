package algoritmoGenetico;

import java.util.Map;

public interface AlgoritmoGenObserver {

	public void ciclo(int generation, Map<String, Object> stats);
	
	public void reset();
}
