import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PuzzlePanel extends JPanel implements MouseListener,
		MouseMotionListener, MouseWheelListener {
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
	private ArrayList<PieceComponent> usedPieceComponents = new ArrayList<PieceComponent>();

	public PuzzlePanel() throws IOException {
		for (int i = 0; i < pieces.length; i++) {
			PieceComponent p = new PieceComponent(pieces[i],
					ImageIO.read(new File("img/piece_" + (i + 1) + ".png")));
			unusedPieceComponents.add(p);
		}
		pu = new Puzzle(3, 3, pieces);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
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
				// System.out.println((i - pu.getCols() / 2 - 50) * PIECE_SIZE
				// + getWidth() / 2);
				g2d.drawRect((i - pu.getRows() / 2) * PIECE_SIZE + getWidth()
						/ 2 - 50, (j - pu.getCols() / 2) * PIECE_SIZE
						+ getHeight() / 2 - 50, PIECE_SIZE, PIECE_SIZE);
			}
		}
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (!p.isAttached()) {
				p.setX((int) ((i - unusedPieceComponents.size() / 2)
						* PIECE_SIZE * 0.6 + getWidth() / 2 - 50));
				p.setY((int) (PIECE_SIZE + getHeight() * 0.6));
			}
			p.paintComponent(g2d);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("pressed");
		int x = e.getX();
		int y = e.getY();
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		// System.out.println(x);
		// System.out.println(y);
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (x > p.getX() && x < p.getX() + PIECE_SIZE && y > p.getY()
					&& y < p.getY() + PIECE_SIZE) {
				p.setAttached(true);
				chosen.add(i);
			}
		}
		while (chosen.size() > 1) {
			unusedPieceComponents.get(chosen.get(0)).setAttached(false);
			chosen.remove(0);
		}
		for (PieceComponent p : usedPieceComponents)
			if (x > p.getX() && x < p.getX() + PIECE_SIZE && y > p.getY()
					&& y < p.getY() + PIECE_SIZE)
				p.setAttached(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// System.out.println("pressed");
		int x = e.getX();
		int y = e.getY();
		// System.out.println(x);
		// System.out.println(y);
		for (PieceComponent p : unusedPieceComponents)
			if (x > p.getX() && x < p.getX() + PIECE_SIZE && y > p.getY()
					&& y < p.getY() + PIECE_SIZE)
				p.setAttached(false);
		for (PieceComponent p : usedPieceComponents)
			if (x > p.getX() && x < p.getX() + PIECE_SIZE && y > p.getY()
					&& y < p.getY() + PIECE_SIZE)
				p.setAttached(false);
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		int x = e.getX();
		int y = e.getY();
		// System.out.println(x);
		// System.out.println(y);
		for (PieceComponent p : unusedPieceComponents) {
			if (p.isAttached()) {
//				System.out.println(p.getX());
				p.setX(x - (int) (PIECE_SIZE * 0.8));
//				System.out.println(p.getX());
				p.setY(y - (int) (PIECE_SIZE * 0.8));
			}
		}
		for (PieceComponent p : usedPieceComponents)
			if (p.isAttached()) {
				System.out.println(p.getX());
				p.setX(x - PIECE_SIZE / 2);
				System.out.println(p.getX());
				p.setY(y - PIECE_SIZE / 2);
			}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub

	}

}
