# Makefile
# Purpose: A Makefile with macros
# Creates ChessMoves.jar file
# 11/14/2017

JAVASRC    = ChessMoves.java ChessPiece.java Queen.java King.java Rook.java Bishop.java Knight.java Pawn.java LinkedList.java Node.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessMoves
CLASSES    = ChessMoves.class ChessPiece.class Queen.class King.class Rook.class Bishop.class Knight.class Pawn.class LinkedList.class Node.class 
JARFILE    = ChessMoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)