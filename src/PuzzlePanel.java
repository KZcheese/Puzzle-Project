import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

<<<<<<< HEAD
@SuppressWarnings("serial")
=======
>>>>>>> origin/master
public class PuzzlePanel extends JPanel implements MouseListener,
		MouseWheelListener {
	Puzzle pu;
	Piece[] pieces;
	PieceComponent[] pieceComponents;

	public void solve() {
		pu.solve();
	}

	public void reset() {
<<<<<<< HEAD
		pu.restart();

=======
		pu = new Puzzle(int row, int col, pieces);
		
>>>>>>> origin/master
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < pu.getRows(); i++) {
			for (int j = 0; j < pu.getCols(); j++) {

			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
