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
import clue.HumanPlayer;
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
		testBoard.setAnswer(new Card("Lars", Card.CardType.PERSON), new Card("Pool", Card.CardType.ROOM), new Card("Knife", Card.CardType.WEAPON));

		Card[] answer = testBoard.getAnswer();

		Card[] accusation = new Card[3];

		// Correct accusation
		Assert.assertTrue(testBoard.checkAccusation("Lars", "Pool", "Knife"));

		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Craig", "Kitchen", "Knife"));

		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Andrew", "Pool", "Pile of dirt"));

		// Incorrect accusation
		Assert.assertFalse(testBoard.checkAccusation("Lars", "Library", "Knife"));
	}

	@Test
	public void testSelectingTarget() {
		//Room Preference tests
		HashSet<BoardCell> targets = new HashSet<BoardCell>();
		ComputerPlayer cp = new ComputerPlayer("Craig", "Blue", 20);
		RoomCell rc = new RoomCell();
		targets.add(rc);
		targets.add(new WalkwayCell());
		targets.add(new WalkwayCell());
		targets.add(new WalkwayCell());

		for (int i = 0; i < 10; i++) {
			//testing to make sure that they don't go into the last room they were in
			if (rc.getRoomInitial() == (cp.getLastRoomVisited())){
				Assert.assertTrue(cp.pickLocation(targets) != rc);
			}

			//Check to see if they will go into the room if it is NOT the last visited room
			Assert.assertEquals(cp.pickLocation(targets), rc);
		}



		//Random choice test
		HashSet<BoardCell> randomTargets = new HashSet<BoardCell>();
		WalkwayCell wc1 = new WalkwayCell();
		WalkwayCell wc2 = new WalkwayCell();
		WalkwayCell wc3 = new WalkwayCell();
		WalkwayCell wc4 = new WalkwayCell();
		randomTargets.add(wc1);
		randomTargets.add(wc2);
		randomTargets.add(wc3);
		randomTargets.add(wc4);

		int wc1Counter = 0;
		int wc2Counter = 0;
		int wc3Counter = 0;
		int wc4Counter = 0;
		for (int i = 0; i < 100; i++) {
			if(cp.pickLocation(randomTargets).equals(wc1)) {
				wc1Counter++;
			}else if(cp.pickLocation(randomTargets).equals(wc2)) {
				wc2Counter++;
			}else if(cp.pickLocation(randomTargets).equals(wc3)) {
				wc3Counter++;
			}else if(cp.pickLocation(randomTargets).equals(wc4)) {
				wc4Counter++;
			}
		}

		Assert.assertTrue(wc1Counter > 1 && wc1Counter < 100);
		Assert.assertTrue(wc2Counter > 1 && wc2Counter < 100);
		Assert.assertTrue(wc3Counter > 1 && wc3Counter < 100);
		Assert.assertTrue(wc4Counter > 1 && wc4Counter < 100);

	}

	@Test
	public void testDisproveSuggestion() {	
		ComputerPlayer cp = new ComputerPlayer("Andrew", "Pink", 40);
		ArrayList<Card> cards = new ArrayList<Card>(); 
		cards.add(new Card("Panda", Card.CardType.PERSON));
		cards.add(new Card("Kitchen", Card.CardType.ROOM));
		cards.add(new Card("Candlestick", Card.CardType.WEAPON));
		cp.setCards(cards);


		//		ArrayList<Card> suggestion = new ArrayList<Card>();
		//		suggestion.add(new Card("Panda", Card.CardType.PERSON));
		//		suggestion.add(new Card("Kitchen", Card.CardType.ROOM));
		//		suggestion.add(new Card("Candlestick", Card.CardType.WEAPON));

		//Checks to see if it will know if there are the suggested cards in the other players deck
		
			//Assert.assertFalse(testBoard.handleSuggestion("Panda", "Pool", "Gun"));
		


		//If there are multiple cards that match return a random card



	}

	@Test
	public void testMakeSuggestion() {
		Assert.fail("nope");
	}
}
