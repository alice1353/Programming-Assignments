/**
 * fileReverse.c
 * Purpose:
   Reads lines of input and prints tokens backward to output file
 * Zifei (Alice) Lu
   alicelu625@gmail.com
 * 10/21/2017
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void stringReverse(char* s);

int main(int argc, char* argv[]) {
	FILE* in; //file handle for input
	FILE* out; //file handle for output
	char word[256]; //char array to store words from input file

	//check command line for correct number of arguments
	if (argc != 3) {
		printf("Usage: %s <input file> <output file> \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	//open input file for reading
	in = fopen(argv[1], "r");
	if (in == NULL) {
		printf("Unable to read from file %s \n", argv[1]);
		exit(EXIT_FAILURE);
	}

	//open output file for writing
	out = fopen(argv[2], "w");
	if (out == NULL) {
		printf("Unable to write to file %s \n", argv[2]);
		exit(EXIT_FAILURE);
	}

	//read words from input file, print on separate lines reversed to outputfile
	while (fscanf(in, " %s", word) != EOF) {
		stringReverse(word);
		fprintf(out, "%s\n", word);
	}

	//close input & output files
	fclose(in);
	fclose(out);
}


//reverses word
void stringReverse(char *s) {
	char temp;
	int start, end = strlen(s)-1;
	for (start = 0; start < end; start++) {
		temp = s[start];
		s[start] = s[end];
		s[end] = temp;
		end--;
	}
	return;
}