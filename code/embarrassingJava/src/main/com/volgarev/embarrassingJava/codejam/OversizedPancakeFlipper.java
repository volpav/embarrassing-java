package com.volgarev.embarrassingJava.codejam;

import java.util.*;
import java.io.*;

public class OversizedPancakeFlipper {
    class Pair {
        public String s;
        public int n;

        public Pair(String s, int n) {
            this.s = s;
            this.n = n;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new OversizedPancakeFlipper().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), i = 1;

        while (t-- > 0) {
            String input = s.next();
            int k = s.nextInt();

            int num = oversizedPancakeFlipper(input, k);

            System.out.println(String.format("Case #%d: %s", i, num < 0 ? "IMPOSSIBLE" : Integer.toString(num)));

            i++;
        }
    }

    public int oversizedPancakeFlipper(String s, int k) {
        char[] chars = s.toCharArray();
        int[] values = new int[chars.length];

        for (int i = 0; i < values.length; i++) {
            values[i] = chars[i] == '+' ? 1 : 0;
        }

        int ret = 0, iterations = 0;

        if (isMaxSum(values)) {
            return 0;
        }

        if (k > values.length) {
            return -1;
        }

        print(values);

        while (!isMaxSum(values)) {
            iterations++;

            if (iterations > 1000) {
                return -1;
            }

            int offset = values.length - 1;

            while (values[offset] == 1 && (offset + 1) > k) {
                offset--;
            }

            int[] minSum = minSubArray(values, k, offset);
            flip(values, minSum[0], minSum[1]);

            print(values);

            ret++;
        }

        return ret;
    }

    private void print(int[] values) {
        /*for (int v : values) {
            System.out.print(v == 1 ? '+' : '-');
        }

        System.out.println();*/
    }

    private boolean isMaxSum(int[] values) {
        for (int v : values) {
            if (v != 1) {
                return false;
            }
        }

        return true;
    }

    private void flip(int[] values, int from, int to) {
        int min = Math.min(from, to),
            max = Math.max(from, to);

        for (int i = min; i <= max; i++) {
            values[i] = values[i] == 1 ? 0 : 1;
        }
    }

    private int[] minSubArray(int[] values, int k, int offset) {
        int from = offset, to = offset - k + 1;
        int sum = 0, tmp = 0;

        for (int i = from; i >= to; i--) {
            sum += values[i];
        }

        for (int i = offset - 1; i >= 0; i--) {
            int newTo = i - k + 1;

            if (newTo < 0) {
                break;
            }

            tmp = sum;
            tmp -= values[i + 1];
            tmp += values[newTo];

            if (tmp < sum) {
                sum = tmp;
                from = i;
                to = newTo;
            }
        }

        return new int[] { from, to };
    }
}
