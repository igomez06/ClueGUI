package tests;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;

public class GameActionsTests {

	private Board testBoard;
	
	@BeforeClass
	public void setUp() throws Exception {
		testBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt", "Weapons.txt");
	}

	@Test
	public void testCheckAccusation() {
		
	}

	@Test
	public void testStartingLocation() {
		
	}
	
	@Test
	public void testDisproveSuggestion() {
		
	}
	
	@Test
	public void testMakeSuggestion() {
		
	}
}
