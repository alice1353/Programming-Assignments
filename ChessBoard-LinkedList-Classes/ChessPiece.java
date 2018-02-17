/**
 * ChessPiece.java
 * Purpose:
   Super class for chess pieces
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class ChessPiece {
	int col;
	int row;
	char type;
	
	//constructor
	ChessPiece(char Ptype, int Pcol, int Prow) {
		type = Ptype;
		col = Pcol;
		row = Prow;
	}
	
	public boolean isAttacking(ChessPiece p) {
		return false;
	}
}
