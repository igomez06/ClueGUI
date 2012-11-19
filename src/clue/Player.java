package clue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class Player {
	protected String name;
	private int startingLocation;
	private ArrayList<Card> cards;
	private Color color;
	protected int y;
	protected int x;
	public static int getCellwidth() {
		return CELLWIDTH;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}

	static final int CELLWIDTH = 25;
	private boolean humanPlayer;
	protected int position;
	protected Board board;
	public Player() {
		cards = new ArrayList<Card>();
	}
	public Player(String name, Color color, int startingLocation, int y, int x, Board b) {
		super();
		this.name = name;
		this.board = b;
		this.startingLocation = startingLocation;
		this.position = startingLocation;
		this.color = color;
		this.y = x;
		this.x = y;
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
		System.out.println("made accusation");
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
		g.fillOval(y * CELLWIDTH, x * CELLWIDTH, CELLWIDTH, CELLWIDTH);

	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public boolean isHuman() {
		if (this.humanPlayer == true ) {
			return true;
		}
		return false;
	}

	public void moveSpot(int newX, int newY) {
		//System.out.println(name + " from X:" + board.getXIndex(position) + " Y:" + board.getYIndex(position));

		this.x = newX;
		this.y = newY;
		position = board.calcIndex(newX, newY);
		//System.out.println(name + " to X:" + board.getXIndex(position) + " Y:" + board.getYIndex(position));

	}
	
	
	public int getPosition() {
		return position;
	}

	public void setposition(int p) {
		this.position = p;
	}

	public void makeSuggestion(String person, String room, String weapon) {}
	public boolean isHumanPlayer() {
		return humanPlayer;
	}
	public void setHumanPlayer(boolean humanPlayer) {
		this.humanPlayer = humanPlayer;
	}
	
	public int getXIndex( int i) {
		//System.out.println("number of cols x: " + i % numCols_X);
		return(i % board.getNumX());
	}
	
	public int getYIndex(int i) {
		//System.out.println("number of rows y: " + i/numRows_Y);
		return(i/board.getNumY());
	}
}
