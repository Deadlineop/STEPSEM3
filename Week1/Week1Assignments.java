public class Week1Assignments {

    // ---------------------------------------------------------------
    // 1. Exam Hall Seat Duplication Checker
    // ---------------------------------------------------------------
    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean foundAny = false;
        boolean[] alreadyReported = new boolean[seatNumbers.length];

        for (int i = 0; i < seatNumbers.length; i++) {
            if (alreadyReported[i]) continue;
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    alreadyReported[j] = true;
                    foundAny = true;
                    break;
                }
            }
        }

        if (!foundAny) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    // ---------------------------------------------------------------
    // 2. Typing Speed Test Accuracy Checker
    // ---------------------------------------------------------------
    static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char originalMismatchChar = ' ';
        char typedMismatchChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-based position
                originalMismatchChar = original.charAt(i);
                typedMismatchChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / total) * 100;
        String mismatchInfo = (firstMismatchPos == -1)
                ? "No Mismatches"
                : "First Mismatch at position " + firstMismatchPos
                  + " ('" + originalMismatchChar + "' vs '" + typedMismatchChar + "')";

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | %s%n",
                matched, total, accuracy, mismatchInfo);
    }

    // ---------------------------------------------------------------
    // 3. Traffic Signal Streak Analyzer
    // ---------------------------------------------------------------
    static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("Empty signal log.");
            return;
        }

        char bestChar = signalLog.charAt(0);
        int bestCount = 1;
        char currentChar = signalLog.charAt(0);
        int currentCount = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentCount++;
            } else {
                currentChar = signalLog.charAt(i);
                currentCount = 1;
            }
            if (currentCount > bestCount) {
                bestCount = currentCount;
                bestChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + bestChar + "' repeated " + bestCount + " times");
    }

    // ---------------------------------------------------------------
    // 4. Warehouse Inventory Balancer
    // ---------------------------------------------------------------
    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        int totalB = 0;

        for (int val : sectionA) totalA += val;
        for (int val : sectionB) totalB += val;

        String status = (totalA == totalB) ? "Balanced" : "Not Balanced";

        int maxVal = Integer.MIN_VALUE;
        String maxSection = "";
        int maxIndex = -1;

        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > maxVal) {
                maxVal = sectionA[i];
                maxSection = "Section A";
                maxIndex = i + 1; // 1-based
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > maxVal) {
                maxVal = sectionB[i];
                maxSection = "Section B";
                maxIndex = i + 1;
            }
        }

        System.out.println("Section A Total: " + totalA
                + " | Section B Total: " + totalB
                + " | Status: " + status
                + " | Highest Quantity: " + maxVal
                + " (" + maxSection + ", Item " + maxIndex + ")");
    }

    // ---------------------------------------------------------------
    // 5. Movie Review Word Length Profiler
    // ---------------------------------------------------------------
    static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            int len = word.length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount
                + " | Medium: " + mediumCount
                + " | Long: " + longCount);
    }

    // ---------------------------------------------------------------
    // Main: sample runs for all 5 problems
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== 1. Seat Duplication Checker ===");
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});

        System.out.println("\n=== 2. Typing Accuracy Checker ===");
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");

        System.out.println("\n=== 3. Traffic Signal Streak Analyzer ===");
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");

        System.out.println("\n=== 4. Warehouse Inventory Balancer ===");
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});

        System.out.println("\n=== 5. Movie Review Word Length Profiler ===");
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
