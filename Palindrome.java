import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        char[] str = s.nextLine().toCharArray();

        int left = 0, right = str.length - 1;

        while (left < right) {
            if (!Character.isLetterOrDigit(str[left])) {
                left++;
            } else if (!Character.isLetterOrDigit(str[right])) {
                right--;
            } else if (Character.toLowerCase(str[left]) != Character.toLowerCase(str[right])) {
                System.out.println("Not a palindrome...");
                return;
            } else {
                left++;
                right--;
            }
        }

        System.out.println("Palindrome!");
    }
}