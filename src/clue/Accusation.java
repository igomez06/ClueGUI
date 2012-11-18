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
	private Card personGuess, roomGuess, weaponGuess;
	private int counter = 0;
	private int buttonCount = 0;
	public Accusation(Board b){
		super();
		//System.out.println("Inside accu");
		this.board = b;
		setSize(new Dimension(300,400));
		setTitle("Accusation");
		setLayout(new GridLayout(5,1));
		add(comboBoxes("Room Guess", CardType.PERSON));
		add(comboBoxes("Room Guess", CardType.ROOM));
		add(comboBoxes("Room Guess", CardType.WEAPON));
		add(button("Submit"));
		add(button("Cancel"));
		
			
	}	
	
	public JPanel comboBoxes (String name, CardType card) {
		JPanel panel = new JPanel();
		JComboBox CBox = new JComboBox();
		panel.setLayout(new GridLayout(0,2));
		for (Card c : this.board.getClone() ) {
			if ( c.getType() == card){
				CBox.addItem(c.getName());
				
			}
		}
		panel.add(CBox);
		panel.setBorder(new TitledBorder(new EtchedBorder(), name));
		CBox.addActionListener(new ComboListener());
		counter++;
		return panel;
	}
	
	public JPanel button(String name){
		JButton jb = new JButton(name);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0,2));
		panel.add(jb);
		jb.addActionListener(new ButtonListener());
		buttonCount++;
		return panel;
	}
	
	private class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(counter == 0){
				personGuess = (Card) e.getSource();
			}else if(counter == 1){
				roomGuess = (Card) e.getSource();
			}else{
				weaponGuess = (Card) e.getSource();
			}
		}
	}
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if(buttonCount == 0){
				
			}
		}
	}
}
