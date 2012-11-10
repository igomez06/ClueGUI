package clue;

import java.awt.GridBagLayout;

import javax.swing.JDialog;


public class DetectiveNotes extends JDialog {
	private NotesCheckBoxes ncb;
	private ComboBox gcb;
	public DetectiveNotes() {
		setSize(600,400);
		setLayout(new GridBagLayout());
		setTitle("Detective Notes");
		//notes
		ncb = new NotesCheckBoxes();
		add(ncb);
		//guesses
		gcb = new ComboBox();
		add(gcb);
	}

}
