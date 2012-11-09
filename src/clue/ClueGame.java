package clue;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClueGame extends JFrame {
	private DetectiveNotes dNotes;
	public ClueGame(){
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(600,600));
		setTitle("Da Bad Ass game");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		repaint();
		}
	
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		return menu;
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

