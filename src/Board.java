/**
 * Purpose: to make a rectangular/square puzzle board to hold solved puzzle
 * pieces, using a two dimensional array to represent the board, user can get,
 * set, remove the pieces on the board and clear the board Written: 04/10/15
 * 
 * @author Kaitlyn Li
 * @author Kevin Ma
 */
public class Board {

	/*
	 * to define the private variables rows = the number of rows in the board
	 * cols = the number of columns in the board board = two dimensional array
	 * to store the puzzle pieces
	 */
	private int rows;
	private int cols;
	private Piece[][] board;

	/*
	 * to construct a rectangle board object parameters: rows = the number of
	 * rows, cols = the number of columns
	 */
	public Board(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		board = new Piece[rows][cols];
	}

	/*
	 * to construct a square board object parameters: sides = the number of rows
	 * and columns
	 */
	public Board(int sides) {
		rows = sides;
		cols = sides;
		board = new Piece[sides][sides];
	}

	/*
	 * to get the number of rows on the board returns the number of rows
	 */
	public int getRows() {
		return rows;
	}

	/*
	 * to get the number of columns on the board returns the number of columns
	 */
	public int getCols() {
		return cols;
	}

	/*
	 * to set a piece on a tile of the board parameter: row = the row the tile
	 * is in, col = the column the tile is in, piece = the piece to be placed on
	 * the tile returns the piece that was previously on the tile
	 */
	public Piece setPiece(int row, int col, Piece piece) {
		Piece old = board[row][col];
		board[row][col] = piece;
		return old;
	}

	/*
	 * to remove a piece from a tile of the board parameter: row = the row the
	 * piece is in, col = the column the piece is in returns the piece that was
	 * previously on the tile
	 */
	public Piece removePiece(int row, int col) {
		Piece a = board[row][col];
		board[row][col] = null;
		return a;
	}

	/*
	 * to get a tile piece on the board parameter: row = the row the piece is
	 * in, col = the column the piece is in returns the piece
	 */
	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

	/*
	 * to check if the tile on the board has a piece parameter: row = the row
	 * the tile is in, col = the column the tile is in returns whether the tile
	 * has a piece or not
	 */
	public boolean hasPiece(int row, int col) {
		if (board[row][col] == null)
			return false;
		return true;
	}

	/*
	 * to check if a certain tile is on the board parameter: row = the row the
	 * tile is in, col = the column the tile is in returns whether the tile is
	 * on the board or not
	 */
	public boolean isValid(int row, int col) {
		if (row >= 0 && col >= 0)
			if (row < rows && col < cols)
				return true;
		return false;
	}

	/*
	 * to clear the entire board
	 */
	public void clear() {
		// for(int i = 0; i < board.length; i++){
		// for(int j = 0; j < board[0].length; j++){
		// board[i][j] = null;
		//
		// }
		// }
		board = new Piece[rows][cols];
	}

	/*
	 * a toString method to convert the board into printable string format
	 * returns a string value
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				s += board[i][j] + " ";
			}
			s += "\n";
		}

		return s;
	}

	/*
	 * main method
	 */
	public static void main(String[] args) {
		Board a = new Board(5, 3);
		System.out.println(a.isValid(0, 2));
		System.out.println(a.hasPiece(2, 3));
		System.out.println(a.removePiece(1, 1));

		/*
		 * System.out.println(a.toString()); Piece b = new Piece(1,2,3,4); Piece
		 * c = new Piece(3,4,5,2); a.setPiece(1,3,b); a.setPiece(3,1,c);
		 * System.out.println(a.toString()); a.removePiece(1,2);
		 * System.out.println(a.toString()); a.clear();
		 * System.out.println(a.toString());
		 * System.out.println(a.hasPiece(2,1));
		 */

	}

}
