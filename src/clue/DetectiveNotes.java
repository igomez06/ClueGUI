package clue;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
//notes
//guesses
public class DetectiveNotes extends JDialog {
	private People p;
	private Rooms r;
	private Weapons w;

	private createGuessPeople people;
	private createGuessRooms rooms;
	private createGuessWeapons weapons;


	public DetectiveNotes() {
		setSize(600,400);
		setTitle("Detective Notes");

		//notes
		p = new People();
		r = new Rooms();
		w = new Weapons();
		setLayout(new GridLayout(2,3));
		add(p);
		add(r);
		add(w);

		//guesses
		people = new createGuessPeople();
		rooms = new createGuessRooms();
		weapons = new createGuessWeapons();
		
		add(people);
		add(rooms);
		add(weapons);

	}

	public class People extends JPanel{
		private JCheckBox a,b,c,d,e,f;


		public People() {
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("a");
			b = new JCheckBox("b");
			c = new JCheckBox("c");
			d = new JCheckBox("d");
			e = new JCheckBox("e");
			f = new JCheckBox("f");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			setBorder (new TitledBorder(new EtchedBorder(), "People"));

		}
	}

	public class Rooms extends JPanel{
		private JCheckBox a,b,c,d,e,f,g,h,i;

		public Rooms() {
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("a");
			b = new JCheckBox("b");
			c = new JCheckBox("c");
			d = new JCheckBox("d");
			e = new JCheckBox("e");
			f = new JCheckBox("f");
			g = new JCheckBox("g");
			h = new JCheckBox("h");
			i = new JCheckBox("i");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			add(g);
			add(h);
			add(i);
			setBorder (new TitledBorder(new EtchedBorder(), "Rooms"));
		}
	}

	public class Weapons extends JPanel{
		private JCheckBox a,b,c,d,e,f;
		public Weapons(){
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("a");
			b = new JCheckBox("b");
			c = new JCheckBox("c");
			d = new JCheckBox("d");
			e = new JCheckBox("e");
			f = new JCheckBox("f");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			setBorder (new TitledBorder(new EtchedBorder(), "Weapon"));
		}
	}
/////////////
//combo boxes
///////////
		public class createGuessPeople extends JPanel {
			public createGuessPeople() {
				JComboBox<String> person = new JComboBox<String>();
				setLayout(new GridLayout(0,2));
				person.addItem("aaaaaaaaaaaaaaa");
				person.addItem("a");
				person.addItem("a");
				person.addItem("a");
				person.addItem("a");
				person.addItem("a");
				setBorder (new TitledBorder(new EtchedBorder(), "Person Guess"));

			}
		}
		public class createGuessRooms extends JPanel {
			public createGuessRooms() {
				JComboBox<String> rooms = new JComboBox<String>();
				setLayout(new GridLayout(0,2));
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				rooms.addItem("a");
				setBorder (new TitledBorder(new EtchedBorder(), "Room Guess"));

			}
		}
		public class createGuessWeapons extends JPanel {
			public createGuessWeapons() {
				JComboBox<String> weapons = new JComboBox<String>();
				setLayout(new GridLayout(0,2));
				weapons.addItem("a");
				weapons.addItem("a");
				weapons.addItem("a");
				weapons.addItem("a");
				weapons.addItem("a");
				weapons.addItem("a");
				setBorder (new TitledBorder(new EtchedBorder(), "Weapon Guess"));

			}
		}

	
}




