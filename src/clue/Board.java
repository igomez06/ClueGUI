/**Kira Combs
 * Nicola Hetrick
 * 10/11/12
 * 
 * Refactored by Lars Walen and Craig Carlson
 */
package clue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

import clue.RoomCell.DoorDirection;
import exceptions.BadConfigFormatException;

public class Board {	
	private ArrayList<BoardCell> cells;		//contains the board layout
	private Map<Character, String> rooms;	//maps the 1-char initial to a Room object
	
	private int numRows;					//determined when you read in file
	private int numCols;					//determined when you read in file
	
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private LinkedList<Integer> path;					//list of paths
	private HashSet<BoardCell> targets;					//stores final targets
	private boolean[] visited;
	
	public Board(String configFile, String legendFile) {
		rooms = new HashMap<Character, String>();	//order does not matter for legend
		cells = new ArrayList<BoardCell>();
		path = new LinkedList<Integer>();			//path traveled during recursion leading to target
		targets = new HashSet<BoardCell>();			
		loadConfigFiles(configFile, legendFile);
		visited = new boolean[numRows * numCols];			//tracks which indexes have been seen
		for(int i = 0; i < numRows*numCols; i++) {
			visited[i] = false;
		}
		calcAdjacencies();
		
	}
	
	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	public Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumCols() {
		return numCols;
	}
	public void loadConfigFiles(String configFile, String legendFile) {
		//call helper functions to load different types of config files
		//generic try/catch statement that will utilize the BadConfigFormatException
		String initialstr;
		String name;
		char initial = '*';
		int index;
		int col = 0;
		int row = 0;
		// Loading Legend
		FileReader reader = null;
		Scanner in = null;
		
		try {
			reader = new FileReader(legendFile);
			//reader = new FileReader("RaderLegend.csv");
			in = new Scanner(reader);
		} catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
		}
		
		try {
			while (in.hasNextLine()){
				name = in.nextLine();
				index = name.indexOf(',');
				if (index >= 0) {
					initialstr = name.substring(0, index);
					initial = initialstr.charAt(0);
					name = name.substring(index + 2);
					if (name.indexOf(',') >= 0) {
						throw new BadConfigFormatException("Bad Config File: Too Many Items...");
					}
				}
				rooms.put(initial, name);
			}
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		// Loading Layout
		String cell;
		String str;
		try {
			reader = new FileReader(configFile);
			//reader = new FileReader("RaderLayout.csv");
			in = new Scanner(reader);
		} catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
		}
		try {
			str = in.nextLine();
			index = str.indexOf(',');
			// Reading Each Element of Line
			while (index >= 0) {
				cell = str.substring(0, index);
				str = str.substring(index + 1);
				saveCellInformation(cell, row, col);
				col++;
				index = str.indexOf(',');
			}
			saveCellInformation(str, row, col);
			col++;
			row++;
			numCols = col;
			while (in.hasNext()){
				str = in.nextLine();
				col = 0;
				index = str.indexOf(',');
				// Reading Each Element of Line
				while (index >= 0) {
					cell = str.substring(0, index);
					str = str.substring(index + 1);
					saveCellInformation(cell, row, col);
					col++;
					index = str.indexOf(',');
				}
				saveCellInformation(str, row, col);
				col++;
				if (col != numCols) {
					throw new BadConfigFormatException("Bad Config File: Number of Columns NOT CONSISTENT");
				}
				row++;
			}
			numRows = row;
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	
	void saveCellInformation(String cell, int row, int col) {
		char cellType = cell.charAt(0);
		char direction;
		//int arrayIndex = 0;
		
		try {
			if (!rooms.containsKey(cellType)) {
				throw new BadConfigFormatException("Bad Config File: INVALID LAYOUT, Nonexistent room used.");
			}
			direction = ' '; 
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		// WALKWAY CELL
		if (cell.equals("W")) {
			WalkwayCell w = new WalkwayCell(row, col);
			//cells.add(arrayIndex, w);
			cells.add(w);
			//arrayIndex++;
		// ROOM CELL
		} else {
			RoomCell newRoomCell;
			if (cell.length() > 1) {
				direction = cell.charAt(1);
				// DOOR DIRECTION
				if (direction == 'L') {
					newRoomCell = new RoomCell(cellType, DoorDirection.LEFT, row, col);
				} else if (direction == 'R') {
					newRoomCell = new RoomCell(cellType, DoorDirection.RIGHT, row, col);
				} else if (direction == 'U') {
					newRoomCell = new RoomCell(cellType, DoorDirection.UP, row, col);
				} else if (direction == 'D') {
					newRoomCell = new RoomCell(cellType, DoorDirection.DOWN, row, col);
				// NOT A DOOR
				} else {
					newRoomCell = new RoomCell(cellType, DoorDirection.NONE, row, col);
				}
			} else {
				newRoomCell = new RoomCell(cellType, DoorDirection.NONE, row, col);
			}
			//cells.add(arrayIndex, r);
			cells.add(newRoomCell);
			//arrayIndex++;
		}	
	}
	
	public int calcIndex(int row, int column) {
		return row*numCols + column;
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
		BoardCell cell = new RoomCell();
		cell = cells.get(calcIndex(row, column));
		if (cell.isWalkway()) {
			return null;
		} else {
			return (RoomCell) cell;
		}
	}	

	// Copied From CluePath
	public void calcAdjacencies(){
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		BoardCell cell;
		for(int i = 0; i < numRows*numCols; ++i) {
			LinkedList<Integer> adj = new LinkedList<Integer>();
			cell = cells.get(i); 
			if (cell.isRoom()) {
				RoomCell rCell = new RoomCell();
				rCell = (RoomCell)cell;
				if(rCell.isDoorway()) {
					//do not have to deal with going out of bounds of board
					//assuming doors actually lead somewhere
					if(rCell.getDoorDirection() == DoorDirection.RIGHT)
						adj.push(i + 1);
					if(rCell.getDoorDirection() == DoorDirection.LEFT)
						adj.push(i-1);
					if(rCell.getDoorDirection() == DoorDirection.UP)
						adj.push(i - numCols);
					if(rCell.getDoorDirection() == DoorDirection.DOWN)
						adj.push(i + numCols);
				} 
				//else push no data to the linked list because no adjacencies
				//need this to fix null pointer
			} else {
				//should be a walkway cell				
				//deals with bottom of grid 
				if((i + numCols) < numRows*numCols) {
					cell = cells.get(i + numCols); 
					if(cell.isRoom())
						//will add if DoorDirection is UP, bc checking downward adj
						accessCheck(cell, DoorDirection.UP, adj, i+numCols);
					else
						adj.push(i + numCols);
				}
				//deals with top of grid
				if((i - numCols) >= 0) {
					cell = cells.get(i - numCols); 
					if(cell.isRoom())
						//will add if DoorDirection is DOWN, bc checking upward adj
						accessCheck(cell, DoorDirection.DOWN, adj, i-numCols);
					else
						adj.push(i - numCols);
				}
				// deals with left side of grid
				if(!(i % numCols == 0)) {
					cell = cells.get(i -1); 
					if(cell.isRoom())
						//will add if DoorDirection is RIGHT, bc checking left adj
						accessCheck(cell, DoorDirection.RIGHT, adj, i-1);
					else
						adj.push(i-1);
				}
				//deals with right side of grid
				if(!((i+1) % numCols == 0)) {
					cell = cells.get(i + 1); 
					if(cell.isRoom())
						//will add if DoorDirection is LEFT, bc checking right adj
						accessCheck(cell, DoorDirection.LEFT, adj, i+1);
					else
						adj.push(i+1);
				}
			}
			adjMtx.put(i, adj);
		}
	}
	public void accessCheck(BoardCell cell, DoorDirection direction, LinkedList<Integer> adj, int i) {
		if (cell.isRoom()) {
			RoomCell rCell = new RoomCell();
			rCell = (RoomCell)cell;
			if(rCell.isDoorway()) {
				//do not have to deal with going out of bounds of board
				//assuming doors actually lead somewhere
				if(rCell.getDoorDirection() == direction)
					adj.push(i);
			} 
		}
	}

	public LinkedList<Integer> getAdjList(int value) {
		return adjMtx.get(value);
	}
	
	public void calcTargets(int startLocation, int numSteps){
		visited[startLocation] = true;
		LinkedList<Integer> possib = new LinkedList<Integer>();
		possib = getAdjList(startLocation);							//adjacent points
		for(int i = 0; i < possib.size(); ++i) {
			//will traverse path only if the index has not been visited
			if (visited[possib.get(i)] == false) {					
				visited[possib.get(i)] = true;		//sets the seen index to true
				path.addLast(possib.get(i));			//adds the index to the path
				if (path.size() == numSteps || getCells().get(possib.get(i)).isRoom()) {	//prevents duplicates		
					targets.add(getCells().get(possib.get(i)));	//adds to targets if the path size is at max numSteps
				} else {
					calcTargets(possib.get(i), numSteps);	//yay recursion!
				}
				path.removeLast();
				visited[possib.get(i)] = false;
			}
		}
	}
	public void clearListsAndSetToFalse() {
		for(int i = 0; i < numCols*numRows; i++) {
			visited[i] = false;
		}
		//clears path LinkedList and targetTreeSet
		path.clear();
		targets.clear();
	}
	
	public HashSet<BoardCell> getTargets(){
		return targets;
	}
	
	public BoardCell getCellAt(int value) {
		return getCells().get(value);
	}
	
	public void selectAnswer() {}
	public void deal() {}
	public void deal(ArrayList<String> cardList) {}
	public boolean checkAccusation(String person, String room, String weapon) {
		return false;
	}
	public void handleSuggestion(String person, String room, String weapon) {}
	
	public class Solution {
		public String person, weapon, room;

	}

	
	
	
}
