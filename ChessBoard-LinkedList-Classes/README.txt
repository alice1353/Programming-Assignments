Zifei (Alice) Lu
alicelu625@gmail.com
10/29/2017
==============================
Source files:
	ChessBoard.java
	ChessPiece.java
	Queen.java
	King.java
	Rook.java
	Bishop.java
	Knight.java
	Pawn.java
	LinkedList.java
	Node.java
Other files:
	Makefile
	test-input.txt
	test-output.txt
==============================
Compile: make
Run: java -jar ChessBoard.jar <input file> <output file>
==============================
Purpose:
Store locations of given chess pieces in a linked list.
Find query piece given in input file and check conditions.
==============================
Input file format:
queryPieceX queryPieceY: chessPieceType locationX locationY ...
==============================
Ouput file format:
if 2 chess pieces are placed in the same location, then output
	Invalid
if queryPiece is not on the chessboard, then output
	-
if queryPiece is on the chessboard and is attacking another piece, then output
	queryPieceType y
if queryPiece is on the chessboard but is not attacking any pieces, then output
	queryPieceType n
