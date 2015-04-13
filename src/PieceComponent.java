import java.awt.Image;
import java.awt.geom.AffineTransform;

public class PieceComponent extends Piece {

	private Image image;
	private int x;
	private int y;
	private boolean attached;

	public PieceComponent(int north, int east, int south, int west,
			Image image, int x, int y) {
		super(north, east, south, west);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public PieceComponent(int north, int east, int south, int west, Image image) {
		super(north, east, south, west);
		this.image = image;
		this.x = 0;
		this.y = 0;
		this.attached = false;
	}

	public PieceComponent(int[] edges, Image image, int x, int y) {
		super(edges);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public PieceComponent(Piece p, Image image, int x, int y) {
		super(p.getEdge(Piece.NORTH), p.getEdge(Piece.EAST), p
				.getEdge(Piece.SOUTH), p.getEdge(Piece.WEST));
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAttached() {
		return attached;
	}

	public void setAttached(boolean attached) {
		this.attached = attached;
	}

	public int getOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}
}
