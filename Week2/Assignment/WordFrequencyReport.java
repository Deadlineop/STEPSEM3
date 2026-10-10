import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordFrequencyReport {

    public static void printFilteredWordFrequency(String feedback) {

        // Fixed stop-word list — these are skipped in the frequency count
        String[] stopWordsArr = { "the", "was", "and", "a", "is", "of", "in" };
        Set<String> stopWords = new HashSet<>(Arrays.asList(stopWordsArr));
        // HashSet gives O(1) lookup, much faster than looping the array every time

        // Normalize: lowercase, strip commas and periods
        String normalized = feedback.toLowerCase()
                                    .replace(",", "")
                                    .replace(".", "")
                                    .replace("!", "")
                                    .replace("?", "");

        // Split on any whitespace (spaces, tabs, multiple spaces)
        String[] words = normalized.split("\\s+");

        // Count frequency of every word that is NOT a stop word
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        // Sort entries by count in descending order
        // (b.getValue() - a.getValue()) sorts highest count first
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Feedback 1 ===");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");

        System.out.println("\n=== Feedback 2 ===");
        printFilteredWordFrequency("The content of the session was very clear and the examples were great.");
    }
}
