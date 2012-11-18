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

	public WalkwayCell(char cellType, int y, int x) {
		this.cellInitial = cellType;
		setY(y);
		setX(x);
	}

	public boolean isWalkway() {
		return true;
	}

	//method to overwrite draw method
	@Override
	public void draw(Graphics g, boolean target, Board board) {

		if(target == true){
			g.setColor(Color.magenta);
			g.fillRect(getX()*CELLWIDTH +5, getY()*CELLWIDTH +5, CELLWIDTH-10, CELLWIDTH-10);
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(getX()*CELLWIDTH, getY()*CELLWIDTH, CELLWIDTH, CELLWIDTH);
		}



	}
}
