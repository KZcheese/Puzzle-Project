/*Purpose: to make a puzzle piece. A piece has 4 sides, each side could have one of 8 edges.
 * User can rotate the piece to change orientation. 
 * Written: 04/10/15
 * by Kaitlyn Li, Kevin Ma
 */
public class Piece {

	/*
	 * to define the private variables sides = an integer array with four
	 * elements representing four sides, each side holds one of 8 edges.
	 * orientation = the number of rotations happened, initially is 0;
	 */
	private int[] sides;
	private int orientation = 0;

	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;

	public static final int HEARTS_IN = 1;
	public static final int HEARTS_OUT = -1;
	public static final int CLUBS_IN = 2;
	public static final int CLUBS_OUT = -2;
	public static final int SPADES_IN = 3;
	public static final int SPADES_OUT = -3;
	public static final int DIAMONDS_IN = 4;
	public static final int DIAMONDS_OUT = -4;

	/*
	 * to construct a piece from four edges parameters: north = the north edge,
	 * east = the east edge, south = the south edge, west = the west edge
	 */
	public Piece(int north, int east, int south, int west) {
		sides = new int[4];
		sides[NORTH] = north;
		sides[EAST] = east;
		sides[SOUTH] = south;
		sides[WEST] = west;
	}

	/*
	 * to construct a piece from an integer array of edges parameters: a = an
	 * array of edges
	 */
	public Piece(int[] a) {
		sides = new int[4];
		for (int i = 0; i < sides.length; i++) {
			sides[i] = a[i];
		}
	}

	/*
	 * to get the edge of the piece parameters: direction = the edge direction
	 * returns the edge
	 */
	public int getEdge(int direction) {
		return sides[direction];
	}

	/*
	 * to get the orientation of the piece returns the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/*
	 * to rotate a piece by 90 degree clockwise
	 */
	public void rotate() {
		int newNorth = sides[WEST];
		for (int i = sides.length - 1; i > 0; i--)
			sides[i] = sides[i - 1];
		sides[NORTH] = newNorth;
		orientation++;

	}

	/*
	 * to rotate a piece by 90 degree counterclockwise
	 */
	private void rotateBackward() {
		int newWest = sides[NORTH];
		for (int i = 0; i < 3; i++)
			sides[i] = sides[i + 1];
		sides[WEST] = newWest;
		orientation--;
	}

	/*
	 * to rotate a piece by number of rotations parameters: rotations = the
	 * number of rotations
	 */
	public void rotate(int rotations) {
		if (rotations > 0)
			for (int i = 0; i < rotations; i++)
				rotate();
		else
			for (int i = 0; i > rotations; i--)
				rotateBackward();
	}

	/*
	 * a toString method to convert the piece into printable string format
	 * returns a string value
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < sides.length; i++) {
			s += sides[i] + " ";
		}
		return s;
	}

	// main method
	public static void main(String[] args) {
		Piece a = new Piece(DIAMONDS_IN, DIAMONDS_OUT, HEARTS_OUT, CLUBS_OUT);
		System.out.println(a.toString());
		a.rotate();
		System.out.println(a.toString());
		a.rotate(2);
		System.out.println(a.toString());
		a.rotate(-1);
		System.out.println(a.toString());
		System.out.println(a.getOrientation());
		System.out.println("South edge is  " + a.getEdge(SOUTH));
	}
}
