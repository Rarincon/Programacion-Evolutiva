package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import algoritmoGenetico.AlgoritmoGenObserver;
import controller.Controller;

public class ReadPanel extends JPanel implements AlgoritmoGenObserver, ActionListener{

	private Controller _ctrl;
	private JTextArea text;
	//private JLabel text;
	private Border _defaultBorder = BorderFactory.createLineBorder(Color.black, 2);	
	
	public ReadPanel(Controller c) {
		_ctrl=c;
		createData();
		_ctrl.addObserver(this);
	}

	private void createData() {
		setBorder(BorderFactory.createTitledBorder(_defaultBorder, "Descifrado", TitledBorder.LEFT, TitledBorder.TOP));
		text = new JTextArea();
		text.setEditable(false);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		JScrollPane area = new JScrollPane(text,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		area.setPreferredSize(new Dimension(320, 200));
		this.add(area);	
	}

	@Override
	public void update(int generation, Map<String, Object> stats) {
		String s =  (String) stats.get("Descifrado");
		text.setText(s);
	}

	@Override
	public void reset() {
		text.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
