/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

import java.awt.Color;
import java.awt.Graphics;

public class WalkwayCell extends BoardCell {

	public WalkwayCell() {
		// TODO Auto-generated constructor stub
	}
	
	public WalkwayCell(char cellType, int row, int col) {
		this.cellInitial = cellType;
		setRow(row);
		setColumn(col);
	}
	
	public boolean isWalkway() {
		return true;
	}
	
	//method to overwrite draw method
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(getColumn()*CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		


	}
}
