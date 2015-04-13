import java.util.ArrayList;

public class Puzzle {

	public Puzzle(int rows, int cols, Piece[] pieces) {
		// TODO Auto-generated constructor stub
	}

	public Puzzle(int rows, int cols, ArrayList<Piece> temp) {
		// TODO Auto-generated constructor stub
	}

	public void solve() {
		// TODO Auto-generated method stub

	}

	public void restart() {
		// TODO Auto-generated method stub

	}

	public int getRows() {
		// TODO Auto-generated method stub
		return 3;
	}

	public int getCols() {
		// TODO Auto-generated method stub
		return 3;
	}

	public Piece getPiece(int row, int col) {
		// TODO Auto-generated method stub
		return null;
	}

	public Piece[] getUnusedPieces() {
		// TODO Auto-generated method stub
		return null;
	}

	public Piece setPiece(int j, int i, Piece piece) {
		// TODO Auto-generated method stub
		return new Piece(i, i, i, i);
	}

	public Piece removePiece(int row, int col) {
		return new Piece(col, col, col, col);
		// TODO Auto-generated method stub

	}

}
