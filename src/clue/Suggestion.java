package clue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


import clue.Card.CardType;

public class Suggestion extends JDialog{

	private Board board;
	private Player player;
	private String personGuess, roomGuess, weaponGuess;
	private int counter = 0;
	private int buttonCount = 0;
	private JComboBox pBox, wBox;
	
	
	public Suggestion(Board b){
		super();
		//System.out.println("Inside accu");
		setVisible(true);
		this.board = b;
		setSize(new Dimension(300,400));
		setTitle("Suggestion");
		setLayout(new GridLayout(5,1));
		pBox = new JComboBox();
		wBox = new JComboBox();
		add(comboBoxes("Person Guess", CardType.PERSON, pBox));
		add(comboBoxes("Weapon Guess", CardType.WEAPON, wBox));
		add(button("Submit"));
		add(button("Cancel"));
		roomGuess = board.getRooms().get(board.getCellAt(board.getCurrentPlayer().getPosition()).cellInitial());
		
	}
	
	public JPanel comboBoxes (String name, CardType cardType, JComboBox cb) {
		JPanel panel = new JPanel();
		JComboBox CBox = cb;
		panel.setLayout(new GridLayout(0,2));
		CBox.addItem("Open Choices");
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == cardType){
				CBox.addItem(c.getName());
				
			}
		}
		panel.add(CBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		CBox.addActionListener(new SugComboListener());
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
	
	private class SugComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == pBox){
				personGuess = pBox.getSelectedItem().toString();
			}else if(e.getSource() == wBox){
				weaponGuess = wBox.getSelectedItem().toString();
			}
		}
	}
	
	private class SubmitListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			System.out.println("p:" +personGuess + " r:" + roomGuess + " w:" + weaponGuess);
			board.getPlayers().get(0).makeSuggestion(personGuess, roomGuess, weaponGuess);
			setVisible(false);
			board.repaint();

		}
	}

	private class CancelListener implements ActionListener {
		public void actionPerformed(ActionEvent e){

			setVisible(false);

			board.repaint();
		}
		
	}
}
