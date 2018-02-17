/**
 * ChessMoves.java
 * Purpose:
   Store locations of given chess pieces in
   a linked list, implement moves, and determine
   if the king is under attack
 * Compile: make
 * Run: java -jar ChessMoves.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/29/2017
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChessMoves {
	public static void main(String[] args) throws IOException{
		
		//Extract test input file
		// check # of command line arguments is at least 2
		if (args.length < 2) {
			System.out.println("Usage: java -jar ChessBoard.jar <input file> <output file>");
			System.exit(1);
		}
		
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		//create LinkedList object
		LinkedList list;
		
		//scan in lines
		while (in.hasNextLine()) {
			list = new LinkedList();
			String line = in.nextLine();
			line = line.replaceAll("\\s",""); //removes all spaces
			String[] lineParts = line.split(":"); //split string on ":"
			char[] lineChar1 = lineParts[0].toCharArray(); //substrings
			char[] lineChar2 = lineParts[1].toCharArray();
			
			//separate type, col, row - declare
			char[] pieceType = new char[lineChar1.length/3];
			char[] pieceColC = new char[lineChar1.length/3];
			char[] pieceRowC = new char[lineChar1.length/3];
			//separate coordinates of moves - declare
			char[] moveFromColC = new char[lineChar2.length/4];
			char[] moveFromRowC = new char[lineChar2.length/4];
			char[] moveToColC = new char[lineChar2.length/4];
			char[] moveToRowC = new char[lineChar2.length/4];
			
			//define arrays
			int index = 0;
			int i = 0;
			while (i < lineChar1.length-1) {
				pieceType[index] = lineChar1[i];
				pieceColC[index] = lineChar1[i+1];
				pieceRowC[index] = lineChar1[i+2];
				index++;
				i = i + 3;
			}

			index = 0;
			i = 0;
			while (i < lineChar2.length-1) {
				moveFromColC[index] = lineChar2[i];
				moveFromRowC[index] = lineChar2[i+1];
				moveToColC[index] = lineChar2[i+2];
				moveToRowC[index] = lineChar2[i+3];
				index++;
				i = i + 4;
			}
			
			//turn char arrays to int arrays
			int[] pieceCol = new int[pieceColC.length];
			int[] pieceRow = new int[pieceRowC.length];
			int[] moveFromCol = new int[moveFromColC.length];
			int[] moveFromRow = new int[moveFromRowC.length];
			int[] moveToCol = new int[moveToColC.length];
			int[] moveToRow = new int[moveToRowC.length];
			
			for (int k = 0; k < pieceColC.length; k++) {
				pieceCol[k] = pieceColC[k] - '0';
				pieceRow[k] = pieceRowC[k] - '0';
			}
			for (int k = 0; k < moveFromColC.length; k++) {
				moveFromCol[k] = moveFromColC[k] - '0';
				moveFromRow[k] = moveFromRowC[k] - '0';
				moveToCol[k] = moveToColC[k] - '0';
				moveToRow[k] = moveToRowC[k] - '0';
			}
			
			//create ChessPiece objects & insert to LinkedList
			for (int j = 0; j < pieceType.length; j++) {
				ChessPiece newPiece = createPiece(list, pieceType[j], pieceCol[j], pieceRow[j]);
				list.insert(newPiece);
			}
			
			//dummy variable
			char moveType = 'B';
			
			//for each move
			for (int j = 0; j < moveFromCol.length; j++) {
				//output for illegal move
				String illegal = moveFromCol[j] + " " + moveFromRow[j] + " " + moveToCol[j] + " " + moveToRow[j] + " illegal";
				//locate fromPiece
				ChessPiece fromPieceN = list.find(moveFromCol[j], moveFromRow[j]);
				//illegal if no piece at source square
				if (fromPieceN == null) {
					out.println(illegal);
					break;
				}
				//create chess piece object for source piece
				ChessPiece fromPiece = createPiece(list, fromPieceN.type, fromPieceN.col, fromPieceN.row);
				
				//locate toPiece
				ChessPiece tempPieceN = list.find(moveToCol[j], moveToRow[j]);
				ChessPiece toPiece;
				//create dummy piece used for checking valid move
				if (tempPieceN == null) {
					toPiece = new Pawn('l', moveToCol[j], moveToRow[j]);
				}
				//create chess piece object for dest square
				else {
					toPiece = createPiece(list, tempPieceN.type, tempPieceN.col, tempPieceN.row);
				}
				
				//variable to check alternating color
				char prevType = moveType;
				moveType = fromPiece.type;
				
				// dummy variable
				ChessPiece tempKing = null;
				
				//if sequence of move is right (alternate colors, white plays first)
				if (Character.isUpperCase(moveType) != Character.isUpperCase(prevType)) {
					//if moveTo is different colored piece or is empty
					if (Character.isUpperCase(fromPiece.type) != Character.isUpperCase(toPiece.type) || tempPieceN == null) {
						//if piece move is valid (pawn can not use isAttacking)
						if (validMove(fromPiece, toPiece) == true) {
							//if path is free
							if (fromPiece.pathBlocked(list, moveToCol[j], moveToRow[j]) == false) {
								//if destination has chess piece, delete
								if (tempPieceN != null) {
									list.delete(toPiece);
								}
								//if destination is empty, move piece there & update	
								ChessPiece updatePiece = createPiece(list, fromPiece.type, moveToCol[j], moveToRow[j]);
								list.delete(fromPiece);
								list.insert(updatePiece);
								// 6 if king is safe (king same color as fromPiece)
								ChessPiece king = createKing(tempKing, fromPieceN.type, list);
								if (list.kingSafe(king, list) == true) {
									if (j == moveFromCol.length-1) {
										out.println("legal");
									}
									continue;
								}
								else if (list.kingSafe(king, list) == false) {
									out.println(illegal);
									break;
								}
							}
							else {
								out.println(illegal);
								break;
							}
						}
						else {
							out.println(illegal);
							break;
						}
					}
					else {
						out.println(illegal);
						break;
					}
				}
				else {
					out.println(illegal);
					break;
				}		
			}
		}
		in.close();
		out.close();
	}
	
	//creates dummy king chess piece used for checking kingSafe
	public static ChessPiece createKing(ChessPiece king, char fromType, LinkedList l) {
		if (Character.isUpperCase(fromType) == true) {
			king = l.findKing('K');
			king = new King(king.type, king.col, king.row);
			return king;
		}
		else {
			king = l.findKing('k');
			king = new King(king.type, king.col, king.row);
			return king;
		}
	}
	
	//checks if chess piece is moving by its rules - with pawn
	public static boolean validMove(ChessPiece from, ChessPiece to) {
		if (from.type == 'P' || from.type == 'p') {
			return from.isMovable(to);
		}
		else if (from.type != 'P' || from.type != 'p') {
			return from.isAttacking(to);
		}
		return false;
	}
	
	//create chess piece object based on piece type
	public static ChessPiece createPiece(LinkedList list, char pieceType, int pieceCol, int pieceRow) {
		if (pieceType == 'Q' || pieceType == 'q') {
			ChessPiece Q = new Queen(pieceType, pieceCol, pieceRow);
			return Q;
		}
		else if (pieceType == 'K' || pieceType == 'k') {
			ChessPiece K = new King(pieceType, pieceCol, pieceRow);
			return K;
		}
		else if (pieceType == 'R' || pieceType == 'r') {
			ChessPiece R = new Rook(pieceType, pieceCol, pieceRow);
			return R;
		}
		else if (pieceType == 'B' || pieceType == 'b') {
			ChessPiece B = new Bishop(pieceType, pieceCol, pieceRow);
			return B;
		}
		else if (pieceType == 'N' || pieceType == 'n') {
			ChessPiece N = new Knight(pieceType, pieceCol, pieceRow);
			return N;
		}
		else if (pieceType == 'P' || pieceType == 'p') {
			ChessPiece P = new Pawn(pieceType, pieceCol, pieceRow);
			return P;
		}
		return null;
	}
}
