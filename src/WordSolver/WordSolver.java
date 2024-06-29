
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class WordSolver {
    // 2-D array representation of the board where char[0][0] represents top left corner
    private char[][] board = new char[4][4];
    private Trie dictionary;
    private Set<String> wordsFound;
    private HashMap<String, PriorityQueue<String>> prefixes;
    private HashMap<String, HashSet<String>> prefixWordSets;
    private PriorityQueue<String> orderedWords;
    private boolean[][] visited;
    private Comparator<String> greatestToL;
    private Comparator<String> leastToG;
    private String prefix = "";

    // to find the perimeter of 8 letters around (x,y)
    private static final int[] xpos = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] ypos = {-1, 0, 1, -1, 1, -1, 0, 1};

    // constructor
    public WordSolver(String str){
        for (int i = 0; i < 16; i+=4){
            this.board[i/4] = str.substring(i, i+4).toCharArray();
        }
        this.dictionary = new Trie();
        this.wordsFound = new HashSet<>();
        this.visited = new boolean[4][4];
        this.greatestToL = (a,b) -> Integer.compare(b.length(), a.length());
        this.leastToG = (a,b) -> Integer.compare(a.length(), b.length());
        this.orderedWords = new PriorityQueue<String>(greatestToL);
        this.prefixes = new HashMap<String, PriorityQueue<String>>();
        this.prefixWordSets = new HashMap<String, HashSet<String>>();
    }

    /**
     * Creates a trie of all the valid words for the Word Hunt
     * @throws IOException 
     */
    public void createDictionary(String filename) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String word;
        while ((word = reader.readLine()) != null){
            dictionary.insert(word.toLowerCase());
        }
        reader.close();
        // System.out.println("dictionary done");
    }

    /**
     * @return a stack in order of longest word to shortest
     */
    public PriorityQueue<String> getOrderedWords(){
        for(String word : wordsFound){
            orderedWords.add(word);
        }
        return orderedWords;
    }

    /**
     * Finds words beginning at every board entry
     * Returns a HashSet to ensure no duplicates
     * @param n minimum length of words wanted
     * @return hash set of the foundwords
     */
    public Set<String> findWords(int n){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                dfs(i, j, "", n);
            }
        }
        return wordsFound;
    }

    /**
     * Uses recursive depth first search to search throught the dictionary for words
     * @param x x coordinate 
     * @param y y coordinate
     * @param currentWord word built through recursion
     * @param n min number of letters in wordsFound
     */
    private void dfs(int x, int y, String currentWord, int n) {

        // if coordinates out of bound or already visited
        if (x < 0 || x > 3 || y < 0 || y > 3 || visited[x][y]){
            return;
        }

        currentWord += Character.toLowerCase(board[x][y]);

        // if the current word is not a prefix, there are no words
        if (!dictionary.isPrefix(currentWord)){
            return;
        }


        // search for current word in dictionary
        if (currentWord.length() >= n && dictionary.contains(currentWord)) {
            wordsFound.add(currentWord);
            if (currentWord.length() >= 3) {
                prefix = currentWord.substring(0, 2);
                prefixWordSets.putIfAbsent(prefix, new HashSet<String>());
                if (!prefixWordSets.get(prefix).contains(currentWord)) {
                    prefixWordSets.get(prefix).add(currentWord);
                    prefixes.putIfAbsent(prefix, new PriorityQueue<>(greatestToL));
                    prefixes.get(prefix).add(currentWord);
                }
            }
        }

        visited[x][y] = true;

        // do dfs on all 8 possible letters of perimeter
        for (int i = 0; i < 8; i++) {
            dfs(x + xpos[i], y + ypos[i], currentWord, n);
        }

        // once all words are found, return x,y to false
        visited[x][y] = false;
    }

    public  void leastToString(){
        for (PriorityQueue<String> valueQueue : prefixes.values()) {
            System.out.println(valueQueue);
        }
    }

    public static void main(String[] args) throws IOException{
        Scanner userInput = new Scanner(System.in);
        System.out.println("Do you want the words to be grouped by prefix?");
        String ans = userInput.nextLine();
        System.out.println("Enter the 16 letters:");
        String str = userInput.nextLine();
        userInput.close();
        WordSolver solver = new WordSolver(str);
        //add in a filename
        solver.createDictionary("src/WordSolver/words.txt");
        Set<String> words = solver.findWords(5);
        if (ans.equals("yes") || ans.equals("y")){
            solver.leastToString();
        } else {
            for (String word : words) {
                System.out.println(word);
            }
        }

        // make an anagram solver, wordbites solver
    }
}
