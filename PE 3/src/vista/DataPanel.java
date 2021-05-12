package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class DataPanel extends JPanel implements AlgoritmoGenObserver{ 
	
	private static final long serialVersionUID = 1L;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	private JLabel fit,med;
	private JTextArea text;
	
	private Controller _ctrl;
	private double fitness,media;

	
	
	public DataPanel(Controller c) {
		_ctrl=c;
		fitness=0;
		media=0;
		
		createData();
		_ctrl.addObserver(this);
	}
	
	
	private void createData() {		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Solucion", TitledBorder.LEFT, TitledBorder.TOP));
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.setPreferredSize(new Dimension(50, 50));
		JPanel s = new JPanel();
		s.setLayout(new GridLayout(2,1));
		s.setPreferredSize(new Dimension(50,100));
		
		JLabel x= new JLabel("Cromosoma");
		x.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		x.setVerticalAlignment(SwingConstants.CENTER);
		JPanel x0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x0.add(x);
		String aux= "Fitness: "+ String.valueOf(fitness);
		fit = new JLabel(aux);
		JPanel x1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x1.add(fit);
		String aux1= "Media: "+ String.valueOf(media);
		med = new JLabel(aux1);
		JPanel x4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		x4.add(med);
		
		text = new JTextArea();
		text.setEditable(false);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		JScrollPane area = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		area.setPreferredSize(new Dimension(400, 400));
		
		fit.setVerticalAlignment(SwingConstants.CENTER);
		med.setVerticalAlignment(SwingConstants.CENTER);
		
		p.add(x1);
		p.add(x4);
		s.add(p);
		s.add(area);
		
		add(x0, BorderLayout.NORTH);
		add(s, BorderLayout.CENTER);
		setPreferredSize(new Dimension(200, 200));
	}


	@Override
	public void update(int generation, Map<String, Object> stats) {
		
		fitness= (double) stats.get("fitness");
		media= (double) stats.get("Media");
		
		String aux= "Fitness: "+ String.valueOf(fitness);
		String aux1= "Media: "+ String.valueOf(media);
		String s =  (String) stats.get("Arbol");
		fit.setText(aux);
		med.setText(aux1);	
		text.setText(s);
		
	}

	@Override
	public void reset() {		
		fit.setText("Fitness: --");
		med.setText("Media: --");
		text.setText("");
	}
	
}

