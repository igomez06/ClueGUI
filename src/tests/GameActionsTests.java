package tests;

import java.util.HashMap;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;
import clue.Card;

public class GameActionsTests {

	private static Board testBoard;
	
	@BeforeClass
	public static void setUp() throws Exception {
		testBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt", "Weapons.txt");
	}

	@Test
	public void testCheckAccusation() {
		testBoard.setAnswer(new Card("Lars", Card.CardType.PERSON), new Card("Pool", Card.CardType.ROOM), new Card("bomb", Card.CardType.WEAPON));
		
		Card[] answer = testBoard.getAnswer();
		
		Card[] accusation = new Card[3];
		
		// Correct accusation
		Assert.assertTrue(testBoard.checkAccusation("Lars", "Pool", "Bomb"));
		
		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Craig", "Kitchen", "Bomb"));
		
		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Andrew", "Pool", "Pile of dirt"));
		
		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Lars", "Library", "Bomb"));
	}

	@Test
	public void testStartingLocation() {
		Assert.fail("nope");
	}
	
	@Test
	public void testDisproveSuggestion() {
		Assert.fail("nope");	
	}
	
	@Test
	public void testMakeSuggestion() {
		Assert.fail("nope");
	}
}
