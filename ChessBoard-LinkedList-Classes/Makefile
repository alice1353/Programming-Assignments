# Makefile
# Purpose: A Makefile with macros
# Creates ChessBoard.jar file
# 10/23/2017

JAVASRC    = ChessBoard.java ChessPiece.java Queen.java King.java Rook.java Bishop.java Knight.java Pawn.java LinkedList.java Node.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessBoard
CLASSES    = ChessBoard.class ChessPiece.class Queen.class LinkedList.class Node.class King.class Rook.class Bishop.class Knight.class Pawn.class
JARFILE    = ChessBoard.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)