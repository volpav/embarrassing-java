package com.volgarev.embarrassingJava.codejam;

import java.util.*;
import java.io.*;

public class CountingSheep {
    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new CountingSheep().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), i = 1;

        while (t-- > 0) {
            int n = s.nextInt(), num = -1;

            if (n != 0) {
                num = countingSheep(n);
            }

            System.out.println(String.format("Case #%d: %s", i, num < 0 ? "INSOMNIA" : Integer.toString(num)));

            i++;
        }
    }

    public int countingSheep(int n) {
        int ret = 0, i = 1;
        Set<Character> nums = new HashSet<>();

        while (nums.size() < 10) {
            ret = n * (i++);

            for (char ch : Integer.toString(ret).toCharArray()) {
                nums.add(ch);
            }
        }

        return ret;
    }
}
