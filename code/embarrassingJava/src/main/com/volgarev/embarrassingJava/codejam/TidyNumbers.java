package com.volgarev.embarrassingJava.codejam;

import java.util.*;
import java.io.*;

public class TidyNumbers {
    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new TidyNumbers().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), i = 1;

        while (t-- > 0) {
            String n = getLastTidyNumber(s.next());

            System.out.println(String.format("Case #%d: %s", i, n));

            i++;
        }
    }

    public String getLastTidyNumber(String n) {
        char[] input = n.toCharArray();
        char[] output = getLastTidyNumber(input);

        StringBuilder sb = new StringBuilder();

        int startIndex = 0;

        while (startIndex < output.length && output[startIndex] == '0') {
            startIndex++;
        }

        if (startIndex < output.length) {
            for (int i = startIndex; i < output.length; i++) {
                sb.append(output[i]);
            }
        } else {
            sb.append('0');
        }

        return sb.toString();
    }

    private char[] getLastTidyNumber(char[] input) {
        int index = 1;

        while (index < input.length) {
            int cmp = input[index - 1] - input[index];

            if (cmp > 0) {
                int prev = (input[index - 1] - '0') - 1;

                input[index - 1] = (char)(prev + '0');

                for (int i = index; i < input.length; i++) {
                    input[i] = '9';
                }

                index--;

                if (index < 1) {
                    index = 1;
                }
            } else {
                index++;
            }
        }

        return input;
    }
}
