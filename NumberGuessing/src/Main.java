import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MAX_ATTEMPTS = 10; // Maximum attempts per round
    private static final int RANGE = 100; // Range of the random number (1 to 100)
    private static int score = 0; // Player's score

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playRound(scanner);
            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }

    private static void playRound(Scanner scanner) {
        Random random = new Random();
        int numberToGuess = random.nextInt(RANGE) + 1; // Random number between 1 and 100
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("\nA new round has started! Try to guess the number between 1 and " + RANGE + ".");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess == numberToGuess) {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                score += calculatePoints(attempts);
            } else if (userGuess < numberToGuess) {
                System.out.println("Your guess is too low.");
            } else {
                System.out.println("Your guess is too high.");
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry, you've used all your attempts. The number was " + numberToGuess + ".");
        }
    }

    private static int calculatePoints(int attempts) {
        // Points decrease as attempts increase
        return Math.max(0, 10 - attempts); // Example scoring: 10 points for 1st attempt, 1 point for 10th attempt
    }
}
