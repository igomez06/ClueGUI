package clue;

import clue.ControlDisplay.PersonCard;
import clue.ControlDisplay.RoomCard;
import clue.ControlDisplay.WeaponCard;

public class Cards {
	private PersonCard pCard;
	private RoomCard rCard;
	private WeaponCard wCard;
	
	public Cards() {
		pCard = new PersonCard();
		rCard = new RoomCard();
		wCard = new WeaponCard();
	}
}
