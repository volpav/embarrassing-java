import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

// https://leetcode.com/problems/combination-sum-ii/
// NOTE: Does not pass on time.
public class CombinationSumTwo {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), i = 0;
        int[] candidates = new int[n];

        while((n--) > 0) {
            candidates[i++] = s.nextInt();
        }

        int target = s.nextInt();

        List<List<Integer>> sums = new CombinationSumTwo().combinationSum2(candidates, target);

        for (List<Integer> next : sums) {
            System.out.print("[ ");

            for (Integer num : next) {
                System.out.print(num);
                System.out.print(" ");
            }

            System.out.println("]");
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> subSets = subSets(candidates);
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        Set<String> signatures = new HashSet<String>();

        for (List<Integer> subSet : subSets) {
            int sum = 0;
            String sig = "";

            subSet.sort((x, y) -> x - y);

            for (int i : subSet) {
                sum += i;
                sig += (i + "");

                if (sum > target) {
                    break;
                }
            }

            if (sum == target && !signatures.contains(sig)) {
                ret.add(subSet);
                signatures.add(sig);
            }
        }

        return ret;
    }

    private static List<List<Integer>> subSets(int[] numbers) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (numbers != null && numbers.length > 0) {
            for (int i = 0; i < numbers.length; i++) {
                List<List<Integer>> temp = new ArrayList<List<Integer>>();

                for (List<Integer> item : ret) {
                    temp.add(new ArrayList<Integer>(item));
                }

                for (List<Integer> item : temp) {
                    item.add(numbers[i]);
                }

                List<Integer> single = new ArrayList<Integer>();
                single.add(numbers[i]);
                temp.add(single);

                ret.addAll(temp);

            }
        }

        return ret;
    }
}