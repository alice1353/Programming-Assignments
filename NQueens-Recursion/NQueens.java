/**
 * NQueens.java
 * Purpose:
   Place n Queens on an nxn chessboard
   such that no 2 queens can attack each other
 * Compile: make
 * Run: java -jar NQueens.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@ucsc.edu
 * 10/15/2017
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class NQueens {
	public static int size;
	public static int index;
	
	//Global variables for storing placed queens' locations
	public static int[] qCol =  new int[size];
	public static int[] qRow =  new int[size];
	
	public static void main(String[] args) throws IOException{
		//Extract test input file
		// check # of command line arguments is at least 2
		if (args.length < 2) {
			System.out.println("Usage: java –jar NQueens.jar <input file> <output file>");
			System.exit(1);
		}
		
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		//read input of size and position of 1st queen
		while (in.hasNextInt()) {
			size = in.nextInt();
			qCol = new int[size];
			qRow = new int[size];
			qCol[0] = in.nextInt();
			qRow[0] = in.nextInt();
			
			index = 0;
			NQueens(size);
			
			//print solution to output file
			if (qCol[size-1] == 0) {
				out.println("No solution");
			}
			else {
				sortSolution();
				for (int j = 0; j < qCol.length; j++) {
					out.print(qCol[j] + " " + qRow[j] + " ");
				}
				out.println();
			}
		}
		
		in.close();
		out.close();
	}
	
	//sorts solution in order of columns (bubble sort)
	static void sortSolution() {
		int start, end = qCol.length-1;
		for (start = 0; start < end; start++) {
			for (int i = end; i > start; i--) {
				//comparing 2 values: swap value in front if smaller 
				if (qCol[i] < qCol[i-1]) {
					int temp = qCol[i];
					int temp2 = qRow[i];
					qCol[i] = qCol[i-1];
					qRow[i] = qRow[i-1];
					qCol[i-1] = temp;
					qRow[i-1] = temp2;
				}
			}
		}
	}
	
	//Solving nQueens using recursions
	static boolean NQueens(int n) {
		index++;
		//base case
		if (n == 0) {
			return true;
		}
		
		if (n == qCol[0]) {
			index--;
			return NQueens(n-1);
		}
		
		for(int i = 1; i <= size; i++) {
			//if isAttaking is false --> place queen
			//if true --> test another spot
			if (isAttacking(n, i) == false) {
					qCol[index] = n;
					qRow[index] = i;
					
				if (NQueens(n-1) == true) {
					return true;
				}
				//empty position of last queen
				index--;
				qCol[index] = 0;
				qRow[index] = 0;
			}
		}
		return false;
	}
	
	//checks row, column, and diagonal
	static boolean isAttacking(int x, int y) {
		for (int i = 0; i < size; i++) {
			if (qCol[i] == x) {
				return true;
			}
			if (qRow[i] == y) {
				return true;
			}
			if (qCol[i] != 0 && qRow[i] != 0) {
				double slope = (double)(qCol[i] - x)/(qRow[i] - y);
				if (slope == 1 || slope == -1) {
					return true;
				}
			}
		}
		return false;
	}
}