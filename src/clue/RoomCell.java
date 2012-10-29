/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

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
		UP, DOWN, LEFT, RIGHT, NONE;
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
		if (doorDirection != DoorDirection.NONE){
			return true;
		} else {
			return false;
		}
	}

	
	//method to overwrite draw method
}
