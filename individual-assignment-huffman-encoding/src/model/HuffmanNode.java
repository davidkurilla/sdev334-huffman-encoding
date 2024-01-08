package model;

/**
 * Huffman Node class for building heap.
 *
 * @author David Kurilla
 * @version 1.0
 */
public class HuffmanNode implements Comparable<HuffmanNode> {

    // FIELDS
    private char data;
    private double probability;
    private HuffmanNode left;
    private HuffmanNode right;

    // CONSTRUCTOR
    /**
     * Constructor for HuffmanNode.
     * @param data char data inside node
     * @param probability double frequency of character
     */
    public HuffmanNode(char data, double probability) {
        this.data = data;
        this.probability = probability;
    }

    // METHOD: getData()
    /**
     * Gets data.
     * @return char data
     */
    public char getData() {
        return data;
    }

    // METHOD: getProbability()
    /**
     * Gets probability.
     * @return double probability
     */
    public double getProbability() {
        return probability;
    }

    // METHOD: getLight()
    /**
     * Gets left node.
     * @return HuffmanNode left
     */
    public HuffmanNode getLeft() {
        return left;
    }

    // METHOD: getRight()
    /**
     * Gets right node.
     * @return HuffmanNode right
     */
    public HuffmanNode getRight() {
        return right;
    }

    // METHOD: setLeft()
    /**
     * Sets left node.
     * @param left HuffmanNode
     */
    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    // METHOD: setRight()
    /**
     * Sets left node.
     * @param right HuffmanNode
     */
    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Double.compare(this.probability, other.getProbability());
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", probability=" + probability +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
