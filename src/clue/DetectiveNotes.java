package clue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
//notes
//guesses
public class DetectiveNotes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private People p;
	private Rooms r;
	private Weapons w;

	private comboBoxPeople people;
	private comboBoxRooms rooms;
	private comboBoxWeapons weapons;
	
	public ArrayList<String> pList;
	public ArrayList<String> rList;
	public ArrayList<String> wList;
	

	public DetectiveNotes() {
		setSize(750,500);
		setTitle("Detective Notes");

		setLayout(new GridLayout(2,3));

		//notes
		p = new People();
		r = new Rooms();
		w = new Weapons();

		add(p);
		add(r);
		add(w);

		//guesses
		people = new comboBoxPeople();
		rooms = new comboBoxRooms();
		weapons = new comboBoxWeapons();

		add(people);
		add(rooms);
		add(weapons);


	}

	public class People extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JCheckBox a,b,c,d,e,f;

		public People() {
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("Craig");
			b = new JCheckBox("Lars");
			c = new JCheckBox("Panda");
			d = new JCheckBox("Andrew");
			e = new JCheckBox("Nathan");
			f = new JCheckBox("Roz");
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
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JCheckBox a,b,c,d,e,f,g,h,i, j;

		public Rooms() {
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("Conservatory");
			b = new JCheckBox("Kitchen");
			c = new JCheckBox("Ballroom");
			d = new JCheckBox("Billiard Room");
			e = new JCheckBox("Library");
			f = new JCheckBox("Study");
			g = new JCheckBox("Dining Room");
			h = new JCheckBox("Lounge");
			i = new JCheckBox("Hall");
			j = new JCheckBox("Closet");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			add(g);
			add(h);
			add(i);
			add(j);
			setBorder (new TitledBorder(new EtchedBorder(), "Rooms"));
		}
	}

	public class Weapons extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JCheckBox a,b,c,d,e,f;
		public Weapons(){
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("CandleStick");
			b = new JCheckBox("Knife");
			c = new JCheckBox("ShoeLace");
			d = new JCheckBox("Gun");
			e = new JCheckBox("Hydrogen Bomb");
			f = new JCheckBox("Pile of dirt");
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
	public class comboBoxPeople extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JComboBox<String> person;
		public comboBoxPeople() {
			super();
			person = new JComboBox<String>();
			setLayout(new GridLayout(0,2));
			createGuessPeople();
			add(person, BorderLayout.CENTER);
			setBorder (new TitledBorder(new EtchedBorder(), "Person Guess"));

		}
		public void createGuessPeople(){
			person = new JComboBox<String> ();
			person.addItem("No Guess");
			person.addItem("Craig");
			person.addItem("Lars");
			person.addItem("Panda");
			person.addItem("Andrew");
			person.addItem("Nathan");
			person.addItem("Roz");
		}
	}
	public class comboBoxRooms extends JPanel {
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JComboBox<String> rooms;
		public comboBoxRooms() {
			super();
			rooms = new JComboBox<String>();
			setLayout(new GridLayout(0,2));
			createGuessRoom();
			add(rooms, BorderLayout.CENTER);
			setBorder (new TitledBorder(new EtchedBorder(), "Room Guess"));
		}
		public void createGuessRoom(){
			rooms =new JComboBox<String>();
			rooms.addItem("No Guess");
			rooms.addItem("Conservatory");
			rooms.addItem("Kitchen");
			rooms.addItem("Ballroom");
			rooms.addItem("Billiard Room");
			rooms.addItem("Library");
			rooms.addItem("Study");
			rooms.addItem("Dining Room");
			rooms.addItem("Lounge");
			rooms.addItem("Hall");
			rooms.addItem("Closet");
		}
	}
	public class comboBoxWeapons extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JComboBox<String> weapons;
		public comboBoxWeapons() {
			super();
			weapons = new JComboBox<String>();
			setLayout(new GridLayout(0,2));
			createGuessWeapon();
			add(weapons, BorderLayout.CENTER);
			setBorder (new TitledBorder(new EtchedBorder(), "Weapon Guess"));

		}
		public void createGuessWeapon(){
			weapons = new JComboBox<String>();
			weapons.addItem("No Guess");
			weapons.addItem("Candlestick");
			weapons.addItem("Knife");
			weapons.addItem("Shoelace");
			weapons.addItem("Gun");
			weapons.addItem("Hydrogen Bomb");
			weapons.addItem("Pile of dirt");
		}
	}


}




