import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;


//add PuzzlePanel, Toolbar
//Toolbar:  solve button, Instructions button, reset button


public class Frame {

	public class Frame extends JFrame{
//		private PuzzlePanel p = new PuzzlePanel();
//		private Toolbar t = new Toolbar();

		public Frame(PuzzlePanel p){ //take in anything?

			JFrame frame = new JFrame();


			frame.setTitle("Puzzle");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//			PuzzlePanel p = new PuzzlePanel(); 
//
			Toolbar t = new Toolbar();
			t.setPreferredSize(new Dimension(325,100)); //size??

//			frame.pack();

			frame.setLayout(new BorderLayout()); //best layout?
			frame.add(t, BorderLayout.NORTH);
			frame.add(p, BorderLayout.CENTER);




			frame.setVisible(true);


		}



	}

	public static void main(String[] args){
		PuzzlePanel p = new PuzzlePanel();
		Toolbar t = new Toolbar();
		Frame d = new Frame(p);



	}

}
