package clue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clue.Card.CardType;

public class Accusation extends JDialog{

	private Board board;
	private Player player;
	private String personGuess, roomGuess, weaponGuess;
	private int counter = 0;
	private int buttonCount = 0;
	public Accusation(Board b, Player p){
		super();
		//System.out.println("Inside accu");
		setVisible(true);
		this.board = b;
		this.player = p;
		setSize(new Dimension(300,400));
		setTitle("Accusation");
		setLayout(new GridLayout(5,1));
		add(comboBoxes("Person Guess", CardType.PERSON));
		add(comboBoxes("Room Guess", CardType.ROOM));
		add(comboBoxes("Weapon Guess", CardType.WEAPON));
		add(button("Submit"));
		add(button("Cancel"));


	}	

	public JPanel comboBoxes (String name, CardType card) {
		JPanel panel = new JPanel();
		JComboBox<String> CBox = new JComboBox<String>();
		panel.setLayout(new GridLayout(0,2));
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == card){
				CBox.addItem(c.getName());

			}
		}
		panel.add(CBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		if(counter == 0){
			CBox.addActionListener(new PersonListener());
		}else if(counter == 1){
			CBox.addActionListener(new RoomListener());
		}else if(counter == 2){
			CBox.addActionListener(new WeaponListener());
		}
		counter++;
		return panel;
	}

	public JPanel button(String name){
		JButton jb = new JButton(name);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(jb);
		if(buttonCount == 0){
			jb.addActionListener(new SubmitListener());
		}else{
			jb.addActionListener(new CancelListener());
		}
		buttonCount++;
		return panel;
	}

	private class PersonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			personGuess = e.getSource().toString();


		}
	}

	private class RoomListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			roomGuess = e.getSource().toString();

		}
	}
	private class WeaponListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			weaponGuess = e.getSource().toString();
		}
	}

	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			player.makeAccusation(personGuess, roomGuess, weaponGuess);
			board.setHadTurn(true);
			setVisible(false);

		}
	}

	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			setVisible(false);

		}
	}
}

