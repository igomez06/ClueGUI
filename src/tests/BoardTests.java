/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
// This file will test the reading of the board file//
package tests;

import static org.junit.Assert.*;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clue.Board;
import clue.BoardCell;
import clue.RoomCell;

public class BoardTests {
	Board newBoard;
	//May change depending on legend files//
	public static final int NUM_ROOMS = 11;		
	public static final int NUM_ROWS = 24;
	public static final int NUM_COLS = 23;
	
	@Before
	public void setup() {
		newBoard = new Board("ClueLayout.csv", "ClueLegend.csv");
	}
	@Test
	public void TestNumRowsColumns() {
		//Ensure proper number of rows and columns
		Assert.assertEquals(NUM_COLS, newBoard.getNumCols());
		Assert.assertEquals(NUM_ROWS, newBoard.getNumRows());
	}
	@Test
	// Changed 3rd, 5th, and 6th due to zero indexing failure. 
	public void calcIndex() {
		// Test each corner of the board
		Assert.assertEquals(0, newBoard.calcIndex(0, 0));
		Assert.assertEquals(NUM_COLS-1, newBoard.calcIndex(0, NUM_COLS-1));
		Assert.assertEquals(529, newBoard.calcIndex(NUM_ROWS-1, 0));				//(NUM_COLS*NUM_ROWS-1) + 0
		Assert.assertEquals(551, newBoard.calcIndex(NUM_ROWS-1, NUM_COLS-1));		//(NUM_COLS*NUM_ROWS-1) + NUM_COLS-1
		// Test a couple others
		Assert.assertEquals(191, newBoard.calcIndex(8, 7));							//row*NUM_COLS + (col) -> (8*NUM_COLS + 7)
		Assert.assertEquals(426, newBoard.calcIndex(18, 12));						//row*NUM_COLS + (col) -> (18*NUM_COLS + 12)
	}
	@Test
	//will test room, door, and walkway cells
	public void TestRoomType() {
		//test a walkway cell
		BoardCell cell = newBoard.getCells().get(newBoard.calcIndex(17, 7));
		Assert.assertTrue(cell.isWalkway());
		Assert.assertFalse(cell.isRoom());
		Assert.assertFalse(cell.isDoorway());
		//test a room cell
		cell = newBoard.getCells().get(newBoard.calcIndex(17, 8));
		Assert.assertFalse(cell.isWalkway());
		Assert.assertTrue(cell.isRoom());
		Assert.assertFalse(cell.isDoorway());
		//test a doorway cell
		cell = newBoard.getCells().get(newBoard.calcIndex(17, 9));
		Assert.assertFalse(cell.isWalkway());
		Assert.assertTrue(cell.isRoom());
		Assert.assertTrue(cell.isDoorway());
	}
	@Test
	public void TestDoorDirections() {
		//The board is zero indexed. Top Left cell is (0,0)
		//Tests right doorway in Kitchen
		RoomCell room = new RoomCell();
		room = newBoard.getRoomCellAt(4, 2);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		//Tests left doorway into Study
		room = newBoard.getRoomCellAt(11, 18);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.LEFT, room.getDoorDirection());
		//Tests Up doorway into Pool
		room = newBoard.getRoomCellAt(19, 2);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.UP, room.getDoorDirection());
		//Tests Right doorway into Pool, to test a room with 2 doors
		room = newBoard.getRoomCellAt(21, 4);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.RIGHT, room.getDoorDirection());
		//Tests Down doorway into Library
		room = newBoard.getRoomCellAt(6, 20);
		Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.DOWN, room.getDoorDirection());
		// Test that non-doorways return true also if in a room
		room = newBoard.getRoomCellAt(0, 0);
		//Assert.assertTrue(room.isDoorway());
		Assert.assertEquals(RoomCell.DoorDirection.NONE, room.getDoorDirection());
		// Test that rooms return true using calcIndex
		BoardCell cell = newBoard.getCells().get(newBoard.calcIndex(21, 21));
		//Assert.assertTrue(cell.isDoorway());	
		Assert.assertEquals(RoomCell.DoorDirection.NONE, room.getDoorDirection());
		// Test that walkways are not doors
		cell = newBoard.getCells().get(newBoard.calcIndex(16, 6));
		Assert.assertFalse(cell.isDoorway());	
		
	}
	@Test
	public void TestNumDoorways() {
		int numDoors = 0;
		int totalCells = newBoard.getNumCols() * newBoard.getNumRows();
		Assert.assertEquals(552, totalCells);	//check that totalCells equals size of board
		Assert.assertEquals(552, newBoard.getCells().size());
		BoardCell cell;
		for (int i = 0; i < newBoard.getCells().size(); ++i) {
			cell = newBoard.getCells().get(i);
			if (cell.isDoorway()) {
				numDoors++;
			}
		}
		Assert.assertEquals(12, numDoors);						//check that numDoors equals total door count
	}
	@Test
	public void TestRoomInitials() {
		//Test that room initial returns correct room
		Assert.assertEquals('B', newBoard.getRoomCellAt(4, 12).getRoomInitial());
		//checks that roomInitial was saved correctly as L on an LN space (Name Space)
		Assert.assertEquals('L', newBoard.getRoomCellAt(3, 19).getRoomInitial());
		//checks that roomInitial was saved correctly as R on a RR space (Right Doorway)
		Assert.assertEquals('R', newBoard.getRoomCellAt(15, 4).getRoomInitial());
		//checks that closet initial is saved correctly
		Assert.assertEquals('X', newBoard.getRoomCellAt(11, 11).getRoomInitial());
	}
}
