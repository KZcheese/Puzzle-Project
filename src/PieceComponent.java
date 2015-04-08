import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class PieceComponent extends JComponent implements MouseWheelListener, MouseMotionListener{
	private Piece piece;
	private Image image;
	private AffineTransform tx;
	private int centerX;
	private int centerY;

	public PieceComponent(Piece piece, Image image){
		super();
		this.piece = piece;
		this.image = image;
		this.tx = new AffineTransform();
	}

	public void rotate(int numquadrants){
		tx.quadrantRotate(numquadrants);
		piece.rotate(numquadrants);

	}

	public void paint(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(image, centerX, centerY, null);
		//turns anti-aliasing on
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		g2.transform(tx);
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		rotate(arg0.getScrollAmount());
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		setCenterX(arg0.getX());
		setCenterY(arg0.getY());

	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
