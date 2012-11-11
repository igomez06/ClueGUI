package clue;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlDisplay extends JPanel{
	
	private WhoseTurn wt;
	private NextButton nb;
	private MakeAcc ma;
	private Die die;
	
	public ControlDisplay() {
		super();
		setSize(300,500);
		setLayout(new GridLayout(3,3));
		
		wt = new WhoseTurn();
		nb = new NextButton();
		ma = new MakeAcc();
		die = new Die();
		add(pCard);
		add(rCard);
		add(wCard);
		add(wt);
		add(nb);
		add(ma);
		add(die);
		
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
	
	public class WhoseTurn extends JPanel {
		JTextField wt;
		
		public WhoseTurn() {
			wt = new JTextField("someones turn");
			add(wt);
			setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));
		}
	}
	
	public class NextButton extends JPanel {
		JButton nextB;
		
		public NextButton() {
			nextB = new JButton("Next Player");
			add(nextB);
		}
	}
	
	public class MakeAcc extends JPanel {
		JButton ma;
		public MakeAcc() {
			ma = new JButton("Make Accusation");
			add(ma);
		}
	}
	
	public class Die extends JPanel {
		JTextField die;
		public Die() {
			die = new JTextField("Roll");
			add(die);
			setBorder(new TitledBorder(new EtchedBorder(), "Die Roll"));
		}
		
		
	}
	
	public class Guess extends JPanel {
		
	}
}
