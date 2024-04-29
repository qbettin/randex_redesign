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
                if (match(chars, i, BEGIN_ENUMERATE.toCharArray())) {
                    i += BEGIN_ENUMERATE.length();
                } else if (match(chars, i, END_ENUMERATE.toCharArray())) {
                    break;
                } else if (match(chars, i, ITEM.toCharArray())) {
                    startList.add(i);
                    i += ITEM.length();
                } else {
                    i++;
                }
            }

            answerStartsList.add(startList);
        }

        return to2DArray(answerStartsList);
    }

    public int[][] findAnswerStops() {
        ArrayList<ArrayList<Integer>> answerStopsList = new ArrayList<>();

        for (int pid = 0; pid < probStarts.length; pid++) {
            ArrayList<Integer> stopList = new ArrayList<>();
            int i = probStarts[pid];
            int stop = probStops[pid];

            while (i < stop) {
                if (match(chars, i, BEGIN_ENUMERATE.toCharArray())) {
                    i += BEGIN_ENUMERATE.length();
                } else if (match(chars, i, END_ENUMERATE.toCharArray())) {
                    break;
                } else if (match(chars, i, ITEM.toCharArray())) {
                    // Find the stop index of the current answer
                    int j = i + ITEM.length();
                    while (j < stop && !match(chars, j, ITEM.toCharArray()) && !match(chars, j, END_ENUMERATE.toCharArray())) {
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

        return to2DArray(answerStopsList);
    }

    private boolean match(char[] chars, int off, char[] sought) {
        int n = sought.length;
        if (off + n > chars.length)
            return false;
        for (int i = 0; i < n; i++) {
            if (sought[i] != chars[off + i])
                return false;
        }
        return true;
    }

    private int[][] to2DArray(ArrayList<ArrayList<Integer>> list) {
        int[][] result = new int[list.size()][];
        for (int i = 0; i < result.length; i++) {
            ArrayList<Integer> sublist = list.get(i);
            result[i] = toArray(sublist);
        }
        return result;
    }

    private int[] toArray(ArrayList<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = list.get(i);
        return result;
    }
}
