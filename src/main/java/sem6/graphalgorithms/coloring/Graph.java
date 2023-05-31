package sem6.graphalgorithms.coloring;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    int verteces;
    ArrayList<Integer>[] adjancencyList;

    public Graph(){}

    public void addEdge(int v, int w) {
        adjancencyList[v].add(w);
        adjancencyList[w].add(v);
    }

    public void generateRandomGraph(Graph graph) {
        int vertexCount = 15;
        graph.verteces = vertexCount;
        graph.adjancencyList = new ArrayList[graph.verteces];
        for (int i = 0; i < graph.verteces; i++){
            adjancencyList[i] = new ArrayList<>();
        }
        Random random = new Random();
        for (int i = 0; i < verteces; i++) {
            int v = random.nextInt(graph.verteces);
            int w = random.nextInt(graph.verteces);

            if ((v == w)
                    || graph.adjancencyList[v].contains(w)) {
                i = i - 1;
                continue;
            }

            addEdge(v, w);
        }
    }

    public static int getMaxSaturation(int[] s) {
        int max = 0;
        for (int i = 0; i < s.length; i++){
            if(s[i] > s[max]) max = i;
        }
        return max;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Graph {\n" +
                "vertexCount=" + verteces +
                ", adjancencyList=\n");
        for (int i = 0; i < verteces; i++){
            string.append(i).append(" -> ").append(adjancencyList[i].toString()).append("\n");
        }

        string.append("}");
        return string.toString();
    }
}
