package clue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

import clue.Card.CardType;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	private int compPlayerIndex;
	private boolean hasPerson = false;
	private boolean hasRoom = false;
	private boolean hasWeapon = false;
	ArrayList<Card> seenCards = new ArrayList<Card>();
	ArrayList<Card> accusation;
	public ComputerPlayer(String name, Color color, int startingLocation, int y, int x, Board board) {
		super(name, color, startingLocation, y, x, board);
		compPlayerIndex = 0;
	}
	public BoardCell pickLocation(HashSet<BoardCell> targets) {

		//If the list of targets locations includes a room, select that location unless the player was just in that room.
		//If the list does not include a room, or the room was just
		//visited, randomly choose from all locations (gives some chance that the player will re-enter the room, but it's not automatic).
		if(accusation != null) {
			String person = " ", room=" ", weapon=" ";
			for(Card c : accusation) {
				if(c.getType() == CardType.PERSON)
					person = c.getName();
				else if(c.getType() == CardType.ROOM)
					room = c.getName();
				else if (c.getType() == CardType.WEAPON)
					weapon = c.getName();
			}
			makeAccusation(person, room, weapon);
		}

		ArrayList<BoardCell> possibleWalkways = new ArrayList<BoardCell>();

		for (BoardCell target : targets) {
			if(target.isDoorway() == true && target.cellInitial() != lastRoomVisited){
				//RoomCell room = (RoomCell) target;
				//this.setposition(target.getLocation());
				lastRoomVisited = target.cellInitial;
				return target;

			}else if (target.isWalkway()) {
				possibleWalkways.add(target);
			}
		}
		Collections.shuffle(possibleWalkways);
		return possibleWalkways.get(0);
	}

	///make sure to check the parameters for this function
	public ArrayList<Card> createSuggestion() {
		ArrayList<Card> suggestion = board.getClone();
		ArrayList<Card> theirCards = new ArrayList<Card>();
		boolean hasPerson = false;
		boolean hasRoom = false;
		boolean hasWeapon = false;
		for (Card c : suggestion){
			Collections.shuffle(suggestion);
			if(!(seenCards.contains(c))){
				if(hasPerson == false && c.getType() == CardType.PERSON) {
					theirCards.add(c);
					hasPerson = true;
				}else if (hasRoom == false && c.getType() == CardType.ROOM) {
					theirCards.add(c);
					hasRoom = true;
				}else if (hasWeapon == false && c.getType() == CardType.WEAPON){
					theirCards.add(c);
					hasWeapon = true;
				}
			}
		}

		return theirCards;

	}
	public void makeSuggestion() {
		//System.out.println("lalalala");
		String person = " ";
		String room = " ";
		String weapon = " ";

		ArrayList<Card> suggestedCards = createSuggestion();
		Player currentCompPlayer = board.getPlayers().get(compPlayerIndex);
		//to make suggestion from the room that you are in
		for (Card c : suggestedCards) {
			if(c.getType() == CardType.PERSON){
				person = c.getName();
			}else if(c.getType() == CardType.WEAPON) {
				weapon = c.getName();
			}
		}
		room = board.getRooms().get(board.getRoomCellAt(x, y).getRoomInitial());
		//Creates and handles suggestion
		Card sugRes = board.handleSuggestion(person, room, weapon, this);
		board.getControl().getGuess().setGuess(person, room, weapon);
		//if no one has any of the cards
		//moves players
		for (Player p : board.getPlayers()) {
			if (p.getName().equals(person)) {
				p.setX((x));
				p.setY((y));
			}

			if(sugRes == null){
				System.out.println("sugres test");
				board.getControl().getResult().setResult("Nothing");

				//REturning the cards that were found	
			}else{
				board.getControl().getResult().setResult(sugRes.getName());
			}
		}

		board.repaint();

	}



	public void updateSeen(Card seen) {
		seenCards.add(seen);
	}
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}
	public void setLastRoomVisited(char lastRoomVisited) {
		this.lastRoomVisited = lastRoomVisited;
	}
	public void doTurn(HashSet<BoardCell> targetCells) {

		//make an rrayList
		//ArrayList<BoardCell> cells = board.getCells();
		BoardCell newSpot = pickLocation(targetCells);

		//System.out.println(newSpot.getLocation());
		//position = cells.indexOf(boardCell);
		//System.out.println(name + " from x:" + this.x + " y:" + this.y);
		this.y = newSpot.getX();
		this.x = newSpot.getY();

		//System.out.println(name + " to x:" + this.x + " y:" + this.y);
		if(board.getCellAt(board.calcIndex(x, y)).isRoom() == true) {
			makeSuggestion();


		}
	}

	public void moveComp(BoardCell newCell){
		position = board.calcIndex(newCell.getX(), newCell.getY());
	}

}
