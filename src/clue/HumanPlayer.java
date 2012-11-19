package clue;

import java.awt.Color;

public class HumanPlayer extends Player{
	public HumanPlayer() {
		
	}
 
	public HumanPlayer(String name, Color color, int startingLocation, int y, int x, Board board) {
		super(name, color, startingLocation, y, x, board);
		// TODO Auto-generated constructor stub
	}
	
	public void makeSuggestion(String person, String room, String weapon) {
		Card handleSuggestion = board.handleSuggestion(person, room, weapon, this);
		board.getControl().getGuess().setGuess(person, room, weapon);
		
		if(handleSuggestion == null) {
			board.getControl().getResult().setResult("No Clue available");
		} else {
			board.getControl().getResult().setResult(handleSuggestion.getName());
		}
	}
	

}
