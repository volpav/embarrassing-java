import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AllSubSets {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(), i = 0;
        int[] numbers = new int[n];

        while ((n--) > 0) {
            numbers[i++] = s.nextInt();
        }

        List<List<Integer>> p = subSets(numbers);

        for (List<Integer> set : p) {
            System.out.print("[ ");

            for (Integer num : set) {
                System.out.print(num + " ");
            }

            System.out.println("]");
        }
    }

    private static List<List<Integer>> subSets(int[] numbers) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();

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

        return ret;
    }
}