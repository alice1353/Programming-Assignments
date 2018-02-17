/**
 * Rook.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class Rook extends ChessPiece {
	
	Rook(char type, int col, int row) {
		super(type, col, row);
	}
	
	
	//checks row & column
	public boolean isAttacking(ChessPiece p) {
		if (this.col == p.col) {
				return true;
		}
		if (this.row == p.row) {
				return true;
		}

		return false;
	}
		
}