package com.volgarev.embarrassingJava.leetcode;

import com.volgarev.embarrassingJava.SolutionRunner;
import com.volgarev.embarrassingJava.util.Interval;
import com.volgarev.embarrassingJava.util.Reader;

import java.util.*;

public class NonOverlappingIntervals implements SolutionRunner {
    public void run(Scanner s) {
        Interval[] intervals = Reader.readIntervals(s);

        System.out.println(eraseOverlapIntervals(intervals));
    }

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval x, Interval y) {
                return x.end - y.end;
            }
        });

        int nonOverlapping = 1, end = intervals[0].end;

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                nonOverlapping++;
            }
        }

        return intervals.length - nonOverlapping;
    }
}
