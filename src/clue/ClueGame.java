package clue;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {
	private DetectiveNotes dNotes;
	private ControlDisplay ControlDisplay;
	private CardsGUI cards;
	public ClueGame(){
		Board board = new Board( "RaderLayout.txt" , "RaderLegend.txt" , "Players.txt", "Weapons.txt" );
		setLayout (new BorderLayout());
		add(board, BorderLayout.CENTER);
		setSize(new Dimension(850, 725));
		setTitle("Clue");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
		cards = new CardsGUI();
		add(cards, BorderLayout.EAST);
		
		ControlDisplay = new ControlDisplay();
		add(ControlDisplay, BorderLayout.SOUTH);
		
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createDetectiveNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createDetectiveNotesItem() {
		JMenuItem item = new JMenuItem("Show Detective Notes");
		dNotes = new DetectiveNotes();
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dNotes.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}	

	public static void main(String[] args) {
		ClueGame clueGui = new ClueGame();
		clueGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clueGui.setVisible(true);


	}
}

