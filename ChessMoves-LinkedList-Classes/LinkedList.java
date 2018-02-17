/**
 * LinkedList.java
 * Purpose:
   Creates linked list to store pieces
   insert piece, find piece, checking
   if query piece is attacking another piece,
   find king piece, check if king is safe
 * Compile: make
 * Run: java -jar ChessMoves.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/14/2017
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
	
	//looks for chess piece by traversing through linked list
	public ChessPiece find (int findCol, int findRow) {
		Node current = head;
		ChessPiece findPiece;
		while (current != null) {
			if (findCol == current.piece.col && findRow == current.piece.row) {
				findPiece = new ChessPiece(current.piece.type, findCol, findRow);
				return findPiece;
			}
			else {
				current = current.next;
			}
		}
		findPiece = null;
		return findPiece;
	}
	
	//looks for king piece by traversing through linked list
	public ChessPiece findKing (char findType) {
		Node current = head;
		ChessPiece findPiece;
		while (current != null) {
			if (findType == current.piece.type) {
				findPiece = new King(current.piece.type, current.piece.col, current.piece.row);
				return findPiece;
			}
			else {
				current = current.next;
			}
		}
		findPiece = null;
		return findPiece;
	}
	
	//checks if king is safe by traversing
	public boolean kingSafe (ChessPiece p, LinkedList l) {
		Node current = head;
		while (current != null) {
			boolean colorCheck = Character.isUpperCase(current.piece.type) != Character.isUpperCase(p.type);
			boolean blockedPath = current.piece.pathBlocked(l, p.col, p.row);
			//king not safe if another piece (opposite color & path is free) is attacking king
			if ((current.piece.isAttacking(p) == true) && (colorCheck == true) && (blockedPath == false)) {
				
				return false;
			}
			else {
				current = current.next;
			}
		}
		return true;
	}
	
	//deletes chess piece
	public Node delete(ChessPiece p) {
		Node prev = null;
		Node curr = head;
		while (((curr.piece.col != p.col) || (curr.piece.row != p.row)) && curr != null) {
			prev = curr;
			curr = curr.next;
		}
		if (curr == null) {
			return null;
		}
		if (prev == null) {
			head = head.next;
		}
		else {
			prev.next = curr.next;

		}
		return curr;
	}
	
	//check if piece is attacking another piece
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
	
	//prints chess pieces in linked list - used for checking
	public void printList() {
		Node current = head;
		while (current != null) {
			System.out.printf("%s %d %d", current.piece.type, current.piece.col, current.piece.row);
			System.out.println();
			current = current.next;
		}
	}
}
