public class WordReversalEncoder {

    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            // StringBuilder has a built-in reverse() method
            // It reverses the characters in place
            StringBuilder reversed = new StringBuilder(words[i]);
            reversed.reverse();

            result.append(reversed);

            // Add a space between words, but not after the last word
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[] sentences = {
            "hello club",
            "Java Programming is fun",
            "Reverse each word"
        };

        for (String s : sentences) {
            System.out.println("Input : \"" + s + "\"");
            System.out.println("Output: \"" + reverseEachWord(s) + "\"");
            System.out.println();
        }
    }
}
