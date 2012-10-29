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
		//  Test checking an accusation, including one that is correct, one with wrong person, one with wrong weapon and one with wrong room.
		testBoard.setAnswer(new Card("Lars", Card.CardType.PERSON), new Card("Pool", Card.CardType.ROOM), new Card("Knife", Card.CardType.WEAPON));

		Card[] answer = testBoard.getAnswerAsArray();

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
		//  Test selecting a target, including a set of targets that include a room, a 
		// random selection from a set of targets that don't include a room, and a test that considers the last visited room.

		// Room Preference tests
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
			Assert.assertEquals(rc, cp.pickLocation(targets));
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
		// Test disproving Suggestions
		ComputerPlayer cp = new ComputerPlayer("Andrew", "Pink", 40);
		ArrayList<Card> cards = new ArrayList<Card>(); 
		cards.add(new Card("Panda", Card.CardType.PERSON));
		cards.add(new Card("Kitchen", Card.CardType.ROOM));
		cards.add(new Card("Candlestick", Card.CardType.WEAPON));
		cards.add(new Card("Lars", Card.CardType.PERSON));
		cards.add(new Card("Library", Card.CardType.ROOM));
		cards.add(new Card("Pile of dirt", Card.CardType.WEAPON));
		cp.setCards(cards);

		// Test one player, no correct matches
		Assert.assertEquals(null, cp.disproveSuggestion("Craig", "Pool", "Hydrogen bomb"));

		// Test one player, one correct match
		// a simple test that one player returns the only possible card (one room, one person, one weapon),
		Assert.assertEquals(new Card("Library", Card.CardType.ROOM), cp.disproveSuggestion("Craig", "Library", "Hydrogen bomb"));

		// Test one player, multiple possible matches
		// a test that one player randomly chooses between two possible cards
		int pandaCounter = 0;
		int candleCounter = 0;
		for( int i = 0; i < 100; i++ ) {

			Card returnedCard = cp.disproveSuggestion("Panda", "Pool", "Candlestick");

			if( new Card("Panda", Card.CardType.PERSON).equals( returnedCard ) ) {
				pandaCounter++;
			} else if( new Card("Candlestick", Card.CardType.WEAPON).equals( returnedCard ) ) {
				candleCounter++;
			}
		}
		Assert.assertTrue(pandaCounter > 1 && pandaCounter < 100);
		Assert.assertTrue(candleCounter > 1 && candleCounter < 100);

		// Test that all players are queried
		// tests involving the human player
		ComputerPlayer cp1 = new ComputerPlayer("Craig", "Blue", 1);
		ComputerPlayer cp2 = new ComputerPlayer("Lars", "Red", 2);
		ComputerPlayer cp3 = new ComputerPlayer("Panda", "Black", 3);
		ComputerPlayer cp4 = new ComputerPlayer("Roz", "Green", 4);
		HumanPlayer hp = new HumanPlayer("Patches", "Tickle me Pink", 5);

		cp1.addCard(new Card("a", Card.CardType.WEAPON));
		cp2.addCard(new Card("b", Card.CardType.WEAPON));
		cp3.addCard(new Card("c", Card.CardType.WEAPON));
		cp4.addCard(new Card("d", Card.CardType.WEAPON));
		hp.addCard(new Card("f", Card.CardType.WEAPON));

		players.clear();
		players.add(cp1);
		players.add(cp2);
		players.add(cp3);
		players.add(cp4);
		players.add(hp);

		// Test unprovable suggestion
		int count = 0;
		for (int i = 0; i < players.size(); i++) {
			if( null != players.get(i).disproveSuggestion("X", "Y", "e") ) {
				count++;
			}
		}
		Assert.assertEquals(0, count);

		// Test suggestion only provable by player
		Card result = new Card(" ", Card.CardType.PERSON);
		for (int i = 0; i < players.size(); i++) {
			result = players.get(i).disproveSuggestion("X", "Y", "f");
		}
		Assert.assertEquals(new Card("f", Card.CardType.WEAPON), result);

		// Test two players with valid cards
		// a test that if two players have a possible card, one person randomly returns a card
		cp1.addCard(new Card("Panda", Card.CardType.PERSON));
		cp2.addCard(new Card("Candlestick", Card.CardType.WEAPON));
		pandaCounter = 0;
		candleCounter = 0;
		for( int i = 0; i < 100; i++ ) {

			Card returnedCard = testBoard.handleSuggestion("Panda", "Pool", "Candlestick", cp3);
			if( new Card("Panda", Card.CardType.PERSON).equals( returnedCard ) ) {
				pandaCounter++;
			} else if( new Card("Candlestick", Card.CardType.WEAPON).equals( returnedCard ) ) {
				candleCounter++;
			}

		}
		System.out.println(pandaCounter);
		System.out.println(candleCounter);
		Assert.assertTrue(pandaCounter > 1 && pandaCounter < 100);
		Assert.assertTrue(candleCounter > 1 && candleCounter < 100);

		// Test suggesting player does not receive own card
		// a test that the player whose turn it is does not return a card
		count = 0;
		for (int i = 0; i < players.size(); i++) {
			if( null != testBoard.handleSuggestion("X", "Y", "f", hp) ) {
				count++;
			}
		}
		Assert.assertEquals(0, count);

	}

	@Test
	public void testMakeSuggestion() {

		// Test making a suggestion, including one correct suggestion...
		ComputerPlayer cp1 = new ComputerPlayer("Lars", "Red", 47);
		ComputerPlayer cp2 = new ComputerPlayer("Craig", "Blue", 48);

		cp1.addCard(new Card("a", Card.CardType.WEAPON));
		cp1.addCard(new Card("1", Card.CardType.ROOM));
		cp2.addCard(new Card("b", Card.CardType.WEAPON));
		cp2.addCard(new Card("2", Card.CardType.ROOM));

		ArrayList<Card> suggestion = new ArrayList<Card>();
		suggestion.add(new Card("Panda", Card.CardType.PERSON));
		suggestion.add(new Card("Kitchen", Card.CardType.ROOM));
		suggestion.add(new Card("Candlestick", Card.CardType.WEAPON));

		testBoard.setAnswer(suggestion.get(0), suggestion.get(1), suggestion.get(1));
		//Testing whether the it will return null for the card if the suggestion is the right answer
		Assert.assertNull(cp1.disproveSuggestion("Panda", "Kitchen", "Candlestick"));

		//Making a suggestion with some random possibilities
		int weaponCounter = 0;
		int roomCounter = 0;
		Card returnedCard = cp1.disproveSuggestion("Panda", "Pool", "Candlestick");
		for (int i = 0; i < 100; i++) {
			if (new Card("b", Card.CardType.WEAPON).equals(returnedCard)){
				weaponCounter++;
			} else if (new Card("b", Card.CardType.WEAPON).equals(returnedCard)) {
				roomCounter++;
			}
		}

		Assert.assertTrue(weaponCounter > 1 && weaponCounter < 100);
		Assert.assertTrue(roomCounter > 1 && weaponCounter < 100);


	}
}
