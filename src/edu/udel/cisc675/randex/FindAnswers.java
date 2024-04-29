package edu.udel.cisc675.randex;

import java.util.ArrayList;

public class FindAnswers {
    private static final String BEGIN_ENUMERATE = "\\begin{enumerate}";
    private static final String END_ENUMERATE = "\\end{enumerate}";
    private static final String ITEM = "\\item";

    private char[] chars;
    private int[] probStarts;
    private int[] probStops;
    private int[][] answerStarts;
    private int[][] answerStops;

    public FindAnswers(char[] chars, int[] probStarts, int[] probStops) {
        this.chars = chars;
        this.probStarts = probStarts;
        this.probStops = probStops;
        this.answerStarts = findAnswerStarts();
        this.answerStops = findAnswerStops();
    }

    public int[][] getAnswerStarts() {
        return answerStarts;
    }

    public int[][] getAnswerStops() {
        return answerStops;
    }

    public int[][] findAnswerStarts() {
        ArrayList<ArrayList<Integer>> answerStartsList = new ArrayList<>();

        for (int pid = 0; pid < probStarts.length; pid++) {
            ArrayList<Integer> startList = new ArrayList<>();
            int i = probStarts[pid];
            int stop = probStops[pid];

            while (i < stop) {
                if (StringUtils.match(chars, i, BEGIN_ENUMERATE.toCharArray())) {
                    i += BEGIN_ENUMERATE.length();
                } else if (StringUtils.match(chars, i, END_ENUMERATE.toCharArray())) {
                    break;
                } else if (StringUtils.match(chars, i, ITEM.toCharArray())) {
                    startList.add(i);
                    i += ITEM.length();
                } else {
                    i++;
                }
            }

            answerStartsList.add(startList);
        }

        return StringUtils.to2DArray(answerStartsList);
    }

    public int[][] findAnswerStops() {
        ArrayList<ArrayList<Integer>> answerStopsList = new ArrayList<>();

        for (int pid = 0; pid < probStarts.length; pid++) {
            ArrayList<Integer> stopList = new ArrayList<>();
            int i = probStarts[pid];
            int stop = probStops[pid];

            while (i < stop) {
                if (StringUtils.match(chars, i, BEGIN_ENUMERATE.toCharArray())) {
                    i += BEGIN_ENUMERATE.length();
                } else if (StringUtils.match(chars, i, END_ENUMERATE.toCharArray())) {
                    break;
                } else if (StringUtils.match(chars, i, ITEM.toCharArray())) {
                    // Find the stop index of the current answer
                    int j = i + ITEM.length();
                    while (j < stop && !(StringUtils.match(chars, j, ITEM.toCharArray())) && !(StringUtils.match(chars, j, END_ENUMERATE.toCharArray()))) {
                        j++;
                    }
                    stopList.add(j);
                    i = j;
                } else {
                    i++;
                }
            }

            answerStopsList.add(stopList);
        }

        return StringUtils.to2DArray(answerStopsList);
    }
}
