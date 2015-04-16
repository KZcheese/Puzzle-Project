public class Piece {

	private int[] sides;

	// stores the number of clockwise rotations, 0 means that it hasn't rotated
	private int orientation;

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

	public Piece(int north, int east, int south, int west) {
		sides = new int[4];
		sides[NORTH] = north;
		sides[EAST] = east;
		sides[SOUTH] = south;
		sides[WEST] = west;
		orientation = 0;
	}

	public Piece(int[] a) {
		sides = new int[4];
		for (int i = 0; i < sides.length; i++) {
			sides[i] = a[i];
		}
		orientation = 0;
	}

	public int getEdge(int direction) {
		return sides[direction];
	}

	public void rotate() {
		orientation += 1;
		int newNorth = sides[WEST];
		for (int i = 3; i > 0; i--)
			sides[i] = sides[(i - 1) % 4];
		sides[NORTH] = newNorth;
		orientation %= 4;
	}

	private void rotateBackward() {
		orientation -= 1;
		int newNorth = sides[EAST];
		for (int i = 2; i >= 0; i--)
			sides[i] = sides[(i + 1) % 4];
		sides[NORTH] = newNorth;
		orientation = 4 - (orientation % 4);
	}

	public void rotate(int rotations) {
		if (rotations > 0)
			for (int i = 0; i < rotations; i++)
				rotate();
		else
			for (int i = 0; i > rotations; i--)
				rotateBackward();
	}

	public int getOrientation() {
		return orientation;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < 4; i++) {
			s += sides[i] + " ";
		}
		return s;
	}

	public static void main(String[] args) {
		Piece a = new Piece(DIAMONDS_IN, DIAMONDS_OUT, HEARTS_OUT, CLUBS_OUT);
		System.out.println(a.toString());
		a.rotate();
		System.out.println(a.toString());
		a.rotate(3);
		System.out.println(a.toString());

		System.out.println(a.getOrientation());
	}
}
