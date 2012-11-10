/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

import java.awt.Color;
import java.awt.Graphics;

public class RoomCell extends BoardCell {
	DoorDirection doorDirection; 

	public RoomCell() {

	}

	public RoomCell(char roomInitial, DoorDirection doorDirection, int row, int col) {
		this.cellInitial = roomInitial;
		setDoorDirection(doorDirection);
		setRow(row);
		setColumn(col);		
	}
	public enum DoorDirection {
		UP, DOWN, LEFT, RIGHT, NONE, NAME;
	}
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	public void setDoorDirection(DoorDirection d) {
		doorDirection = d;
	}
	public char getRoomInitial() {
		return cellInitial;
	}
	public boolean isRoom() {
		return true;
	}
	public boolean isDoorway() {
		if (doorDirection != DoorDirection.NONE || doorDirection == DoorDirection.NAME){
			return true;
		} else {
			return false;
		}
	}
	public boolean isName() {
		if(doorDirection == DoorDirection.NAME){
			return true;
		}
		else {
			return false;
		}
	}
	

	//method to overwrite draw method
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(getColumn()*CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		int adjCol = getColumn()*CELLWIDTH;
		int adjRow = getRow()*CELLWIDTH;
		if(isDoorway() == true) {
			g.setColor(Color.GREEN);
			System.out.println("test1");
			if (doorDirection == DoorDirection.LEFT) {
				g.setColor(Color.RED);
				g.fillRect(adjCol, adjRow, 3, CELLWIDTH);
				} 
			if (doorDirection == DoorDirection.RIGHT) {
				g.setColor(Color.RED);
				g.fillRect((adjCol+CELLWIDTH-3), adjRow, 3, CELLWIDTH);
				} 
			if (doorDirection == DoorDirection.UP) {
				g.setColor(Color.RED);
				g.fillRect(adjCol, adjRow, CELLWIDTH, 3);
				} 
			if (doorDirection == DoorDirection.DOWN) {
				g.setColor(Color.RED);
				g.fillRect(adjCol, adjRow+CELLWIDTH-3, CELLWIDTH, 3);
				} 
		}
		if(isName() == true){
			
		}


	}
}
