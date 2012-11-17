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
	@Override
	public void draw(Graphics g, boolean target, Board board) {

		if(target == true){
			g.setColor(Color.RED);
			g.fillRect(getColumn()*CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(getColumn()*CELLWIDTH, getRow()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		}



	}
}
