package edu.udel.cisc675.randex;

import java.io.PrintStream;

/* Module Output: given all data from previous phases, writes the
   randomized exam to an output stream. */
public class Output {

    /* Output stream to write to */
    PrintStream out;

    /* The characters of the original file, from module Input */
    char[] chars;

    /* Start indexes of problems, from module FindProblems */
    int[] probStarts;

    /* Stop indexes of problems, from module FindProblems */
    int[] probStops;

    /* Start indexes of all answers, from module FindAnswers */
    int[][] answerStarts;

    /* Stop indexes of all answers, from module FindAnswers */
    int[][] answerStops;

    /* Permutation of problem IDs, from module RandomizeProblems */
    int[] probPerm;

    /* For each problem, permutation of answers, from module
     * RandomizeAnswers */
    int[][] answerPerms;

    /* Constructs new instance of Output from given fields; only sets
       fields and does nothing else */
    public Output(PrintStream out, char[] chars,
		  int[] probStarts, int[] probStops,
		  int[][] answerStarts, int[][] answerStops,
		  int[] probPerm, int[][] answerPerms) {
	this.out = out;
	this.chars = chars;
	this.probStarts = probStarts;
	this.probStops = probStops;
	this.answerStarts = answerStarts;
	this.answerStops = answerStops;
	this.probPerm = probPerm;
	this.answerPerms = answerPerms;
    }

    /* Prints a range of characters to out, starting at start, ending
     * at stop-1 */
    private void printRange(int start, int stop) {
	for (int i=start; i<stop; i++)
	    out.print(chars[i]);
    }

    /* Prints the aid-th answer of problem pid */
    private void printAnswer(int pid, int aid) {
	printRange(answerStarts[pid][aid], answerStops[pid][aid]);
    }

    /* Prints problem pid, with answers permuted */
    private void printProblem(int pid) {
	int nanswer = answerStarts[pid].length;
	if (nanswer == 0) {
	    printRange(probStarts[pid], probStops[pid]);
	    return;
	}
	printRange(probStarts[pid], answerStarts[pid][0]);
	for (int i=0; i<nanswer; i++)
	    printAnswer(pid, answerPerms[pid][i]);
	printRange(answerStops[pid][nanswer-1], probStops[pid]);
    }

    /* Prints the entire exam with permuted problems and permuted
     * answers */
    public void execute() {
	int nprob = probStarts.length;
	if (nprob == 0) {
	    printRange(0, chars.length);
	} else {
	    printRange(0, probStarts[0]);
	    for (int i=0; i<nprob; i++) {
		printProblem(probPerm[i]);
		if (i<nprob-1)
		    printRange(probStops[i], probStarts[i+1]);
	    }
	    printRange(probStops[nprob-1], chars.length);
	}
    }
}
