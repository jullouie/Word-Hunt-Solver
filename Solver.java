/**
 * Class to solve Word Hunt
 * 
 * @author Julianne
*/

public class Solver{

    /**
     * Constructor that takes the 16 Word Hunt letters (L to R, top to bottom)
     */
    public Solver(String letters){

    }
}
    /*
     *     
     * input: a string of 16 characters
     * output: all the words (5 letters+) you can make
     * 
     * methods:
     * read in a file of words
     * make the string of 16 characters data that the program can use for DFS => a graph
     * conduct DFS and match to all the words
     * print the answers
     * 
     * add complexity:
     * make a website (github pages)
     * output the words in a different order? a way to maximize points for the user? group them by letter?
     * 
     * Plan:
     * make a board representation to be char[] or String
     * make a getNeighbors function
     * implement DFS - initially just check if the word is in the hashset (fast lookup)
     * 
     * implement w a trie
     * serialize the trie
     */
