package clue;

import java.awt.GridLayout;
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
	private Board board;
	public DetectiveNotes(Board b) {
		this.board = b;
		setSize(750,500);
		setTitle("Detective Notes");
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
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == card){
				panel.add(new JCheckBox(c.getName()));
			}
		}
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		return panel;

	}

	public JPanel comboBoxes (String name, CardType card) {
		JPanel panel = new JPanel();
		JComboBox jBox = new JComboBox();
		panel.setLayout(new GridLayout(0,2));
		jBox.addItem("No Guess");
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == card){
				jBox.addItem(c.getName());
				
			}
		}
		panel.add(jBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		return panel;
	}
}

