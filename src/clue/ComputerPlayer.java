package clue;

import java.util.HashSet;

public class ComputerPlayer extends Player {
	private char lastRoomVisited;
	
	public ComputerPlayer(String name, String color, int startingLocation) {
		super(name, color, startingLocation);
		// TODO Auto-generated constructor stub
	}
	public BoardCell pickLocation(HashSet<BoardCell> targets) {
		//If the list of targets locations includes a room, select that location unless the player was just in that room.
		//If the list does not include a room, or the room was just
		//visited, randomly choose from all locations (gives some chance that the player will re-enter the room, but it's not automatic).
		
		//call calctargets
		return null;
	}
	public void createSuggestion() {
		
	}
	public void updateSeen(Card seen) {
		
	}
	public char getLastRoomVisited() {
		return lastRoomVisited;
	}
}
