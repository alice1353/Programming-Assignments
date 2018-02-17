/**
 * LinkedList.java
 * Purpose:
   Creates linked list to store pieces
   insert piece, find piece, and checking
   if query piece is atttacking another piece
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

public class LinkedList {
	Node head;
	
	//constructor
	LinkedList() {
		head = null;
	}
	
	//inserts chess piece
	public void insert (ChessPiece newPiece) {
		Node latest = new Node(newPiece);
		latest.next = head;
		head = latest;
	}
	
	//looks for query piece by traversing through linked list
	public ChessPiece find (int findCol, int findRow) {
		Node current = head;
		ChessPiece queryPiece;
		while (current != null) {
			if (findCol == current.piece.col && findRow == current.piece.row) {
				queryPiece = new ChessPiece(current.piece.type, findCol, findRow);
				return queryPiece;
			}
			else {
				current = current.next;
			}
		}
		queryPiece = null;
		return queryPiece;
	}
	
	//check if query piece is attacking another piece
	public boolean attacking(ChessPiece p) {
		Node current = head;
		while (current != null) {
			if (Character.isUpperCase(p.type) != Character.isUpperCase(current.piece.type)) {
				if (p.type == 'Q' || p.type == 'q') {
					ChessPiece queen = new Queen(p.type, p.col, p.row);
					if (queen.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
				else if (p.type == 'K' || p.type == 'k') {
					ChessPiece king = new King(p.type, p.col, p.row);
					if (king.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
				else if (p.type == 'R' || p.type == 'r') {
					ChessPiece rook = new Rook(p.type, p.col, p.row);
					if (rook.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
				else if (p.type == 'B' || p.type == 'b') {
					ChessPiece bishop = new Bishop(p.type, p.col, p.row);
					if (bishop.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
				else if (p.type == 'N' || p.type == 'n') {
					ChessPiece knight = new Knight(p.type, p.col, p.row);
					if (knight.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
				else if (p.type == 'P' || p.type == 'p') {
					ChessPiece pawn = new Pawn(p.type, p.col, p.row);
					if (pawn.isAttacking(current.piece) == true) {
						return true;
					}
					else {
						current = current.next;
					}
				}
			}
			else {
				current = current.next;
			}
		}
		return false;
	}
	
	//prints linked list for checking
	/*
	public void printList() {
		Node current = head;
		while (current != null) {
			System.out.printf("%s %d %d", current.piece.type, current.piece.col, current.piece.row);
			System.out.println();
			current = current.next;
		}
	}
	*/
}
