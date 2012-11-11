package clue;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlDisplay extends JPanel{

	private WhoseTurn wt;
	private NextButton nb;
	private MakeAcc ma;
	private Die die;
	private Guess guess;
	private Result rs;

	public ControlDisplay() {
		super();
		setLayout(new GridLayout(2,3));

		wt = new WhoseTurn();
		nb = new NextButton();
		ma = new MakeAcc();
		die = new Die();
		guess = new Guess();
		rs = new Result();

		add(wt);
		add(nb);
		add(ma);
		add(die);
		add(guess);
		add(rs);

	}


	public class WhoseTurn extends JPanel {
		JTextField wt;

		public WhoseTurn() {
			wt = new JTextField("someones turn");
			add(wt);
			setBorder(new TitledBorder(new EtchedBorder(), "Whose Turn?"));
		}
	}

	public class NextButton extends JPanel {
		JButton nextB;

		public NextButton() {
			nextB = new JButton("Next Player");
			add(nextB);
		}
	}

	public class MakeAcc extends JPanel {
		JButton ma;
		public MakeAcc() {
			ma = new JButton("Make Accusation");
			add(ma);
		}
	}

	public class Die extends JPanel {
		JTextField die;
		public Die() {
			die = new JTextField("Roll");
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
}
