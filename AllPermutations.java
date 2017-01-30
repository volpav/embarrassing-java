import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AllPermutations {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), i = 0;
        int[] numbers = new int[n];

        while ((n--) > 0) {
            numbers[i++] = s.nextInt();
        }

        List<List<Integer>> p = permutations(numbers);

        for (List<Integer> set : p) {
            System.out.print("[ ");

            for (Integer num : set) {
                System.out.print(num + " ");
            }

            System.out.println("]");
        }
    }

    private static List<List<Integer>> permutations(int[] numbers) {
        int[] nums = new int[numbers.length];
        List<List<Integer>> addTo = new ArrayList<List<Integer>>();

        System.arraycopy(numbers, 0, nums, 0, numbers.length);
        permute(nums, 0, addTo);

        return addTo;
    }

    private static void permute(int[] numbers, int k, List<List<Integer>> addTo) {
        for (int i = k; i < numbers.length; i++) {
            swap(numbers, i, k);
            permute(numbers, k + 1, addTo);
            swap(numbers, k, i);
        }

        if (k == (numbers.length - 1)) {
            List<Integer> item = new ArrayList<Integer>();

            for (int n : numbers) {
                item.add(n);
            }

            addTo.add(item);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}