/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

import java.awt.Graphics;


public abstract class BoardCell {
	char cellInitial;
	static final int CELLWIDTH = 32;
		
	public boolean isWalkway() {
		return false;
	}
	public boolean isRoom() {
		return false;
	}
	public boolean isDoorway() {
		return false;
	}
	
	public char cellInitial() {
		return cellInitial;
	}
	//draw method to be implemented
	public void draw(Graphics g, Object o) {
		
	}
	//row and column will be used in draw
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}

	private int row;
	private int column;
}
