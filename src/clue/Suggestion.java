package clue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Suggestion extends JDialog{

	private JComboBox suggWeapon, suggPerson ;
	private JButton submit, cancel;
	private JLabel yourRoomLabel, cRoomLabel, personGuessLabel, weaponGuessLabel;
	private String person, weapon;
	private Board board;
	
	public Suggestion(Board b, Player player){
		this.board = b;
		setSize(new Dimension(300,200));
		setTitle("Make a Guess");
		setLayout(new GridLayout(4,2));
		yourRoomLabel = new JLabel("Your room");
		personGuessLabel = new JLabel("Person");
		weaponGuessLabel = new JLabel("Weapon");
		cRoomLabel = new JLabel(board.getRooms().get(b.getCellAt(player.getPosition()).cellInitial()));
		suggPerson = new JComboBox();
		person = board.getPlayers().get(0).getName();
		for (Player p : b.getPlayers()){
			suggPerson.addItem(p.getName());
		}
		weapon = board.getPlayers().get(0).getName();
		for (Card c : board.getCards()){
			if( c.getType() == Card.CardType.WEAPON){
				suggWeapon.addItem(c.getName());
			}
		}
		
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		submit.addActionListener(new ButtonListener(this));
		cancel.addActionListener(new ButtonListener(this));
		
		add(suggWeapon);
		add(suggPerson);
		add(submit);
		add(cancel);
		add(yourRoomLabel);
		add(cRoomLabel);
		add(personGuessLabel);
		add(weaponGuessLabel);
		
				
	}
	
	private class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == suggPerson) {
				person = (String) suggPerson.getSelectedItem();
			}else if(e.getSource() == suggWeapon) {
				weapon = (String) suggWeapon.getSelectedItem();
			}
		}
	}
	
	private class ButtonListener implements ActionListener {
		Suggestion s;
		public ButtonListener(Suggestion sug) {
			this.s = sug;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit) {
				board.getPlayers().get(0).makeSuggestion(person, cRoomLabel.getText(), weapon);
				s.setVisible(false);
			}else if(e.getSource() == cancel) {
				s.setVisible(false);
			}
			board.repaint();
		}
	}
}
