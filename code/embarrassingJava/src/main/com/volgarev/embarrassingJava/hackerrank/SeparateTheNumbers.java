package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class SeparateTheNumbers implements SolutionRunner {
    public void run(Scanner s) {
        int q = s.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            String str = s.next();

            long sequenceStart = getSequenceStart(str);

            System.out.println(sequenceStart < 0 ? "NO" : "YES " + sequenceStart);
        }
    }

    private static long getSequenceStart(String s) {
        for (int i = 1; i < (s.length() / 2) + 1; i++) {
            long start = parseLong(s, 0, i);

            if (start < 0) {
                return -1;
            }

            long next = start;
            String remaining = s.substring(i);

            while (remaining.length() > 0) {
                next++;
                String prefix = Long.toString(next);

                if (remaining.indexOf(prefix) != 0) {
                    break;
                }

                remaining = remaining.substring(prefix.length());
            }

            if (remaining.length() == 0) {
                return start;
            }
        }

        return -1;
    }

    private static long parseLong(String s, int start, int end) {
        String part = s.substring(start, end);

        return part.indexOf('0') == 0 ? -1 : Long.parseLong(part);
    }
}
