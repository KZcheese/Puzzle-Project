import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

public class PieceComponent extends JComponent {
	private Piece piece;
	private Image image;
	private AffineTransform tx;
	private int x;
	private int y;
	private boolean attached;

	public PieceComponent(Piece piece, Image image, int x, int y) {
		super();
		this.attached = false;
		this.x = x;
		this.y = y;
		this.piece = piece;
		this.image = image;
		this.tx = new AffineTransform();
	}

	public void rotate(int numquadrants) {
		if (attached) {
			tx.quadrantRotate(numquadrants);
			piece.rotate(numquadrants);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, x, y, this);
		// turns anti-aliasing on
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.transform(tx);
	}
}
