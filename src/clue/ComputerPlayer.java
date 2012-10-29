package clue;

import java.util.ArrayList;
import java.util.HashSet;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	ArrayList<Card> seenCards = new ArrayList<Card>();
	
	public ComputerPlayer(String name, String color, int startingLocation) {
		super(name, color, startingLocation);
	}
	public BoardCell pickLocation(HashSet<BoardCell> targets) {
		//If the list of targets locations includes a room, select that location unless the player was just in that room.
		//If the list does not include a room, or the room was just
		//visited, randomly choose from all locations (gives some chance that the player will re-enter the room, but it's not automatic).
		
		//call calctargets
		return null;
	}
	///make sure to check the parameters for this function
	public void createSuggestion(ArrayList<Card> suggestion, ArrayList<Card> theirCards) {
		
	}
	public void updateSeen(Card seen) {
		seenCards.add(seen);
	}
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}
}
