package com.volgarev.embarrassingJava.hackerrank;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

// https://www.hackerrank.com/challenges/caesar-cipher-1
public class CaesarCipher implements SolutionRunner {
    public void run(Scanner s) {
        // Ignoring N
        s.nextInt();

        String str = s.next();
        int k = s.nextInt();

        String out = caesarCipher(str, k);

        System.out.println(out);
    }

    private String caesarCipher(String s, int k) {
        StringBuilder sb = new StringBuilder();

        int beg = (int)'a';
        int end = (int)'z';
        int size = end - beg + 1;

        for (Character ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                Character lower = Character.toLowerCase(ch);

                int pos = ((int)lower) - beg;

                if (pos + k < size) {
                    pos += k;
                } else {
                    int wholes = (pos + k) / size;
                    int rem = (pos + k) - (wholes * size);

                    pos = rem;
                }

                Character converted = (char)(pos + beg);

                if (Character.isUpperCase(ch)) {
                    converted = Character.toUpperCase(converted);
                }

                sb.append(converted);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
