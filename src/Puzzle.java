import java.util.ArrayList;

public class Puzzle {

	private Board board;
	private int rows;
	private int cols;
	private ArrayList<Piece> unused;
	private boolean solved = false;

	public Puzzle(int rows, int cols) {
		board = new Board(rows - 1, cols - 1);
		this.rows = rows - 1;
		this.cols = cols - 1;
		unused = new ArrayList<Piece>();
	}

	public Puzzle(int rows, int cols, Piece[] pieces) {
		this(rows, cols);
		for (int i = 0; i < pieces.length; i++)
			unused.add(pieces[i]);
	}

	public Puzzle(Board board) {
		this.board = board;
		rows = board.getRows();
		cols = board.getCols();
		unused = new ArrayList<Piece>();
	}

	// Constructs a puzzle with specified rows and cols
	// and set of pieces to be used. Remember to copy the pieces into a new
	// array

	public Puzzle(int rows, int cols, ArrayList<Piece> startingPieces) {
		board = new Board(rows, cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();

	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public boolean matchEdge(Piece piece1, Piece piece2, int directionFrom1to2) {
		int directionFrom2to1;
		if (directionFrom1to2 < 2)
			directionFrom2to1 = directionFrom1to2 + 2;
		else
			directionFrom2to1 = directionFrom1to2 - 2;
		if (piece1.getEdge(directionFrom1to2)
				+ piece2.getEdge(directionFrom2to1) == 0)
			return true;
		return false;
	}

	public boolean doesFit(int row, int col, Piece piece) {
		for (int i = 0; i < 4; i++) {
			if (!(sideFit(row, col, i, piece)))
				return false;
		}
		return true;
	}

	private boolean sideFit(int row, int col, int direction, Piece piece) {
		if (direction == Piece.NORTH)
			row--;
		if (direction == Piece.EAST)
			col++;
		if (direction == Piece.SOUTH)
			row++;
		if (direction == Piece.WEST)
			col--;
		if (board.isValid(row, col) == true) {
			if (board.hasPiece(row, col) == true) {
				if (!(matchEdge(board.getPiece(row, col), piece, 0)))
					return false;
			}
		}
		return true;
	}

	public boolean isSolved() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				if (!doesFit(i, j, board.getPiece(i, j)))
					return false;
		}
		solved = true;
		return solved;
	}

	public Piece setPiece(int row, int col, Piece piece) {
		if (!doesFit(row, col, piece))
			return null;
		else {
			Piece a = board.getPiece(row, col);
			board.setPiece(row, col, piece);
			return a;
		}
	}

	public Piece getPiece(int row, int col) {
		return board.getPiece(row, col);
	}

	public Piece removePiece(int row, int col) {
		Piece a = board.getPiece(row, col);
		unused.add(board.removePiece(row, col));
		return a;
	}

	public void addPiece(Piece piece) {
		unused.add(piece);
	}

	public ArrayList<Piece> getUnusedPieces() {
		ArrayList<Piece> a = new ArrayList<Piece>();
		for (int i = 0; i < unused.size(); i++)
			a.add(unused.get(i));
		return a;
	}

	public String toString() {
		String s = "";
		for (int i = 1; i <= board.getRows(); i++) {
			for (int j = 1; j <= board.getCols(); j++) {
				s += board.getPiece(i, j) + " ";
			}
			s += "\n";
		}

		return s;
	}

	public void solve() {
		restart();
		solve(0, 0);
	}

	private boolean solve(int row, int col) {
		if (isSolved() || board.isValid(row, col) == false
				|| board.hasPiece(row, col))
			return true;
		boolean a = false;
		boolean b = false;
		Piece[] unusedCopy = new Piece[unused.size()];
		for (int i = 0; i < unusedCopy.length; i++)
			unusedCopy[i] = unused.get(i);
		for (int i = 0; i < unusedCopy.length; i++) {
			for (int j = 0; j < 3; j++) {
				unusedCopy[i].rotate();
				if (doesFit(row, col, unusedCopy[i])) {
					setPiece(row, col, unusedCopy[i]);
					a = solve(row + 1, col);
					b = solve(row, col + 1);
					// System.out.println(board.toString());
					if (a && b)
						return true;
				}
				// unusedCopy[i].rotate();
			}
			clearBranch(row, col);
		}
		return false;
	}

	private void clearBranch(int row, int col) {
		for (int i = row; i < rows; i++)
			for (int j = col; j < cols; j++)
				removePiece(i, j);
	}

	public void restart() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				if (board.hasPiece(i, j) == true)
					removePiece(i, j);
	}

	public static void main(String[] args) {

	}

}
