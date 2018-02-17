/**
 * Node.java
 * Purpose:
   Node class for linked list
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class Node {
	ChessPiece piece;
	Node next;
	
	//constructor
	Node(ChessPiece aPiece) {
		this.piece = aPiece;
		this.next = null;
	}
}
