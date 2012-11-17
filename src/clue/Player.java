package clue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Player {
	private String name;
	private int startingLocation;
	private ArrayList<Card> cards;
	private Color color;
	private int col;
	private int row;
	static final int CELLWIDTH = 25;
	private boolean humanPlayer;
	protected int location;
	protected Board board;
	public Player() {
		cards = new ArrayList<Card>();
	}
	public Player(String name, Color color, int startingLocation, int col, int row, Board b) {
		super();
		this.name = name;
		this.board = b;
		this.startingLocation = startingLocation;
		this.location = startingLocation;
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
	
	public void makeAccusation(String person, String room, String weapon) {
		boolean result = board.checkAccusation(person, room, weapon);
		String accu;
		String correctAns;
		accu = this.name + " accused " + person + " in the " + room + "with the " + weapon;
		correctAns = "The result was: ";
		
		if( board.getWhichPerson() !=0 && result == true ){
			correctAns = correctAns + "The computer won";
		}else if (board.getWhichPerson() == 0 && result ==true) {
			correctAns = correctAns + "You win";
		}else{
			correctAns = correctAns + "Wrong";
		}
		
		JOptionPane.showMessageDialog(null, accu+correctAns);
		
		
		
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
		g.setColor(this.color);
		//g.fillOval(board.getRowIndex(location) * CELLWIDTH, board.getColumnIndex(location) * CELLWIDTH, CELLWIDTH, CELLWIDTH);
		
		g.fillOval(getRow() * CELLWIDTH, getCol() * CELLWIDTH, CELLWIDTH, CELLWIDTH);
		g.setColor(Color.BLACK);
		g.fillOval(getCol() * CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		
	}



	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}

	public boolean isHuman() {
		if (this.humanPlayer == true ) {
			return true;
		}
		return false;
	}
	
	public void moveSpot(int row, int col) {
		location = board.calcIndex(row, col);
		System.out.println(name + "to" + location);
	}
	public int getLocation() {
		return location;
	}
	
	public void makeSuggestion(String person, String room, String weapon) {}
}
