import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    private final Random random;
    private final Map<Integer, List<Integer>> numbers;

    public Solution(int[] nums) {
        random = new Random();
        numbers = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            if (!numbers.containsKey(n)) {
                numbers.put(n, new ArrayList<Integer>());
            }
            
            numbers.get(n).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indices = numbers.get(target);

        int idx = random.nextInt(indices.size());

        return indices.get(idx);
    }
}

public class RandomPickIndex {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), i = 0;
        int[] nums = new int[n];

        while((n--) > 0) {
            nums[i++] = s.nextInt();
        }

        Solution sln = new Solution(nums);

        while (true) {
            System.out.println(sln.pick(s.nextInt()));
        }
    }
}