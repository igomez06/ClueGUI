/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
		// TODO Auto-generated constructor stub
	}
	
	public WalkwayCell(int row, int col) {
		setRow(row);
		setColumn(col);
	}
	
	public boolean isWalkway() {
		return true;
	}
	
	//method to overwrite draw method
}
