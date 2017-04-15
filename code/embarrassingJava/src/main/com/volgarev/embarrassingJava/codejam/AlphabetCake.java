package com.volgarev.embarrassingJava.codejam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlphabetCake {
    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new AlphabetCake().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), index = 1;

        while (t-- > 0) {
            int r = s.nextInt();
            int c = s.nextInt();

            char[][] m = new char[r][c];
            List<int[]> children = new ArrayList<>();

            for (int i = 0; i < r; i++) {
                char[] chars = s.next().toCharArray();

                for (int j = 0; j < c; j++) {
                    m[i][j] = chars[j];

                    if (chars[j] != '?') {
                        children.add(new int[] { i, j });
                    }
                }
            }

            solve(m, children);

            System.out.println(String.format("Case #%d:", index++));

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    System.out.print(m[i][j]);
                }

                System.out.println();
            }
        }
    }

    private void solve(char[][] m, List<int[]> children) {
        solveInternal(m, children);
    }

    private void solveInternal(char[][] m, List<int[]> children) {
        List<int[]> newChildren = new ArrayList<>();

        boolean[] emptyRows = new boolean[m.length];
        boolean[][] taken = new boolean[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            if (isEmptyRow(m, i)) {
                emptyRows[i] = true;
            }
        }

        for (int i = 0; i < children.size(); i++) {
            int[] crd = children.get(i);
            int ch_i = crd[0], ch_j = crd[1];

            boolean takenAboveRow = false;
            boolean takenBelowRow = false;

            if (ch_i - 1 >= 0 && emptyRows[ch_i - 1] && !taken[ch_i - 1][ch_j]) {
                m[ch_i - 1][ch_j] = m[ch_i][ch_j];
                newChildren.add(new int[] { ch_i - 1, ch_j });
                taken[ch_i - 1][ch_j] = true;
                takenAboveRow = true;
            }

            if (ch_i + 1 < m.length && emptyRows[ch_i + 1] && !taken[ch_i + 1][ch_j]) {
                m[ch_i + 1][ch_j] = m[ch_i][ch_j];
                newChildren.add(new int[] { ch_i + 1, ch_j });
                taken[ch_i + 1][ch_j] = true;
                takenBelowRow = true;
            }

            int w = 1;

            int grewLeft = 0, grewRight = 0;

            boolean canGrowLeft = true;
            boolean canGrowRight = true;

            while (true) {
                canGrowLeft = canGrowLeft && expand(m, ch_i, ch_j - w, m[ch_i][ch_j]);
                canGrowRight = canGrowRight && expand(m, ch_i, ch_j + w, m[ch_i][ch_j]);

                if (!canGrowLeft && !canGrowRight) {
                    break;
                }

                if (canGrowLeft) {
                    grewLeft++;
                }

                if (canGrowRight) {
                    grewRight++;
                }

                w++;
            }

            if (takenAboveRow) {
                for (int k = ch_j; k >= (ch_j - grewLeft); k--) {
                    taken[ch_i - 1][k] = true;
                }

                for (int k = ch_j; k <= (ch_j + grewRight); k++) {
                    taken[ch_i - 1][k] = true;
                }
            }

            if (takenBelowRow) {
                for (int k = ch_j; k >= (ch_j - grewLeft); k--) {
                    taken[ch_i + 1][k] = true;
                }

                for (int k = ch_j; k <= (ch_j + grewRight); k++) {
                    taken[ch_i + 1][k] = true;
                }
            }

        }

        if (newChildren.size() > 0) {
            solveInternal(m, newChildren);
        }
    }

    private boolean isEmptyRow(char[][] m, int i) {
        if (i >= 0 && i < m.length) {
            for (int j = 0; j < m[i].length; j++) {
                if (!isEmpty(m, i, j)) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean isEmpty(char[][] m, int i, int j) {
        return i >= 0 && i < m.length && j >= 0 && j < m[i].length && m[i][j] == '?';
    }

    private boolean expand(char[][] m, int i, int j, char ch) {
        if (isEmpty(m, i, j)) {
            m[i][j] = ch;
            return true;
        } else {
            return false;
        }
    }
}
