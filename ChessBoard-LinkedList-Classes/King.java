/**
 * King.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class King extends ChessPiece {
	
	King(char type, int col, int row) {
		super(type, col, row);
	}
	
	
	//checks all squares around piece
	public boolean isAttacking(ChessPiece p) {
		//left & right
		if ((this.col == p.col+1 && this.row == p.row) || (this.col == p.col-1 && this.row == p.row)) {
				return true;
		}
		//top & bottom
		if ((this.row == p.row+1 && this.col == p.col) || (this.row == p.row-1 && this.col == p.col)) {
				return true;
		}
		
		//right diagonals
		if ((this.row == p.row-1 && this.col == p.col-1) || (this.row == p.row+1 && this.col == p.col-1)) {
			return true;
		}
		
		//left diagonals
		if ((this.row == p.row+1 && this.col == p.col+1) || (this.row == p.row+1 && this.col == p.col+1)) {
			return true;
		}
		
		return false;
	}
		
}