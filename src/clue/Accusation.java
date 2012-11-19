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
	private String guessP, guessR, guessW;
	private int buttonCount = 0;
	private JComboBox pBox, rBox, wBox; 


	public Accusation(Board b, Player p){
		super();
		//System.out.println("Inside accu");
		setVisible(true);
		this.board = b;
		this.player = p;
		pBox = new JComboBox();
		rBox = new JComboBox();
		wBox = new JComboBox();

		setSize(new Dimension(300,400));
		setTitle("Accusation");
		setLayout(new GridLayout(5,1));
		add(comboBoxes("Person Guess", CardType.PERSON, pBox));
		add(comboBoxes("Room Guess", CardType.ROOM, rBox));
		add(comboBoxes("Weapon Guess", CardType.WEAPON, wBox));
		add(button("Submit"));
		add(button("Cancel"));




	}	

	public JPanel comboBoxes (String name, CardType card, JComboBox cb) {
		JPanel panel = new JPanel();
		JComboBox CBox = cb;
		panel.setLayout(new GridLayout(0,2));
		CBox.addItem("Open Choices");
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == card){
				CBox.addItem(c.getName());

			}
		}
		panel.add(CBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));

		CBox.addActionListener(new ComboListener());

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

	private class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == pBox){
				guessP = pBox.getSelectedItem().toString();
				//System.out.println(guessP);
			}else if(e.getSource() == rBox) {
				guessR = rBox.getSelectedItem().toString();
				//System.out.println(guessR);
			}else if(e.getSource() == wBox){
				guessW = wBox.getSelectedItem().toString();
				//System.out.println(guessW);
			}

		}
	}



	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			//System.out.println(personGuess.toString() +  roomGuess.toString() + weaponGuess.toString());
			board.getPlayers().get(0).makeAccusation(guessP, guessR, guessW);
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

