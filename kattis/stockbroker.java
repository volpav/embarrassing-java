import java.util.Scanner;
import java.math.BigInteger;

public class stockbroker {
    private static long money;
    private static long stocks;
    private static boolean justBought;
    private static final int maxShares = 100000;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
    
        stocks = 0;
        money = 100;

        long days = s.nextLong();

        if (days == 1) {
            System.out.println(money);
            return;
        }

        long price = s.nextLong();

        for (int i = 0; i < days - 1; i++) {
            long nextPrice = s.nextLong();

            if (nextPrice > price) {
                if (stocks != maxShares && !justBought) {
                    buy(price);
                    justBought = true;
                }
            } else if (nextPrice < price) {
                sell(price);
                justBought = false;
          }

          price = nextPrice;
        }

        sell(price);

        System.out.println(String.valueOf(money));
    }

    static void buy(long price) {
        long leftovermoney = money % price;
        
        stocks = money / price;
        money = 0 + leftovermoney;

        if (stocks > maxShares) {
            sell(price, (stocks - maxShares));
        }
    }

    static void sell(long price) {
        money += price * stocks;
        stocks = 0;
    }

    static void sell(long price, long amount) {
        money += price * amount;
        stocks -= amount;
    }
}