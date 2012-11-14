package clue;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
//this class is to draw the cards that are in the players hand
public class CardsGUI extends JPanel{
	
	//Create three new jPanels
	private DrawPanel p,r,w;
	private String pCard = "Person Card";
	private String rCard =  "Room Card";
	private String wCard = "Weapon Card";

	public CardsGUI(Player player) {
		setLayout(new GridLayout(0,2));
		p = new DrawPanel();
		r = new DrawPanel();
		w = new DrawPanel();
		JLabel label = new JLabel("Your Cards");
		p.createDisplay(player.getCards().get(0).getType().toString(), player.getCards().get(0));
		r.createDisplay(player.getCards().get(1).getType().toString(), player.getCards().get(1));
		w.createDisplay(player.getCards().get(2).getType().toString(), player.getCards().get(2));
		
		add(label);
		add(p);
		add(r);
		add(w);
	}
	

	public class DrawPanel extends JPanel {
		JTextField card;
		
		public DrawPanel() {
			
		}
		public JTextField createDisplay (String name, Card c) {
			card = new JTextField(c.getName());
			add(card);
			setBorder(new TitledBorder(new EtchedBorder(), name));
			return card;
		}
	}
}
