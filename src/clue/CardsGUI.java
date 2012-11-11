package clue;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
//this class is to draw the cards that are in the players hand
public class CardsGUI extends JPanel{
	private PersonCard pCard;
	private RoomCard rCard;
	private WeaponCard wCard;
	
	public CardsGUI() {
		setLayout(new GridLayout(3,0));
		pCard = new PersonCard();
		rCard = new RoomCard();
		wCard = new WeaponCard();
		JLabel label = new JLabel("Your Cards");
		add(label);
		add(pCard);
		add(rCard);
		add(wCard);
	}
	public class PersonCard extends JPanel{
		JTextField pc;
		
		public PersonCard() {
			pc = new JTextField("enter card name");
			add(pc);
			setBorder (new TitledBorder(new EtchedBorder(), "Person Card"));
		}
	}
	
	public class RoomCard extends JPanel{
		JTextField pc;
		
		public RoomCard() {
			pc = new JTextField("enter card name");
			add(pc);
			setBorder (new TitledBorder(new EtchedBorder(), "Room Card"));
		}
	}
	
	public class WeaponCard extends JPanel{
		JTextField pc;
		
		public WeaponCard() {
			pc = new JTextField("enter card name");
			add(pc);
			setBorder (new TitledBorder(new EtchedBorder(), "Weapon Card"));
		}
	}
}
