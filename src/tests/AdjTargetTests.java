/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package tests;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clue.Board;
import clue.BoardCell;

public class AdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt");
	}

	// Ensure that player does not move around within room
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(0, 22));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(board.calcIndex(6, 9));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(board.calcIndex(9, 20));
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(board.calcIndex(9, 1));
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(board.calcIndex(10, 18));
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(board.calcIndex(23, 15));
		Assert.assertEquals(0, testList.size());
	}

	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 13));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(4, 14)));
		// TEST DOORWAY LEFT
		testList = board.getAdjList(board.calcIndex(4, 6));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(4, 5)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(board.calcIndex(6, 20));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(7, 20)));
		//TEST DOORWAY UP
		testList = board.getAdjList(board.calcIndex(17, 12));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcIndex(16, 12)));
		
	}

	// Test a variety of walkway scenarios
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on left edge of board, just one walkway piece
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(12, 0));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 1)));
		Assert.assertEquals(1, testList.size());
		
		// Test on bottom edge of board, three walkway pieces
		testList = board.getAdjList(board.calcIndex(23, 17));
		Assert.assertTrue(testList.contains(board.calcIndex(23, 16)));
		Assert.assertTrue(testList.contains(board.calcIndex(23, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(22, 17)));
		Assert.assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(board.calcIndex(18, 1));
		Assert.assertTrue(testList.contains(board.calcIndex(18, 0)));
		Assert.assertTrue(testList.contains(board.calcIndex(18, 2)));
		Assert.assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(board.calcIndex(15,17));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 18)));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 16)));
		Assert.assertTrue(testList.contains(board.calcIndex(14, 17)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 17)));
		Assert.assertEquals(4, testList.size());
		
		//
		testList = board.getAdjList(board.calcIndex(2, 15));
		Assert.assertTrue(testList.contains(board.calcIndex(1, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(2, 14)));
		Assert.assertEquals(3, testList.size());
		
		// 
		testList = board.getAdjList(board.calcIndex(17, 7));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 7)));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 6)));
		Assert.assertEquals(2, testList.size());

	}

	// Test adjacency at entrance to rooms
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<Integer> testList = board.getAdjList(board.calcIndex(4, 14));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 14)));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 14)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 13)));
		Assert.assertEquals(4, testList.size());		//size was incorrect
		// Test beside a door direction DOWN
		testList = board.getAdjList(board.calcIndex(7, 20));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 19)));
		Assert.assertTrue(testList.contains(board.calcIndex(7, 21)));
		Assert.assertTrue(testList.contains(board.calcIndex(8, 20)));
		Assert.assertTrue(testList.contains(board.calcIndex(6, 20)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(board.calcIndex(4, 5));
		Assert.assertTrue(testList.contains(board.calcIndex(3, 5)));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 5)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 4)));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 6)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(board.calcIndex(16, 12));
		Assert.assertTrue(testList.contains(board.calcIndex(17, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(15, 12)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 11)));
		Assert.assertTrue(testList.contains(board.calcIndex(16, 13)));
		Assert.assertEquals(4, testList.size());
	}

	@Test
	public void testTargetsOneStep() {
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(23, 6), 1);
		HashSet<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(23, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(22, 6))));		
	
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(16, 17), 1);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 18))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 17))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 17))));	
		
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(23, 17), 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(23, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(23, 18))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(22, 17))));	
	}
	// 2 steps
	@Test
	public void testTargetsTwoSteps() {
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(15, 22), 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 21))));
		
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(7, 5), 2);
		targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 5))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(9, 5))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 6))));
	}
	
	// 3 steps
	@Test
	public void testTargetsThreeSteps() {
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(7, 19), 3);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 22))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 16))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 21))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 19))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 20))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 18))));
	}	
		
	// 4 steps
	@Test
	public void testTargetsFourSteps() {
		//lists and sets must be cleared and set to false in order for the tests to run properly //
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(6, 14), 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(12, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(2, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(8, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(3, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(4, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 15)))); //added...forgot it before :( it felt left out :(
	}
	
	// 6 steps
	@Test
	public void testTargetsSixSteps() {
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(23, 18), 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(8, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(19, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(18, 17))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(22, 17))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 18))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(23, 16))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 17))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 16))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(20, 18))));
	}	
	
	@Test
	public void testRoomEntrance()
	{
		// Room Entrance 1 away
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(6, 3), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(7, 3))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 2))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(6, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(5, 3))));
		
		board.clearListsAndSetToFalse();
		//Adjacent room, but wrong door direction
		board.calcTargets(board.calcIndex(21, 18), 1);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(21, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(22, 18))));
		
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(16, 14), 3);
		targets= board.getTargets();
		Assert.assertEquals(9, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 17))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(17, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 15))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 14))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 13)))); //this one felt left out too....
		
		
	}
	
	// Test getting out of a room
	@Test
	public void testRoomExit()
	{
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(15, 4), 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(15, 5))));
		
		board.clearListsAndSetToFalse();
		board.calcTargets(board.calcIndex(17, 9), 1);
		targets= board.getTargets();
		// Ensure doesn't exit through the wall
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcIndex(16, 9))));
	}

}