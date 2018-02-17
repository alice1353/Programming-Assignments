/**
 * Queen.java
 * Purpose:
   Defines Queen objects
 * Compile: make
 * Run: java -jar NQueens.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/26/2017
 */

public class Queen {
	public int col;
	public int row;
	
	Queen(int Qcol, int Qrow) {
		col = Qcol;
		row = Qrow;
	}
	
	
	//checks row, column, and diagonal
	public boolean isAttacking(Queen p) {
		if (this.col == p.col) {
				return true;
		}
		if (this.row == p.row) {
				return true;
		}
		if (this.col != 0 && this.row != 0) {
			double slope = (double)(this.col - p.col)/(this.row - p.row);
			if (slope == 1 || slope == -1) {
				return true;
			}
		}
		return false;
	}
}
