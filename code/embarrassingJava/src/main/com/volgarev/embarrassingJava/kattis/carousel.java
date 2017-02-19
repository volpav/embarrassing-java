import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class carousel implements SolutionRunner {
    class Deal {
        private final Integer cost;
        private final Integer rides;
        private final Double pricePerTicket;

        public Deal(int rides, int cost) {
            this.rides = rides;
            this.cost = cost;
            this.pricePerTicket = (double)((double)cost / rides);
        }

        public Integer getRides() {
            return this.rides;
        }

        public Integer getCost() {
            return this.cost;
        }

        public Double getPricePerTicket() {
            return this.pricePerTicket;
        }

        @Override public String toString() {
            return String.format("Buy %d tickets for $%d", this.rides, this.cost);
        }
    }

    public void run(Scanner s) {
        carousel me = new carousel();

        int n = 0, m = 0;

        while (true) {
            n = s.nextInt(); m = s.nextInt();

            if (n == 0 && m == 0) {
                break;
            }

            List<Deal> deals = new ArrayList<>();

            while ((n--) > 0) {
                int a = s.nextInt(), b = s.nextInt();

                if (a <= m) {
                    deals.add(me.new Deal(a, b));
                }
            }

            if (deals.size() > 0) {
                deals.sort((Deal x, Deal y) -> {
                    int result = x.getPricePerTicket().compareTo(y.getPricePerTicket());

                    if (result == 0) {
                        result = y.getRides().compareTo(x.getRides());
                    }

                    return result;
                });

                System.out.println(deals.get(0).toString());
            } else {
                System.out.println("No suitable tickets offered");
            }
        }
    }
}