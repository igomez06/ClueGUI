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
		cards = new ArrayList<Card>();
	}

	

	public Card disproveSuggestion(String person, String room, String weapon) {
		// If player has one or more of the cards, return one of the ones they have randomly
		
		
		// If player doesn't have any of the given cards...
		return null;
	}

	public String getName() {
		return name;
	}

	public int getStartingLocation() {
		return startingLocation;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public String getColor() {
		return color;
	}
	public void addCard(Card card) {
		cards.add(card);
	}

	
	
	
}
