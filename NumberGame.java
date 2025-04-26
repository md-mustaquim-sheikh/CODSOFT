
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        char choice;

        System.out.println("Welcome to Number Game!");
        System.out.println("Guess the number between 1 to 100.");

        do {
            int random = rand.nextInt(100) + 1;
            int attempts = 0;

            while (true) {
                System.out.print("Enter your guess: ");
                int guess = in.nextInt();
                if (guess < 1 || guess > 100) {
                    System.out.println("Please enter a number between 1 and 100.");
                    continue;
                }
                attempts++;
                if (guess == random) {
                    System.out.println("Congratulation! You Guess it in " + attempts + " attempts.");
                    break;
                } else if (guess < random) {
                    System.out.println("Too low! Try Again...");
                } else {
                    System.out.println("Too high! Try Again...");
                }
            }
            System.out.print("Do you want to play again? (y/n): ");
            choice = in.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');

        System.out.println("Thanks for playing! Goodbye!");

        in.close();
    }

}
