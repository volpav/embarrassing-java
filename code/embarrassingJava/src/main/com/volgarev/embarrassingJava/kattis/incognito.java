import com.volgarev.embarrassingJava.SolutionRunner;

import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class incognito implements SolutionRunner {
    public void run(Scanner s) {
        int t = s.nextInt();

        while ((t--) > 0) {
            int n = s.nextInt();

            Map<String, Integer> opts = new HashMap<>();

            while ((n--) > 0) {
                s.next();

                String category = s.next();

                opts.put(category, opts.getOrDefault(category, 0) + 1);
            }

            Integer result = 1;

            for (String category : opts.keySet()) {
                result *= opts.get(category) + 1;
            }

            System.out.println(result - 1);
        }
    }
}