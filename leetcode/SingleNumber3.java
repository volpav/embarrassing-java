import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class SingleNumber3 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), index = 0;
        int[] nums = new int[n];

        while ((n--) > 0) {
            nums[index++] = s.nextInt();
        }

        int[] ret = new SingleNumber3().singleNumber(nums);
        System.out.println(String.format("[ %d, %d ]", ret[0], ret[1]));
    }

    public int[] singleNumber(int[] nums) {
        Set<Integer> cur = new HashSet<Integer>();
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            if (cur.contains(num)) {
                cur.remove(num);
            } else {
                cur.add(num);
            }
        }

        Integer[] temp = new Integer[2];
        cur.toArray(temp);
        
        return new int[] { temp[0], temp[1] };
    }
}