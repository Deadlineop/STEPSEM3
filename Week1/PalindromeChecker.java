import java.util.Arrays;

public class PalindromeChecker {

    // ---- Approach 1: Iterative ----
    // Two pointers walk from both ends toward the middle
    public static boolean isPalindromeIterative(String text) {
        int left  = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;   // mismatch found, not a palindrome
            }
            left++;
            right--;
        }
        return true;
    }

    // ---- Approach 2: Recursive ----
    // Compare first and last character, then recurse on the inner substring
    public static boolean isPalindromeRecursive(String text) {
        // Base case: 0 or 1 character is always a palindrome
        if (text.length() <= 1) {
            return true;
        }
        // If the outer characters don't match, stop
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        // Recurse on everything except the first and last character
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // ---- Approach 3: Array Reversal ----
    // Convert to char array, reverse it manually, compare with original
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = text.toCharArray();

        int left = 0, right = reversed.length - 1;
        while (left < right) {
            char temp      = reversed[left];
            reversed[left] = reversed[right];
            reversed[right] = temp;
            left++;
            right--;
        }

        return Arrays.equals(original, reversed);
    }

    // Helper to print all three results for one input
    public static void checkAndPrint(String text) {
        boolean iter   = isPalindromeIterative(text);
        boolean recur  = isPalindromeRecursive(text);
        boolean arrRev = isPalindromeArrayReversal(text);

        System.out.println("Input: \"" + text + "\"");
        System.out.println(
            "  Iterative     : " + (iter   ? "Palindrome" : "Not Palindrome") +
            "\n  Recursive     : " + (recur  ? "Palindrome" : "Not Palindrome") +
            "\n  Array Reversal: " + (arrRev ? "Palindrome" : "Not Palindrome")
        );
        System.out.println();
    }

    public static void main(String[] args) {
        checkAndPrint("madam");
        checkAndPrint("hello");
        checkAndPrint("racecar");
        checkAndPrint("java");
    }
}
