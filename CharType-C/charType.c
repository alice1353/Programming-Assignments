/**
 * charType.c
 * Purpose:
   Reads lines of input and categories
   alphabetic characters, numeric characters, punctuations, and white space
 * Zifei (Alice) Lu
   alicelu625@gmail.com
 * 11/14/2017
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]) {

	FILE* in; //file handle for input
	FILE* out; //file handle for output

	
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

	int n = 256;
	char str[n]; //char array to store words from input file
	
	//check command line for correct number of arguments
	if (argc != 3) {
		printf("Usage: %s <input file> <output file> \n", argv[0]);
		exit(EXIT_FAILURE);
	}

	char* a;
	char* d;
	char* p;
	char* w;

	int countLine = 1;

	while (fgets(str, sizeof(str), in) != NULL) {
		a = calloc(n, sizeof(char));
		d = calloc(n, sizeof(char));
		p = calloc(n, sizeof(char));
		w = calloc(n, sizeof(char));

		extract_chars(str, a, d, p, w);
		fprintf(out, "line %d contains: \n", countLine);

		if (strlen(a) == 1) {
			fprintf(out, "%d alphabetic character: %s\n", strlen(a), a);
		}
		else {
			fprintf(out, "%d alphabetic characters: %s\n", strlen(a), a);
		}

		if (strlen(d) == 1) {
			fprintf(out, "%d numeric character: %s\n", strlen(d), d);
		}
		else {
			fprintf(out, "%d numeric characters: %s\n", strlen(d), d);
		}

		if (strlen(p) == 1) {
			fprintf(out, "%d punctuation character: %s\n", strlen(p), p);
		}
		else {
			fprintf(out, "%d punctuation characters: %s\n", strlen(p), p);
		}

		if (strlen(w) == 1) {
			fprintf(out, "%d whitespace character: %s\n", strlen(w), w);
		}
		else {
			fprintf(out, "%d whitespace characters: %s\n", strlen(w), w);
		}

		countLine++;
		
		free(a);
		free(d);
		free(p);
		free(w);
		a = NULL;
		d = NULL;
		p = NULL;
		w = NULL;
		
	}

	fclose(in);
	fclose(out);
	return EXIT_SUCCESS;
}

void extract_chars(char* s, char* a, char* d, char* p, char* w) {
	int i, countA, countD, countP, countW;
	countA = countD = countP = countW = 0;
	for (i = 0; i < strlen(s); i++) {
		if (isalpha(s[i])) {
			a[countA] = s[i];
			countA++;
		}
		else if (isalnum((int)s[i])) {
			d[countD] = s[i];
			countD++;
		}
		else if (ispunct(s[i])) {
			p[countP] = s[i];
			countP++;
		}
		else if (isspace(s[i])) {
			w[countW] = s[i];
			countW++;
		}
	}
}