package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class WritePanel extends JPanel implements AlgoritmoGenObserver {

	private Controller _ctrl;
	private JTextArea text;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	
	public WritePanel(Controller c) {
		_ctrl=c;
		createData();
		_ctrl.addObserver(this);
	}

	private void createData() {
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Cifrado", TitledBorder.LEFT, TitledBorder.TOP));
		text = new JTextArea();		
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		text.setEditable(true);
		JScrollPane area = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		area.setPreferredSize(new Dimension(320, 200));
		this.add(area);	
	}

	@Override
	public void update(int generation, Map<String, Object> stats) {
	
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
