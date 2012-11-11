package clue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
	protected String name;
	protected int startingLocation;
	protected ArrayList<Card> cards;
	protected Color color;
	protected int col;
	protected int row;
	static final int CELLWIDTH = 25;
	public Player(String name, Color color, int startingLocation, int col, int row) {
		super();
		this.name = name;
		this.startingLocation = startingLocation;
		this.color = color;
		this.col = col;
		this.row = row;
		cards = new ArrayList<Card>();
	}



	public Card disproveSuggestion(String person, String room, String weapon) {

		ArrayList<Card> solutions = new ArrayList<Card>();

		// If player has one or more of the cards, return one of the ones they have randomly
		for( Card card : cards ) {
			if( card.getName().equalsIgnoreCase(person) && card.getType() == Card.CardType.PERSON ) {
				solutions.add(card);
			} else if( card.getName().equalsIgnoreCase(room) && card.getType() == Card.CardType.ROOM ) {
				solutions.add(card);
			} else if( card.getName().equalsIgnoreCase(weapon) && card.getType() == Card.CardType.WEAPON ) {
				solutions.add(card);
			}
		}
		// If player doesn't have any of the given cards...
		if( solutions.isEmpty() ) {
			return null;
		} else {
			Collections.shuffle(solutions);
			return solutions.get(0);
		}
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

	public Color getColor() {
		return color;
	}
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.fillOval(getCol()*CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
	}



	public int getCol() {
		return col;
	}



	public int getRow() {
		return row;
	}


}
