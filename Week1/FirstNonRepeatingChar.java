import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeatingChar {

    /*
     * LinkedHashMap is used here because it preserves insertion order.
     * A regular HashMap does NOT guarantee order, which would break the
     * "first" non-repeating requirement.
     *
     * Returns '\0' (null character) if no non-repeating character exists.
     */
    public static char findFirstNonRepeatingChar(String text) {

        // Step 1: Count frequency of every character
        Map<Character, Integer> frequency = new LinkedHashMap<>();
        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        // Step 2: Scan the map in insertion order, return the first char with count = 1
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return '\0';    // sentinel value: means "not found"
    }

    public static void main(String[] args) {
        String[] testCases = { "swiss", "aabbcc", "programming", "aabb", "abcabc" };

        for (String text : testCases) {
            char result = findFirstNonRepeatingChar(text);
            System.out.print("Input: \"" + text + "\"  ->  ");

            if (result == '\0') {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
        }
    }
}
