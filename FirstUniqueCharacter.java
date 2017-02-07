import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class FirstUniqueCharacter {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println(new FirstUniqueCharacter().firstUniqueCharacter(s.nextLine()));
    }

    public char firstUniqueCharacter(String s) {
        if (s == null || s.length() == 0) {
            return '\0';
        }

        Map<Character, Integer[]> occur = new HashMap<Character, Integer[]>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];

            if (occur.containsKey(ch)) {
                Integer[] v = occur.get(ch);

                occur.put(ch, new Integer[] { v[0], v[1] + 1 });
            } else {
                occur.put(ch, new Integer[] { i, 1 });
            }
        }

        int idx = s.length();

        for (Integer[] occ : occur.values()) {
            if (occ[1] == 1 && idx > occ[0]) {
                idx = occ[0];
            }
        }

        return idx < s.length() ? chars[idx] : '\0';
    }
}
