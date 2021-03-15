package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.math.plot.Plot2DPanel;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class GraphicsPanel extends JPanel implements AlgoritmoGenObserver{

	private static final long serialVersionUID = 1L;
	private Controller ctrl;
	private Plot2DPanel plot;
	
	public GraphicsPanel(Controller _ctrl) {
		ctrl=_ctrl;
		ctrl.addObserver(this);
		initGUI();		
	}

	public void initGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2), "Graphics", TitledBorder.LEFT, TitledBorder.TOP));
		
		plot = new Plot2DPanel();
		plot.setAxisLabel(0, " Generation");
		plot.setAxisLabel(1, " Value");
		plot.addLegend("NORTH");
		
		add(plot);
		
		setPreferredSize(new Dimension(800, 500));
	}
	
	@Override
	public void update(int generation, Map<String, Object> stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
