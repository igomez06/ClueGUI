package clue;

import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;


public class NotesCheckBoxes extends JPanel{

	private People p;
	private Rooms r;
	private Weapons w;
	public NotesCheckBoxes() {
		super();

		p = new People();
		r = new Rooms();
		w = new Weapons();
		setLayout(new GridLayout(3,2));
		add(p);
		add(r);
		add(w);

	}

	public class People extends JPanel{
		private JCheckBox a,b,c,d,e,f;


		public People() {
			super();
			setLayout(new GridLayout(0,2));
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
			setBorder (new TitledBorder(new EtchedBorder(), "People"));

		}
	}

	public class Rooms extends JPanel{
		private JCheckBox a,b,c,d,e,f,g,h,i;

		public Rooms() {
			super();
			setLayout(new GridLayout(0,2));
			a = new JCheckBox("a");
			b = new JCheckBox("b");
			c = new JCheckBox("c");
			d = new JCheckBox("d");
			e = new JCheckBox("e");
			f = new JCheckBox("f");
			g = new JCheckBox("g");
			h = new JCheckBox("h");
			i = new JCheckBox("i");
			add(a);
			add(b);
			add(c);
			add(d);
			add(e);
			add(f);
			add(g);
			add(h);
			add(i);
			setBorder (new TitledBorder(new EtchedBorder(), "Rooms"));
		}
	}

	public class Weapons extends JPanel{
		private JCheckBox a,b,c,d,e,f;
		public Weapons(){
			super();
			setLayout(new GridLayout(3,2));
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
			setBorder (new TitledBorder(new EtchedBorder(), "People"));
		}

	}
}
