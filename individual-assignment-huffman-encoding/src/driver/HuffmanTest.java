package driver;
import model.HuffmanEncoding;
import tools.Console;
/**
 * Client class for Huffman Encoding.
 * @author David Kurilla
 * @version 1.0
 */
public class HuffmanTest {

    // FIELDS
    private static String encodedString;
    private static String decodedString;
    private static String originalText;

    // METHOD: main()
    /**
     * This method runs the program.
     * @param args String[] (NOT IN USE)
     */
    public static void main(String[] args) {
        welcomeUser();
        encodeAndDecodeString();
        showResults();
    }

    // METHOD: welcomeUser()
    private static void welcomeUser() {
        Console.print("Welcome to my Huffman Encoding Program!");
        Console.print("***************************************\n");
    }

    // METHOD: encodeAndDecodeString()
    private static void encodeAndDecodeString() {
        originalText = Console.input("Please enter a string");
        HuffmanEncoding huffmanEncoding = new HuffmanEncoding(originalText);
        huffmanEncoding.printCharacterFrequencies();
        encodedString = huffmanEncoding.encode();
        decodedString = huffmanEncoding.decode(encodedString);
    }

    // METHOD: showResults()
    private static void showResults() {

        final int num1 = 8 * originalText.length();
        final int num2 = 16 * originalText.length();

        Console.print("Original Text: " + originalText);
        Console.print("Original Text Length: " + num1 + " - " + num2 + " bits");
        Console.print("");
        Console.print("Encoded Text: " + encodedString);
        Console.print("Encoded Text Length: " + encodedString.length() + " bits");
        Console.print("");
        Console.print("Decoded Text: " + decodedString);
    }
}
