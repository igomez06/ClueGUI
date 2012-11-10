package clue;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ClueGame extends JFrame {
	private DetectiveNotes dNotes;
	public ClueGame(){
		Board board = new Board( "RaderLayout.txt" , "RaderLegend.txt" , "Players.txt", "Weapons.txt" );
		setLayout (new BorderLayout());
		add(board, BorderLayout.CENTER);
		setSize(new Dimension(board.getNumRows()*32,board.getNumCols()*32));
		
		setTitle("Da Bad Ass game");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
	}

	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createDetectiveNotesItem());
		menu.add(createFileExitItem());
		return menu;
	}

	private JMenuItem createDetectiveNotesItem() {
		JMenuItem item = new JMenuItem("Show Detective Notes");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				dNotes = new DetectiveNotes();
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

