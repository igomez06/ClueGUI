package tests;

import java.util.ArrayList;
import java.util.HashSet;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;
import clue.BoardCell;
import clue.Card;
import clue.ComputerPlayer;
import clue.Player;
import clue.RoomCell;
import clue.WalkwayCell;

public class GameActionsTests {

	private static Board testBoard;
	private static ArrayList<Player> players;	

	@BeforeClass
	public static void setUp() throws Exception {
		testBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt", "Weapons.txt");
		players = new ArrayList<Player>();
		players = testBoard.getPlayers();
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
		//Room Preference tests
		HashSet<BoardCell> targets = new HashSet<BoardCell>();
		ComputerPlayer cp = new ComputerPlayer("Craig", "Blue", 20);
		RoomCell rc = new RoomCell();
		targets.add(rc);
		targets.add(new WalkwayCell());
		targets.add(new WalkwayCell());

		for (int i = 0; i < targets.size(); i++) {
			Assert.assertEquals(cp.pickLocation(targets), rc);
		}

		//Random choice test


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
