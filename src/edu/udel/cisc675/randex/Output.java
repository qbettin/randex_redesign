package edu.udel.cisc675.randex;

import java.io.PrintStream;

/* Module Output: given all data from previous phases, writes the
   randomized exam to an output stream. */
public class Output {

    /* Output stream to write to */
    PrintStream out;

    /* The characters of the original file, from module Input */
    private char[] chars;

    /* Start indexes of problems, from module FindProblems */
    private int[] probStarts;

    /* Stop indexes of problems, from module FindProblems */
    private int[] probStops;

    /* Start indexes of all answers, from module FindAnswers */
    private int[][] answerStarts;

    /* Stop indexes of all answers, from module FindAnswers */
    private int[][] answerStops;

    /* Permutation of problem IDs, from module RandomizeProblems */
    private int[] probPerm;

    /* For each problem, permutation of answers, from module
     * RandomizeAnswers */
    private int[][] answerPerms;

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

    public void PrintOutput() {
        PrintUtil printUtil = new PrintUtil(out, chars, probStarts, probStops, answerStarts, answerStops, answerPerms);
        int nprob = probStarts.length;
        if (nprob == 0) {
            printUtil.printRange(0, chars.length);
        } else {
            printUtil.printRange(0, probStarts[0]);
            for (int i=0; i<nprob; i++) {
            printUtil.printProblem(probPerm[i]);
            if (i<nprob-1)
                printUtil.printRange(probStops[i], probStarts[i+1]);
            }
            printUtil.printRange(probStops[nprob-1], chars.length);
        }
    }
}
