import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Permutations {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        char[] str = s.nextLine().toCharArray();
        int[] nums = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            nums[i] = Character.getNumericValue(str[i]);
        }

        List<List<Integer>> ret = new Permutations().permute(nums);

        for (List<Integer> p : ret) {
            System.out.print("[ ");
            
            int cnt = 0;

            for (Integer i : p) {
                System.out.print(i);

                cnt++;

                if (cnt < nums.length) {
                    System.out.print(", ");
                }
            }
            
            System.out.println(" ]");
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

        if (nums != null && nums.length > 0) {
            List<Integer> single = new ArrayList<Integer>();
            iterate(nums, ret, single);
        }

        return ret;
    }

    private void iterate(int[] nums, List<List<Integer>> all, List<Integer> single) {
        if (nums.length == single.size()) {
            List<Integer> temp = new ArrayList<Integer>(single);
            
            all.add(temp);
        }

        for (int i = 0; i < nums.length; i++) {
            if (!single.contains(nums[i])) {
                single.add(nums[i]);

                iterate(nums, all, single);

                single.remove(single.size() - 1);
            }
        }
    }
}