package clue;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlDisplay extends JPanel{
	private JTextField p,r,w;
	
	public ControlDisplay() {
		super();
		setLayout(new GridLayout(3,0));
		p = new JTextField("personCard");
		r = new JTextField("roomCard");
		w = new JTextField("weaponCard");
		add(p);
		add(r);
		add(w);
		setBorder (new TitledBorder(new EtchedBorder(), "Person Card"));
	}
	
}
