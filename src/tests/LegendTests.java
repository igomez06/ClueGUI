/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
// This file will test the reading of the legend file//
package tests;

import static org.junit.Assert.*;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clue.Board;

public class LegendTests {
	Board newBoard;
	//May change depending on legend files//
	public static final int NUM_ROOMS = 11;	//must include the walkway. essentially, num of legend items	
	public static final int NUM_ROWS = 24;
	public static final int NUM_COLS = 23;
	
	@Before
	public void setup() {
		newBoard = new Board("NKLayout.txt", "NKLegend.txt", "Players.txt", "Weapons.txt");
	}
	@Test
	public void TestNumRooms() {
		//tests number of rooms there should be NUM_ROOMS based off of our legend
		Assert.assertEquals(NUM_ROOMS, newBoard.getRooms().size());
	}
	@Test
	public void TestMapping() {
		Map<Character, String> rooms = newBoard.getRooms();
		assertEquals("Kitchen", rooms.get('K'));
		assertEquals("Ballroom", rooms.get('B'));
		assertEquals("Library", rooms.get('L'));
		assertEquals("Dining Room", rooms.get('D'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Study", rooms.get('S'));
		assertEquals("Pool", rooms.get('P'));
		assertEquals("Great Hall", rooms.get('H'));
		assertEquals("Lounge", rooms.get('G'));
		assertEquals("Walkway", rooms.get('W'));
	}
	
}
