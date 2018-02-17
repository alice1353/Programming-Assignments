/**
 * Knight.java
 * Purpose:
   Sub class of ChessPiece class
 * Compile: make
 * Run: java -jar ChessMoves.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/14/2017
 */

public class Knight extends ChessPiece {
	
	Knight(char type, int col, int row) {
		super(type, col, row);
	}
	
	//checks all squares around piece
	public boolean isAttacking(ChessPiece p) {
		//top right
		if ((this.col == p.col-1 && this.row == p.row-2) || (this.col == p.col-2 && this.row == p.row-1)) {
				return true;
		}
		
		//top left
		else if ((this.col == p.col+1 && this.row == p.row-2) || (this.col == p.col+2 && this.row == p.row-1)) {
			return true;
		}
		
		//bottom right
		else if ((this.col == p.col+1 && this.row == p.row+2) || (this.col == p.col+2 && this.row == p.row+1)) {
			return true;
		}
		
		//bottom left
		else if ((this.col == p.col-1 && this.row == p.row+2) || (this.col == p.col-2 && this.row == p.row+1)) {
			return true;
		}
		
		else {
			return false;
		}
		
	}
	
	public boolean pathBlocked(LinkedList l, int x, int y) {
		return false;
	}
}