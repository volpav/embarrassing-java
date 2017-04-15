package com.volgarev.embarrassingJava.codejam;

import java.util.*;
import java.io.*;

public class BathroomStalls {
    class Segment {
        public long from;
        public long to;

        public Segment(long from, long to) {
            this.from = from;
            this.to = to;
        }

        public long getDistance() {
            return to - from + 1;
        }
    }

    class Position {
        public long toNearestLeft;
        public long toNearestRight;

        public Position(long toNearestLeft, long toNearestRight) {
            this.toNearestLeft = toNearestLeft;
            this.toNearestRight = toNearestRight;
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        new BathroomStalls().run(s);
    }

    public void run(Scanner s) {
        int t = s.nextInt(), i = 1;

        while (t-- > 0) {
            long n = s.nextLong();
            long k = s.nextLong();

            Position pos = computeLastStallPosition(n, k);

            System.out.println(String.format("Case #%d: %d %d", i,
                Math.max(pos.toNearestLeft, pos.toNearestRight),
                Math.min(pos.toNearestLeft, pos.toNearestRight)
            ));

            i++;
        }
    }

    private Position computeLastStallPosition(long n, long k) {
        long toNearestLeft = 1, toNearestRight = 1;

        PriorityQueue<Segment> segments = new PriorityQueue<>(
                1000,
                new Comparator<Segment>() {
                    @Override
                    public int compare(Segment o1, Segment o2) {
                        return Long.compare(o2.getDistance(), o1.getDistance());
                    }
                }
        );

        segments.add(new Segment(0, n - 1));

        while (k-- > 0) {
            Segment best = segments.remove();
            long distance = best.getDistance();

            long middle = best.from + (long)Math.floor(
                    (distance % 2 == 0 ? distance - 1 : distance) / 2D
            );

            toNearestLeft = middle - best.from;
            toNearestRight = best.to - middle;

            long leftTo = middle - 1;
            long rightFrom = middle + 1;

            if (leftTo >= best.from) {
                segments.add(new Segment(best.from, leftTo));
            }

            if (best.to >= rightFrom) {
                segments.add(new Segment(rightFrom, best.to));
            }
        }

        return new Position(toNearestLeft, toNearestRight);
    }
}
