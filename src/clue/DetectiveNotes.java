package clue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clue.Card.CardType;
//notes
//guesses
public class DetectiveNotes extends JDialog {
	private ArrayList<Card> cards;	
	public DetectiveNotes() {
		setSize(750,500);
		setTitle("Detective Notes");
		cards = new ArrayList<Card>();
		setLayout(new GridLayout(3,2));
		add(checkBoxes("People", CardType.PERSON));
		add(comboBoxes("Room Guess", CardType.ROOM));
		add(checkBoxes("Weapons", CardType.WEAPON));
		add(comboBoxes("Person Guess", CardType.PERSON));
		add(checkBoxes("Rooms", CardType.ROOM));
		add(comboBoxes("Weapon Guess", CardType.WEAPON));
		

	}

	public JPanel checkBoxes (String name, CardType card) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		for (Card c : cards ) {
			if ( c.getType() == card){
				add(new JCheckBox(c.getName()));
			}
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		return panel;

	}

	public JPanel comboBoxes (String name, CardType card) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		for (Card c : cards ) {
			if ( c.getType() == card){
				JComboBox cb = new JComboBox();
				cb.addItem(c.getName());
			}
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		return panel;
	}
}

