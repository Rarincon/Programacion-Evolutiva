package algoritmoGenetico;

import java.util.Map;

public interface AlgoritmoGenObserver {

	public void update(int generation, Map<String, Object> stats);
	
	public void reset();
}
