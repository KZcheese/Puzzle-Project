/* Board Class 
 * 3/31/15
 */

public class Board {

	private int rows;
	private int cols; 
	private Piece[][] board;

	public Board(int rows, int cols){
		this.rows = rows;
		this.cols = cols;
		board = new Piece[rows][cols];
	}

	public Board(int sides){
		rows = sides;
		cols = sides;
		board = new Piece[sides][sides];
	}

	public int getRows(){
		return rows;
	}

	public int getCols(){
		return cols;
	}

	public Piece setPiece(int row, int col, Piece piece){
		Piece old = board[row][col];
		board[row][col] = piece;
		return old;
	}

	public Piece removePiece(int row, int col){
		Piece a = board[row][col];
		board[row][col] = null;
		return a;
	}

	public Piece getPiece(int row, int col){
		return board[row][col];
	}

	public boolean hasPiece(int row, int col){
		if(board[row][col] == null) return false;
		return true;
	}


	public boolean isValid(int row, int col){
		if(row >= 0 && col >= 0)
			if(row < rows && col < cols) 
				return true; 
		return false;
	}

	public void clear(){
//		for(int i = 0; i < board.length; i++){
//			for(int j = 0; j < board[0].length; j++){
//				board[i][j] = null;
//
//			}
//		}
		board = new Piece[rows][cols];
	}

	public String toString(){
		String s = "";
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				s += board[i][j] + " ";
			}
			s += "\n";
		}

		return s;
	}

	public static void main(String[] args){
		Board a = new Board(5,3);
		System.out.println(a.isValid(0, 2));
		System.out.println(a.hasPiece(2, 3));
		System.out.println(a.removePiece(1, 1));
		
		
		
		/*	System.out.println(a.toString());
		Piece b = new Piece(1,2,3,4);
		Piece c = new Piece(3,4,5,2);
		a.setPiece(1,3,b);
		a.setPiece(3,1,c);
		System.out.println(a.toString());
		a.removePiece(1,2);
		System.out.println(a.toString());
		a.clear();
		System.out.println(a.toString());
		System.out.println(a.hasPiece(2,1));
		 */

	}






}