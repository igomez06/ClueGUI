package clue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlDisplay extends JPanel{


	private WhoseTurn whoseTurn;
	private NextButton nb;
	private MakeAccu ma;
	private Die die;
	private Guess guess;
	private Result result;
	private Board board;
	private Player currentPlayer;


	public ControlDisplay(Board b) {
		super();
		this.board = b;
		setLayout(new GridLayout(2,3));
		board.setControlDisplay(this);
		whoseTurn = new WhoseTurn(board);
		whoseTurn.setWhoseTurn(board.getPlayers().get(0).getName());
		nb = new NextButton();
		ma = new MakeAccu(board);
		die = new Die();
		guess = new Guess(board);
		result = new Result();

		add(whoseTurn);
		add(nb);
		add(ma);
		add(die);
		add(guess);
		add(result);

	}

	public class WhoseTurn extends JPanel {
		private JLabel whoseTurn;

		public WhoseTurn(Board b) {
			setBorder(new TitledBorder (new EtchedBorder(), "Whose Turn"));
			whoseTurn = new JLabel("");
			add(whoseTurn);

		}
		public void setWhoseTurn (String name) {

			setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));
			whoseTurn.setText(name);


		}
	}
	public class NextButton extends JPanel {
		JButton nextB;

		public NextButton() {
			nextB = new JButton("Next Player");
			add(nextB);
			nextB.addActionListener(new ButtonListener());
		}

		public class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//Logic for Next turn
				//System.out.println("testing");

				if (board.isHadTurn() == true || board.getWhichPerson() != 0) {
					board.NextTurn();
				}else {
					JOptionPane message = new JOptionPane();
					message.showMessageDialog(board, "You either need to make an accusation or move.", "Error", JOptionPane.ERROR_MESSAGE);
					message.setVisible(true);
				}
			} 
		}

	}



	public class MakeAccu extends JPanel {
		JButton ma;
		Board board;
		public MakeAccu(Board b) {
			this.board = b;
			ma = new JButton("Make Accusation");
			add(ma);
			ma.addActionListener(new ButtonListener(board));
		}

		public class ButtonListener implements ActionListener {
			Board board;
			ButtonListener(Board b) {this.board = b;}
			public void actionPerformed(ActionEvent e) {
				//System.out.println(board.getWhichPerson());
				//System.out.println("had Turn: " + board.isHadTurn());
				if( board.getWhichPerson() <= 0 && board.isHadTurn() == false){
					//System.out.println("inside first if in accu");
					if(board.isPastAccusation() == false){
						//System.out.println("\ninside second if");
						System.out.println("current Player: " + board.getPlayers().get(0).getName());
						Accusation accu = new Accusation(board);
					}
				}else {
					return;
				}
			} 
		}

	}

	public class Die extends JPanel {
		JLabel die;
		public Die() {
			die = new JLabel();
			die.setText("");
			add(die);
			setBorder(new TitledBorder(new EtchedBorder(), "Die Roll"));
		}

		public void setRoll(int r) {
			die.setText(String.valueOf(r));
		}


	}

	public class Guess extends JPanel {
		private JLabel guess;
		private String person,room,weapon;
		public Guess(Board board) {
			guess = new JLabel("Guess");
			add(guess);
			guess.setText("\t\t\t");
			setBorder(new TitledBorder(new EtchedBorder(), "Guess"));


		}
		public void setGuess(String p, String r, String w){
			this.person = p;
			this.room = r;
			this.weapon = w;

			guess.setText(person + " in the " + room + " with the " + weapon);

		}
		public String getPerson() {
			return person;
		}
		public String getRoom() {
			return room;
		}
		public String getWeapon() {
			return weapon;
		}

		public void clear() {
			guess.setText("\t\t\t");
		}
	}

	public class Result extends JPanel {
		JLabel result;
		public Result () {
			result = new JLabel("");
			add(result);
			setBorder(new TitledBorder(new EtchedBorder(), "Result"));
		}
		public String getResult() {
			return result.getText();
		}
		public void setResult(String r) {
			result.setText(r);
		}

	}

	public WhoseTurn getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(WhoseTurn whoseTurn) {
		this.whoseTurn = whoseTurn;
	}
	public Result getResult() {
		return result;
	}

	public Guess getGuess() {
		return guess;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public Die getDie() {
		return die;
	}




}
