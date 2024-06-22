package src.WordSolver;
import java.util.HashMap;
import java.util.Map;

public class Trie {

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndofWord = false;

        TrieNode() {
            children = new HashMap<>();
        }
        
    }
    
    private TrieNode root;

    // Constructor
    public Trie(){
        root = new TrieNode();
    }

    /**
     * Inserts a valid word into the trie at the right place
     * @param word
     */
    public void insert(String word){
        TrieNode current = root;

        // iterates through entire word and adds a TrieNode if it's not there
        for (char c : word.toCharArray()){
            if (!current.children.containsKey(c)){

                // adds to the hashmap of children stemming from current
                current.children.put(c, new TrieNode());
            }

            // retrieves the trie node value from the key c
            current = current.children.get(c);
        }

        // indicates reached the end of the word
        current.isEndofWord = true;

    }

    /**
     * Checks whether the string exists in a trie (doesn't have to be a complete word)
     * @param prefix
     * @return if string exists in the trie
     */
    public boolean isPrefix(String prefix){
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    /**
     * Determines if the string is a word on its own
     * @param word
     * @return if string is a valid word in the trie
     */
    public boolean contains(String word){
        TrieNode node = searchNode(word);
        return node != null && node.isEndofWord;
    }

    /**
     * Searches for a given string, returns node of end of the word
     * @param str
     * @return node of end of the word
     */
    private TrieNode searchNode(String str){
        TrieNode current = root;
        for (char c : str.toCharArray()){
            if (!current.children.containsKey(c)){
                return null;
            }
            current = current.children.get(c);
        }
        return current;
    }
}
