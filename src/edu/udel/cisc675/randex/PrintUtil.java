package edu.udel.cisc675.randex;
import java.io.PrintStream;

public class PrintUtil {
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

    /* For each problem, permutation of answers, from module
     * RandomizeAnswers */
    private int[][] answerPerms;

    public PrintUtil(PrintStream out, char[] chars, int[] probStarts, int[] probStops, int[][] answerStarts, int[][] answerStops, int[][] answerPerms) {
        this.out = out;
        this.chars = chars;
        this.probStarts = probStarts;
        this.probStops = probStops;
        this.answerStarts = answerStarts;
        this.answerStops = answerStops;
        this.answerPerms = answerPerms;
    }

    public void printRange(int start, int stop) {
        for (int i=start; i<stop; i++)
            out.print(chars[i]);
        }
    
        /* Prints the aid-th answer of problem pid */
        public void printAnswer(int pid, int aid) {
        printRange(answerStarts[pid][aid], answerStops[pid][aid]);
        }
    
        /* Prints problem pid, with answers permuted */
        public void printProblem(int pid) {
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

}
