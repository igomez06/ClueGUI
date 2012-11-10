package clue;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ComboBox extends JPanel{

	private createGuessPeople people;
	private createGuessRooms rooms;
	private createGuessWeapons weapons;
	public ComboBox() {
		people = new  createGuessPeople();
		rooms = new createGuessRooms();
		weapons = new createGuessWeapons();
		setLayout(new GridLayout(3,2));
		add(people);
		add(rooms);
		add(weapons);
	}
	public class createGuessPeople extends JPanel {
		private createGuessPeople() {
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
		private createGuessRooms() {
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
		private createGuessWeapons() {
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
