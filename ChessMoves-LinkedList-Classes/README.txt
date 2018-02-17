Zifei (Alice) Lu
alicelu625@gmail.com
11/14/2017
==============================
Source files:
	ChessMoves.java
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
	simple-input.txt
	simple-output.txt
========================================================
Compile: make
Run: java -jar ChessMoves.jar <input file> <output file>
========================================================
Purpose:
Store locations of given chess pieces in a linked list.
Implement given moves and determine if the king is under attack.
========================================================
Input file format:
chessPieceType locationX locationY ... : moveFromX moveFromY moveToX moveToY ...
========================================================
Output file format:
If all the moves are legal, then output
	legal
If one move is illegal, then output the illegal move
	moveFromX moveFromY moveToX moveToY illegal
Chess move is illegal if:
	1. There is no piece at given moveFrom coordinates.
	2. Sequence of move is not correct (white moves first then black).
	3. Moving to location where chess piece is of the same color.
	4. The move is invalid (rules of how each chess piece type moves).
	5. The moving path is blocked by a chesspiece.
	6. By the end of the move, king piece (of the same color) is under attack.
