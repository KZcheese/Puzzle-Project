import java.util.ArrayList;

/*Purpose: to create a puzzle with a board and a list of puzzle piece, and to check and find the solution for user
 * Written: 04/10/15
 * by Kaitlyn Li, Kevin Ma
 */
public class Puzzle {
	
	/* to define the private variables
	 * rows = the number of rows in the board
	 * cols = the number of columns in the board
	 * board = two dimensional array to store the solved puzzle pieces
	 * unused = the list of available pieces not put on the board yet
	 */
	private Board board;
	private int rows;
	private int cols;
	private ArrayList<Piece> unused;
	
	/* to construct a puzzle object
	 * parameters: rows = the number of rows, cols = the number of columns
	 */
	public Puzzle(int rows, int cols){
		board = new Board(rows, cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
	}
	
	/* to construct a puzzle object with an array of pieces to be used
	 * parameters: rows = the number of rows, cols = the number of columns, pieces = the array of available/unused pieces
	 */
	public Puzzle(int rows, int cols, Piece[] pieces){
		board = new Board(rows, cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
		for (int i = 0; i < pieces.length; i++){
			unused.add(pieces[i]);
		}
	}
	
	/* to construct a puzzle object with any array list of pieces to be used
	 * parameters: rows = the number of rows, cols = the number of columns, pieces = the array list of available/unused pieces
	 */
	public Puzzle(int rows, int cols, ArrayList<Piece> pieces){
		board = new Board(rows, cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
		unused.addAll(pieces);
	}
	
	/* to construct a puzzle object with the board
	 * parameters: board = the board holding puzzle piece
	 */
	public Puzzle(Board board){
		this.rows = board.getRows();
		this.cols = board.getCols();
		this.board = new Board(rows, cols);
		unused = new ArrayList<Piece>();
	}
	
	/* to get the board of the puzzle
	 * returns the board
	 */
	public Board getBoard(){
		return board;
	}
	
	/* to get the unused pieces of the puzzle
	 * returns the copy of unused pieces 
	 */
	public ArrayList<Piece> getUnusedPieces(){
		ArrayList<Piece> unusedCopy = new ArrayList<Piece>();
		unusedCopy.addAll(unused);
		return unusedCopy;
	}
	
	/* to check if the edges of two piece sides match 
	 * parameters: piece1 = the first piece, piece2 = the second piece, direction = the side of first piece
	 * returns true if matched, or false if not
	 */
	public boolean matchEdge(Piece piece1, Piece piece2, int directionFrom1to2){
		if(piece1.getEdge(directionFrom1to2) + piece2.getEdge((directionFrom1to2 + 2) % 4) == 0) return true;
		return false;
	}
	
	/* to check if the side of the piece matches with the corresponding neighbor piece edge in the board
	 * parameters: row = the row which the piece to be in , col = the column which the piece to be in, 
	 * direction = the side of piece, piece = the piece to fit in
	 * returns true if matched, or false if not
	 */
	private boolean sideFit(int row, int col, int direction, Piece piece){
		if(direction == Piece.NORTH) row --;
		if(direction == Piece.EAST) col ++;
		if(direction == Piece.SOUTH) row ++;
		if(direction == Piece.WEST) col--;
		if(board.isValid(row, col)){
			if(board.hasPiece(row, col)){
				if(!matchEdge(piece, getPiece(row, col), direction)) return false;
			}
		}
		return true;
	}
	
	/* to check if each side of the piece fits in the specified tile of the board
	 * parameters: row = the row which the piece to be in , col = the column which the piece to be in, 
	 * piece = the piece to fit in
	 * returns true if fit, or false if not
	 */
	public boolean doesFit(int row, int col, Piece piece){
		for(int i = 0; i < 4; i++){
			if(!sideFit(row, col, i, piece)) return false;
		}
		return true;
	}
<<<<<<< HEAD
	
	/* to check if puzzle is solved, that is, each tile has a piece and is fitted 
	 * returns true if solved, or false if not
	 */
	public boolean isSolved(){	
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if (getPiece(i, j) == null) return false;
				if(!doesFit(i, j, getPiece(i, j))) return false;
			}
		}	
		return true;
	}
		
	/* to set piece into the specified tile of the board and remove the piece from unused list
	 * parameters: row = the row which the piece will be in , col = the column which the piece will be in, 
	 * piece = the piece to set
	 * returns the original piece at the tile of the board
	 */
	public Piece setPiece(int row, int col, Piece piece){
		if(!doesFit(row,col,piece)) return null;
=======

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
>>>>>>> origin/master
		else {
			Piece a = board.getPiece(row, col);
			unused.remove(piece);
			board.setPiece(row, col, piece);
			return a;
		}
	}
	
	/* to get piece at the specified tile of the board 
	 * parameters: row = the row which the piece is in , col = the column which the piece is in, 
	 * returns the piece at the tile of the board
	 */
	public Piece getPiece(int row, int col){
		return board.getPiece(row, col);
	}

	/* to remove a piece from the specified tile of the board and add the piece to unused list
	 * parameters: row = the row which the piece is in , col = the column which the piece is in, 
	 * returns the piece removed
	 */
	public Piece removePiece(int row, int col){
		if(!board.isValid(row, col) || !board.hasPiece(row, col)) return null;
		Piece a = board.getPiece(row, col);
		unused.add(board.removePiece(row, col));
<<<<<<< HEAD
		return a;	
=======
		return a;
>>>>>>> origin/master
	}

	/* to add the piece to unused list
	 * piece = the piece to add
	 */
	public void addPiece(Piece piece){
		unused.add(piece);
	}
<<<<<<< HEAD
	
	/* to remove all pieces on the board to unused list and clear the board
	 * 
	 */
	public void restart(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(board.hasPiece(i, j))
					unused.add(board.getPiece(i, j));
=======

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
>>>>>>> origin/master
			}
			s += "\n";
		}
<<<<<<< HEAD
		board.clear();
=======

		return s;
>>>>>>> origin/master
	}
	
	/* a toString method to convert the puzzle board into printable string format
	 * returns a string value
	 */
	public String toString(){
		return board.toString();
	}
	
	/* to solve the puzzle
	 * 
	 */
	public void solve() {
		restart();
<<<<<<< HEAD
		ArrayList<Piece> unusedPieces = getUnusedPieces();
		permuteToSolve(unusedPieces, unusedPieces.size());
	}
 
	/*Find a permutation of unused pieces (by Heap's algorithm), if the permutation is a solution, done; 
	 *Otherwise, reset board and try next permutation until all permutations exhausted 
	 *parameters: pieces = the unused pieces, size = the number of unused pieces
	 */
	private void permuteToSolve(ArrayList<Piece> pieces, int size) {
		if (isSolved()) return;
		if (size == 1) {
			restart();
			ArrayList<Piece> newlyPermuted = new ArrayList<Piece>();
			newlyPermuted.addAll(pieces);
			solve(0, 0, newlyPermuted);
		} 
		else {
			for (int i = 0; i < size; i++) {
				permuteToSolve(pieces, size-1);
				if (size % 2 == 1) 
					swap(pieces, 0, size-1);
				else 
					swap(pieces, i, size-1);
			}
		}
	}

	/* Swap two pieces in the array list
	 * 
	 */
	private void swap(ArrayList<Piece> pieces, int i, int j) {
		Piece tmp = pieces.get(i);
		pieces.set(i, pieces.get(j));
		pieces.set(j, tmp);
	}
	
	/* Recursively solve row, col in the board to set fitted piece until no fitted piece found or all pieces fitted
	 * parameters: row = the row which the piece to be in, col = the column which the piece to be in,
	 * pieces = current unused pieces
	 */
	private void solve(int row, int col, ArrayList <Piece> pieces){			
	    if(pieces.size() == 0 || row >= rows) return;
		
		//Find a fitted piece to row, col from remaining pieces
		Piece fitPiece = null;
		for (Piece p: pieces){
			for (int direction = 0; direction < 4; direction++) {
				if(doesFit(row, col, p)) {
					fitPiece = p;
					break;
				}
				else 
					p.rotate();
			}
			if (fitPiece != null)
				break;
		}
			
	    //set piece if found fit piece, solve next col or row
		if (fitPiece != null){
			setPiece(row, col, fitPiece);
			if (col == cols - 1){
				row++;
				col = 0;
			}
			else 
				col++;	
			pieces.remove(fitPiece);
			solve(row, col, pieces);
=======
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
>>>>>>> origin/master
		}
		return false;
	}

<<<<<<< HEAD
/*	public void solve(){
		restart();
		solve(0, 0);
	}

	private boolean solve(int row, int col){
		if(isSolved()) return true;
		if(board.isValid(row, col) == false) return true;
		if(board.hasPiece(row, col)) return true;
		boolean a = false;
		boolean b = false;
		Piece[] unusedCopy = new Piece[unused.size()];
		for(int i = 0; i < unusedCopy.length; i++) unusedCopy[i] = unused.get(i);
		for(int i = 0; i < unusedCopy.length; i++){
			for(int j = 0; j < 3; j++){
				unusedCopy[i].rotate();
				if(doesFit(row, col, unusedCopy[i])){			
					setPiece(row, col, unusedCopy[i]);
					a = solve(row + 1, col);
					b = solve(row, col + 1);
			//		System.out.println(board.toString());
					if(a && b) return true;
				}
			}
			clearBranch(row, col);
		}
		return false;
=======
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
>>>>>>> origin/master
	}
	
	private void clearBranch(int row, int col){
		for(int i = row; i < rows; i++)
			for(int j = col; j < cols; j++)
				removePiece(i, j);
	}
*/
	//main method
	public static void main(String[] args){
		
		//Test my puzzle pieces
		System.out.println("-------------------Test my pieces---------------------");
		Piece[] myPieces = new Piece[3*3];
		myPieces[0] = new Piece(Piece.HEARTS_IN, Piece.CLUBS_IN, Piece.HEARTS_OUT, Piece.DIAMONDS_OUT);
		myPieces[1] = new Piece(Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.SPADES_IN, Piece.CLUBS_OUT);
		myPieces[2] = new Piece(Piece.SPADES_OUT, Piece.CLUBS_OUT, Piece.DIAMONDS_IN, Piece.HEARTS_IN);
		myPieces[3] = new Piece(Piece.HEARTS_IN, Piece.DIAMONDS_IN, Piece.SPADES_IN, Piece.HEARTS_IN);
	    myPieces[4] = new Piece(Piece.HEARTS_IN, Piece.DIAMONDS_IN, Piece.HEARTS_IN, Piece.CLUBS_IN);
		myPieces[5] = new Piece(Piece.SPADES_OUT, Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.DIAMONDS_OUT);
		myPieces[6] = new Piece(Piece.DIAMONDS_OUT, Piece.SPADES_IN, Piece.HEARTS_IN, Piece.HEARTS_IN);
		myPieces[7] = new Piece(Piece.HEARTS_OUT, Piece.CLUBS_OUT, Piece.HEARTS_IN, Piece.SPADES_OUT);
		myPieces[8] = new Piece(Piece.HEARTS_OUT, Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.CLUBS_IN);		
		Puzzle myPuzzle = new Puzzle(3, 3, myPieces);
		myPuzzle.setPiece(1, 1, myPieces[5]);
		myPuzzle.setPiece(2, 1, myPieces[8]);
		if (myPuzzle.isSolved())
			System.out.println("My puzzle is already solved");
		else 
			System.out.println("My puzzle is unsolved yet");
		System.out.println(myPuzzle.toString());
		myPuzzle.solve();
		System.out.println(myPuzzle.toString());
		//Modify my Puzzle pieces so it has no solution
	//	System.out.println("----Test a puzzle which has no solution----");
	//	myPieces[4] = new Piece(Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.HEARTS_IN, Piece.HEARTS_IN);
	//	Puzzle myPuzzleB = new Puzzle(3, 3, myPieces);
	//	myPuzzleB.solve();
	//	System.out.println(myPuzzleB.toString());
		
		//Test Mr. Marshall's pieces
		System.out.println("----------------Test Mr. Marshall's pieces--------------");
		Piece[] pieces = new Piece[3*3];
		pieces[0] = new Piece(Piece.CLUBS_OUT, Piece.HEARTS_OUT, Piece.DIAMONDS_IN, Piece.CLUBS_IN);
		pieces[1] = new Piece(Piece.SPADES_OUT, Piece.DIAMONDS_OUT, Piece.SPADES_IN, Piece.HEARTS_IN);
		pieces[2] = new Piece(Piece.HEARTS_OUT, Piece.SPADES_OUT, Piece.SPADES_IN, Piece.CLUBS_IN);
		pieces[3] = new Piece(Piece.HEARTS_OUT, Piece.DIAMONDS_OUT, Piece.CLUBS_IN, Piece.CLUBS_IN);
	    pieces[4] = new Piece(Piece.SPADES_OUT, Piece.SPADES_OUT, Piece.HEARTS_IN, Piece.CLUBS_IN);
		pieces[5] = new Piece(Piece.HEARTS_OUT, Piece.DIAMONDS_OUT, Piece.DIAMONDS_IN, Piece.HEARTS_IN);
		pieces[6] = new Piece(Piece.SPADES_OUT, Piece.DIAMONDS_OUT, Piece.HEARTS_IN, Piece.DIAMONDS_IN);
		pieces[7] = new Piece(Piece.CLUBS_OUT, Piece.HEARTS_OUT, Piece.SPADES_IN, Piece.HEARTS_IN);
		pieces[8] = new Piece(Piece.CLUBS_OUT, Piece.CLUBS_IN, Piece.DIAMONDS_IN, Piece.DIAMONDS_OUT);
		Puzzle tPuzzle = new Puzzle(3, 3, pieces);
		tPuzzle.solve();
		System.out.println(tPuzzle.toString());
	}
}


