package tools;
import java.util.Scanner;
/**
 * Personal use helper class for printing to console.
 * @author David Kurilla
 * @version 1.0
 */
public class Console {

    // METHOD: input()
    /**
     * Collects input from console.
     * @param prompt String prompt printed to console
     * @return String input from Scanner
     */
    public static String input(String prompt) {
        System.out.print(prompt + ": ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }

    // METHOD: print()
    /**
     * Method prints to console.
     * @param prompt String message to print
     */
    public static void print(String prompt) {
        System.out.println(prompt);
    }
}
