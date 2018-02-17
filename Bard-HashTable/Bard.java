/**
 * Bard.java
 * Purpose:
   Analyzes words of all compositions of Shakespeare
   Given input of word length and frequency,
   find corresponding word from shakespeare.txt
 * Compile: make
 * Run: java -jar Bard.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 12/05/2017
 */

import java.io.*;
import java.util.*;

public class Bard {
	public static void main(String[] args) throws IOException {
			
		//Extract test input file
		// check # of command line arguments is at least 2
		if (args.length < 2) {
			System.out.println("Usage: java -jar Bard.jar <input file> <output file>");
			System.exit(1);
		}
			
		//open files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));
			
		FileInputStream fileStrm = new FileInputStream("shakespeare.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fileStrm));
		String fileLine;
		Hashtable<String, Integer> hashT = new Hashtable<String, Integer>();
		//replace special characters with white space
		while ((fileLine = br.readLine()) != null) {
			fileLine = fileLine.replace("?"," ");
			fileLine = fileLine.replace(","," ");
			fileLine = fileLine.replace("."," ");
			fileLine = fileLine.replace("!"," ");
			fileLine = fileLine.replace(":"," ");
			fileLine = fileLine.replace(";"," ");
			fileLine = fileLine.replace("[", " ");
			fileLine = fileLine.replace("]", " ");
			fileLine = fileLine.replace("\"", " ");
			//split words by white space
			String[] tokens = fileLine.trim().split("\\s+");
			for (int i = 0; i < tokens.length; i++) {
				tokens[i] = tokens[i].toLowerCase();
				//insert into hash table
				if (hashT.get(tokens[i]) == null) {
					hashT.put(tokens[i], 1);
				}
				else {
					hashT.put(tokens[i], hashT.get(tokens[i])+1);
				}
			}
		}
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] lineParts = line.split(" ", 2);
			int length = Integer.parseInt(lineParts[0]);
			int freq = Integer.parseInt(lineParts[1]);
			
			ArrayList<WordObj> arrList = new ArrayList<>();
			
			Set<String> hashKeys = hashT.keySet();
			Iterator<String> itr = hashKeys.iterator();
			
			//put words of given length into arraylist
			while (itr.hasNext()) {
				String wd = itr.next();
				if (wd.length() == length) {
					WordObj addWord = new WordObj(wd, hashT.get(wd));
					arrList.add(addWord);
				}
			}
			if (arrList.isEmpty()) {
				out.println("-");
			}
			else {		
				Collections.sort(arrList, new WordObj());
				if (freq < arrList.size()) {
					out.println((arrList.get(freq)).word);
				}
				else {
					out.println("-");
				}
			}
		}
		in.close();
		out.close();
		br.close();
	}
}