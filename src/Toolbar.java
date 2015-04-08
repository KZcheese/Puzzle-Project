import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Toolbar extends JPanel{
	private Instructions i= new Instructions();
	
	public Toolbar(){ 
		super(new BorderLayout());
		JToolBar toolBar = new JToolBar("Toolbar");
		addButtons(toolBar);
		setPreferredSize(new Dimension(450, 130));
		add(toolBar, BorderLayout.PAGE_START);
		toolBar.setFloatable(false);
	}

	protected void addButtons(JToolBar toolBar) {
		JButton reset = new JButton("Reset");	
		JButton instructions = new JButton("Instructions");
		JButton solve = new JButton("Solve");

		ActionListener forSolve = new SolveButtonListener(p);
		solve.addActionListener(forSolve);

		ActionListener forReset = new ResetButtonListener(p);
		reset.addActionListener(forReset);

		ActionListener forInstructions = new InstructionsButtonListener(i);
		instructions.addActionListener(forInstructions);

		toolBar.add(reset);
		toolBar.add(instructions);
		toolBar.add(solve);


	}

	
	/*
		This class dictates the actions of the "reset" button and constructs the listener
	 */
	class ResetButtonListener implements ActionListener{
		private PuzzlePanel pan; //idk if this stuff is right???

		//constructs a "reset" button listener
		public ResetButtonListener(PuzzlePanel pan1){
			pan = pan1;
		}

		//displays blank puzzle after "reset" button is pressed:
		public void actionPerformed(ActionEvent event){
			pan.restart(); 
		}
	}

	/*
		This class dictates the actions of the "solve" button and constructs the listener
	 */
	class SolveButtonListener implements ActionListener{
		private PuzzlePanel pan; //idk if this stuff is right???

		//constructs a "solve" button listener
		public SolveButtonListener(PuzzlePanel pan1){
			pan = pan1;
		}

		//displays solved puzzle after "solve" button is pressed:
		public void actionPerformed(ActionEvent event){
			pan.solve();
		}
	}

	/*
	This class dictates the actions of the "instructions" button and constructs the listener
	 */
	class InstructionsButtonListener implements ActionListener{
		private Instructions instructions;
		//constructs an "instructions" button listener
		public InstructionsButtonListener(Instructions instructions){
			this.instructions = instructions;
		}

		//displays instructions after "instructions" button is pressed:
		public void actionPerformed(ActionEvent event){
			instructions.display(); //I MADE THIS UP CHANGE LATERRRR
			
		}
	}

	public static void main(String[] args){
		PuzzlePanel p = new PuzzlePanel();
		Instructions i = new Instructions();
		Toolbar t = new Toolbar(p,i);



	}



}
