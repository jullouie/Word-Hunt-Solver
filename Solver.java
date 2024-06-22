/**
 * Class to solve Word Hunt
 * 
 * @author Julianne
*/

public class Solver{

    // 2-D array representation of the board where char[0][0] represents top left corner
    private char[][] board;
    private Trie dictionary;
    private HashSet<String> wordsFound;
    private boolean[][] visited;

    // to find the perimeter of 8 letters around (x,y)
    private static final int[] xpos = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] ypos = {-1, 0, 1, -1, 1, -1, 0, 1};

    // constructor
    public Solver(char[][] board){
        this.board = board;
        this.dictionary = new Trie();
        this.wordsFound = new HashSet<>;
        this.visited = new boolean[4][4]
    }

    /**
     * Creates a trie of all the valid words for the Word Hunt
     */
    public void createDictionary(String filename){
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String word;
        while ((word = reader.readLine()) != null){
            dictionary.insert(word.toLowerCase());
        }
        reader.close();
    }

    /**
     * Finds words beginning at every board entry
     * Returns a HashSet to ensure no duplicates
     * @param n minimum length of words wanted
     * @return hash set of the foundwords
     */
    public HashSet<String> findWords(n){
        for (int i; i < 4; i++){
            for (int j; j < 4; j++){
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
    private void dfs(int x, int y, String currentWord, n) {

        // if coordinates out of bound or already visited
        if (x < 0 || x > 3 || y < 0 || y > 3 || visited[x][y]){
            return;
        }

        currentWord += board[x][y];

        // if the current word is not a prefix, there are no words
        if (!dictionary.isPrefix(currentWord)){
            return;
        }

        // search for current word in dictionary
        if (currentWord.length >= n && dictionary.contains(currentWord)){
            wordsFound.add(currentWord);
        }

        visited[x][y] = true;

        // do dfs on all 8 possible letters of perimeter
        for (int i = 0; i < 8; i++) {
            dfs(x + xpos[i], y + ypos[i], currentWord, n);
        }

        // once all words are found, return x,y to false
        visited[x][y] = false;
    }

    public static void main(String[] args){
        Solver solver = new Solver({'A', 'D', 'E', 'R'}{'B', 'R', 'I', 'P'}{'C', 'E', 'D', 'A'}{'D', 'S', 'J', 'I'});
        // add in a filename
        solver.createDictionary("");

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
