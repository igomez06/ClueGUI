package tests;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;
import clue.Card;
import clue.Player;

public class GameSetupTest {
	private static ArrayList<Player> players;
	static Board testBoard;
	@BeforeClass
	public static void setUp() throws Exception {
		testBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt", "Weapons.txt");
		players = new ArrayList<Player>();
		players = testBoard.getPlayers();
	}

	@Test
	public void testLoadingPlayers() {
		Player hp = players.get(0);
		Assert.assertEquals(hp.getName(), "Craig");
		Assert.assertEquals(hp.getColor(), "Blue");
		Assert.assertEquals(hp.getStartingLocation(), testBoard.calcIndex(1, 4));

		Player cp1 = players.get(1);
		Assert.assertEquals(cp1.getName(), "Lars");
		Assert.assertEquals(cp1.getColor(), "Red");
		Assert.assertEquals(cp1.getStartingLocation(), testBoard.calcIndex(1, 14));

		Player cp2 = players.get(5);
		Assert.assertEquals(cp2.getName(), "Panda");
		Assert.assertEquals(cp2.getColor(), "Black");
		Assert.assertEquals(cp2.getStartingLocation(), testBoard.calcIndex(11, 16));

	}

	@Test
	public void testLoadingCards() {

		ArrayList<Card> cards = testBoard.getCards();
		// check total number of cards
		Assert.assertEquals(cards.size(), 21);

		// check number of types of cards
		int numberOfWeaponCards = 0;
		int numberOfRoomCards = 0;
		int numberOfPersonCards = 0;
		for( Card card : cards ) {
			if( card.getType() == Card.CardType.WEAPON ) numberOfWeaponCards++;
			if( card.getType() == Card.CardType.ROOM ) numberOfRoomCards++;
			if( card.getType() == Card.CardType.PERSON ) numberOfPersonCards++;
		}
		Assert.assertEquals(numberOfWeaponCards, 6);
		Assert.assertEquals(numberOfPersonCards, 6);
		Assert.assertEquals(numberOfRoomCards, 9);

		// check if specific cards are in the deck
		String person = "Lars";
		String weapon = "pile of dirt";
		String room = "Pool";
		boolean personExists = false;
		boolean weaponExists = false;
		boolean roomExists = false;
		for( Card card : cards ) {
			if( card.getName().equalsIgnoreCase(person) ) personExists = true;
			if( card.getName().equalsIgnoreCase(weapon) ) weaponExists = true;
			if( card.getName().equalsIgnoreCase(room) ) roomExists = true;
		}
		Assert.assertTrue(personExists);
		Assert.assertTrue(weaponExists);
		Assert.assertTrue(roomExists);

	}

	@Test
	public void testDealingCards() {
		Player cp1 = players.get(1);
		Player cp2 = players.get(2);
		Player cp3 = players.get(3);
		ArrayList<Card> cards = testBoard.getCards();
		//check to make sure that all of the cards are gone
		Assert.assertEquals(cards.size(), 0);

		//Make sure that each player has the same number of cards
		Assert.assertEquals(cp1.getCards().size(), cp2.getCards().size());
		Assert.assertEquals(cp2.getCards().size(), cp3.getCards().size());
		Assert.assertEquals(cp1.getCards().size(), cp3.getCards().size());

		//make sure that one card is not given to multiple players
		for( int k = 0; k < cards.size(); k++ ) {
			String name = cards.get(k).getName();
			int counter = 0;
			for( int i = 0; i < players.size(); i++ ) {
				for ( int j = 0; j < players.get(i).getCards().size(); j++ ) {
					if (players.get(i).getCards().get(j).getName().equalsIgnoreCase(name)) counter++;
				}
			}
			Assert.assertEquals(counter, 1);
		}


	}
}

