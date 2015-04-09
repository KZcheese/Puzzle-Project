import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//add PuzzlePanel, Toolbar
//Toolbar:  solve button, Instructions button, reset button

public class Frame extends JFrame {
	private PuzzlePanel p;
	private JPanel toolbar;
	private Instructions i; //add = new Instructions()

	public Frame() { // take in anything?

		JFrame frame = new JFrame();

		frame.getContentPane().setPreferredSize(new Dimension(500, 500));
		frame.setMinimumSize(new Dimension(500, 550));
		frame.pack();
		frame.setTitle("Puzzle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PuzzlePanel p = new PuzzlePanel();

<<<<<<< HEAD
		JPanel t = new JPanel();

		JButton reset = new JButton("Reset");
=======
		
		JPanel toolbar = new JPanel();

		JButton reset = new JButton("Reset");	
>>>>>>> origin/master
		JButton instructions = new JButton("Instructions");
		JButton solve = new JButton("Solve");

		ActionListener forSolve = new SolveButtonListener(p);
		solve.addActionListener(forSolve);

		ActionListener forReset = new ResetButtonListener(p);
		reset.addActionListener(forReset);

		ActionListener forInstructions = new InstructionsButtonListener(i);
		instructions.addActionListener(forInstructions);

		toolbar.add(reset);
		toolbar.add(instructions);
		toolbar.add(solve);

<<<<<<< HEAD
		frame.setLayout(new BorderLayout()); // best layout?
		frame.add(t, BorderLayout.NORTH);
		frame.add(p, BorderLayout.CENTER);
=======


		frame.setLayout(new BorderLayout()); //best layout?
		frame.getContentPane().add(toolbar, BorderLayout.NORTH);
		frame.getContentPane().add(p, BorderLayout.CENTER);

>>>>>>> origin/master

		frame.setVisible(true);

	}

	/*
	 * This class dictates the actions of the "reset" button and constructs the
	 * listener
	 */
	class ResetButtonListener implements ActionListener {
		private PuzzlePanel pan; // idk if this stuff is right???

		// constructs a "reset" button listener
		public ResetButtonListener(PuzzlePanel pan1) {
			pan = pan1;
		}

		// displays blank puzzle after "reset" button is pressed:
		public void actionPerformed(ActionEvent event) {
			pan.reset();
		}
	}

	/*
	 * This class dictates the actions of the "solve" button and constructs the
	 * listener
	 */
	class SolveButtonListener implements ActionListener {
		private PuzzlePanel pan; // idk if this stuff is right???

		// constructs a "solve" button listener
		public SolveButtonListener(PuzzlePanel pan1) {
			pan = pan1;
		}

		// displays solved puzzle after "solve" button is pressed:
		public void actionPerformed(ActionEvent event) {
			pan.solve();
		}
	}

	/*
	 * This class dictates the actions of the "instructions" button and
	 * constructs the listener
	 */
	class InstructionsButtonListener implements ActionListener {
		private Instructions instructions;

		// constructs an "instructions" button listener
		public InstructionsButtonListener(Instructions instructions) {
			this.instructions = instructions;
		}

		// displays instructions after "instructions" button is pressed:
		public void actionPerformed(ActionEvent event) {
			instructions.display(); // I MADE THIS UP CHANGE LATERRRR

		}
	}

	public static void main(String[] args) {
		Frame d = new Frame();

	}
}