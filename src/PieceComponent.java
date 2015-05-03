import java.awt.image.BufferedImage;

/**
 * An extension of Piece that also holds X and Y coordinates, along with an
 * image and a boolean isAttached, which is used by the PuzzlePanel class to
 * detect whether or not the object is being moved by the user.
 * 
 * @author Robert Colley
 * @author Kevin Zhan
 * @author Amy Liu
 *
 */
public class PieceComponent extends Piece {

	private BufferedImage image;
	private int x;
	private int y;
	private boolean attached;
	private int row = -1;
	private int col = -1;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public PieceComponent(int north, int east, int south, int west,
			BufferedImage image, int x, int y) {
		super(north, east, south, west);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public PieceComponent(int north, int east, int south, int west,
			BufferedImage image) {
		super(north, east, south, west);
		this.image = image;
		this.x = 0;
		this.y = 0;
		this.attached = false;
	}

	public PieceComponent(int[] edges, BufferedImage image, int x, int y) {
		super(edges);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public PieceComponent(Piece p, BufferedImage image, int x, int y) {
		super(p.getEdge(Piece.NORTH), p.getEdge(Piece.EAST), p
				.getEdge(Piece.SOUTH), p.getEdge(Piece.WEST));
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
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

	public void setPos(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public boolean isAttached() {
		return attached;
	}

	public void setAttached(boolean attached) {
		this.attached = attached;
	}

}
