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

/**
 * A JPanel object that allows the user to interact with a Puzzle object. Stores
 * a default preset of puzzle pieces in the form of PieceComponents. Requires
 * all Pieces added to contained Puzzle class to be PieceComponents in order to
 * properly draw them. All methods that need to repaint do so when appropriate.
 * 
 * @author Kevin Zhan
 * @author Amy Liu
 * @author Robert Colley
 *
 */
@SuppressWarnings("serial")
public class PuzzlePanel extends JPanel implements MouseListener,
		MouseMotionListener, MouseWheelListener {
	private static final int H_OUT = Piece.HEARTS_OUT, H_IN = Piece.HEARTS_IN,
			C_OUT = Piece.CLUBS_OUT, C_IN = Piece.CLUBS_IN,
			D_IN = Piece.DIAMONDS_IN, D_OUT = Piece.DIAMONDS_OUT,
			S_IN = Piece.SPADES_IN, S_OUT = Piece.SPADES_OUT;
	private static final int PIECE_SIZE = 70, PIECE_SIZE_EDGE = 118;
	private Puzzle pu;
	private PieceComponent[] pieces = {
			new PieceComponent(C_OUT, H_OUT, D_IN, C_IN, ImageIO.read(new File(
					"img/piece_1.png"))),
			new PieceComponent(S_OUT, D_OUT, S_IN, H_IN, ImageIO.read(new File(
					"img/piece_2.png"))),
			new PieceComponent(H_OUT, S_OUT, S_IN, C_OUT,
					ImageIO.read(new File("img/piece_3.png"))),
			new PieceComponent(H_OUT, D_OUT, C_IN, C_IN, ImageIO.read(new File(
					"img/piece_4.png"))),
			new PieceComponent(S_OUT, S_OUT, H_IN, C_IN, ImageIO.read(new File(
					"img/piece_5.png"))),
			new PieceComponent(H_OUT, D_OUT, D_IN, H_IN, ImageIO.read(new File(
					"img/piece_6.png"))),
			new PieceComponent(S_OUT, D_OUT, H_IN, D_IN, ImageIO.read(new File(
					"img/piece_7.png"))),
			new PieceComponent(C_OUT, H_OUT, S_IN, H_IN, ImageIO.read(new File(
					"img/piece_8.png"))),
			new PieceComponent(C_OUT, C_IN, D_IN, D_OUT, ImageIO.read(new File(
					"img/piece_9.png"))) };

	private boolean isSolved = false;
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

	/**
	 * Calls solve() in Puzzle and then syncs the GUI to Puzzle
	 */
	public void solve() {
		pu.solve();
		usedPieceComponents = new ArrayList<PieceComponent>();
		for (int i = 0; i < pu.getCols(); i++) {
			for (int j = 0; j < pu.getRows(); j++) {
				PieceComponent p = (PieceComponent) pu.getPiece(i, j);
				if (p != null) {
					p.setPos(j, i);
					usedPieceComponents.add(p);
				}
			}
		}
		unusedPieceComponents = new ArrayList<PieceComponent>();
		ArrayList<Piece> temp = pu.getUnusedPieces();
		for (Piece p : temp)
			unusedPieceComponents.add((PieceComponent) p);
		isSolved = true;
		repaint();
	}

	/**
	 * Calls restart() in Puzzle and resets all pieces to their
	 * starting positions.
	 * 
	 * @throws IOException
	 */
	public void reset() throws IOException {
		pu.restart();
		ArrayList<Piece> temp = pu.getUnusedPieces();
		unusedPieceComponents = new ArrayList<PieceComponent>();
		usedPieceComponents = new ArrayList<PieceComponent>();
		for (int i = 0; i < temp.size(); i++) {
			Piece p = temp.get(i);
			unusedPieceComponents.add((PieceComponent) p);
		}
		isSolved = false;
		repaint();
	}

	/**
	 * Draws all Puzzle pieces and the board. Automatically sets x and y coordinates for all pieces that are not attached to the mouse.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < pu.getCols(); i++) {
			for (int j = 0; j < pu.getRows(); j++) {
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
		}
	}

	/**
	 * Rotates the selected pieces in the direction in which the mouse is
	 * scrolled. The piece must be attached or it will not be rotated, meaning
	 * the user can only rotate a piece when they are currently dragging it.
	 * Forward rotates the piece counterclockwise. Backward rotates the piece
	 * clockwise.
	 */
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (isSolved)
			return;
		int rotated = e.getWheelRotation();
		for (PieceComponent p : unusedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
		for (PieceComponent p : usedPieceComponents)
			if (p.isAttached())
				p.rotate(rotated);
		repaint();
	}

	/**
	 * Updates the coordinates of the currently dragged piece so it is centered
	 * on the mouse cursor. The piece must be attached, meaning this method only
	 * updates pieces that are actively being dragged.
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (isSolved)
			return;
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

	/**
	 * Detects the location of the mouse cursor and compares it to the locations
	 * of the pieces on screen, then sets whichever pieces on overlapped with
	 * the mouse cursor to attached. Only detects if the mouse cursor is within
	 * the bound of the square area of the piece, not parts of the piece that
	 * are hanging off. If multiple pieces are clicked due to overlapping, only
	 * the one that is "on top" or the farthest right in location is attached.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (isSolved)
			return;
		int mouseX = e.getX();
		int mouseY = e.getY();
		ArrayList<PieceComponent> clicked = new ArrayList<PieceComponent>();
		for (int i = 0; i < unusedPieceComponents.size(); i++) {
			PieceComponent p = unusedPieceComponents.get(i);
			if (p.getX() + 23 < mouseX && p.getX() + PIECE_SIZE + 23 > mouseX
					&& p.getY() + 23 < mouseY
					&& p.getY() + PIECE_SIZE + 23 > mouseY) {
				p.setAttached(true);
				clicked.add(p);
			}
		}
		while (clicked.size() > 1) {
			clicked.get(0).setAttached(false);
			clicked.remove(0);
		}
		for (int i = 0; i < usedPieceComponents.size(); i++) {
			PieceComponent p = usedPieceComponents.get(i);
			if (p.getX() + 23 < mouseX && p.getX() + PIECE_SIZE + 23 > mouseX
					&& p.getY() + 23 < mouseY
					&& p.getY() + PIECE_SIZE + 23 > mouseY)
				p.setAttached(true);
		}
	}

	/**
	 * Uses the location of the mouse to attempt to "drop" the currently
	 * attached piece in an appropriate location. If the mouse is on top of an
	 * empty area of the board and fits in that space, the pieces is set to that
	 * empty space and removed from unused pieces. If the mouse is not on to of
	 * the board or does not fit in the space it in on top of, it will move back
	 * to its previous location before being dragged.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (isSolved)
			return;
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
							if (pu.doesFit(j, i, p)) {
								unusedPieceComponents.remove(p);
								usedPieceComponents.add(p);
								p.setPos(j, i);
								PieceComponent pTemp = (PieceComponent) pu
										.setPiece(j, i, p);
								pu.getUnusedPieces().remove(p);

								if (pTemp != null && pTemp != p) {
									pTemp.setPos(-1, -1);
									usedPieceComponents.remove(pTemp);
									unusedPieceComponents.add(pTemp);
									pu.addPiece(pTemp);
								}
							}
							break;
						}
					}
				}
			}
		}

		boolean isSet = false;
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
							isSet = true;
							pu.removePiece(p.getRow(), p.getCol());
							if (pu.doesFit(j, i, p)) {
								pu.removePiece(p.getRow(), p.getCol());
								p.setPos(j, i);
								PieceComponent pTemp = (PieceComponent) pu
										.setPiece(j, i, p);
								if (pTemp != null && pTemp != p) {
									pTemp.setPos(-1, -1);
									usedPieceComponents.remove(pTemp);
									unusedPieceComponents.add(pTemp);
									pu.addPiece(pTemp);
								}
								break;
							}
							else {
								usedPieceComponents.remove(p);
								unusedPieceComponents.add(p);
							}
						}

					}
				}
				if (!isSet) {
					pu.removePiece(p.getRow(), p.getCol());
					usedPieceComponents.remove(p);
					unusedPieceComponents.add(p);
					p.setPos(-1, -1);
				}
			}

		}
		repaint();
	}

	/**
	 * Unused.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Unused.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Unused.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}

	/**
	 * Unused.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
