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
	/**
	 * to define the private variables image = is the image file of the piece, 
	 * x = the x location of the component on the screen, y = the y location
	 * of the component on the screen, attached = whether or not the component 
	 * is attached to the mouse, row = the row of the puzzle that the component
	 * is in, col = the column of the puzzle that the component is in. row and 
	 * col are initialized to -1.
	 * 
	 */
	private BufferedImage image;
	private int x;
	private int y;
	private boolean attached;
	private int row = -1;
	private int col = -1;

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * to set the row of the component 
	 * 
	 * @param row
	 *            = the new row to set row to 
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * to set the column of the component 
	 * 
	 * @param col
	 *            = the new column to set col to 
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * Constructs a PieceComponent with north, east, south, west 
	 * edges, an image, and an x and y location
	 * 
	 * @param north
	 *            = the north edge
	 * @param east
	 *            = the east edge
	 * @param south 
	 *            = the south edge  
	 * @param west
	 *            = the west edge
	 * @param image
	 *            = the piece image
	 * @param x
	 *            = the x location
	 * @param y
	 *            = the y location       
	 */
	public PieceComponent(int north, int east, int south, int west,
			BufferedImage image, int x, int y) {
		super(north, east, south, west);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	/**
	 * Constructs a PieceComponent with north, east, south, west 
	 * edges and an image
	 * 
	 * @param north
	 *            = the north edge
	 * @param east
	 *            = the east edge
	 * @param south 
	 *            = the south edge  
	 * @param west
	 *            = the west edge
	 * @param image
	 *            = the piece image 
	 */
	public PieceComponent(int north, int east, int south, int west,
			BufferedImage image) {
		super(north, east, south, west);
		this.image = image;
		this.x = 0;
		this.y = 0;
		this.attached = false;
	}

	/**
	 * Constructs a PieceComponent with an array of ints for the
	 * edges, an image, and an x and y location
	 * 
	 * @param edges
	 *            = an int array of the edges
	 * @param image
	 *            = the piece image
	 * @param x
	 *            = the x location
	 * @param y
	 *            = the y location       
	 */
	public PieceComponent(int[] edges, BufferedImage image, int x, int y) {
		super(edges);
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	/**
	 * Constructs a PieceComponent with a Piece, an image, 
	 * and an x and y location
	 * 
	 * @param p
	 *            = the Piece object
	 * @param image
	 *            = the piece image
	 * @param x
	 *            = the x location
	 * @param y
	 *            = the y location       
	 */
	public PieceComponent(Piece p, BufferedImage image, int x, int y) {
		super(p.getEdge(Piece.NORTH), p.getEdge(Piece.EAST), p
				.getEdge(Piece.SOUTH), p.getEdge(Piece.WEST));
		this.image = image;
		this.x = x;
		this.y = y;
		this.attached = false;
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * to set the image
	 * 
	 * @param image
	 *            = the new image to set image to 
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * to set the x
	 * 
	 * @param x
	 *            = the new x location to set x to 
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * to set the y
	 * 
	 * @param y
	 *            = the new y to set y to 
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * to set the row and col of the component
	 * 
	 * @param row
	 *            = the new row to set row to
	 * @param col
	 *            = the new col to set col to  
	 */
	public void setPos(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the private boolean attached
	 */
	public boolean isAttached() {
		return attached;
	}

	/**
	 * to set the private boolean attached
	 * 
	 * @param attached
	 *            = the new boolean value of attached
	 */
	public void setAttached(boolean attached) {
		this.attached = attached;
	}

}
