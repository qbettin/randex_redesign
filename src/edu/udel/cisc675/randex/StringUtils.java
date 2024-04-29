package edu.udel.cisc675.randex;

import java.util.ArrayList;

public class StringUtils {
    public static boolean match(char[] chars, int off, char[] sought) {
        int n = sought.length;
        if (off + n > chars.length)
            return false;
        for (int i = 0; i < n; i++) {
            if (sought[i] != chars[off + i])
                return false;
        }
        return true;
    }
    public static int[][] to2DArray(ArrayList<ArrayList<Integer>> list) {
        int[][] result = new int[list.size()][];
        for (int i = 0; i < result.length; i++) {
            ArrayList<Integer> sublist = list.get(i);
            result[i] = toArray(sublist);
        }
        return result;
    }

    public static int[] toArray(ArrayList<Integer> list) {
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++){
            result[i] = list.get(i);
        }
        return result;
    }
}