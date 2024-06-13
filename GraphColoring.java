import java.util.*;

public class GraphColoring {
    private int vertices;
    private LinkedList<Integer>[] adjacencyList;

    public GraphColoring(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjacencyList[i] = new LinkedList<>();
    }

    void addEdge(int v, int w) {
        adjacencyList[v].add(w);
        adjacencyList[w].add(v); // Graf nieskierowany
    }

    void greedyColoring() {
        int result[] = new int[vertices];

        Arrays.fill(result, -1);

        result[0]  = 0;

        boolean available[] = new boolean[vertices];
        Arrays.fill(available, true);

        for (int u = 1; u < vertices; u++) {

            Iterator<Integer> it = adjacencyList[u].iterator();
            while (it.hasNext()) {
                int i = it.next();
                if (result[i] != -1)
                    available[result[i]] = false;
            }


            int cr;
            for (cr = 0; cr < vertices; cr++) {
                if (available[cr])
                    break;
            }

            result[u] = cr;


            Arrays.fill(available, true);
        }

        // Wyświetlanie wyników
        for (int u = 0; u < vertices; u++)
            System.out.println("Vertex " + u + " --->  Color " + result[u]);
    }

    // testowanie
    public static void main(String args[]) {
        GraphColoring g1 = new GraphColoring(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Coloring of graph 1");
        g1.greedyColoring();

        System.out.println();

        GraphColoring g2 = new GraphColoring(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(4, 3);
        System.out.println("Coloring of graph 2");
        g2.greedyColoring();
    }
}