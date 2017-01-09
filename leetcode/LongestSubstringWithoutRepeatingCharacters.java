import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String str = s.nextLine();

        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(str));
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int[] len = new int[n];

        Map<Character, Integer> chars = new HashMap<Character, Integer>();

        if (n > 0) {
            len[0] = 1;
            chars.put(s.charAt(0), 0);

            for (int i = 1; i < n; i++) {
                char ch = s.charAt(i);

                if (!chars.containsKey(ch)) {
                    len[i] = len[i - 1] + 1;
                } else {
                    int lastPos = chars.get(ch);
                    
                    len[i] = i - lastPos;
                    chars.clear();

                    for (int j = lastPos + 1; j < i; j++) {
                        chars.put(s.charAt(j), j);
                    }
                }

                chars.put(ch, i);
            }

            int max = 0;

            for (int i = 0; i < len.length; i++) {
                if (len[i] > max) {
                    max = len[i];
                }
            }

            return max;
        } else {
            return 0;
        }
    }
}