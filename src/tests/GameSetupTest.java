package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;
import clue.ComputerPlayer;
import clue.HumanPlayer;
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
		fail("failing");
	}
	
	@Test
	public void testDealingCards() {
		fail("failing");
	}
}
