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
	private boolean isSolved = false;
	private static final int H_OUT = Piece.HEARTS_OUT, H_IN = Piece.HEARTS_IN,
			C_OUT = Piece.CLUBS_OUT, C_IN = Piece.CLUBS_IN,
			D_IN = Piece.DIAMONDS_IN, D_OUT = Piece.DIAMONDS_OUT,
			S_IN = Piece.SPADES_IN, S_OUT = Piece.SPADES_OUT;
	private static final int PIECE_SIZE = 70;
	private Puzzle pu;
	private PieceComponent[] pieces = {
			new PieceComponent(C_OUT, H_OUT, D_IN, S_IN, ImageIO.read(new File(
					"img/piece_1.png"))),
			new PieceComponent(S_OUT, D_OUT, S_IN, H_IN, ImageIO.read(new File(
					"img/piece_2.png"))),
			new PieceComponent(H_OUT, S_OUT, S_IN, C_OUT,
					ImageIO.read(new File("img/piece_3.png"))),
			new PieceComponent(H_OUT, D_OUT, S_IN, S_IN, ImageIO.read(new File(
					"img/piece_4.png"))),
			new PieceComponent(S_OUT, S_OUT, H_IN, S_IN, ImageIO.read(new File(
					"img/piece_5.png"))),
			new PieceComponent(H_OUT, D_OUT, D_IN, H_IN, ImageIO.read(new File(
					"img/piece_6.png"))),
			new PieceComponent(S_OUT, D_OUT, H_IN, D_IN, ImageIO.read(new File(
					"img/piece_7.png"))),
			new PieceComponent(C_OUT, H_OUT, S_IN, H_IN, ImageIO.read(new File(
					"img/piece_8.png"))),
			new PieceComponent(C_OUT, C_IN, D_IN, D_OUT, ImageIO.read(new File(
					"img/piece_9.png"))) };

	private ArrayList<PieceComponent> unusedPieceComponents = new ArrayList<PieceComponent>();
	private ArrayList<PieceComponent> usedPieceComponents = new ArrayList<PieceComponent>();

	public PuzzlePanel() throws IOException {
		ArrayList<Piece> temp = new ArrayList<Piece>();
		for (int i = 0; i < pieces.length; i++) {
			temp.add((Piece) pieces[i]);
		}
		pu = new Puzzle(3, 3, temp);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.setVisible(true);
		repaint();
	}

	public void solve() {
		pu.solve();
		isSolved = true;
		repaint();
	}

	public void reset() throws IOException {
		pu.restart();
		unusedPieceComponents = new ArrayList<PieceComponent>();
		usedPieceComponents = new ArrayList<PieceComponent>();
		for (int i = 0; i < pieces.length; i++) {
			PieceComponent p = new PieceComponent(pieces[i],
					ImageIO.read(new File("img/piece_" + (i + 1) + ".png")));
			unusedPieceComponents.add(p);
		}
		repaint();
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
				int xPos = (i - pu.getCols() / 2) * PIECE_SIZE + getWidth() / 2
						- 50;
				int yPos = (j - pu.getRows() / 2) * PIECE_SIZE + getHeight()
						/ 2 - 50;
				g2d.drawRect(xPos, yPos, PIECE_SIZE, PIECE_SIZE);
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
		for (PieceComponent p : usedPieceComponents) {
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
		if (isSolved)
			return;
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
		if (isSolved)
			return;

		// System.out.println("pressed");
		int x = e.getX();
		int y = e.getY();
		// System.out.println(x);
		// System.out.println(y);
		boolean isSet = false;
		for (int k = 0; k < unusedPieceComponents.size(); k++) {
			PieceComponent p = unusedPieceComponents.get(k);
			if (p.isAttached()) {
				p.setAttached(false);
				for (int i = 0; i < pu.getCols() && !isSet; i++) {
					for (int j = 0; j < pu.getRows() && !isSet; j++) {
						int xPos = (i - pu.getCols() / 2) * PIECE_SIZE
								+ getWidth() / 2 - 50;
						int yPos = (j - pu.getRows() / 2) * PIECE_SIZE
								+ getHeight() / 2 - 50;
						if (x > xPos && x < xPos + PIECE_SIZE && y > yPos
								&& y < yPos + PIECE_SIZE) {
							if (pu.setPiece(j, i, p.getPiece()) != null) {
								p.setX(xPos - 23);
								p.setY(yPos - 23);
								p.setRow(j);
								p.setCol(i);
								for (int l = 0; l < usedPieceComponents.size(); l++) {
									if (usedPieceComponents.get(l).getRow() == j
											&& usedPieceComponents.get(l)
													.getCol() == i)
										unusedPieceComponents
												.add(usedPieceComponents
														.remove(l));
								}
								unusedPieceComponents.remove(k);
								usedPieceComponents.add(p);
							}
							isSet = true;
						}
					}
				}
			}
		}
		boolean isOn = false;
		for (int k = 0; k < usedPieceComponents.size(); k++) {
			PieceComponent p = usedPieceComponents.get(k);
			if (p.isAttached()) {
				p.setAttached(false);
				pu.removePiece(p.getRow(), p.getCol());
				for (int i = 0; i < pu.getCols() && !isSet; i++) {
					for (int j = 0; j < pu.getRows() && !isSet; j++) {
						int xPos = (i - pu.getCols() / 2) * PIECE_SIZE
								+ getWidth() / 2 - 50;
						int yPos = (j - pu.getRows() / 2) * PIECE_SIZE
								+ getHeight() / 2 - 50;
						if (x > xPos && x < xPos + PIECE_SIZE && y > yPos
								&& y < yPos + PIECE_SIZE) {
							if (pu.setPiece(j, i, p.getPiece()) != null) {
								pu.removePiece(p.getRow(), p.getCol());
								p.setX(xPos - 23);
								p.setY(yPos - 23);
								p.setRow(j);
								p.setCol(i);
								for (int l = 0; l < usedPieceComponents.size(); l++) {
									if (usedPieceComponents.get(l).getRow() == j
											&& usedPieceComponents.get(l)
													.getCol() == i)
										unusedPieceComponents
												.add(usedPieceComponents
														.remove(l));
								}
								usedPieceComponents.add(p);
							}
							isSet = true;
						}
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (isSolved)
			return;

		int x = e.getX();
		int y = e.getY();
		// System.out.println(x);
		// System.out.println(y);
		for (PieceComponent p : unusedPieceComponents) {
			if (p.isAttached()) {
				// System.out.println(p.getX());
				p.setX(x - (int) (PIECE_SIZE * 0.8));
				// System.out.println(p.getX());
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
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (isSolved)
			return;

		System.out.println("rotate");
		int rotated = e.getWheelRotation();
		for (PieceComponent p : unusedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
		for (PieceComponent p : usedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
	}

}
