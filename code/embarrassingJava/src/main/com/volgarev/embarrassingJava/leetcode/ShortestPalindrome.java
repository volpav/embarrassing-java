package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;

public class ShortestPalindrome implements SolutionRunner {
    public void run(Scanner s) {
        String str = s.nextLine();

        System.out.println(shortestPalindrome(str));
    }

    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = getTable(temp);
    
        return new StringBuilder(s.substring(table[table.length - 1])).reverse().toString() + s;
    }

    // This uses KMP Algorithm. The code is not mine but I really wanted to figure out the solution (mine was stack-based prefix insert).
    private int[] getTable(String s){
        int[] table = new int[s.length()];
        
        
        int index = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(index) == s.charAt(i)) {
                table[i] = table[i-1] + 1;
                index ++;
            } else {
                index = table[i-1];
                
                while (index > 0 && s.charAt(index) != s.charAt(i)) {
                    index = table[index-1];
                }
                
                if (s.charAt(index) == s.charAt(i)) {
                    index ++ ;
                }
                
                table[i] = index;
            }
        }
        
        return table;
    }
}