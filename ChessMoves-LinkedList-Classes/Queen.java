/**
 * Queen.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessMoves.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/29/2017
 */

public class Queen extends ChessPiece {
	
	Queen(char type, int col, int row) {
		super(type, col, row);
	}
	
	
	//checks row, column, and diagonal
	public boolean isAttacking(ChessPiece p) {
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
	
	//checks if path is blocked
	public boolean pathBlocked(LinkedList l, int x , int y) {
		int xCol = this.col;
		int yRow = this.row;
		//bottom left diagonal direction
		if (this.col > x && this.row > y) {
			while (this.col != x+1 && this.row != y+1) {
				if (l.find(this.col-1, this.row-1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col--;
					this.row--;
				}
			}
			//resets position of piece if path is free
			this.col = xCol;
			this.row = yRow;
		}
		//top right diagonal direction
		else if (this.col < x && this.row < y) {
			while (this.col != x-1 && this.row != y-1) {
				if (l.find(this.col+1, this.row+1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col++;
					this.row++;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
		//top left diagonal direction
		else if (this.col > x && this.row < y) {
			while (this.col != x+1 && this.row != y-1) {
				if (l.find(this.col-1, this.row+1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col--;
					this.row++;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
		//bottom right diagonal direction
		else if (this.col < x && this.row > y) {
			while (this.col != x-1 && this.row != y+1) {
				if (l.find(this.col+1, this.row-1) != null) {
					this.col = xCol;
					this.row = yRow;
					return true;
				}
				else {
					this.col++;
					this.row--;
				}
			}
			this.col = xCol;
			this.row = yRow;
		}
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
