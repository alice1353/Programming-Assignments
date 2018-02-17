/**
 * FileReverse.java
 * Purpose:
   Reads lines of input and prints tokens backward to output file
 * Author: Some codes obtained from Lab 2 instructions file FileTokens.java
 * Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/5/2017
 */

import java.io.*;
import java.util.Scanner;

class FileReverse {
	public static void main(String[] args) throws IOException {
		
		int lineNumber = 0;
		
		//check number of command line arguments is at least 2
		if (args.length < 2) {
			System.out.println("Usage: java -jar FileReverse.jar <input file> <output file>");
			System.exit(1);
		}
		
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
		
		//read lines from in, extract & print tokens from each line
		while (in.hasNextLine()) {
			lineNumber++;
			//trim leading & trailing spaces, then add one trailing space so split works on blank lines
			String line = in.nextLine().trim() + " ";
			//split line around white spaces
			String[] token = line.split("\\s+");
			//print out tokens
			int n = token.length;
			for (int i = 0; i < n; i++) {
				out.println(stringReverse(token[i]));
				//out.println(token[i]);
			}
		}
		
		//close files
		in.close();
		out.close();
	}
	
	public static String stringReverse(String s) {
		char[] line = s.toCharArray();
		char temp;
		int start, end = line.length-1;
		for (start = 0; start < end; start++) {
			temp = line[start];
			line[start] = line[end];
			line[end] = temp;
			end--;
		}
		return new String(line);
	}
}
