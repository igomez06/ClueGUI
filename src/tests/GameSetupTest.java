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
	private ArrayList<Player> players;
	@BeforeClass
	public void setUp() throws Exception {
		Board testBoard;
		testBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt");
		players = new ArrayList<Player>();
		players = testBoard.getPlayers();
	}

	@Test
	public void testLoadingPlayers() {
		HumanPlayer hp = (HumanPlayer) players.get(0);
		
		
		ComputerPlayer cp1 = (ComputerPlayer) players.get(1);
		
		ComputerPlayer cp2 = (ComputerPlayer) players.get(5);
		
	}

	@Test
	public void testLoadingCards() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDealingCards() {
		fail("Not yet implemented");
	}
}
