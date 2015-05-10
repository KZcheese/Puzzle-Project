import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 * A Frame used to hold all functions used in a 3x3 jigsaw puzzle with a
 * specific set of pieces. Contains a PuzzlePanel object used to display and
 * interact with the puzzle's board and pieces. Contains Buttons that Reset and
 * Solve the puzzle, and a button that opens a separate window with
 * instructions.
 * 
 * @author Kevin Zhan
 * @author Robert Colley
 * @author Amy Liu
 */
@SuppressWarnings("serial")
public class Frame extends JFrame {
	private PuzzlePanel p = new PuzzlePanel();
	private JPanel toolbar = new JPanel();
	private JButton reset = new JButton("Reset");
	private JButton instructions = new JButton("Instructions");
	private JButton solve = new JButton("Solve");
	private Dimension buttonSize = new Dimension(100, 40);
	private Font buttonFont = new Font("Tahoma", Font.PLAIN, 20);

	public Frame() throws IOException {

		// this.getContentPane().setPreferredSize(new Dimension(500, 550));
		this.setPreferredSize(new Dimension(500, 500));
		this.setMinimumSize(new Dimension(500, 550));

		this.setTitle("Puzzle");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// PuzzlePanel p = new PuzzlePanel();

		ActionListener forSolve = new SolveButtonListener();
		solve.addActionListener(forSolve);

		ActionListener forReset = new ResetButtonListener();
		reset.addActionListener(forReset);

		ActionListener forInstructions = new InstructionsButtonListener(this);
		instructions.addActionListener(forInstructions);
		reset.setSize(buttonSize);
		reset.setFont(buttonFont);
		instructions.setSize(buttonSize);
		instructions.setFont(buttonFont);
		solve.setFont(buttonFont);
		solve.setSize(buttonSize);
		toolbar.add(reset);
		toolbar.add(instructions);
		toolbar.add(solve);
		toolbar.setBorder(new EmptyBorder(10, 0, 0, 0));
		this.setLayout(new BorderLayout()); // best layout?
		this.add(p, BorderLayout.CENTER);

		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(p, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);

	}

	/**
	 * Dictates the actions of the "reset" button and constructs the listener
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

	/**
	 * Dictates the actions of the "solve" button and constructs the listener
	 */
	class SolveButtonListener implements ActionListener {

		// displays solved puzzle after "solve" button is pressed:
		public void actionPerformed(ActionEvent event) {
			p.solve();
		}
	}

	/**
	 * Dictates the actions of the "instructions" button and constructs the
	 * listener
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
									+ "Puzzle Internals created by Kevin Ma and Kaitlyn Li.",
							"Instructions", JOptionPane.PLAIN_MESSAGE);
		}
	}

	/**
	 * Sets LookandFeel to system and starts the game by instantiating Frame.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Frame();
	}
}
