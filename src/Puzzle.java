import java.util.ArrayList;

public class Puzzle {

	private Board board;
	private int rows;
	private int cols;
	private ArrayList<Piece> unused;
	private boolean solved = false;


	public Puzzle(int rows, int cols){
		board = new Board(rows, cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
	}

	public Puzzle(int rows, int cols, Piece[] pieces){
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
		for(int i = 0; i < pieces.length; i++){
			unused.add(pieces[i]);
		}
	}

	public Puzzle(Board board){
		
		this.board = board;
		rows = board.getRows();
		cols = board.getCols();
		unused = new ArrayList<Piece>();
	}
	
 //Constructs a puzzle with specified rows and cols 
//and set of pieces to be used. Remember to copy the pieces into a new array

	public Puzzle(int rows, int cols, ArrayList<Piece> startingPieces){
		board = new Board(rows,cols);
		this.rows = rows;
		this.cols = cols;
		unused = new ArrayList<Piece>();
		
	}

	public int getRows(){
		return rows;
	}

	public int getCols(){
		return cols;
	}

	public boolean matchEdge(Piece piece1, Piece piece2, int directionFrom1to2){
		int directionFrom2to1;
		if(directionFrom1to2 < 2) 
			directionFrom2to1 = directionFrom1to2 + 2;
		else 
			directionFrom2to1 = directionFrom1to2 - 2;
		if(piece1.getEdge(directionFrom1to2) + piece2.getEdge(directionFrom2to1) == 0) return true;
		
		return false;
	}


	public boolean doesFit(int row, int col, Piece piece){
		for(int i = 0; i < 4; i++){
			if(!(sideFit(row, col, i, piece))) return false;
		}
		return true;
	}

	private boolean sideFit(int row, int col, int direction, Piece piece){
		if(direction == Piece.NORTH) row --;
		if(direction == Piece.EAST) col ++;
		if(direction == Piece.SOUTH) row ++;
		if(direction == Piece.WEST) col--;
		if(board.isValid(row, col) == true){
			if(board.hasPiece(row, col) == true){
				if(!(matchEdge(board.getPiece(row, col), piece, 0))) return false;
			}
		}
		return true;
	}

	public boolean isSolved(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++)
				if(!doesFit(i, j, board.getPiece(i, j))) return false;
		}
		solved = true;
		return true;
	}



	public Piece setPiece(int row, int col, Piece piece){
		if(!doesFit(row,col,piece)) return null;

		else{
			Piece a = board.getPiece(row, col);
			board.setPiece(row, col, piece);
			return a;
		}
	}

	public Piece getPiece(int row, int col){
		return board.getPiece(row, col);
	}

	public Piece removePiece(int row, int col){
		Piece a = board.getPiece(row, col);
		unused.add(board.removePiece(row, col));
		return a;

	}

	public void addPiece(Piece piece){
		unused.add(piece);
	}
	
	public ArrayList<Piece> getUnusedPieces(){
		ArrayList<Piece> a = new ArrayList<Piece>();
		for(int i = 0; i < unused.size(); i++) a.add(unused.get(i));
		return a;
	}

	public void restart(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(board.hasPiece(i, j) == true)
					unused.add(board.getPiece(i, j));
			}
		}
		solved = false;
		board.clear();
	}
	
	

	public void solve() {
		restart();
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		pieces.addAll(unused);
		solve (pieces, pieces.size());
		if (solved)
			System.out.println("Solved!");
		else 
			System.out.println("Sorry, no solution!");
	}
 
	//Find a permutation of unused pieces, if the permutation is a solution, done; 
	//Otherwise, reset board and try next permutation until all permutations exhausted 
	private void solve(ArrayList<Piece> pieces, int size) {
		if (solved) return;
		if (size == 1) {
			ArrayList<Piece> permuted = new ArrayList<Piece>();
			permuted.addAll(pieces);
			solve(1, 1, permuted);
			if (!isSolved()) 
				restart();
		} 
		else {
			for (int i = 0; i < size; i++) {
				solve(pieces, size-1);
				if (size % 2 == 1) 
					swap(pieces, 0, size-1);
				else 
					swap(pieces, i, size-1);
			}
		}
	}

	private void swap(ArrayList<Piece> pieces, int i, int j) {
		Piece tmp = pieces.get(i);
		pieces.set(i, pieces.get(j));
		pieces.set(j, tmp);
	}
	
	//Recursively move across row, col to set fitted piece until no fitted piece
	private void solve(int row, int col, ArrayList <Piece> pieces){			
	    if (pieces.size() == 0) return;
		
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
			
	    //set piece if found fit piece, solve next col, row
		if (fitPiece != null){
			setPiece(row, col, fitPiece);
			if (col == cols){
				row++;
				col = 1;
			}
			else 
				col++;	
			pieces.remove(fitPiece);
			solve(row, col, pieces);
		}
	}

	
	public String toString(){
		String s = "";
		for(int i = 1; i <= board.getRows(); i++){
			for(int j = 1; j <= board.getCols(); j++){
				s += board.getPiece(i, j) + " ";
			}
			s += "\n";
		}

		return s;
	}


	public static void main(String[] args){

		





	}


}



