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
	private MakeAcc ma;
	private Die die;
	private Guess guess;
	private Result rs;
	private Board board;
	private int roll;
	private Player currentPlayer;
	public ControlDisplay(Board board) {
		super();
		setLayout(new GridLayout(2,3));
		this.board = board;
		whoseTurn = new WhoseTurn(board);
		whoseTurn.setWhoseTurn(board.getPlayers().get(0).getName());
		nb = new NextButton();
		ma = new MakeAcc();
		die = new Die();
		guess = new Guess();
		rs = new Result();

		add(whoseTurn);
		add(nb);
		add(ma);
		add(die);
		add(guess);
		add(rs);

	}

	public void Move(){
		Random generator = new Random();
		roll = generator.nextInt(6) + 1;
		repaint();


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
				if (board.isHadTurn() == true || board.getWhichPerson() != 0) {
					board.NextTurn();
				}else {
					JOptionPane message = new JOptionPane();
					message.showMessageDialog(board, "What is your accusation", "You either need to make an accusation or move.", JOptionPane.ERROR_MESSAGE);
					message.setVisible(true);
				}
			} 

		}

	}

	public class MakeAcc extends JPanel {
		JButton ma;
		public MakeAcc() {
			ma = new JButton("Make Accusation");
			add(ma);
			ma.addActionListener(new ButtonListener());
		}

		public class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				//Logic for Next turn
				System.out.println("make accu");
			} 
		}

	}

	public class Die extends JPanel {
		JTextField die;
		public Die() {
			die = new JTextField("Roll is " + roll);
			add(die);
			setBorder(new TitledBorder(new EtchedBorder(), "Die Roll"));
		}


	}

	public class Guess extends JPanel {
		JTextField guess;
		public Guess() {
			guess = new JTextField("Guess");
			add(guess);
			setBorder(new TitledBorder(new EtchedBorder(), "Guess"));


		}
	}

	public class Result extends JPanel {
		JTextField result;
		public Result () {
			result = new JTextField("Result");
			add(result);
			setBorder(new TitledBorder(new EtchedBorder(), "Result"));
		}
	}

	public WhoseTurn getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(WhoseTurn whoseTurn) {
		this.whoseTurn = whoseTurn;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}
