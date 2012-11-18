package clue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Accusation extends JDialog{
	private JComboBox accuWeapon, accuPerson ;
	private JButton submit, cancel;
	private JLabel yourRoomLabel, cRoomLabel, personGuessLabel, weaponGuessLabel;
	private String person, weapon;
	private Board board;
	
	public Accusation(Board b, Player player){
		this.board = b;
		setSize(new Dimension(300,200));
		setTitle("Make a Guess");
		setLayout(new GridLayout(4,2));
		yourRoomLabel = new JLabel("Your room");
		personGuessLabel = new JLabel("Person");
		weaponGuessLabel = new JLabel("Weapon");
		cRoomLabel = new JLabel(board.getRooms().get(b.getCellAt(player.getPosition()).cellInitial()));
		accuPerson = new JComboBox();
		person = board.getPlayers().get(0).getName();
		for (Player p : b.getPlayers()){
			accuPerson.addItem(p.getName());
		}
		weapon = board.getPlayers().get(0).getName();
		for (Card c : board.getCards()){
			if( c.getType() == Card.CardType.WEAPON){
				accuWeapon.addItem(c.getName());
			}
		}
		
		submit = new JButton("Submit");
		cancel = new JButton("Cancel");
		submit.addActionListener(new ButtonListener(this));
		cancel.addActionListener(new ButtonListener(this));
		
		add(accuWeapon);
		add(accuPerson);
		add(submit);
		add(cancel);
		add(yourRoomLabel);
		add(cRoomLabel);
		add(personGuessLabel);
		add(weaponGuessLabel);
		
				
	}
	
	private class ComboListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == accuPerson) {
				person = (String) accuPerson.getSelectedItem();
			}else if(e.getSource() == accuWeapon) {
				weapon = (String) accuWeapon.getSelectedItem();
			}
		}
	}
	
	private class ButtonListener implements ActionListener {
		Accusation accusation;
		public ButtonListener(Accusation accu) {
			this.accusation = accu;
		}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit) {
				board.getPlayers().get(0).makeAccusation(person, cRoomLabel.getText(), weapon);
				accusation.setVisible(false);
			}else if(e.getSource() == cancel) {
				accusation.setVisible(false);
			}
			board.repaint();
		}
	}
}
