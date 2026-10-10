public class VowelConsonantCounter {

    public static void countVowelsAndConsonants(String text) {
        String vowels = "aeiou";   // reference string for vowel check
        int vowelCount     = 0;
        int consonantCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = Character.toLowerCase(text.charAt(i));

            if (c == ' ') continue;   // skip spaces entirely

            // indexOf returns -1 if the character is NOT in the vowels string
            if (vowels.indexOf(c) != -1) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        System.out.println("Input: \"" + text + "\"");
        System.out.println("Vowels: " + vowelCount + " | Consonants: " + consonantCount);
        System.out.println();
    }

    public static void main(String[] args) {
        countVowelsAndConsonants("Java Programming");
        countVowelsAndConsonants("Hello World");
        countVowelsAndConsonants("aeiou");
    }
}
