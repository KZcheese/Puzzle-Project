import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
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
	private static final int PIECE_SIZE = 70, PIECE_SIZE_EDGE = 118;
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
			unusedPieceComponents.add(pieces[i]);
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

	public void reset() throws IOException { // adds ghost pieces to
												// unusedPieces
		pu.restart();
		ArrayList<Piece> temp = pu.getUnusedPieces();
		unusedPieceComponents = new ArrayList<PieceComponent>();
		usedPieceComponents = new ArrayList<PieceComponent>();
		for (int i = 0; i < temp.size(); i++) {
			Piece p = temp.get(i);
			unusedPieceComponents.add((PieceComponent) p);
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

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
				PieceComponent p = (PieceComponent) pu.getPiece(j, i);
				if (p != null) {
					if (!p.isAttached()) {
						p.setX(xPos - 23);
						p.setY(yPos - 23);
					}
					AffineTransform tx = AffineTransform
							.getQuadrantRotateInstance(p.getOrientation(),
									PIECE_SIZE_EDGE / 2, PIECE_SIZE_EDGE / 2);
					AffineTransformOp op = new AffineTransformOp(tx,
							AffineTransformOp.TYPE_BILINEAR);
					g2d.drawImage(
							op.filter((BufferedImage) p.getImage(), null),
							p.getX(), p.getY(), null);
				}
			}
		}
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (!p.isAttached()) {
				p.setX((int) ((i - unusedPieceComponents.size() / 2)
						* PIECE_SIZE * getWidth() / 800 + getWidth() / 2 - 50));
				p.setY((int) (PIECE_SIZE + getHeight() * 0.6));
			}

			AffineTransform tx = AffineTransform.getQuadrantRotateInstance(
					p.getOrientation(), PIECE_SIZE_EDGE / 2,
					PIECE_SIZE_EDGE / 2);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			g2d.drawImage(op.filter((BufferedImage) p.getImage(), null),
					p.getX(), p.getY(), null);
			// g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
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
	public void mouseMoved(MouseEvent e) {

		// TODO Auto-generated method stub

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (isSolved)
			return;
		// System.out.println("rotate");
		int rotated = e.getWheelRotation();
		// System.out.println(rotated);
		for (PieceComponent p : unusedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
		for (PieceComponent p : usedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// System.out.println("drag");
		int mouseX = e.getX();
		int mouseY = e.getY();
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (p.isAttached()) {
				p.setX(mouseX - PIECE_SIZE_EDGE / 2);
				p.setY(mouseY - PIECE_SIZE_EDGE / 2);
				break;
			}
		}
		for (int i = 0; i < usedPieceComponents.size(); i++) {
			PieceComponent p = usedPieceComponents.get(i);
			if (p.isAttached()) {
				p.setX(mouseX - PIECE_SIZE_EDGE / 2);
				p.setY(mouseY - PIECE_SIZE_EDGE / 2);
				break;
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		int mouseX = e.getX();
		int mouseY = e.getY();
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (p.getX() + 23 < mouseX && p.getX() + PIECE_SIZE > mouseX
					&& p.getY() + 23 < mouseY && p.getY() + PIECE_SIZE > mouseY)
				p.setAttached(true);
		}
		for (int i = 0; i < usedPieceComponents.size(); i++) {
			PieceComponent p = usedPieceComponents.get(i);
			if (p.getX() + 23 < mouseX && p.getX() + PIECE_SIZE > mouseX
					&& p.getY() + 23 < mouseY && p.getY() + PIECE_SIZE > mouseY)
				p.setAttached(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("released");
		int x = e.getX();
		int y = e.getY();
		for (int k = 0; k < unusedPieceComponents.size(); k++) {
			PieceComponent p = unusedPieceComponents.get(k);
			if (p.isAttached()) {
				p.setAttached(false);
				for (int i = 0; i < pu.getCols(); i++) {
					for (int j = 0; j < pu.getRows(); j++) {
						int xPos = (i - pu.getCols() / 2) * PIECE_SIZE
								+ getWidth() / 2 - 50;
						int yPos = (j - pu.getRows() / 2) * PIECE_SIZE
								+ getHeight() / 2 - 50;
						if (x > xPos && x < xPos + PIECE_SIZE && y > yPos
								&& y < yPos + PIECE_SIZE) {
							if (pu.doesFit(i, j, p)) {
								unusedPieceComponents.remove(p);
								usedPieceComponents.add(p);
							}
							PieceComponent pTemp = (PieceComponent) pu
									.setPiece(i, j, p);

							if (pTemp != null) {
								usedPieceComponents.remove(pTemp);
								unusedPieceComponents.add(pTemp);
							}
							break;
						}
					}
				}
			}
		}
		for (int k = 0; k < usedPieceComponents.size(); k++) {
			PieceComponent p = usedPieceComponents.get(k);
			if (p.isAttached()) {
				p.setAttached(false);
				for (int i = 0; i < pu.getCols(); i++) {
					for (int j = 0; j < pu.getRows(); j++) {
						int xPos = (i - pu.getCols() / 2) * PIECE_SIZE
								+ getWidth() / 2 - 50;
						int yPos = (j - pu.getRows() / 2) * PIECE_SIZE
								+ getHeight() / 2 - 50;
						if (x > xPos && x < xPos + PIECE_SIZE && y > yPos
								&& y < yPos + PIECE_SIZE) {
							if (pu.doesFit(i, j, p)) {
								pu.removePiece(i, j);
							}
							PieceComponent pTemp = (PieceComponent) pu
									.setPiece(i, j, p);
							if (pTemp != null) {
								usedPieceComponents.remove(pTemp);
								unusedPieceComponents.add(pTemp);
							}
							break;
						}
					}
				}
			}
		}
		repaint();
	}
}
