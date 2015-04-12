import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PuzzlePanel extends JPanel implements MouseListener,
		MouseWheelListener {
	private static final int H_OUT = Piece.HEARTS_OUT, H_IN = Piece.HEARTS_IN,
			C_OUT = Piece.CLUBS_OUT, C_IN = Piece.CLUBS_IN,
			D_IN = Piece.DIAMONDS_IN, D_OUT = Piece.DIAMONDS_OUT,
			S_IN = Piece.SPADES_IN, S_OUT = Piece.SPADES_OUT;
	private static final int PIECE_SIZE = 70;
	private Puzzle pu;
	private Piece[] pieces = { new Piece(C_OUT, H_OUT, D_IN, S_IN),
			new Piece(S_OUT, D_OUT, S_IN, H_IN),
			new Piece(H_OUT, S_OUT, S_IN, C_OUT),
			new Piece(H_OUT, D_OUT, S_IN, S_IN),
			new Piece(S_OUT, S_OUT, H_IN, S_IN),
			new Piece(H_OUT, D_OUT, D_IN, H_IN),
			new Piece(S_OUT, D_OUT, H_IN, D_IN),
			new Piece(C_OUT, H_OUT, S_IN, H_IN),
			new Piece(C_OUT, C_IN, D_IN, D_OUT) };

	private ArrayList<PieceComponent> unusedPieceComponents = new ArrayList<PieceComponent>();
	private ArrayList<PieceComponent> usedPieceComponenets = new ArrayList<PieceComponent>();

	public PuzzlePanel() throws IOException {
		for (int i = 0; i < pieces.length; i++)
			unusedPieceComponents.add(new PieceComponent(pieces[i], ImageIO
					.read(new File("img/piece_" + (i + 1) + ".png"))));
		pu = new Puzzle(3, 3, pieces);
		this.setVisible(true);
		repaint();
	}

	public void solve() {
		pu.solve();
	}

	public void reset() {
		pu.restart();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// System.out.println("puzzlepaint");
		for (int i = 0; i < pu.getCols(); i++) {
			for (int j = 0; j < pu.getRows(); j++) {
				// if (pu.getPiece() == null)
				System.out.println((i - pu.getCols() / 2 - 50) * PIECE_SIZE
						+ getWidth() / 2);
				g2d.drawRect((i - pu.getRows() / 2) * PIECE_SIZE + getWidth()
						/ 2 - 50, (j - pu.getCols() / 2) * PIECE_SIZE
						+ getHeight() / 2 - 50, PIECE_SIZE, PIECE_SIZE);
			}
		}
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			p.setX((int) ((i - unusedPieceComponents.size() / 2) * PIECE_SIZE
					* 0.6 + getWidth() / 2 - 50));
			p.setY((int) (PIECE_SIZE + getHeight() * 0.6));
			p.paintComponent(g2d);
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
