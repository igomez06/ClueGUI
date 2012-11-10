package clue;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.border.*;

public class NotesCheckBoxes extends JDialog{

	public NotesCheckBoxes() {
		super();
	}

	public class People {
		private JCheckBox a,b,c,d,e,f;

		public People() {
			super();
			a = new JCheckBox("a");
			b = new JCheckBox("b");
			c = new JCheckBox("c");
			d = new JCheckBox("d");
			e = new JCheckBox("e");
			f = new JCheckBox("f");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			ButtonGroup group = new ButtonGroup();
			group.add(a);
			group.add(b);
			group.add(c);
			group.add(d);
			group.add(e);
			group.add(f);
			//setBorder(titledBorder(new Ed))

		}

	}
}
