import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static String[] moves = { "Rock", "Paper", "Scissors" };
    static Random random = new Random();

    // Returns the computer's random move
    public static String getComputerMove() {
        return moves[random.nextInt(3)];
    }

    // Core game logic: returns "Player Wins", "Computer Wins", or "Draw"
    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        boolean playerWins =
            (playerMove.equals("Rock")     && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper")    && computerMove.equals("Rock"))     ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"));

        return playerWins ? "Player Wins" : "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 5;

        // Arrays to store each round's data for the summary table
        String[] playerMoves   = new String[n];
        String[] computerMoves = new String[n];
        String[] results       = new String[n];

        int wins = 0, losses = 0, draws = 0;

        // Play n rounds
        for (int i = 0; i < n; i++) {
            System.out.print("Round " + (i + 1) + " - Enter your move (Rock / Paper / Scissors): ");
            playerMoves[i]   = scanner.nextLine().trim();
            computerMoves[i] = getComputerMove();
            results[i]       = playRound(playerMoves[i], computerMoves[i]);

            System.out.println("Computer chose: " + computerMoves[i] + "  ->  " + results[i]);
            System.out.println();

            if      (results[i].equals("Player Wins"))   wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else                                         draws++;
        }

        // Summary table
        System.out.println("=== Final Summary ===");
        System.out.printf("%-8s %-15s %-17s %-15s%n",
                          "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-".repeat(57));

        for (int i = 0; i < n; i++) {
            System.out.printf("%-8d %-15s %-17s %-15s%n",
                              (i + 1), playerMoves[i], computerMoves[i], results[i]);
        }

        System.out.println("-".repeat(57));
        double winPercentage = ((double) wins / n) * 100;
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                          wins, losses, draws, winPercentage);

        scanner.close();
    }
}
