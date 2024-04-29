package edu.udel.cisc675.randex;

import java.util.ArrayList;

public class ProblemFinder {
    private static final String BEGIN_PROBLEM = "\\begin{problem}";
    private static final String END_PROBLEM = "\\end{problem}";

    private char[] chars;
    private int[] probStarts;
    private int[] probStops;

    public ProblemFinder(char[] chars) {
        this.chars = chars;
		execute();
    }

	public void execute(){
		findProblemIndices();
	}

    private void findProblemIndices() {
        ArrayList<Integer> startList = new ArrayList<>();
        ArrayList<Integer> stopList = new ArrayList<>();
        int n = chars.length;
        boolean inProblem = false;

        for (int i = 0; i < n; i++) {
            if (StringUtils.match(chars, i, BEGIN_PROBLEM.toCharArray())) {
                if (inProblem) {
                    throw new RuntimeException("Encountered \\begin{problem} when inside a problem");
                }
                startList.add(i);
                inProblem = true;
            } else if (StringUtils.match(chars, i, END_PROBLEM.toCharArray())) {
                if (!inProblem) {
                    throw new RuntimeException("Encountered \\end{problem} when outside any problem");
                }
                stopList.add(i + END_PROBLEM.length());
                inProblem = false;
            }
        }

        if (inProblem) {
            throw new RuntimeException("Missing \\end{problem} for last problem");
        }

        probStarts = StringUtils.toArray(startList);
        probStops = StringUtils.toArray(stopList);
    }

    public int[] getProbStarts() {
        return probStarts;
    }

    public int[] getProbStops() {
        return probStops;
    }

}
