import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*			"GUI made by Robert Colley, Kevin Zhan, and Amy Liu." + "/n" +
 "Puzzle Engine created by ___________ and ____________." + "/n" +
 "Click and drag a piece to move it into the puzzle." + "/n" +
 "Release to place that piece in the puzzle" + "/n" +
 "While the mouse is hovering over the piece, scrolling the mouse wheel will cause the piece to rotate."+ "/n" +
 "A piece that does not fit will remain attacked to the mouse until it goes outside the board or in a place where it fits.";
 */
//add PuzzlePanel, Toolbar
//Toolbar:  solve button, Instructions button, reset button

public class Frame extends JFrame {
	private PuzzlePanel p = new PuzzlePanel();
	private JPanel toolbar = new JPanel();
	private JButton reset = new JButton("Reset");
	private JButton instructions = new JButton("Instructions");
	private JButton solve = new JButton("Solve");

	public Frame() throws IOException {

		// this.getContentPane().setPreferredSize(new Dimension(500, 550));
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 550));

		this.setTitle("Puzzle");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		PuzzlePanel p = new PuzzlePanel();

		ActionListener forSolve = new SolveButtonListener();
		solve.addActionListener(forSolve);

		ActionListener forReset = new ResetButtonListener();
		reset.addActionListener(forReset);

		ActionListener forInstructions = new InstructionsButtonListener(this);
		instructions.addActionListener(forInstructions);

		toolbar.add(reset);
		toolbar.add(instructions);
		toolbar.add(solve);

		this.setLayout(new BorderLayout()); // best layout?
		this.add(p, BorderLayout.CENTER);

		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(p, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

	}

	/*
	 * This class dictates the actions of the "reset" button and constructs the
	 * listener
	 */
	class ResetButtonListener implements ActionListener {

		// displays blank puzzle after "reset" button is pressed:
		public void actionPerformed(ActionEvent event) {
			try {
				p.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * This class dictates the actions of the "solve" button and constructs the
	 * listener
	 */
	class SolveButtonListener implements ActionListener {

		// displays solved puzzle after "solve" button is pressed:
		public void actionPerformed(ActionEvent event) {
			p.solve();
		}
	}

	/*
	 * This class dictates the actions of the "instructions" button and
	 * constructs the listener
	 */
	class InstructionsButtonListener implements ActionListener {
		private JFrame f;

		// constructs an "instructions" button listener
		public InstructionsButtonListener(JFrame f) {
			this.f = f;
		}

		// displays instructions after "instructions" button is pressed:
		public void actionPerformed(ActionEvent event) {
			JOptionPane
					.showMessageDialog(
							f,
							"Click and drag a piece to move it into the puzzle.\n"
									+ "Release to place that piece in the puzzle.\n"
									+ "While the mouse is hovering over the piece, scrolling the mouse wheel\n"
									+ "will cause the piece to rotate.\n"
									+ "A piece that does not fit will remain attached to the mouse\n"
									+ "until it goes outside the board or in a place where it fits."
									+ "\n\n\nGUI made by Robert Colley, Kevin Zhan, and Amy Liu.\n"
									+ "Puzzle Engine created by ___________ and ____________.",
							"Instructions", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public static void main(String[] args) throws IOException {
		new Frame();
	}
}
