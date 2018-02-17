/**
 * NQueens.java
 * Purpose:
   Place n Queens on an nxn chessboard
   such that no 2 queens can attack each other
   using stacks
 * Compile: make
 * Run: java -jar NQueens.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/26/2017
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class NQueens {
	
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
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] lineParts = line.split(" ", 2); //split board size & queen positions
			
			int size = Integer.parseInt(lineParts[0]);
			
			String[] splitNumC = lineParts[1].split(" ");
			int[] splitNum = new int[splitNumC.length];
			for (int k = 0; k < splitNum.length; k++) {
				splitNum[k] = Integer.parseInt(splitNumC[k]);
			}
			
			//create arrays for col and row to store given queen positions
			int[] pieceCol = new int[splitNum.length/2];
			int[] pieceRow = new int[splitNum.length/2];
			
			//define arrays
			int index = 0;
			int i = 0;
			while (i < splitNum.length-1) {
				pieceCol[index] = splitNum[i];
				pieceRow[index] = splitNum[i+1];
				index++;
				i = i + 2;
			}
			
			int sizeofGiven = pieceCol.length;
			
			//push given queens onto stack
			Stack<Queen> queens = new Stack<Queen>();
			for (int k = 0; k < pieceCol.length; k++) {
				Queen q = new Queen(pieceCol[k], pieceRow[k]);
				queens.push(q);
			}
			int sizeofStack = sizeofGiven;
			
			if (NQueens(size, sizeofGiven, queens, sizeofStack) == true) {
				Queen[] output = new Queen[size];
				for (int o = 0; o < size; o++) {
					Queen outQueen = queens.pop();
					output[o] = outQueen;
				}
				sortSolution(output);
				for (int h = 0; h < output.length; h++) {
					out.print(output[h].col + " " + output[h].row + " ");
				}
				out.println();
			}
			else {
				out.println("No solution");
			}
		}
		
		in.close();
		out.close();
	}
	
	//sort queens by col
	static Queen[] sortSolution(Queen[] output) {
		int start, end = output.length-1;
		for (start = 0; start < end; start++) {
			for (int i = end; i > start; i--) {
				//comparing 2 values: swap value in front if smaller 
				if (output[i].col < output[i-1].col) {
					Queen temp = output[i];
					output[i] = output[i-1];
					output[i-1] = temp;
				}
			}
		}
		return output;
	}
	
	static boolean NQueens(int n, int sizeofP, Stack<Queen> st, int sizeofSt) {
		//check if given queens are attacking each other
		for (int k = 0; k < sizeofSt; k++) {
			Queen checkStack = st.get(k);
			for (int k2 = 0; k2 < sizeofSt; k2++) {
				if (checkStack.col == ((Queen)st.get(k2)).col && checkStack.row == ((Queen)st.get(k2)).row) {
					k2++;
					if (k2 == sizeofSt) {
						break;
					}
				}
				if (((Queen)st.get(k2)).isAttacking(checkStack) == true) {
					return false;
				}
			}
		}
		col_loop:
		for (int i = 1; i <= n; i++) { //col
			row_loop:
			for (int j = 1; j <= n; j++) { //row
				//stop if popped a given piece
				if (sizeofP > sizeofSt) {
					return false;
				}
				//skip column if already a piece exist
				for (int k = 0; k < sizeofSt; k++) {
					if (((Queen)st.get(k)).col == i) {
						i++;
					}
				}
				for (int k = 0; k < sizeofSt; k++) {
					Queen check = new Queen(i, j);
					//if is attacking, move up a row
					if (((Queen)st.get(k)).isAttacking(check) == true && j < n) {
						continue row_loop;
					}
					//if already at top row, then remove last queen
					else if (((Queen)st.get(k)).isAttacking(check) == true && j == n) {
						break; //goes to while loop
					}
					//if not attacking, and already checked every existing piece, place queen
					else if (((Queen)st.get(k)).isAttacking(check) == false && k == sizeofSt-1) {
						Queen print = st.push(check);
						sizeofSt++;
						if (sizeofSt == n) {
							return true;
						}
						else {
							break row_loop;
						}
					}
				}
				
				//if at top row, remove last placed queen
	 			while (j == n) {
	 				Queen fail = st.pop();
	 				i = fail.col;
	 				j = fail.row;
	 				sizeofSt--;
					if (sizeofP > sizeofSt) {
						return false;
					}
	 			}
			}
		}
		return false;
	}
}