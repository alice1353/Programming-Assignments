/**
 * Rook.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessMoves.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/29/2017
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
	
	//checks if path is blocked
	public boolean pathBlocked(LinkedList l, int x, int y) {
		int xCol = this.col;
		int yRow = this.row;
		//top direction
		if (this.col == x && this.row < y) {
			while (this.row != y-1) {
				if (l.find(this.col, this.row+1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.row++;
				}
			}
			//resets position of piece if path is free
			this.col = xCol;
			this.row = yRow;
		}
		//bottom direction
		else if (this.col == x && this.row > y) {
			while (this.row != y+1) {
				if (l.find(this.col, this.row-1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.row--;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
		//right direction
		else if (this.col < x && this.row == y) {
			while (this.col != x-1) {
				if (l.find(this.col+1, this.row) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col++;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
		//left direction
		else if (this.col > x && this.row == y) {
			while (this.col != x+1) {
				if (l.find(this.col-1, this.row) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col--;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
		return false;
	}	
}