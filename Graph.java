import java.util.ArrayList;

public class Graph {
    private final int V;
    private int E;
    private ArrayList<ArrayList<String>> adj;

    //Initializes an empty graph with V vertices and 0 edges
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = new ArrayList<ArrayList<String>>(V);
        for (int v = 0; v < V; v++){
            adj.add(new ArrayList<String>());
        }
    }


    // Adds undirected ege v-w to the graph
    public void addEdge(String v, String w){
        E++;
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    //Returns vertices adjacent to vertex v
    public Iterable<Integer> adj(String v){
        return adj.get(v);
    }
}
