package clue;

import java.util.ArrayList;

public class Player {
	protected String name;
	protected int startingLocation;
	protected ArrayList<Card> cards;
	protected String color;

	public Player(String name, String color, int startingLocation) {
		super();
		this.name = name;
		this.startingLocation = startingLocation;
		this.color = color;
	}

	public Card disproveSuggestion(String person, String room, String weapon) {
		return null;
	}
	
	
}
