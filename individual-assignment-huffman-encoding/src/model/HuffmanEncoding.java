package model;
import tools.Console;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
/**
 * Huffman Encoding class that will encode and decode a String.
 * @author David Kurilla
 * @version 1.0
 */
public class HuffmanEncoding {

    // FIELDS
    private String sourceText;
    private String encodedText;
    private Map<Character, Double> probabilityMap;
    private HuffmanNode huffmanNode = new HuffmanNode('\u0000', 0);
    private HashMap<Character, String> encodingMap = new HashMap<>();

    // CONSTRUCTOR
    /**
     * This is a constructor for the HuffmanEncoding class.
     * @param sourceText String representing the original text input
     */
    public HuffmanEncoding(String sourceText) {
        this.sourceText = sourceText;
        this.probabilityMap = new TreeMap<>();
        analyzeText();
    }

    // METHOD: analyzeText()
    private void analyzeText() {

        // NOTE: Convert text to array. Then add contents of array to map.
        char[] array = sourceText.toCharArray();
        for (char character : array) {
            if (probabilityMap.containsKey(character)) {
                probabilityMap.put(character, probabilityMap.get(character) + 1);
            } else {
                probabilityMap.put(character, 1.0);
            }
        }
        probabilityMap.replaceAll((l, v) -> probabilityMap.get(l) / array.length);
    }

    // METHOD: printCharacterFrequencies()
    /**
     * Method prints out frequency map for characters.
     */
    public void printCharacterFrequencies() {
        Console.print("Character frequencies from text:");
        for (Character character : probabilityMap.keySet()) {
            Console.print(character + " -> " + probabilityMap.get(character));
        }
    }

    // METHOD: encode()
    /**
     * This method uses Huffman Encoding to encode the source text.
     * @return String Huffman Encoded text
     */
    public String encode() {

        // NOTE: Create PQ and add map items to PQ.
        PriorityQueue<HuffmanNode> tree = new PriorityQueue<>();
        for (char key: probabilityMap.keySet()) {
            tree.add(new HuffmanNode(key, probabilityMap.get(key)));
        }

        // NOTE: Assemble heap using PQ elements and Huffman encoding
        while (tree.size() > 1) {
            HuffmanNode node = tree.remove();
            HuffmanNode node2 = tree.remove();
            huffmanNode = new HuffmanNode('\u0000', (node.getProbability() + node2.getProbability()));

            if (node.getProbability() > node2.getProbability()) {
                huffmanNode.setLeft(node2);
                huffmanNode.setRight(node);
            } else {
                huffmanNode.setLeft(node);
                huffmanNode.setRight(node2);
            }
            tree.add(huffmanNode);
        }

        // NOTE: Assign binary strings to heap elements.
        search(huffmanNode, "");
        StringBuilder encodedText = new StringBuilder();

        // NOTE: Print encoding map.
        Console.print("Huffman encoding map for text:");
        for (char key: encodingMap.keySet()) {
            Console.print(key + " -> " + encodingMap.get(key));
        }

        // NOTE: Assemble encoded string.
        char[] array = sourceText.toCharArray();
        for (char c : array) {
            String code = encodingMap.get(c);
            encodedText.append(code);
        }
        Console.print("");
        this.encodedText = encodedText.toString();
        return this.encodedText;
    }

    // METHOD: search()
    private void search(HuffmanNode root, String code) {

        // NOTE: Navigate heap and build binary Strings.
        if (root.getLeft() != null) {
            if (root.getLeft().getData() != '\u0000') {
                encodingMap.put(root.getLeft().getData(), code + "0");
            }
            search(root.getLeft(), code+"0");

            if (root.getRight() != null) {
                if (root.getRight().getData() != '\u0000') {
                    encodingMap.put(root.getRight().getData(), code + "1");
                }
                search(root.getRight(), code+"1");
            }
        }
    }

    // METHODS: decode()
    /**
     * This method decodes a Huffman Encoded String.
     * @param encodedText String Huffman Encoded text
     * @return String decoded text
     */
    public String decode(String encodedText) {

        StringBuilder decodedText = new StringBuilder();
        HuffmanNode currentNode = huffmanNode;

        // NOTE: Loop through array of encoded text and navigate heap.
        for (int i = 0; i < encodedText.length(); i++) {

            if (currentNode.getData() == '\u0000') {
                if (encodedText.charAt(i) == '0') {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode = currentNode.getRight();
                }
            }

            // NOTE: Assign ASCII value back to text.
            if(currentNode.getLeft() == null) {
                decodedText.append(currentNode.getData());
                currentNode = huffmanNode;
            }

        }
        return decodedText.toString();
    }

    @Override
    public String toString() {
        return "HuffmanEncoding{" +
                "sourceText='" + sourceText + '\'' +
                ", encodedText='" + encodedText + '\'' +
                ", probabilityMap=" + probabilityMap +
                ", huffmanNode=" + huffmanNode +
                ", encodingMap=" + encodingMap +
                '}';
    }
}
