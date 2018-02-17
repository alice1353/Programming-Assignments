/**
 * Bishop.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class Bishop extends ChessPiece {
	
	Bishop(char type, int col, int row) {
		super(type, col, row);
	}
	
	
	//checks diagonal
	public boolean isAttacking(ChessPiece p) {
		if (this.col != 0 && this.row != 0) {
			double slope = (double)(this.col - p.col)/(this.row - p.row);
			if (slope == 1 || slope == -1) {
				return true;
			}
		}
		return false;
	}
		
}
