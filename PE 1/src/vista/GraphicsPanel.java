package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class GraphicsPanel extends JPanel implements AlgoritmoGenObserver{

	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	private Controller ctrl;
	private Plot2DPanel plot;
	
	private List<Double>Generaciones;
	private List<Double>MejorActual;
	//private List<Double>PeorActual;
	private List<Double>Media; //Verde
	private List<Double>Objetivo;
	
	public GraphicsPanel(Controller _ctrl) {
		ctrl=_ctrl;
		ctrl.addObserver(this);
		initGUI();		
	}

	public void initGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Graficas", TitledBorder.LEFT, TitledBorder.TOP));
		
		plot = new Plot2DPanel();
		plot.setAxisLabel(0, " Generaciones");
		plot.setAxisLabel(1, " Fitness");
		plot.addLegend("SOUTH");
		
		Generaciones= new ArrayList<Double>();
		
		add(plot);
		
		setPreferredSize(new Dimension(800, 500));
	}
	
	public void paint() {
		 plot.removeAllPlots();
		  
		if (!Generaciones.isEmpty()) {
		  // add a line plot to the PlotPanel
			plot.addLinePlot("Absolute Best", Color.BLUE, Array(Generaciones), Array(Objetivo));
			plot.addLinePlot("Average Fitness", Color.GREEN, Array(Generaciones), Array(Media));
			plot.addLinePlot("Best Fitness", Color.RED, Array(Generaciones), Array(MejorActual));
			//plot.addLinePlot("Worst Fitness", Color.ORANGE, Array(Generaciones), Array(_currentWorstValues));
		}
	}
	
	private double[] Array(List<Double> l) {
		double[] array = new double[l.size()];	int i = 0;
		for (Double d : l) {	array[i] = d.doubleValue();	++i;	}
		return array;
	}
	
	@Override
	public void update(int generation, Map<String, Object> stats) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
		// TODO Auto-generated method stub
		
=======
=======
>>>>>>> Stashed changes
		plot.setAxisLabel(1, " Generacion: " + Integer.toString(generation));
		Generaciones.add((double) generation);
		Media.add((Double) stats.get("Media"));
		Objetivo.add((Double) stats.get("Objetivo"));
		MejorActual.add((Double) stats.get("Mejor Actual"));
		//_currentWorstValues.add((Double) stats.get("Current Worst Fitness"));
		paint();
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
