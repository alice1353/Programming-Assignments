/**
 * WordObj.java
 * Purpose:
   Creates objects for words
 * Compile: make
 * Run: java -jar Bar.jar <input file> <output file>
 * Author: Zifei (Alice) Lu
   alicelu625@gmail.com
 * 12/05/2017
 */

import java.util.Comparator;

public class WordObj implements Comparator<WordObj>, Comparable<WordObj> {
	public String word;
	public int frequency;
	
	WordObj() {
		
	}
	
	WordObj(String w, int fr) {
		word = w;
		frequency = fr;
	}

	@Override
	public int compareTo(WordObj wd) {
		return (this.word).compareTo(wd.word);
	}

	@Override
	public int compare(WordObj o1, WordObj o2) {
		int cmpWd = o2.frequency - o1.frequency;
	  	if (o1.frequency == o2.frequency && o1.word.length() == o2.word.length()) {
	  		cmpWd = o1.compareTo(o2);
	  	}
		return cmpWd;
	}
}
