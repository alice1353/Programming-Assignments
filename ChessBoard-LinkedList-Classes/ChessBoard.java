/**
 * ChessBoard.java
 * Purpose:
   Store locations of given chess pieces in
   a linked list
 * Compile: make
 * Run: java -jar ChessBoard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/29/2017
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChessBoard {
	
	public static void main(String[] args) throws IOException{
		//Extract test input file
		// check # of command line arguments is at least 2
		if (args.length < 2) {
			System.out.println("Usage: java –jar ChessBoard.jar <input file> <output file>");
			System.exit(1);
		}
		
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		//create LinkedList object
		LinkedList list = new LinkedList();
		
		while (in.hasNextLine()) {
			list = new LinkedList();
			String line = in.nextLine();
			line = line.replaceAll(":", ""); //replaces ":" with a space
			line = line.replaceAll("\\s",""); //removes all spaces
			char[] lineChar = line.toCharArray(); //convert string to character array

			//read in query
			int queryCol = lineChar[0] - '0';
			int queryRow = lineChar[1] - '0';
			
			//separate type, col, row
			char[] pieceType = new char[lineChar.length/3];
			char[] pieceColC = new char[lineChar.length/3];
			char[] pieceRowC = new char[lineChar.length/3];
			int index = 0;
			int i = 2;
			while (i < lineChar.length-1) {
				pieceType[index] = lineChar[i];
				pieceColC[index] = lineChar[i+1];
				pieceRowC[index] = lineChar[i+2];
				index++;
				i = i + 3;
			}
			
			//turn char arrays to int arrays
			int[] pieceCol = new int[pieceColC.length];
			int[] pieceRow = new int[pieceRowC.length];
			for (int k = 0; k < pieceColC.length; k++) {
				pieceCol[k] = pieceColC[k] - '0';
				pieceRow[k] = pieceRowC[k] - '0';
			}
			
			//create ChessPiece objects & insert to LinkedList
			for (int j = 0; j < pieceType.length; j++) {
				if (pieceType[j] == 'Q' || pieceType[j] == 'q') {
					ChessPiece Q = new Queen(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(Q);
				}
				else if (pieceType[j] == 'K' || pieceType[j] == 'k') {
					ChessPiece K = new King(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(K);
				}
				else if (pieceType[j] == 'R' || pieceType[j] == 'r') {
					ChessPiece R = new Rook(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(R);
				}
				else if (pieceType[j] == 'B' || pieceType[j] == 'b') {
					ChessPiece B = new Bishop(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(B);
				}
				else if (pieceType[j] == 'N' || pieceType[j] == 'n') {
					ChessPiece N = new Knight(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(N);
				}
				else if (pieceType[j] == 'P' || pieceType[j] == 'p') {
					ChessPiece P = new Pawn(pieceType[j], pieceCol[j], pieceRow[j]);
					list.insert(P);
				}
			}
			
			//print output
			if (isValid(pieceCol, pieceRow) == false) {
				out.println("Invalid");
			}
			else {
				ChessPiece queryPiece = list.find(queryCol, queryRow);
				if (queryPiece == null) {
					out.println("-");
				}
				else {
					char attackingPiece;
					if (list.attacking(queryPiece) == true) {
						attackingPiece = 'y';
					}
					else {
						attackingPiece = 'n';
					}
					out.println(queryPiece.type + " " + attackingPiece);
				}
			}
		}	

		in.close();
		out.close();
		
	}
	
	//check if 2 chess pieces are in the same square
	public static boolean isValid(int[] pieceCol, int[] pieceRow) {
		for (int i = 0; i < pieceCol.length; i++) {
			for (int j = 0; j < pieceCol.length; j++) {
				if (pieceCol[i] == pieceCol[j] && pieceRow[i] == pieceRow[j]) {
					if (i != j) {
						return false;
					}
				}
				else {
					continue;
				}
			}
		}
		return true;
	}
}
