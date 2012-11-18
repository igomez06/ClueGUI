/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 */
package clue;

import java.awt.Graphics;


public abstract class BoardCell {
	private int y;
	private int x;
	private int location;
	char cellInitial;
	static final int CELLWIDTH = 25;
		
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
	public abstract void draw(Graphics g, boolean isTarget, Board board);
	//row and column will be used in draw
	
	public int getY() {
		return y;
	}
	public void setY(int row) {
		this.y = row;
	}
	public int getX() {
		return x;
	}
	public void setX(int column) {
		this.x = column;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}


	
	
}
