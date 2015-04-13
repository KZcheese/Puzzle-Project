public class Piece {

	public static final int HEARTS_OUT = 0;
	public static final int HEARTS_IN = 0;
	public static final int CLUBS_OUT = 0;
	public static final int CLUBS_IN = 0;
	public static final int DIAMONDS_IN = 0;
	public static final int DIAMONDS_OUT = 0;
	public static final int SPADES_IN = 0;
	public static final int SPADES_OUT = 0;
	public static final int NORTH = 0;
	public static final int EAST = 0;
	public static final int SOUTH = 0;
	public static final int WEST = 0;
	private int orientation = 0;

	public Piece(int north, int east, int south, int west) {
		// TODO Auto-generated constructor stub
	}

	public Piece(int[] edges) {
		// TODO Auto-generated constructor stub
	}

	public void rotate(int numquadrants) {
		orientation += numquadrants;

	}

	public int getEdge(int edge) {
		// TODO Auto-generated method stub
		return 0;
	}

}
