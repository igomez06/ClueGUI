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
	ArrayList<Card> seenCards = new ArrayList<Card>();
	ArrayList<Card> accusation;
	public ComputerPlayer(String name, Color color, int startingLocation, int y, int x, Board board) {
		super(name, color, startingLocation, y, x, board);
		compPlayerIndex = 0;
	}
	public BoardCell pickLocation(HashSet<BoardCell> targets) {
		ArrayList<BoardCell> possibleWalkways = new ArrayList<BoardCell>();
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
		for (BoardCell target : targets) {
			if(target.isRoom() == true){
				RoomCell room = (RoomCell) target;
				if(room.getRoomInitial() != lastRoomVisited){
					this.setX(target.getX());
					this.setY(target.getY());
					this.setposition(target.getLocation());
					lastRoomVisited = target.cellInitial;
					return target;
				}
			}
			else if (target.isWalkway()) {
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
			if(!seenCards.contains(c)){
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
		String person = " ";
		String room = " ";
		String weapon = " ";

		ArrayList<Card> suggestedCards = createSuggestion();

		//have to make suggestion from the room that you are in

		for (Card c : suggestedCards) {
			if(c.getType() == CardType.PERSON){
				person = c.getName();
			}else if(c.getType() == CardType.WEAPON) {
				weapon = c.getName();
			}
		}
		room = board.getRooms().get(board.getRoomCellAt(board.getXIndex(position), board.getYIndex(position)).getRoomInitial());


		Card sugRes = board.handleSuggestion(person, room, weapon, this);
		board.getControl().getGuess().setGuess(person, room, weapon);
		//if no one has any of the cards
		if(sugRes == null){
			board.getControl().getResult().setResult("Nothing");

			//REturning the cards that were found	
		}else{
			board.getControl().getResult().setResult(sugRes.getName());
		}



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
	public void doTurn(HashSet<BoardCell> bc) {
		BoardCell boardCell = pickLocation(bc);
		int x = boardCell.getX();
		int y = boardCell.getY();
		this.moveSpot(x,y);
		if(board.getCellAt(getPosition()).isRoom() == true) {
			//makeSuggestion( );
		}
		board.repaint();
	}

	public void moveComp(BoardCell newCell){
		position = board.calcIndex(newCell.getX(), newCell.getY());
	}

}
