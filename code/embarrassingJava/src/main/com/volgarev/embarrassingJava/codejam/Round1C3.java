package com.volgarev.embarrassingJava.codejam;

import java.util.*;
import java.io.*;

public class Round1C3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new Round1C3().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), index = 1;

        while (t-- > 0) {

            solve();

            System.out.println(String.format("Case #%d: ", index++));
        }
    }

    private void solve() {

    }
}
