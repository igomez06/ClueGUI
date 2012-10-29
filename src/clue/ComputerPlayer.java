package clue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

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
		for (BoardCell target : targets) {
			if (target.isDoorway() && (target.cellInitial != lastRoomVisited)) {
				lastRoomVisited = target.cellInitial;
				return target;
			}else if (target.cellInitial == lastRoomVisited) {
				targets.remove(target);
			}
		}
		int size = targets.size();
		int item = new Random().nextInt(size); 
		int i = 0;
		BoardCell selectedWalkway = new WalkwayCell();
		for(Object obj : targets)
		{
			if (i == item)
				selectedWalkway = (BoardCell) obj;
			i = i + 1;
		}
		return selectedWalkway;
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
