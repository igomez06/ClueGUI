/**
 * Refactored by Israel Gomez and Craig Carlson
 */
package clue;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clue.RoomCell.DoorDirection;
import exceptions.BadConfigFormatException;

public class Board extends JPanel{
	
	private ArrayList<BoardCell> cells;		//contains the board layout
	private Map<Character, String> rooms;	//maps the 1-char initial to a Room object
	private ArrayList<Player> players;
	private ArrayList<Card> weaponsList;
	private ArrayList<Card> roomsList;
	private ArrayList<Card> personList;
	private ArrayList<Card> cards;
	private ArrayList<Card> cloneCards;
	private Solution answer;
	private Player human;
	private int whichPerson;
	private Player currentPlayer;
	private int numRows_Y;					//determined when you read in file
	private int numCols_X;					//determined when you read in file
	private int roll;
	private boolean hadTurn;
	private boolean pastAccusation;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private LinkedList<Integer> path;					//list of paths
	private HashSet<BoardCell> targets;					//stores final targets
	private boolean[] visited;
	private ControlDisplay controlDisplay;
	
	
	//Default constructor
	public Board(String configFile, String legendFile, String playerFile, String weaponFile) {
		rooms = new HashMap<Character, String>();	//order does not matter for legend
		cells = new ArrayList<BoardCell>();
		players = new ArrayList<Player>();
		cards = new ArrayList<Card>();
		cloneCards = cloneList(cards);
		answer = new Solution();
		human = new HumanPlayer();
		
		pastAccusation = false;
		path = new LinkedList<Integer>();			//path traveled during recursion leading to target
		targets = new HashSet<BoardCell>();			

		loadConfigFiles(configFile, legendFile, playerFile, weaponFile);
		cloneCards = cloneList(cards);
		deal();
		whichPerson = -1;
		hadTurn = false;

		visited = new boolean[numRows_Y * numCols_X];			//tracks which indexes have been seen
		for(int i = 0; i < numRows_Y*numCols_X; i++) {
			visited[i] = false;
		}

		calcAdjacencies();
		
		addMouseListener(new Click(this));

	}
	
	private class Click implements MouseListener {
		Board board;
		
		Click(Board b) {
			this.board = b;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			
			int clickXCol = e.getX() / BoardCell.CELLWIDTH;
			int clickYRow = e.getY() / BoardCell.CELLWIDTH;
			
			if (e.getX() > (BoardCell.CELLWIDTH*numCols_X) || e.getY() > (BoardCell.CELLWIDTH*numRows_Y)){
				return;
			}
			//System.out.println("x:" + e.getX()/BoardCell.CELLWIDTH + " Y:" + e.getY()/BoardCell.CELLWIDTH);
			
			
			//make a cell to where you want to move the person
			BoardCell bc = getCellAt(calcIndex(clickYRow, clickXCol));
			if( hadTurn == false ) {
				
				if(targets.contains(bc)) {
					
					players.get(whichPerson).moveSpot(bc.getY(), bc.getX());
					board.clearListsAndSetToFalse();
					hadTurn = true;
					if(board.getCellAt(players.get(0).getPosition()).isRoom() == true && whichPerson == 0 ){
						//System.out.println("test");
						Suggestion suggestion = new Suggestion(board);
						
						//System.out.println("test2");
						suggestion.setVisible(true);
					}
					pastAccusation = true;
				} else {
					JOptionPane error = new JOptionPane("Invalid click");
					error.showMessageDialog(board, "Clicked in invalid cell", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		
			board.repaint();
			
		}
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}
	public Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumY() {
		return numRows_Y;
	}
	public int getNumX() {
		return numCols_X;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		
		
		
		//Highlights where you can click to move
		
		if(getWhichPerson() == 0) {
			if(hadTurn == false) {
				calcTargets(players.get(whichPerson).getPosition(), roll );
			}
			
			for (BoardCell bc : cells) {
				if (targets.contains(bc)){
					bc.draw(g,true,this);
				}else {
					bc.draw(g, false, this);
				}
			}
		}else {
			for (BoardCell bc : cells) {
				bc.draw(g, false, this);
			}
		}
		for (Player p : players) {
			p.draw(g);
		}
		
	}
	public void loadConfigFiles(String configFile, String legendFile, String playerFile, String weaponFile) {
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
				if( !name.equalsIgnoreCase("closet") && !name.equalsIgnoreCase("walkway") ) {
					Card room = new Card(name, Card.CardType.ROOM);
					cards.add(room);
					//roomsList.add(room);
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
			numCols_X = col;
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
				if (col != numCols_X) {
					throw new BadConfigFormatException("Bad Config File: Number of Columns NOT CONSISTENT");
				}
				row++;
			}
			numRows_Y = row;
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			return;
		}

		//PlayerFile reader
		try {
			reader = new FileReader(playerFile);

			in = new Scanner(reader);
		} catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
		}

		try {
			String playerLine = in.nextLine();
			String[] line = playerLine.split("\t");
			
			//get the human player
			if(line.length > 4) throw new BadConfigFormatException("Player file has more than 4 items per line");
			Player p = new HumanPlayer(line[0], convertColor(line[1]), calcIndex(Integer.parseInt(line[3]), Integer.parseInt(line[2])), Integer.parseInt(line[3]), Integer.parseInt(line[2]), this);
			System.out.println(p.getY() + " row config test");
			System.out.println(p.getX() + " col config Test");
			p.setHumanPlayer(true);
			players.add(p);
			Card hPersonCard = new Card(line[0], Card.CardType.PERSON);
			cards.add(hPersonCard);
			//personList.add(hPersonCard);
			//Get the computer players after getting the human player
			while (in.hasNextLine()){
				playerLine = in.nextLine();
				line = playerLine.split("\t");
				if(line.length > 4) throw new BadConfigFormatException("Player file has more than 4 items per line");
				p = new ComputerPlayer(line[0], convertColor(line[1]), calcIndex(Integer.parseInt(line[3]), Integer.parseInt(line[2])), Integer.parseInt(line[3]), Integer.parseInt(line[2]), this);
				players.add(p);
				Card personCard = new Card(line[0], Card.CardType.PERSON);
				cards.add(personCard);
				//personList.add(personCard);
			}
		} catch(BadConfigFormatException e) {
			System.out.println(e.getMessage());
			return;
		}


		//Weapons file reader
		try {
			reader = new FileReader(weaponFile);
			in = new Scanner(reader);

		}catch (FileNotFoundException e1) {
			System.out.println("File Not Found");
		}

		while(in.hasNextLine()){
			Card weapon = new Card(in.nextLine(), Card.CardType.WEAPON);
			cards.add(weapon);
			//weaponsList.add(weapon);
		}


	}

	// Be sure to trim the color, we don't want spaces around the name
	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
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
			WalkwayCell w = new WalkwayCell(cellType, row, col);
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
					newRoomCell = new RoomCell(cellType, DoorDirection.LEFT, row, col, rooms);
				} else if (direction == 'R') {
					newRoomCell = new RoomCell(cellType, DoorDirection.RIGHT, row, col, rooms);
				} else if (direction == 'U') {
					newRoomCell = new RoomCell(cellType, DoorDirection.UP, row, col, rooms);
				} else if (direction == 'D') {
					newRoomCell = new RoomCell(cellType, DoorDirection.DOWN, row, col, rooms);


				} else if (direction == 'N') {
					newRoomCell = new RoomCell(cellType, DoorDirection.NAME, row, col, rooms);
					// NOT A DOOR
				} else {
					newRoomCell = new RoomCell(cellType, DoorDirection.NONE, row, col, rooms);
				}
			} else {
				newRoomCell = new RoomCell(cellType, DoorDirection.NONE, row, col, rooms);
			}
			//cells.add(arrayIndex, r);
			cells.add(newRoomCell);
			//arrayIndex++;
		}	
	}



	public int calcIndex(int rowY, int columnX) {
		return rowY*numCols_X + columnX;
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
		for(int i = 0; i < numRows_Y*numCols_X; ++i) {
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
						adj.push(i - numCols_X);
					if(rCell.getDoorDirection() == DoorDirection.DOWN)
						adj.push(i + numCols_X);
				} 
				//else push no data to the linked list because no adjacencies
				//need this to fix null pointer
			} else {
				//should be a walkway cell				
				//deals with bottom of grid 
				if((i + numCols_X) < numRows_Y*numCols_X) {
					cell = cells.get(i + numCols_X); 
					if(cell.isRoom())
						//will add if DoorDirection is UP, bc checking downward adj
						accessCheck(cell, DoorDirection.UP, adj, i+numCols_X);
					else
						adj.push(i + numCols_X);
				}
				//deals with top of grid
				if((i - numCols_X) >= 0) {
					cell = cells.get(i - numCols_X); 
					if(cell.isRoom())
						//will add if DoorDirection is DOWN, bc checking upward adj
						accessCheck(cell, DoorDirection.DOWN, adj, i-numCols_X);
					else
						adj.push(i - numCols_X);
				}
				// deals with left side of grid
				if(!(i % numCols_X == 0)) {
					cell = cells.get(i -1); 
					if(cell.isRoom())
						//will add if DoorDirection is RIGHT, bc checking left adj
						accessCheck(cell, DoorDirection.RIGHT, adj, i-1);
					else
						adj.push(i-1);
				}
				//deals with right side of grid
				if(!((i+1) % numCols_X == 0)) {
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

	public void calcTargets(int location, int numSteps){
		visited[location] = true;
		LinkedList<Integer> possib = new LinkedList<Integer>();
		possib = getAdjList(location);							//adjacent points
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
		for(int i = 0; i < numCols_X*numRows_Y; i++) {
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

	public void selectAnswer() {
		Collections.shuffle(cards);

		boolean havePerson = false;
		boolean haveRoom = false;
		boolean haveWeapon = false;

		for( int i = 0; i < cards.size(); i++ ) {
			if( cards.get(i).getType() == Card.CardType.PERSON && !havePerson ) {
				answer.person = cards.remove(i);
				havePerson = true;
			}
			if( cards.get(i).getType() == Card.CardType.ROOM && !haveRoom ) {
				answer.room = cards.remove(i);
				haveRoom = true;
			}
			if( cards.get(i).getType() == Card.CardType.WEAPON && !haveWeapon ) {
				answer.weapon = cards.remove(i);
				haveWeapon = true;
			}

		}
	}

	public void HumanCards() {

		boolean havePerson = false;
		boolean haveRoom = false;
		boolean haveWeapon = false;


		for( int i = 0; i < cards.size(); i++) {
			if(( cards.get(i).getType() == Card.CardType.PERSON) && (havePerson == false )) {
				//System.out.println(cards.get(i).getName());
				players.get(0).addCard(cards.remove(i)); 


				havePerson = true;
			}
			if( (cards.get(i).getType() == Card.CardType.ROOM) && (haveRoom == false )) {
				//System.out.println(cards.get(i).getName());
				players.get(0).addCard(cards.remove(i));


				haveRoom = true;
			}
			if( (cards.get(i).getType() == Card.CardType.WEAPON) && (haveWeapon == false)) {
				//System.out.println(cards.get(i).getName());
				players.get(0).addCard(cards.remove(i));


				haveWeapon = true;
			}
		}

	}

	public void deal() {


		//human = players.get(0);
		selectAnswer();
		HumanCards();		

		human = players.get(0);

		while( !cards.isEmpty() ) {

			for( int i = 1; i < players.size(); i++ ) {
				players.get(i).addCard(cards.remove(0));
			}
		}

	}

	//Clone the ArrayList of cards
	public static ArrayList<Card> cloneList(ArrayList<Card> original) {
		
		ArrayList<Card> clone = new ArrayList<Card>(original.size());
		for(Card c: original) {
			clone.add(c);
		}
		return clone;
	}

	public ArrayList<Card> getClone() {
		return cloneCards;
	}
	
	public Player getHuman() {
		return human;
	}
	public boolean checkAccusation(String person, String room, String weapon) {

		if( person.equalsIgnoreCase(answer.person.getName()) 
				&& room.equalsIgnoreCase(answer.room.getName())
				&& weapon.equalsIgnoreCase(answer.weapon.getName()) ) {
			return true;
		} else {
			return false;
		}
	}

	public Card handleSuggestion(String person, String room, String weapon, Player currentPlayer) {

		ArrayList<Card> solutions = new ArrayList<Card>();

		for( Player player : players ) {
			Card result = player.disproveSuggestion(person, room, weapon);
			if( result != null && (player != currentPlayer) ) {
				solutions.add(result);
			}
		}

		if( solutions.isEmpty() ) {
			return null;
		} else {
			Collections.shuffle(solutions);
			return solutions.get(0);
		}
		
	

	}

	public class Solution {
		public Card person, weapon, room;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public ArrayList<Card> getCards() {
		return cards;
	}


	// Pretty much only for testing
	public Card[] getAnswerAsArray() {
		Card[] answerArray = new Card[3];
		answerArray[0] = answer.person;
		answerArray[1] = answer.room;
		answerArray[2] = answer.weapon;
		return answerArray;
	}

	// Also for testing
	public void setAnswer(Card person, Card room, Card weapon) {
		answer.person = person;
		answer.room = room;
		answer.weapon = weapon;
	}
	public void NextTurn() {
		whichPerson++;
		currentPlayer = players.get(whichPerson);
		//if at the end of the player list go back to the start
		//whose turn it is
		//System.out.println(whichPerson);
		//Outputs the name of whose turn it is
		controlDisplay.getWhoseTurn().setWhoseTurn(currentPlayer.getName());
		
		//System.out.println(currentPlayer.getName());
		//Roll the die
		roll();
		clearListsAndSetToFalse();
		
		//calculate the targets
		calcTargets(players.get(whichPerson).getPosition(), roll);
		
		//clear the guess
		controlDisplay.getGuess().clear();
		
		hadTurn = false;
		repaint();
		
		if(whichPerson != 0) {
			//currentPlayer = players.get(whichPerson);
			System.out.println("accu 2");
			ComputerPlayer compPlayer = (ComputerPlayer) currentPlayer;
			if(controlDisplay.getResult().getResult().equals("No Clues this round")){
				
				compPlayer.makeAccusation(controlDisplay.getGuess().getPerson(), controlDisplay.getGuess().getRoom() , controlDisplay.getGuess().getWeapon());
				System.out.println("acc test");
			}else{
				
				controlDisplay.getResult().setResult("");
				
				compPlayer.doTurn(targets);
			}
			//System.out.println("test");
			if (whichPerson  == players.size() -1) {
				//System.out.println("sorry");
				pastAccusation = false;
				whichPerson = -1;
				//currentPlayer = players.get(whichPerson);
			}
		}
		
	
	}

	public boolean isHadTurn() {
		
		return hadTurn;
	}

	public void setHadTurn(boolean hadTurn) {
		this.hadTurn = hadTurn;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	public int getWhichPerson() {
		return whichPerson;
	}
	
	public void roll() {
		roll = getRandom();
		controlDisplay.getDie().setRoll(roll);
		
	}
	
	public void setControlDisplay(ControlDisplay cd) {
		this.controlDisplay = cd;
	}
	
	public int getRandom(){
		int number = 0;
		Random generator = new Random();
		number = generator.nextInt(6) + 1;
		return number;
	}
	public boolean isPastAccusation() {
		return pastAccusation;
	}
	
	public int getXIndex( int i) {
		//System.out.println("number of cols x: " + i % numCols_X);
		return(i % numCols_X);
	}
	
	public int getYIndex(int i) {
		//System.out.println("number of rows y: " + i/numRows_Y);
		return(i/numRows_Y);
	}
	
	public ControlDisplay getControl() {
		return controlDisplay;
	}
	public ArrayList<Card> getWeaponsList() {
		return weaponsList;
	}
	public ArrayList<Card> getRoomsList() {
		return roomsList;
	}
	public ArrayList<Card> getPersonList() {
		return personList;
	}
	
	public String RoomName(char roomCode) {
		return rooms.get(roomCode);
	}
	
	
	




}
